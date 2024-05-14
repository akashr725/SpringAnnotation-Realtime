package com.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.customer.bo.CustomerBo;


@Repository("custDao")
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	@Qualifier("hikariCp")
	private DataSource ds;
	
	private Connection con;
	private static final String insertQuary="insert into customer values(cust_id,?,?,?,?)";
	
	

	@Override
	public int registerCustomer(CustomerBo bo) throws Exception {
		
		con=ds.getConnection();
	    PreparedStatement ps=con.prepareCall(insertQuary);
	    
	    ps.setString(1, bo.getName());
	    ps.setString(2, bo.getAddress());
	    ps.setFloat(3, bo.getpAmnt());
	    ps.setFloat(4, bo.getInterest());
	    
	   int rowCount= ps.executeUpdate();
	   ps.close();
	   con.close();
		
		return rowCount;
	}

}
