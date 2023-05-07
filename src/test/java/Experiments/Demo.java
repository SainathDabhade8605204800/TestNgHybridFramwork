package Experiments;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class Demo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Date date = new Date();
		System.out.println(date.toString().replace(" ", "_").replace(":", "_"));

		Properties prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		String URL = prop.getProperty("url");
		System.out.println(URL);
		;

	}

}
