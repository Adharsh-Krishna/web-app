package org.adharsh.test.webapp.download_file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.MediaType;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

@Path("/{thing}")
public class Download {
	
	
	static void fileProcessor(int cipherMode,String key,File inputFile,File outputFile)throws NullPointerException{
		 try {
		       Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
		       Cipher cipher = Cipher.getInstance("AES");
		       cipher.init(cipherMode, secretKey);

		       FileInputStream inputStream = new FileInputStream(inputFile);
		       byte[] inputBytes = new byte[(int) inputFile.length()];
		       inputStream.read(inputBytes);

		       byte[] outputBytes = cipher.doFinal(inputBytes);

		       FileOutputStream outputStream = new FileOutputStream(outputFile);
		       outputStream.write(outputBytes);

		       inputStream.close();
		       outputStream.close();

		    } 
		 catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | IOException e) 
		 {
			e.printStackTrace();
	     }
		 
	     }
	
	
	
	@POST
	@Path("/download_file")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	public String upload(@FormParam("password") String key,@FormParam("downloadname") String downloadname,@FormParam("filename") String filename,@FormParam("checked") String checked,@PathParam("thing") String mail) throws DbxApiException, DbxException, IOException
	{
		
		String VAL="FAILURE";
		String token1="";
		String downloaded_file=downloadname;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/addy","root","root");  
			Statement stmt=con.createStatement();  
			String sql="select token from users where email='"+mail+"'";
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next())  
			{
				token1=rs.getString("token");
				System.out.println(token1); 
			}
			con.close(); 
		}
		catch(Exception e)
		{
			System.out.println(e);
		}  
		
		
		DbxRequestConfig requestConfig = new DbxRequestConfig("encryption_decryption");
	    DbxClientV2 client = new DbxClientV2(requestConfig, token1);
	    System.out.println("checked"+checked);
	    
      try
      {
          OutputStream downloadFile = new FileOutputStream(downloaded_file);
          try
          {
          FileMetadata metadata = client.files().downloadBuilder(filename)
                  .download(downloadFile);
          VAL="SUCCESS";
          System.out.println(metadata);
          try
  		{
  			Class.forName("com.mysql.jdbc.Driver");  
  			Connection con=DriverManager.getConnection(  
  			"jdbc:mysql://localhost:3306/addy","root","root");  
  			String sql="UPDATE users SET downloads=downloads+1 WHERE email='"+mail+"'";
			PreparedStatement stmt=con.prepareStatement(sql);
			int done = stmt.executeUpdate();
			System.out.println(done);
  			con.close(); 
  		}
  	  	catch(Exception e)
  		{
  			System.out.println(e);
  		}  
          }
          finally
          {
              downloadFile.close();
          }
      }
    
      catch (DbxException e)
      {
         System.out.println("error"+e);
      }
      catch (IOException e)
      {
    	  System.out.println("error"+e);
      }
      
      File down=new File(downloadname);
      File decryptedFile = new File(downloadname);
      if(checked.equals("off"))
      {
    	  System.out.println("checked"+checked);
      }
      
      
      if(checked.equals("on"))
      {
    	  Download.fileProcessor(Cipher.DECRYPT_MODE,key,down,decryptedFile);
    	  System.out.println("decrypted");
    	  try
  		{
  			Class.forName("com.mysql.jdbc.Driver");  
  			Connection con=DriverManager.getConnection(  
  			"jdbc:mysql://localhost:3306/addy","root","root");  
  			String sql="UPDATE users SET decr=decr+1 WHERE email='"+mail+"'";
			PreparedStatement stmt=con.prepareStatement(sql);
			int done = stmt.executeUpdate();
			System.out.println(done);
  			con.close(); 
  		}
  	  	catch(Exception e)
  		{
  			System.out.println(e);
  		}  
      }
	
  return VAL;
	  }
	  
	  	
	

	}

