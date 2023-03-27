package com.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class CmsDatabase {

	public static void main(String[] args) {

		CmsDatabase connection = new CmsDatabase();
		System.out.println(connection.database());
	}

	/* Database Connections */
	
	public Connection database() {

		Connection con = null;

		try {
			String username = "root";
			String pass = "hasterweak";
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/cms";
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, pass);

		}

		catch (Exception e) {

			System.out.println("Connection in CMS Database Fail " + e.getMessage());

			e.printStackTrace();
		}

		return con;

	}
}
