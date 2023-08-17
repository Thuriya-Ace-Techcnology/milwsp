package org.ace.insurance.system.rta.service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/*
 * @author Kyaw Yea Lwin
 * @date 14/07/2020
 */
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.ace.insurance.system.rta.RTA;
import org.ace.insurance.system.rta.TempRTA;
import org.ace.insurance.system.rta.persistence.interfaces.IRTADAO;
import org.ace.insurance.system.rta.service.interfaces.IRTAService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.factory.RTAConverter;
import org.ace.ws.model.thirdParty.RTADTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

@Service("RTAService")
public class RTAService extends BaseService implements IRTAService {

	@Resource(name = "RTADAO")
	private IRTADAO rtaDAO;

	@Override
	public RTA insert(RTA rta) throws DAOException {
		try {
			setIDPrefixForInsert(rta);
			return rtaDAO.insert(rta);
		} catch (DAOException e) {
			throw new DAOException(e.getErrorCode(), e.getMessage(), new Throwable());
		}
	}

	@Override
	public RTA update(RTA rta) throws DAOException {
		try {
			return rtaDAO.update(rta);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update RTA ", e);
		}
	}

	@Override
	public void delete(RTA rta) throws DAOException {
		try {
			rtaDAO.delete(rta);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to delete RTA ", e);
		}
	}

	@Override
	public RTA findById(String id) throws DAOException {
		RTA result = null;
		try {
			result = rtaDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a RTA (ID : " + id + ")", e);
		}
		return result;
	}

	@Override
	public List<RTA> findAllRTA() throws DAOException {
		List<RTA> result = null;
		try {
			result = rtaDAO.findAllRTA();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a RTA ", e);
		}
		return result;
	}

	private void setIDPrefixForInsert(RTA rta) {
		String parentPrefix = customIDGenerator.getPrefix(RTA.class);
		rta.setPrefix(parentPrefix);
	}

	public List<RTADTO> accessFileRead(File file) {
		List<RTADTO> rtaDTOList = new ArrayList<>();
		try {
			Database db = DatabaseBuilder.open(file);
			db.getTableNames().stream().filter(names -> names != null).forEach(tableName -> {
				Table table = null;
				try {
					table = db.getTable(tableName);
					if (!tableName.equals("Insurance")) {
						return;
					}
					for (Row row : table) {
						if (!checkNull(row.get("LOCATION")).equals("A4")) {
							continue;
						}
						RTADTO rta = new RTADTO();
						rta.setRegNo(checkNull(row.get("REG_NO")));
						rta.setMakeModel(checkNull(row.get("MAKE_MODEL")));
						rta.setType(checkNull(row.get("TYPE")));
						rta.setType8(checkNull(row.get("TYPE_8")));
						rta.setOwner(checkNull(row.get("OWNER")));
						rta.setLocation(checkNull(row.get("LOCATION")));
						rta.setName(checkNull(row.get("NAME")));
						rta.setNrcNo(checkNull(row.get("NRC_NO")));
						rta.setHouseNo(checkNull(row.get("HOUSE_NO")));
						rta.setRdSt(checkNull(row.get("RD_ST")));
						rta.setQtr(checkNull(row.get("QTR")));
						rta.setTsp(checkNull(row.get("TSP")));
						rta.setPayload(checkNull(row.get("PAYLOAD")));
						rta.setiRg(changeDateFormat(row.get("I_RG")));
						rta.setDe(changeDateFormat(row.get("D_E")));
						if (tableName.equals("Y3")) {
							rta.setStatus(checkNull(row.get("STATUS")));
						}
						if (rta.getRegNo() == "" || rta.getDe() == "") {
							throw new IOException("RegNo or Data of Expired cann't be null or empty.");
						} else {
							rtaDTOList.add(rta);
						}
					}
					List<TempRTA> rtaList = RTAConverter.rtaDTOToTempModelConverter(rtaDTOList);
					rtaDAO.insertRTATempList(rtaList);
					System.out.println("Table : " + tableName + " is finish insert");

				} catch (IOException e) {
					System.out.println("Problem at Table : " + tableName + " ,row (" + table.getRowCount() + "");
					System.out.println(e.getMessage());
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		return rtaDTOList;
	}

	public static String checkNull(Object object) {
		if (object == null) {
			return "";
		} else {
			return object.toString();
		}
	}

	@Override
	public String findKgByVehicleNo(String vehicleNo) throws DAOException {
		try {
			RTA result = rtaDAO.findByVehicleNo(vehicleNo);
			return result.getPayload();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a RTA (REG_NO : " + vehicleNo + ")", e);
		}
	}

	@Override
	public RTA findByVehicleNo(String vehicleNo) throws DAOException {
		RTA result = null;
		try {
			result = rtaDAO.findByVehicleNo(vehicleNo);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a RTA (REG_NO : " + vehicleNo + ")", e);
		}
		if (result != null) {
			char[] payLoadChars = result.getPayload().toCharArray();
			StringBuffer payLoad = new StringBuffer();
			for (char payChar : payLoadChars) {
				if (StringUtils.isNumeric("" + payChar)) {
					payLoad.append(payChar);
				} else if (String.valueOf(payChar).equalsIgnoreCase("P")
						&& !result.getOwner().toLowerCase().endsWith("p")) {
					result.setType_8("MOTOR_COMMERCIAL");
					break;
				} else if (String.valueOf(payChar).equalsIgnoreCase("P")
						&& result.getOwner().toLowerCase().endsWith("p")) {
					result.setType_8("MOTOR_PRIVATE");
					break;
				}
			}
			if (!result.getPayload().toLowerCase().contains("p") && result.getOwner().toLowerCase().endsWith("p")) {
				result.setType_8("TURCK_PRIVATE");
				StringBuffer sb = changeToTan(String.valueOf(payLoad));
				payLoad = new StringBuffer();
				payLoad.append(sb);
			} else if (!result.getPayload().toLowerCase().contains("p") && !result.getPayload().equals("-")) {
				result.setType_8("TRUCK_COMMERCIAL");
				StringBuffer sb = changeToTan(String.valueOf(payLoad));
				payLoad = new StringBuffer();
				payLoad.append(sb);
			}
			result.setPayload(String.valueOf(payLoad));
		}
		return result;
	}

	private static StringBuffer changeToTan(String calLoad) {
		StringBuffer sb = new StringBuffer();
		double calRes = Double.parseDouble(calLoad) * 0.00110231;
		double round = Math.round(calRes);
		return sb.append(round >= calRes ? round : round + 1);
	}

	private static String changeDateFormat(Object value) {
		if (value == null) {
			return "";
		} else {
			Date date = (Date) value;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			String dateFormat = format.format(date);
			return dateFormat;
		}
	}
}
