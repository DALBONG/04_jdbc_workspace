package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class TestRun {

	public static void main(String[] args) {
	
		// Properties  복습
		/*
		 	*특징  
		 	- Map 계열 컬렉션 (key + value 세트로 담는 특징)
		 	- key와 value값이 모두 String으로 담아야 함.
		 	
		 	  setProperty(String key, String value)
		 	  getProperty(String key) : string value --> 밸류값을 모르겠으면 키값만 알려주면 밸류값 뱉어냄
		 	  
		 	- 주로 외부파일(.properties, .xml)로 입.출력 할 때 사용 -> ex.환경설정 파일 (개발자 아닌 사람이 보는 것)
		 */
		/*
		Properties prop = new Properties();
		prop.setProperty("C", "INSERT");
		prop.setProperty("R", "SELECT");
		prop.setProperty("U", "UPDATE");
		prop.setProperty("D", "DELETE");
		//순서를 유지하지 않고 막 담길 수 있음.
		
		try {
			//prop.store(new FileOutputStream("resources/test.properties"), "propertiesTest");
			prop.storeToXML(new FileOutputStream("resources/test.xml"), "아무Hourse");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		*/
		
		Properties prop = new Properties();
		// 파일 읽어들이기
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println(prop.getProperty("driver"));
		System.out.println(prop.getProperty("url"));
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));
		System.out.println(prop.getProperty("driver1")); // 존재하지 않는 key 값은 null로 반환
		


		
		
		
		
	}
}
