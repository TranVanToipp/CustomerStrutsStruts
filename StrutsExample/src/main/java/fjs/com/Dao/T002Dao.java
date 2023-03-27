package fjs.com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fjs.com.Dto.T002Dto;
import fjs.com.common.DbConnection;


public class T002Dao {
	public List<T002Dto> getData() {
		List<T002Dto> list = new ArrayList<T002Dto>();
		Connection conn = null;
		try {
			String query = "SELECT CUSTOMER_ID, CUSTOMER_NAME,\r\n" + 
					"CASE WHEN SEX = 0 THEN 'Male' ELSE 'Female' END AS SEX,BIRTHDAY, ADDRESS\r\n" + 
					"FROM mstcustomer WHERE DELETE_YMD IS NULL ORDER BY CUSTOMER_ID";
			conn = new DbConnection().getConnection(conn);
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				T002Dto dataT002 = new T002Dto();
				dataT002.setCUSTOMER_ID(rs.getString(1));
				dataT002.setCUSTOMER_NAME(rs.getString(2));
				dataT002.setSEX(rs.getString(3));
				dataT002.setBIRTHDAY(rs.getString(4));
				dataT002.setADDRESS(rs.getString(5));
				list.add(dataT002);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	
	public List<T002Dto> getDataSearch(String name, String sex, String birthdayFrom, String birthdayTo) {
	    List<T002Dto> listSearch = new ArrayList<T002Dto>();
	    Connection conn = null;
	    try {
	        String query = "SELECT CUSTOMER_ID, CUSTOMER_NAME, CASE WHEN SEX = 0 THEN 'Male' else 'Female' end as SEX, BIRTHDAY, ADDRESS FROM mstcustomer\r\n"
	        		+ "WHERE CUSTOMER_NAME LIKE ?\r\n"
	        		+ "    AND SEX = ?\r\n"
	        		+ "    AND BIRTHDAY >= ?\r\n"
	        		+ "    AND BIRTHDAY <= ?\r\n"
	        		+ "ORDER BY CUSTOMER_ID;\r\n";
	        conn = new DbConnection().getConnection(conn);
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, "%" + name + "%");
	        ps.setString(2, sex);
	        ps.setString(3, birthdayFrom);
	        ps.setString(4, birthdayTo);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	            T002Dto dataT002Search = new T002Dto();
	            dataT002Search.setCUSTOMER_ID(rs.getString(1));
	            dataT002Search.setCUSTOMER_NAME(rs.getString(2));
	            dataT002Search.setSEX(rs.getString(3));
	            dataT002Search.setBIRTHDAY(rs.getString(4));
	            dataT002Search.setADDRESS(rs.getString(5));
	            listSearch.add(dataT002Search);
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return listSearch;
	}
	
	public List<T002Dto> pagingData(int index) {
		List<T002Dto> list = new ArrayList<>();
		Connection conn = null;
		//sql
		//select * from mstcustomer order by CUSTOMER_ID OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY
		String query = "SELECT * FROM mstcustomer ORDER BY CUSTOMER_ID LIMIT ?, 5";
		try {
			 conn = new DbConnection().getConnection(conn);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, (index-1)*5);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				T002Dto datapagin = new T002Dto();
				datapagin.setCUSTOMER_ID(rs.getString(1));
				datapagin.setCUSTOMER_NAME(rs.getString(2));
				datapagin.setSEX(rs.getString(3));
				datapagin.setBIRTHDAY(rs.getString(4));
				datapagin.setADDRESS(rs.getString(5));
	            list.add(datapagin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<T002Dto> deleteData(String[] selecValue){
	    List<T002Dto> listDelete = new ArrayList<T002Dto>();
	    Connection conn = null;
	    try {
	        String query = "UPDATE mstcustomer "
	                     + "SET Delete_YMD = CURRENT_TIMESTAMP "
	                     + "WHERE customer_Id IN (";
	        for (int i = 0; i < selecValue.length; i++) {
	        	String[] ids = selecValue[0].split(",");
	            for (int j = 0; j < ids.length; j++) {
	                query += "?,";
	            }
	        }
	        query = query.substring(0, query.length() - 1) + ")";
	        conn = new DbConnection().getConnection(conn);
	        PreparedStatement ps = conn.prepareStatement(query);
	        for (int i = 0; i < selecValue.length; i++) {
	            ps.setLong(i + 1, Long.parseLong(selecValue[i]));
	        }
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
	    	
	    }
	    return listDelete;
	}
	
	
	public int getDataPage(){
		String query = "select count(*) from mstcustomer";
		Connection conn = null;
		try {
			conn = new DbConnection().getConnection(conn);
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
	    }
		return 0;
	}
}
