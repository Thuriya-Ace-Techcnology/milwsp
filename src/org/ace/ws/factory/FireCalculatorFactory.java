package org.ace.ws.factory;

import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.system.cargo.cargoType.CargoType;
import org.ace.insurance.system.cargo.route.Route;
import org.ace.insurance.system.common.currency.Currency;
import org.ace.insurance.system.common.paymenttype.PaymentType;
import org.ace.insurance.system.fire.buildingOccupation.BuildingOccupation;
import org.ace.insurance.system.fire.buildingclass.BuildingClass;
import org.ace.insurance.system.fire.buildingclass.ClassValue;
import org.ace.insurance.system.fire.floor.Floor;
import org.ace.insurance.system.fire.roof.Roof;
import org.ace.insurance.system.fire.wall.Wall;
import org.ace.ws.model.premiumCal.ClassValueDTO;
import org.ace.ws.model.premiumCal.ContentDTO;
import org.ace.ws.model.premiumCal.RouteDTO;

public class FireCalculatorFactory {
	/** Floor **/
	public static ContentDTO convertFloorDTO(Floor floor) {
		ContentDTO dto = new ContentDTO();
		dto.setId(floor.getId());
		dto.setName(floor.getName());
		return dto;
	}

	public static List<ContentDTO> convertFloorDTOList(List<Floor> floorList) {
		List<ContentDTO> result = new ArrayList<ContentDTO>();
		for (Floor f : floorList) {
			result.add(convertFloorDTO(f));
		}
		return result;
	}

	/** Wall **/
	public static ContentDTO convertWallDTO(Wall wall) {
		ContentDTO dto = new ContentDTO();
		dto.setId(wall.getId());
		dto.setName(wall.getName());
		return dto;
	}

	public static List<ContentDTO> convertWallDTOList(List<Wall> wallList) {
		List<ContentDTO> result = new ArrayList<ContentDTO>();
		for (Wall f : wallList) {
			result.add(convertWallDTO(f));
		}
		return result;
	}

	/** Roof **/
	public static ContentDTO convertRoofDTO(Roof roof) {
		ContentDTO dto = new ContentDTO();
		dto.setId(roof.getId());
		dto.setName(roof.getName());
		return dto;
	}

	public static List<ContentDTO> convertRoofDTOList(List<Roof> roofList) {
		List<ContentDTO> result = new ArrayList<ContentDTO>();
		for (Roof f : roofList) {
			result.add(convertRoofDTO(f));
		}
		return result;
	}

	/** BuildingClass **/
	public static ContentDTO convertBuildingClassDTO(BuildingClass bclass) {
		ContentDTO dto = new ContentDTO();
		dto.setId(bclass.getId());
		dto.setName(bclass.getName());
		return dto;
	}

	public static List<ContentDTO> convertBuildingClassDTOList(List<BuildingClass> bclassList) {
		List<ContentDTO> result = new ArrayList<ContentDTO>();
		for (BuildingClass f : bclassList) {
			result.add(convertBuildingClassDTO(f));
		}
		return result;
	}

	/** BuildingOccupation **/
	public static ContentDTO convertBuildingOccupationDTO(BuildingOccupation bocc) {
		ContentDTO dto = new ContentDTO();
		dto.setId(bocc.getId());
		if (bocc.getBuildingOccupationType() == null) {
			dto.setName(bocc.getName());
		} else {
			dto.setName(bocc.getBuildingOccupationType().concat(" " + bocc.getName()));
		}

		return dto;
	}

	public static List<ContentDTO> convertBuildingOccupationDTOList(List<BuildingOccupation> boccList) {
		List<ContentDTO> result = new ArrayList<ContentDTO>();
		for (BuildingOccupation f : boccList) {
			result.add(convertBuildingOccupationDTO(f));
		}
		return result;
	}

	/** Class Value **/
	public static ClassValueDTO convertClassValueDTO(ClassValue ClassValueDTO) {
		ClassValueDTO dto = new ClassValueDTO(ClassValueDTO);
		return dto;
	}

	public static List<ClassValueDTO> convertClassValueDTOList(List<ClassValue> classValueList) {
		List<ClassValueDTO> result = new ArrayList<ClassValueDTO>();
		for (ClassValue f : classValueList) {
			result.add(convertClassValueDTO(f));
		}
		return result;
	}

	/** CargoType **/
	public static ContentDTO convertCargoTypeDTO(CargoType cargoType) {
		ContentDTO dto = new ContentDTO();
		dto.setId(cargoType.getId());
		dto.setName(cargoType.getName());
		return dto;
	}

	public static List<ContentDTO> convertCargoTypeDTOList(List<CargoType> cargoTypeList) {
		List<ContentDTO> result = new ArrayList<ContentDTO>();
		for (CargoType f : cargoTypeList) {
			result.add(convertCargoTypeDTO(f));
		}
		return result;
	}

	/** Route **/
	public static ContentDTO convertRouteeDTO(Route route) {
		ContentDTO dto = new ContentDTO();
		dto.setId(route.getId());
		dto.setName(route.getName());
		return dto;
	}

	public static List<ContentDTO> convertRouteDTOList(List<Route> cargoTypeList) {
		List<ContentDTO> result = new ArrayList<ContentDTO>();
		for (Route f : cargoTypeList) {
			result.add(convertRouteeDTO(f));
		}
		return result;
	}

	/** Currency **/
	public static ContentDTO convertCurrencyDTO(Currency cur) {
		ContentDTO dto = new ContentDTO();
		dto.setId(cur.getId());
		dto.setName(cur.getCurrencyCode());
		return dto;
	}

	public static List<ContentDTO> convertCurrencyList(List<Currency> curList) {
		List<ContentDTO> result = new ArrayList<ContentDTO>();
		for (Currency f : curList) {
			result.add(convertCurrencyDTO(f));
		}
		return result;
	}

	/** PaymentType **/
	public static ContentDTO convertPaymentTypeDTO(PaymentType payType) {
		ContentDTO dto = new ContentDTO();
		dto.setId(payType.getId());
		dto.setName(payType.getName());
		return dto;
	}

	public static List<ContentDTO> convertPaymentTypeList(List<PaymentType> payTypeList) {
		List<ContentDTO> result = new ArrayList<ContentDTO>();
		for (PaymentType f : payTypeList) {
			if(f.getName().contains("-")) {
				f.setName(f.getName().replace("-", "_"));
			}
			result.add(convertPaymentTypeDTO(f));
		}
		return result;
	}
	
	public static List<RouteDTO> convertRouteWithInsuranceTypeDTOList(List<Route> cargoTypeList) {
		List<RouteDTO> result = new ArrayList<RouteDTO>();
		for (Route f : cargoTypeList) {
			result.add(convertRouteWithInsuranceTypeDTO(f));
		}
		return result;
	}
	
	/** Route **/
	public static RouteDTO convertRouteWithInsuranceTypeDTO(Route route) {
		RouteDTO dto = new RouteDTO();
		dto.setId(route.getId());
		dto.setName(route.getName());
		dto.setInsuranceType(route.getInsuranceType().name());
		return dto;
	}
}
