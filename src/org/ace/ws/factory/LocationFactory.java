package org.ace.ws.factory;

import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.system.common.country.Country;
import org.ace.insurance.system.common.district.District;
import org.ace.insurance.system.common.province.Province;
import org.ace.insurance.system.common.township.Township;
import org.ace.ws.model.location.LocationDTO;

public class LocationFactory {
	/** Township **/

	public static LocationDTO convertTownshipDTO(Township township) {
		LocationDTO dto = new LocationDTO();
		dto.setId(township.getId());
		dto.setName(township.getName());
		dto.setShortName(township.getShortName());
		return dto;
	}

	public static List<LocationDTO> convertTownshipDTOList(List<Township> townshipList) {
		List<LocationDTO> result = new ArrayList<LocationDTO>();
		for (Township t : townshipList) {
			result.add(convertTownshipDTO(t));
		}
		return result;
	}

	/** Province **/

	public static LocationDTO convertProvinceDTO(Province province) {
		LocationDTO dto = new LocationDTO();
		dto.setId(province.getId());
		dto.setName(province.getName());
		dto.setProvinceNo(province.getProvinceNo());
		return dto;
	}

	public static List<LocationDTO> convertProvinceDTOList(List<Province> provinceList) {
		List<LocationDTO> result = new ArrayList<LocationDTO>();
		for (Province p : provinceList) {
			result.add(convertProvinceDTO(p));
		}
		return result;
	}

	/** Country **/

	public static LocationDTO convertCountryDTO(Country country) {
		LocationDTO dto = new LocationDTO();
		dto.setId(country.getId());
		dto.setName(country.getName());
		return dto;
	}

	public static List<LocationDTO> convertCountryDTOList(List<Country> countryList) {
		List<LocationDTO> result = new ArrayList<LocationDTO>();
		for (Country c : countryList) {
			result.add(convertCountryDTO(c));
		}
		return result;
	}
	
	/** District **/
	public static LocationDTO convertDistrictDTO(District district) {
		LocationDTO dto = new LocationDTO();
		dto.setId(district.getId());
		dto.setName(district.getName());
		return dto;
	}

	public static List<LocationDTO> convertDistrictDTOList(List<District> districtList) {
		List<LocationDTO> result = new ArrayList<LocationDTO>();
		for (District d : districtList) {
			result.add(convertDistrictDTO(d));
		}
		return result;
	}
}
