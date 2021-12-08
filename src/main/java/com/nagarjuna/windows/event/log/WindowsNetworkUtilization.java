package com.nagarjuna.windows.event.log;

public class WindowsNetworkUtilization {

	private String name = "";
	private String bytesTotalPersec = "";
	private String currentBandWidth = "";
	private String networkUtlization = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBytesTotalPersec() {
		return bytesTotalPersec;
	}

	public void setBytesTotalPersec(String bytesTotalPersec) {
		this.bytesTotalPersec = bytesTotalPersec;
	}

	public String getCurrentBandWidth() {
		return currentBandWidth;
	}

	public void setCurrentBandWidth(String currentBandWidth) {
		this.currentBandWidth = currentBandWidth;
	}

	public String getNetworkUtlization() {
		return networkUtlization;
	}

	public void setNetworkUtlization(String networkUtlization) {
		this.networkUtlization = networkUtlization;
	}

	public WindowsNetworkUtilization(String name, String bytesTotalPersec,
			String currentBandWidth, String networkUtlization) {
		super();
		this.name = name;
		this.bytesTotalPersec = bytesTotalPersec;
		this.currentBandWidth = currentBandWidth;
		this.networkUtlization = networkUtlization;
	}

	@Override
	public String toString() {
		return "WindowsNetworkUtilization [name=" + name
				+ ", bytesTotalPersec=" + bytesTotalPersec
				+ ", currentBandWidth=" + currentBandWidth
				+ ", networkUtlization=" + networkUtlization + "]";
	}

}
