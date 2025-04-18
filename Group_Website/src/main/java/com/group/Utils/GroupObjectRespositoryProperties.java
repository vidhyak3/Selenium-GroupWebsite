package com.group.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GroupObjectRespositoryProperties {
	Properties gbProps;
	public GroupObjectRespositoryProperties() {
		try {
			FileInputStream inputStream = new FileInputStream("./src/main/resources/groupObjectRepositiryProperties");
			gbProps = new Properties();
			gbProps.load(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String get(String key) {
		return gbProps.getProperty(key);
	}

}

