package com.group.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadGroupProperties {
	Properties  gprops;
	public LoadGroupProperties() {
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("./src/main/resources/config.groupProperties");
			gprops = new Properties();
			gprops.load(inputStream);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String get1(String key) {
		return gprops.getProperty(key);
	}
}

