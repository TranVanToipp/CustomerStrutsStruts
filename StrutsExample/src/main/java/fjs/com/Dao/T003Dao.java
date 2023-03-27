package fjs.com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import fjs.com.Dto.T003Dto;
import fjs.com.common.DbConnection;

public class T003Dao {
	public int update(T003Dto u, HttpSession session) {
		int status = 0;
		Connection conn = null;
		try {
			conn = new DbConnection().getConnection(conn);
			String query = "UPDATE MSTCUSTOMER SET CUSTOMER_NAME = ?, SEX = ?, BIRTHDAY = ?, EMAIL = ?, ADDRESS = ?, DELETE_YMD = NULL, UPDATE_YMD = CURRENT_TIMESTAMP, UPDATE_PSN_CD = (SELECT PSN_CD FROM mstuser WHERE USERID = ?) WHERE CUSTOMER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, u.getCUSTOMER_NAME());
			ps.setString(2, u.getSEX());
			ps.setString(3, u.getBIRTHDAY());
			ps.setString(4, u.getEMAIL());
			ps.setString(5, u.getADDRESS());
			ps.setString(6, (String) session.getAttribute("psnCd"));
			ps.setInt(7, u.getCUSTOMER_ID());
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return status;
	}

	public static T003Dto getCustomerById(int id) {
		T003Dto customerId = null;
		Connection conn = null;
		try {
			String query = "select * from MSTCUSTOMER where CUSTOMER_ID=?";
			conn = new DbConnection().getConnection(conn);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customerId = new T003Dto();
				customerId.setCUSTOMER_ID(rs.getInt("CUSTOMER_ID"));
				customerId.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
				customerId.setSEX(rs.getString("SEX"));
				customerId.setBIRTHDAY(rs.getString("BIRTHDAY"));
				customerId.setEMAIL(rs.getString("Email"));
				customerId.setADDRESS(rs.getString("ADDRESS"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return customerId;
	}

	public int save(T003Dto save, HttpSession session) {
		int status = 0;
		Connection conn = null;
		try {
			String query = "INSERT INTO MSTCUSTOMER (CUSTOMER_ID, CUSTOMER_NAME, SEX, BIRTHDAY, EMAIL, ADDRESS, DELETE_YMD, INSERT_YMD, INSERT_PSN_CD, UPDATE_YMD, UPDATE_PSN_CD) \r\n" + 
					"VALUES (NEXT VALUE FOR SEQ_CUSTOMER_ID, ?, ?, ?, ?, ?, NULL, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?)";
			conn = new DbConnection().getConnection(conn);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, save.getCUSTOMER_NAME());
			ps.setString(2, save.getSEX());
			ps.setString(3, save.getBIRTHDAY());
			ps.setString(4, save.getEMAIL());
			ps.setString(5, save.getADDRESS());
			ps.setString(6, (String) session.getAttribute("psnCd"));
			ps.setString(7, (String) session.getAttribute("psnCd"));
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return status;
	}
	
	
}
