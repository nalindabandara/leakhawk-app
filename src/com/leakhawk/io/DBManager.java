package com.leakhawk.io;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.leakhawk.model.FeedEntry;





public class DBManager {

	
	public void saveFeedEntryBatch( List<FeedEntry> entryList ){
		
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = DBConnector.getConnection();
			
            StringBuilder sb = new StringBuilder("INSERT INTO feed_entry ( ");                       
            sb.append("entry_key, "); 
            sb.append("entry_url, ");
            sb.append("entry_title, ");
            sb.append("entry_file_name, ");            
            sb.append("entry_user ) VALUES ( ?, ?, ?, ?, ? ); ");
            statement = con.prepareStatement( sb.toString() );
            
            for( FeedEntry feedEntry : entryList){
            	insertFeedEntryBatch( feedEntry, statement );
            }
            
            statement.executeBatch();            
            
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBConnector.closeStatement(statement);
				DBConnector.closeConnection(con);				
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
		}
	}
	
	
	public void saveContextFeedEntryBatch( List<FeedEntry> entryList ){
		
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = DBConnector.getConnection();
			
            StringBuilder sb = new StringBuilder("INSERT INTO feed_entry_context ( ");                       
            sb.append("entry_key, "); 
            sb.append("entry_url, ");
            sb.append("entry_title, ");
            sb.append("entry_file_name, ");            
            sb.append("entry_user ) VALUES ( ?, ?, ?, ?, ? ); ");
            statement = con.prepareStatement( sb.toString() );
            
            for( FeedEntry feedEntry : entryList){
            	insertFeedEntryBatch( feedEntry, statement );
            }
            
            statement.executeBatch();            
            
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBConnector.closeStatement(statement);
				DBConnector.closeConnection(con);				
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
		}
	}
	
	private void insertFeedEntryBatch( FeedEntry feedEntry, PreparedStatement statement) throws MalformedURLException, IOException{
		
		 int count = 0;           
         
		 try {
			 statement.setString( ++count, feedEntry.getKey() );
			 statement.setString( ++count, feedEntry.getScrapperUrl() ); 
			 statement.setString( ++count, feedEntry.getTitle() ); 
			 statement.setString( ++count, feedEntry.getEntryFileName());			
			 statement.setString( ++count, feedEntry.getUser() ); 
			 statement.addBatch();
		} catch (SQLException e) {			
			e.printStackTrace();
		} 		
	}
	
	public boolean isUserExists( String user ){
		
		int count = 0;           
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			if( user == null || user.trim().length() == 0){
				return false;
			}
			
			con = DBConnector.getConnection();
			
            String sql = "SELECT * FROM mal_user where user_name = ?";                     
                                          
            statement = con.prepareStatement( sql );
            
            statement.setString( ++count, user );
            
            rs = statement.executeQuery();   
            
            if( rs.next() ){
            	return true;
            }
            
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBConnector.closeResultSet(rs);				
				DBConnector.closeStatement(statement);
				DBConnector.closeConnection(con);
				
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
		}
		return false;
	}
}
