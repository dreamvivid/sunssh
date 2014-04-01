package com.sund.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class TestSystemProperties {
	@Test
	public void getSystemProperties() {
		InputStream inStream;
		try {
			inStream = new FileInputStream("G:\\DevData\\eclipse-workspace\\sundssh\\src\\test\\resources\\testSystem.properties.txt");
			System.getProperties().load(inStream);
			System.out.println("#################Current property is : "+ System.getProperty("myTest"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
