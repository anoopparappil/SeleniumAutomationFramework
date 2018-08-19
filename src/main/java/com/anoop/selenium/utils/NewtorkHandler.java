package com.anoop.selenium.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NewtorkHandler {
	
	public static String getHubAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
	
	public static int getHubPort() {
		String strPort = PropertyHandler.getProperty("hub_port");
		return Integer.parseInt(strPort);
	}
	
	public static String getHubRegistrationAddress() throws UnknownHostException {
		String hubAddress = getHubAddress();
		int port = getHubPort();
		String hubRegistrationAddress = "http://"+hubAddress+":"+port+"/wd/hub";
		return hubRegistrationAddress;
	}

}
