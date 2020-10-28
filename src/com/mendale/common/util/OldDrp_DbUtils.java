package com.mendale.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.InputStream;

public class OldDrp_DbUtils {
	public final static String FILE_NAME="/olddrp.properties";	
	static String USER              = ""  ;
	static String PWD               = ""  ;
	static String SCONNSTR          = ""  ;
	static String FORNAME           = ""  ;
	static PreparedStatement pstmt  = null;
	public static Connection conn   = null;
	static ResultSet rs             = null;
	static Statement stat                 ;
	
	public void getConnection(String bandcode) {
		try {
			Properties props = new Properties();   
			InputStream meidrpStream = this.getClass().getResourceAsStream(FILE_NAME); //Object.class.getResourceAsStream(FILE_NAME);   
			props.load(meidrpStream); 
			
			if(bandcode.equals("03")){
				SCONNSTR = (String)props.get("mei_drp.url");
				FORNAME  = (String)props.get("mei_drp.driverClass");
				USER     = (String)props.get("mei_drp.username");
				PWD      = (String)props.get("mei_drp.password");
			}else{
				SCONNSTR = (String)props.get("mendale_drp.url");
				FORNAME  = (String)props.get("mendale_drp.driverClass");
				USER     = (String)props.get("mendale_drp.username");
				PWD      = (String)props.get("mendale_drp.password");
			}
			
			Class.forName(FORNAME.trim());
			conn = DriverManager.getConnection(SCONNSTR.trim(), USER.trim(), PWD.trim());
			conn.setAutoCommit(false);
			stat = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String sql,String bandcode) {
		rs = null;
		try {
			if(conn == null) {
				getConnection(bandcode);
			}
			rs = stat.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void close() {
		try {
			if(stat!=null) {stat.close();stat=null;}
			if(conn!=null) {conn.close();conn=null;}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
