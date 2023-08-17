package org.ace.ws.model;

public class AceAppVersion {
		private String iosVersion;
		private String andriodVersion;
		
		public AceAppVersion(){
			this.setIosVersion("1.0.5");
			this.setAndriodVersion("1.2.0");
		}

		public String getIosVersion() {
			return iosVersion;
		}

		public void setIosVersion(String iosVersion) {
			this.iosVersion = iosVersion;
		}

		public String getAndriodVersion() {
			return andriodVersion;
		}

		public void setAndriodVersion(String andriodVersion) {
			this.andriodVersion = andriodVersion;
		}
}
