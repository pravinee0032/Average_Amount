package com.lbg.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

public class FileConfigTest {
	@Test
	public void testReadFile() {

		   ClassLoader classLoader = this.getClass().getClassLoader();
	        File file = new File(classLoader.getResource("FILE.DAT").getFile());
	        assertTrue(file.exists());

	}
	  @Test
	    public void testReadFile2() {
	        ClassLoader classLoader = this.getClass().getClassLoader();
	        File file = new File(classLoader.getResource("exchangeRates.csv").getFile());
	        assertTrue(file.exists());
	 
	    }

}
