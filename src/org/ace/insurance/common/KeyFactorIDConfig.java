package org.ace.insurance.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.ace.insurance.product.Product;
import org.ace.insurance.product.ProductGroup;
import org.ace.insurance.system.common.keyfactor.KeyFactor;
import org.ace.java.component.SystemException;

public class KeyFactorIDConfig {
	
	private static String SUM_INSURED = "SUM_INSURED";
	private static String BUILDINGOCCUPATION = "BUILDINGOCCUPATION";
	private static String BUILDINGCLASS = "BUILDINGCLASS";
	private static String CURRENCY = "CURRENCY";
	private static String FIRE_BUILDING = "FIRE_BUILDING";
	private static String FIRE_MACHINERY = "FIRE_MACHINERY";
	private static String FIRE_FURNITURE = "FIRE_FURNITURE";
	private static String FIRE_STOCK = "FIRE_STOCK";
	private static String FIRE_DECLARATION_POLICY = "FIRE_DECLARATION_POLICY";
	private static String PUBLIC_LIFE = "PUBLIC_LIFE";
	private static String GROUP_LIFE = "GROUP_LIFE";
	private static String PERSONAL_ACCIDENT_KYT = "PERSONAL_ACCIDENT_KYT";
	private static String PERSONAL_ACCIDENT_USD = "PERSONAL_ACCIDENT_USD";
	private static String SNAKE_BITE = "SNAKE_BITE";
	private static String SPORT_MAN = "SPORT_MAN";
	private static String SPORT_MAN_ABROAD = "SPORT_MAN_ABROAD";
	private static String MOTOR_CYCLE = "MOTOR_CYCLE";
	private static String NIL_PRIVATE = "NIL_PRIVATE";
	private static String NIL_COMMERCIAL = "NIL_COMMERCIAL";
	private static String WINDSCREEN = "WINDSCREEN";
	private static String ACT_OF_GOD = "ACT_OF_GOD";
	private static String THEFT = "THEFT";
	private static String SRCC = "SRCC";
	private static String WAR = "WAR";
	private static String UNIT = "UNIT";
	private static String ISOFFICIAL = "ISOFFICIAL";
	private static String NUMBER_OF_MEMBERS = "NUMBER_OF_MEMBERS";
	private static String PAYMENTTYPE = "PAYMENTTYPE";
	private static String ENDOW_WEIGHT = "ENDOW_WEIGHT";
	private static String POUND = "POUND";
	private static String ENDOW_FEET = "ENDOW_FEET";
	private static String ENDOW_INCHES = "ENDOW_INCHES";
	private static String FIXED_AGE = "AGE";
	private static String MEDICAL_AGE = "MEDICAL_AGE";

	private static String ISGOVBANK = "ISGOVBANK";
	private static String ISIMPORTANT = "ISIMPORTANT";
	private static String ISSECURITY = "ISSECURITY";
	private static String ISSAFEPLACE = "ISSAFEPLACE";
	private static String ISSYSTEMATICALLY = "ISSYSTEMATICALLY";
	private static String ISNEARANCE = "ISNEARANCE";

	private static String DISTANCE_MILE = "DISTANCE_MILE";
	private static String IS_OWN_VEH_USE = "IS_OWN_VEH_USE";
	private static String IS_BOX_USE = "IS_BOX_USE";
	private static String IS_PUBLIC_WAY = "IS_PUBLIC_WAY";
	private static String IS_MAIN_PLACE = "IS_MAIN_PLACE";

	private static String RSM = "RIOT_STRIKE_AND_MALICIOUS_DAMAGE";
	private static String AIR = "AIR_CRAFT_DAMAGE";
	private static String IMPACT = "IMPACT_DAMAGE";
	private static String SI = "SUBSIDENCE_AND_LANDSLIDE";
	private static String EQ = "EARTH_QUAKE_FIRE_EARTH_QUAKE_SHOCK";
	private static String EXP = "EXPLOSION";
	private static String SP = "SPONTANEOUS_COMBUSTION";
	private static String STROM_OTHER = "STORM_TYPHOON_HURRICANE_TEMPEST_CYCLONE_OTHERS";
	private static String STROM2_WATER = "STORM_TYPHOON_HURRICANE_TEMPEST_CYCLONE_WATER";
	private static String FI = "FLOOD_AND_INUNDATION";
	private static String WAR_FIRE = "WAR_RISKS_FIRE";
	private static String BURG_ONLY_1 = "BURGLARY_ONLY_FIRST_CLASS";
	private static String BURG_ONLY_2 = "BURGLARY_ONLY_SECOND_CLASS";
	private static String BURG_ONLY_3 = "BURGLARY_ONLY_THIRD_CLASS";
	private static String BURG_ONLY_4 = "BURGLARY_ONLY_FOURTH_CLASS";
	private static String BURG_1 = "BURGLARY_FIRST_CLASS";
	private static String BURG_2 = "BURGLARY_SECOND_CLASS";
	private static String BURG_3 = "BURGLARY_THIRD_CLASS";
	private static String BURG_4 = "BURGLARY_FOURTH_CLASS";
	private static String THIRD_PARTY = "THIRD_PARTY";
	private static String FIRE = "FIRE";

	private static String WIND_SCREEN_USD = "WIND_SCREEN_USD";
	private static String ACT_OF_GOD_USD = "ACT_OF_GOD_USD";
	private static String STANDARD_EXCESS_USD = "STANDARD_EXCESS_USD";
	private static String NIL_EXCESS_USD = "NIL_EXCESS_USD";
	private static String PAID_DRIVER = "PAID_DRIVER";
	private static String LOSS_OF_LUGGAGE = "LOSS_OF_LUGGAGE";
	private static String MEDICAL_EXPENSES = "MEDICAL_EXPENSES";
	private static String TRANSFER_FEES = "TRANSFER_FEES";
	private static String THIRD_PARTY_LIABILITY_USD = "THIRD_PARTY_LIABILITY_USD";
	private static String PASSENGER_LIABILITY = "PASSENGER_LIABILITY";

	private static String YANGON_BRANCH = "YANGON_BRANCH";
	private static String MEDICAL_INSURANCE = "MEDICAL_INSURANCE";
	private static String CARGO_TYPE = "CARGO_TYPE";
	private static String ROUTE = "ROUTE";
	private static String COASTAL = "COASTAL";
	private static String MARINE_CARGO = "MARINE_CARGO";
	private static String FARMER = "FARMER";
	private static String SHORT_TERM_ENDOWMNENT = "SHORT_TERM_ENDOWMNENT";
	private static String OVERLAND_CARGO = "OVERLAND_CARGO";
	private static String AIRWAY_CARGO = "AIRWAY_CARGO";
	private static String WAR_RISK_CARGO = "WAR_RISK_CARGO";
	private static String TWOCTWOP_PROD_SECRETKEY= "TWOCTWOP_PROD_SECRETKEY";
	private static String TWOCTWOP_UAT_SECRETKEY= "TWOCTWOP_SECRETKEY";
	private static String TWOCTWOP_OUTBOUND_MMK_UAT_SECRETKEY="TWOCTWOP_OUTBOUND_MMK_UAT_SECRETKEY";
	private static String TWOCTWOP_OUTBOUND_MMK_PROD_SECRETKEY="TWOCTWOP_OUTBOUND_MMK_PROD_SECRETKEY";
	private static String TWOCTWOP_USD_UAT_SECRETKEY= "TWOCTWOP_USD_UAT_SECRETKEY";
	private static String TWOCTWOP_USD_PROD_SECRETKEY= "TWOCTWOP_USD_PROD_SECRETKEY";
	private static String TWOCTWOP_OUTBOUND_USD_UAT_SECRETKEY= "TWOCTWOP_OUTBOUND_USD_UAT_SECRETKEY";
	private static String TWOCTWOP_OUTBOUND_USD_PROD_SECRETKEY= "TWOCTWOP_OUTBOUND_USD_PROD_SECRETKEY";
	private static String TWOCTWOP_LIFE_UAT_SECRETKEY= "TWOCTWOP_LIFE_UAT_SECRETKEY";
	private static String TWOCTWOP_LIFE_PROD_SECRETKEY = "TWOCTWOP_LIFE_PROD_SECRETKEY";
	private static String PORT_OVERDUE = "PORT_OVERDUE";
	private static String SRCC_CARGO = "SRCC_CARGO";
	private static String MED_ADDON1 = "MED_ADDON1";
	private static String MED_ADDON2 = "MED_ADDON2";
	private static String MED_ADDON3 = "MED_ADDON3";
	private static String MED_PROD_ADDON1 = "MED_PROD_ADDON1";
	private static String MED_PROD_ADDON2 = "MED_PROD_ADDON2";
	private static String MED_PROD_ADDON3 = "MED_PROD_ADDON3";

	private static String FIDELITY = "FIDELITY";
	private static String CASH_IN_SAFE = "CASH_IN_SAFE";
	private static String CASH_IN_TRANSIT = "CASH_IN_TRANSIT";

	private static String LOCAL_TRAVEL = "LOCAL_TRAVEL";
	private static String UNDER_100MILES_TRAVEL = "UNDER_100MILES_TRAVEL";
	private static String FOREIGN_TRAVEL = "FOREIGN_TRAVEL";

	/* MotorUSDProduct */
	private static String TAXI_WITH_METER = "TAXI_WITH_METER";
	private static String TAXI_WITH_LOCAL = "TAXI_WITH_LOCAL";
	private static String BUSES = "BUSES";
	private static String MOBILE_PLANT_USD = "MOBILE_PLANT_USD";
	private static String MOTOR_CYCLE_USD = "MOTOR_CYCLE_USD";
	private static String PRIVATE_CAR_USD = "PRIVATE_CAR_USD";
	private static String GOOD_CARRYING = "GOOD_CARRYING";
	
	private static String TRCREDIT = "TRCREDIT";
	private static String TRDEBIT = "TRDEBIT";
	private static String CSCREDIT = "CSCREDIT";
	private static String CSDEBIT = "CSDEBIT";
	

	private static Properties idConfig;

	static {
		try {
			idConfig = new Properties();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream in = classLoader.getResourceAsStream("keyfactor-id-config.properties");
			idConfig.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new SystemException(ErrorCode.SYSTEM_ERROR, "Failed to load keyfactor-id-config.properties");
		}
	}

	public static String getPaymentTypeKFId() {
		return idConfig.getProperty(PAYMENTTYPE);
	}

	public static String getNumberOfMemberKFId() {
		return idConfig.getProperty(NUMBER_OF_MEMBERS);
	}

	public static String getCashInTransitId() {
		return idConfig.getProperty(CASH_IN_TRANSIT);
	}

	public static String getIsMainPlaceKfId() {
		return idConfig.getProperty(IS_MAIN_PLACE);
	}

	public static String getIsPublicWayKfId() {
		return idConfig.getProperty(IS_PUBLIC_WAY);
	}

	public static String getIsBoxSaveKfId() {
		return idConfig.getProperty(IS_BOX_USE);
	}

	public static String getIsOwnVehUseKfId() {
		return idConfig.getProperty(IS_OWN_VEH_USE);
	}

	public static String getDistanceMileKFId() {
		return idConfig.getProperty(DISTANCE_MILE);
	}

	public static String getCashInSafeId() {
		return idConfig.getProperty(CASH_IN_SAFE);
	}

	public static String getIsNearanceKfId() {
		return idConfig.getProperty(ISNEARANCE);
	}

	public static String getIsSystematicallyKfId() {
		return idConfig.getProperty(ISSYSTEMATICALLY);
	}

	public static String getIsSafePlaceKfId() {
		return idConfig.getProperty(ISSAFEPLACE);
	}

	public static String getIsSecurityKfId() {
		return idConfig.getProperty(ISSECURITY);
	}

	public static String getIsImportantKfId() {
		return idConfig.getProperty(ISIMPORTANT);
	}

	public static String getIsGpvBamlKfId() {
		return idConfig.getProperty(ISGOVBANK);
	}

	public static String getFidelityId() {
		return idConfig.getProperty(FIDELITY);
	}

	public static String getIsOfficialKFId() {
		return idConfig.getProperty(ISOFFICIAL);
	}

	public static String getUnitKFId() {
		return idConfig.getProperty(UNIT);
	}

	public static String getGoodCarryingUSDId() {
		return idConfig.getProperty(GOOD_CARRYING);
	}

	public static String getPrivateCarUSDId() {
		return idConfig.getProperty(PRIVATE_CAR_USD);
	}

	public static String getMotorCycleUSDId() {
		return idConfig.getProperty(MOTOR_CYCLE_USD);
	}

	public static String getMobilePlantUSDId() {
		return idConfig.getProperty(MOBILE_PLANT_USD);
	}

	public static String getBusUSDId() {
		return idConfig.getProperty(BUSES);
	}

	public static String getLocalTaxiUSDId() {
		return idConfig.getProperty(TAXI_WITH_LOCAL);
	}

	public static String getMeterTaxiUSDId() {
		return idConfig.getProperty(TAXI_WITH_METER);
	}

	public static String getNilPrivateId() {
		return idConfig.getProperty(NIL_PRIVATE);
	}

	public static String getNilCommercailId() {
		return idConfig.getProperty(NIL_COMMERCIAL);
	}

	public static String getBuildingClassId() {
		return idConfig.getProperty(BUILDINGCLASS);
	}

	public static String getCurrencyId() {
		return idConfig.getProperty(CURRENCY);
	}

	public static String getBuildingOccupationId() {
		return idConfig.getProperty(BUILDINGOCCUPATION);
	}

	public static String getFireBuildingId() {
		return idConfig.getProperty(FIRE_BUILDING);
	}

	public static String getFireMachineryId() {
		return idConfig.getProperty(FIRE_MACHINERY);
	}

	public static String getFireFurnitureId() {
		return idConfig.getProperty(FIRE_FURNITURE);
	}

	public static String getFireStockId() {
		return idConfig.getProperty(FIRE_STOCK);
	}

	public static String getMedAddOn1() {
		return idConfig.getProperty(MED_ADDON1);
	}

	public static String getMedAddOn2() {
		return idConfig.getProperty(MED_ADDON2);
	}

	public static String getMedAddOn3() {
		return idConfig.getProperty(MED_ADDON3);
	}

	public static String getFireDelclarationPolicyId() {
		return idConfig.getProperty(FIRE_DECLARATION_POLICY);
	}

	public static String getPublicLifeId() {
		return idConfig.getProperty(PUBLIC_LIFE);
	}

	public static String getGroupLifeId() {
		return idConfig.getProperty(GROUP_LIFE);
	}

	public static String getFarmerId() {
		return idConfig.getProperty(FARMER);
	}

	public static String getShortEndowLifeId() {
		return idConfig.getProperty(SHORT_TERM_ENDOWMNENT);
	}

	public static String getPersonalAccidentMMKId() {
		return idConfig.getProperty(PERSONAL_ACCIDENT_KYT);
	}

	public static String getPersonalAccidentUSDId() {
		return idConfig.getProperty(PERSONAL_ACCIDENT_USD);
	}

	public static String getSnakeBikeId() {
		return idConfig.getProperty(SNAKE_BITE);
	}

	public static String getSportManId() {
		return idConfig.getProperty(SPORT_MAN);
	}

	public static String getSportManAbroadId() {
		return idConfig.getProperty(SPORT_MAN_ABROAD);
	}

	public static String getMotorCycleId() {
		return idConfig.getProperty(MOTOR_CYCLE);
	}

	public static String getActOfGodId() {
		return idConfig.getProperty(ACT_OF_GOD);
	}

	public static String getThirdPartyId() {
		return idConfig.getProperty(THIRD_PARTY);
	}

	public static String getSrccId() {
		return idConfig.getProperty(SRCC);
	}

	public static String getWindScreenId() {
		return idConfig.getProperty(WINDSCREEN);
	}

	public static String getTheftId() {
		return idConfig.getProperty(THEFT);
	}

	public static String getRSMId() {
		return idConfig.getProperty(RSM);
	}

	public static String getAirId() {
		return idConfig.getProperty(AIR);
	}

	public static String getImpactId() {
		return idConfig.getProperty(IMPACT);
	}

	public static String getSIId() {
		return idConfig.getProperty(SI);
	}

	public static String getEQId() {
		return idConfig.getProperty(EQ);
	}

	public static String getEXPId() {
		return idConfig.getProperty(EXP);
	}

	public static String getSPId() {
		return idConfig.getProperty(SP);
	}

	public static String getStromOtherId() {
		return idConfig.getProperty(STROM_OTHER);
	}

	public static String getStromWaterId() {
		return idConfig.getProperty(STROM2_WATER);
	}

	public static String getFIId() {
		return idConfig.getProperty(FI);
	}

	public static String getWarFireId() {
		return idConfig.getProperty(WAR_FIRE);
	}

	public static String getBurgOnly1Id() {
		return idConfig.getProperty(BURG_ONLY_1);
	}

	public static String getBurgOnly2Id() {
		return idConfig.getProperty(BURG_ONLY_2);
	}

	public static String getBurgOnly3Id() {
		return idConfig.getProperty(BURG_ONLY_3);
	}

	public static String getBurgOnly4Id() {
		return idConfig.getProperty(BURG_ONLY_4);
	}

	public static String getMedProdAddOn1() {
		return idConfig.getProperty(MED_PROD_ADDON1);
	}

	public static String getMedProdAddOn2() {
		return idConfig.getProperty(MED_PROD_ADDON2);
	}

	public static String getMedProdAddOn3() {
		return idConfig.getProperty(MED_PROD_ADDON3);
	}

	public static String getBurg1Id() {
		return idConfig.getProperty(BURG_1);
	}

	public static String getBurg2Id() {
		return idConfig.getProperty(BURG_2);
	}

	public static String getBurg3Id() {
		return idConfig.getProperty(BURG_3);
	}

	public static String getBurg4Id() {
		return idConfig.getProperty(BURG_4);
	}

	public static String getYangonBranchId() {
		return idConfig.getProperty(YANGON_BRANCH);
	}

	public static String getWindScreenUSDId() {
		return idConfig.getProperty(WIND_SCREEN_USD);
	}

	public static String getActOfGodUSDId() {
		return idConfig.getProperty(ACT_OF_GOD_USD);
	}

	public static String getStandardExcessUSDId() {
		return idConfig.getProperty(STANDARD_EXCESS_USD);
	}

	public static String getNaillExcessUSDId() {
		return idConfig.getProperty(NIL_EXCESS_USD);
	}

	public static String getPaidDriverId() {
		return idConfig.getProperty(PAID_DRIVER);
	}

	public static String getLossOfLuggageId() {
		return idConfig.getProperty(LOSS_OF_LUGGAGE);
	}

	public static String getMedicalExpensesId() {
		return idConfig.getProperty(MEDICAL_EXPENSES);
	}

	public static String getTransferFeesId() {
		return idConfig.getProperty(TRANSFER_FEES);
	}

	public static String getThirdPartyUSDId() {
		return idConfig.getProperty(THIRD_PARTY_LIABILITY_USD);
	}

	public static String getPassengerLiabilityId() {
		return idConfig.getProperty(PASSENGER_LIABILITY);
	}

	public static String getWeightId() {
		return idConfig.getProperty(ENDOW_WEIGHT);
	}

	public static String getFeetId() {
		return idConfig.getProperty(ENDOW_FEET);
	}

	public static String getInchesId() {
		return idConfig.getProperty(ENDOW_INCHES);
	}

	public static String getFixedAgeId() {
		return idConfig.getProperty(FIXED_AGE);
	}

	public static String getMedicalAgeId() {
		return idConfig.getProperty(MEDICAL_AGE);
	}

	public static boolean isSumInsured(KeyFactor kf) {
		if (kf.getId().equals(idConfig.getProperty(SUM_INSURED))) {
			return true;
		}
		return false;
	}

	public static boolean isFireDeclarationPolicy(Product product) {
		if (product.getId().equals(idConfig.getProperty(FIRE_DECLARATION_POLICY))) {
			return true;
		}
		return false;
	}

	public static boolean isFireProductGroup(ProductGroup productGroup) {
		if (productGroup.getId().equals(idConfig.getProperty(FIRE))) {
			return true;
		}
		return false;
	}

	public static boolean isPublicLife(Product product) {
		if (product.getId().equals(idConfig.getProperty(PUBLIC_LIFE))) {
			return true;
		}
		return false;
	}

	public static boolean isGroupLife(Product product) {
		if (product.getId().equals(idConfig.getProperty(GROUP_LIFE))) {
			return true;
		}
		return false;
	}

	public static boolean isSportMan(Product product) {
		if (product.getId().equals(idConfig.getProperty(SPORT_MAN))) {
			return true;
		}
		return false;
	}

	public static String getMedicalProductId() {
		return idConfig.getProperty(MEDICAL_INSURANCE).trim();
	}

	public static String getCargoTypeId() {
		return idConfig.getProperty(CARGO_TYPE).trim();
	}

	public static String getRouteId() {
		return idConfig.getProperty(ROUTE).trim();
	}

	public static String getCoastalId() {
		return idConfig.getProperty(COASTAL).trim();
	}

	public static String getSumInsuredId() {
		return idConfig.getProperty(SUM_INSURED);
	}

	public static String getMarineCargoInsuranceId() {
		return idConfig.getProperty(MARINE_CARGO).trim();
	}

	public static String getInLandCargoInsuranceId() {
		return idConfig.getProperty(OVERLAND_CARGO).trim();
	}

	public static String getAirWayCargoInsuranceId() {
		return idConfig.getProperty(AIRWAY_CARGO).trim();
	}

	public static String getWarId() {
		return idConfig.getProperty(WAR);
	}

	public static boolean isMarineCargo(Product product) {
		if (product.getId().trim().equals(idConfig.getProperty(MARINE_CARGO).trim())) {
			return true;
		}
		return false;
	}

	public static boolean isFarmer(Product product) {
		if (product.getId().trim().equals(idConfig.getProperty(FARMER).trim())) {
			return true;
		}
		return false;
	}

	public static boolean isOverLandCargo(Product product) {
		if (product.getId().trim().equals(idConfig.getProperty(OVERLAND_CARGO).trim())) {
			return true;
		}
		return false;
	}

	public static boolean isAirCargo(Product product) {
		if (product.getId().trim().equals(idConfig.getProperty(AIRWAY_CARGO).trim())) {
			return true;
		}
		return false;
	}

	public static String getWarRiskCargoId() {
		return idConfig.getProperty(WAR_RISK_CARGO);
	}
	public static String getTwoCTwoPSecKey() {
		return idConfig.getProperty(TWOCTWOP_UAT_SECRETKEY);
	}
	public static String getTwoCTwoPLifeSecKey() {
		return idConfig.getProperty(TWOCTWOP_LIFE_UAT_SECRETKEY);
	}
	
	public static String getTwoCTwoPUSDSecKey() {
		return idConfig.getProperty(TWOCTWOP_USD_UAT_SECRETKEY);
	}
	public static String getTwoCTwoPOutboundUSDSecKey() {
		return idConfig.getProperty(TWOCTWOP_OUTBOUND_USD_UAT_SECRETKEY);
	}
	public static String getTwoCTwoPOutboundMMKSecKey() {
		return idConfig.getProperty(TWOCTWOP_OUTBOUND_MMK_UAT_SECRETKEY);
	}
	public static String getSrccCargoId() {
		return idConfig.getProperty(SRCC_CARGO);
	}

	public static String getPortOverduceId() {
		return idConfig.getProperty(PORT_OVERDUE);
	}

	public static String getPoundId() {
		return idConfig.getProperty(POUND);
	}

	public static boolean isPersonalAccidentKTY(Product product) {
		if (product.getId().trim().endsWith(idConfig.getProperty(PERSONAL_ACCIDENT_KYT).trim())) {
			return true;
		}
		return false;
	}

	public static boolean isPersonalAccidentUSD(Product product) {
		if (product.getId().trim().endsWith(idConfig.getProperty(PERSONAL_ACCIDENT_USD).trim())) {
			return true;
		}
		return false;
	}

	public static boolean isLocalTravelInsurance(Product product) {
		if (product.getId().trim().endsWith(idConfig.getProperty(LOCAL_TRAVEL).trim())) {
			return true;
		}
		return false;
	}

	public static boolean isUnder100MileTravelInsurance(Product product) {
		if (product.getId().trim().endsWith(idConfig.getProperty(UNDER_100MILES_TRAVEL).trim())) {
			return true;
		}
		return false;
	}

	public static boolean isForeignTravelInsurance(Product product) {
		if (product.getId().trim().endsWith(idConfig.getProperty(FOREIGN_TRAVEL).trim())) {
			return true;
		}
		return false;
	}
	
	
	public static String getTRCredit() {
		return idConfig.getProperty(TRCREDIT);
	}
	
	
	public static String getTRDebit() {
		return idConfig.getProperty(TRDEBIT);
	}
	
	public static String getCSCredit() {
		return idConfig.getProperty(CSCREDIT);
	}

	public static String getCSDebit() {
		return idConfig.getProperty(CSDEBIT);
	}

	


}
