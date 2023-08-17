package org.ace.insurance.common;

public class NearestBranchCalculater {
	private static double deg2rad(double deg){
	    return deg * Math.PI / 180.0;
	}

	private static double rad2deg(double rad){
	    return rad * 180.0 / Math.PI;
	}
	
	public static double distance(Location customerLocation,Location branchLocation) {
		double theta = customerLocation.getLongitude() - branchLocation.getLongitude();
		double dist = (Math.sin(deg2rad(customerLocation.getLatitude())) * Math.sin(deg2rad(branchLocation.getLatitude()))
	            + (Math.cos(deg2rad(customerLocation.getLatitude())) * Math.cos(deg2rad(branchLocation.getLatitude()))
	            * Math.cos(deg2rad(theta))));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist *= 60; // 60 nautical miles per degree of seperation
		dist *= 1852; // 1852 meters per nautical mile
		return dist;
	}
}
