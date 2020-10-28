package com.mendale.common.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

class DbUtilsEx{}
public class DbUtils {
	public final static String FILE_NAME="/db.properties";	
	static String USER              = ""  ;
	static String PWD               = ""  ;
	static String SCONNSTR          = ""  ;
	static String FORNAME           = ""  ;
	static PreparedStatement pstmt  = null;
	public static Connection conn   = null;
	static ResultSet rs             = null;
	static Statement stat                 ;
	
	public static void getConnection() {
		try {
			Properties props = new Properties();
			InputStream Stream = DbUtilsEx.class.getClassLoader().getResourceAsStream(FILE_NAME);
			props.load(Stream); 
			
			SCONNSTR = (String)props.get("db.url");
			FORNAME  = (String)props.get("db.driverClass");
			USER     = (String)props.get("db.username");
			PWD      = (String)props.get("db.password");
			
			Class.forName(FORNAME.trim());
			conn = DriverManager.getConnection(SCONNSTR.trim(), USER.trim(), PWD.trim());
			conn.setAutoCommit(false);
			stat = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResultSet executeQuery(String sql) {
		rs = null;
		try {
			if(conn == null) {
				getConnection();
			}
			rs = stat.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void close() {
		try {
			if(stat!=null) stat.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}
	}
}
