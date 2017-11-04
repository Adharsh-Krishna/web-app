package org.adharsh.test.webapp.operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;



@Path("/operation")
public class Operations {
	
	@POST
	
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response show(@FormParam("token") String token1) throws DbxApiException, DbxException{
	  	System.out.println("token"+token1);
	  	
	  	DbxRequestConfig requestConfig = new DbxRequestConfig("encryption_decryption");
	    DbxClientV2 client = new DbxClientV2(requestConfig, token1);
	  	FullAccount account = client.users().getCurrentAccount();
	  	
	  	System.out.println(account.getName().getDisplayName());
		System.out.println(account.getEmail());
		
		String mail=account.getEmail();
		String account_id=account.getAccountId();
		String location=account.getCountry();
		
	    ListFolderResult result = client.files().listFolder("");
	    String files="";
	    
	      while (true) {
	          for (Metadata metadata1 : result.getEntries()) {
	              files=files+metadata1.getPathLower();
	              files=files+"<br>"+"<hr>";
	          }

	          if (!result.getHasMore()) {
	              break;
	          }

	          result = client.files().listFolderContinue(result.getCursor());
	      }
	      
	      String tok="";

		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/addy","root","root");  
			Statement stmt=con.createStatement();  
			String sql="select * from users where email='"+mail+"'";
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next())  
			{
				tok=rs.getString("token");
				System.out.println(tok); 
			}
			con.close(); 
			if(tok.equals(""))
			{
				try
				{
					Class.forName("com.mysql.jdbc.Driver");  
					Connection conn=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/addy","root","root");  
			  
					String sqll="insert into users values('"+mail+"','"+token1+"',CURRENT_TIMESTAMP,0,0,0,0)";
					
					PreparedStatement Stmt = conn.prepareStatement(sqll);
					Stmt.execute();
					con.close(); 
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}  
			}
			else
			{
				try
				{
					Class.forName("com.mysql.jdbc.Driver");  
					Connection connn=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/addy","root","root");  
					String sqlll="UPDATE users SET token='"+token1+"' WHERE email='"+mail+"'";
					PreparedStatement stmt1=connn.prepareStatement(sqlll);
					int done = stmt1.executeUpdate();
					System.out.println(done);
					con.close(); 
				}
			  	catch(Exception e)
				{
					System.out.println(e);
				} 
			}
				
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}  

	  	
	  	int upload=0;
	  	int download=0;
	  	int encrypted=0;
	  	int decrypted=0;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/addy","root","root");  
			Statement stmt=con.createStatement();  
			String sql="select uploads,downloads,encr,decr from users where email='"+mail+"'";
			ResultSet rs=stmt.executeQuery(sql); 
			while(rs.next())  
			{
				upload=rs.getInt("uploads");
				download=rs.getInt("downloads");
				encrypted=rs.getInt("encr");
				decrypted=rs.getInt("decr");
				System.out.println("upload"+upload); 
				System.out.println("download"+download); 
				System.out.println("enc"+encrypted); 
				System.out.println("dec"+decrypted); 
			}
			con.close(); 
		}
		catch(Exception e)
		{
			System.out.println(e);
		}  
		String last_logged_in="";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/addy","root","root");  
			Statement stmt=con.createStatement();  
			String sql="select last_logged_in from users where email='"+mail+"'";
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next())  
			{
				last_logged_in=rs.getString("last_logged_in");
			}
			con.close(); 
		}
		catch(Exception e)
		{
			System.out.println(e);
		}  
			      return Response.ok().entity("<html>\n" + 
	      		"<head>\n" + 
	      		"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" + 
	      		"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" + 
	      		"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" + 
	      		"</head>\n" + 
	      		"</html>\n" + 
	      		"<style>\n" + 
	      		"#header\n" + 
	      		"    {\n" + 
	      		"        height: 200px;\n" + 
	      		"        width: auto;\n" + 
	      		"        background-color: black;\n" + 
	      		"        color:white;\n" + 
	      		"    }\n" + 
	      		"#footer\n" + 
	      		"    {\n" + 
	      		"        height: 200px;\n" + 
	      		"        width: auto;\n" + 
	      		"        background-color: black;\n" + 
	      		"    }\n" + 
	      		"#body\n" + 
	      		"    {\n" + 
	      		"        height: 550px;\n" + 
	      		"        width: auto;\n" + 
	      		"        background-color: white;\n" + 
	      		"    }\n" + 
	      		"    \n" + 
	      		"form\n" + 
	      		"    {\n" + 
	      		"        padding-top:200px;\n" + 
	      		"    }\n" + 
	      		"#heading\n" + 
	      		"    {\n" + 
	      		"        padding-bottom:30px;\n" + 
	      		"    }\n" + 
	      		".well-lg\n" + 
	      		"    {\n" + 
	      		"        height: 490px;\n" + 
	      		"        font-size: 20px;\n" + 
	      		"        overflow-y: auto;\n" + 
	      		"    }\n" + 
	      		"#files\n" + 
	      		"    {\n" + 
	      		"        background-color: black;\n" + 
	      		"        color:white;\n" + 
	      		"        text-align: center;\n" + 
	      		"      font-size: 20px;\n" + 
	      		"        margin: 3px;\n" + 
	      		"        height: 30px;\n" + 
	      		"    }\n" + 
	      		"#operation\n" + 
	      		"    {\n" + 
	      		"        background-color: lightgrey;\n" + 
	      		"        border-radius: 5px;\n" + 
	      		"        height:490px;\n" + 
	      		"        \n" + 
	      		"    } \n" + 
	      		"    \n" + 
	      		"</style>\n" + 
	      		"<body class=\"container-fluid\">\n" + 
	      		"    <div id=\"header\">\n" + 
	      		"        <div class=\"row\">\n" + 
	      		"                <div class=\"col-sm-4\" >\n" + 
	      		"                    <p  style=\"float:left;padding-left:30px;;padding-top:30px\"><span class=\"glyphicon glyphicon-user\" style=\"margin: 5px;\"></span><b>Account ID :</b> "+account_id+"</p>\n" + 
	      		"                </div>\n" + 
	      		"                 <div class=\"col-sm-4\" >\n" + 
	      		"                    <h1 align=\"center\"><b> Welcome    "+account.getName().getDisplayName()+" </b> </h1>\n" + 
	      		"                </div>\n" + 
	      		"                <div class=\"col-sm-4\" >\n" + 
	      		"                    <p  style=\"float:right;padding-right:30px;padding-top:30px;\"><span class=\"glyphicon glyphicon-map-marker\" style=\"margin: 5px;\"></span><b>Location :</b>"+location+" </p>\n" + 
	      		"                </div>\n" + 
	      		"        </div>\n" + 
	      		"        <p style=\"float: right;padding-right:30px;;padding-top:30px\"><span class=\"glyphicon glyphicon-envelope\" style=\"margin: 5px;\"></span><b>Logged in with :</b>"+mail+"</p>\n" + 
	      		"    </div>\n" + 
	      		"    <div id=\"body\">\n" + 
	      		"        <div class=\"row\">\n" + 
	      		"                <div class=\"col-sm-4\" style=\"background-color:white;\">\n" + 
	      		"                    <div id=\"files\">\n" + 
	      		"                        <p ><b>FILES</b></p>\n" + 
	      		"                    </div>\n" + 
	      		"                    <div class=\"well well-lg\">\n" + 
	      		"                        <b>\n" + 
	      		"                     "+files+"  "+
	      		"                        </b>\n" + 
	      		"                    </div>\n" + 
	      		"                </div>\n" + 
	      		"                <div class=\"col-sm-4\" style=\"background-color:white;\">\n" + 
	      		"                    <div id=\"files\">\n" + 
	      		"                        <p ><b>OPERATIONS</b></p>\n" + 
	      		"                    </div>\n" + 
	      		"                    <div id=\"operation\">\n" + 
	      		"                        <div class=\"row\" style=\"padding-top:50px;\">\n" + 
	      		"                            <div class=\"col-sm-2\"></div>\n" + 
	      		"                            <div class=\"col-sm-8\">\n" + 
	      		"                                <button type=\"button\" class=\"btn btn-primary btn-block\" onclick=\"window.location.href='/webapp/webapi/"+mail+"/upload'\">UPLOAD</button>\n" + 
	      		"                            </div>\n" + 
	      		"                            <div class=\"col-sm-2\"></div>\n" + 
	      		"                        </div>\n" + 
	      		"                        <div class=\"row\" style=\"padding-top:50px;\">\n" + 
	      		"                            <div class=\"col-sm-2\"></div>\n" + 
	      		"                            <div class=\"col-sm-8\">\n" + 
	      		"                                <button type=\"button\" class=\"btn btn-primary btn-block\" onclick=\"window.location.href='/webapp/webapi/"+mail+"/download'\">DOWNLOAD</button>\n" + 
	      		"                            </div>\n" + 
	      		"                            <div class=\"col-sm-2\"></div>\n" + 
	      		"                        </div>\n" + 
	      		"                    </div>\n" + 
	      		"                </div>\n" + 
	      		"                <div class=\"col-sm-4\" style=\"background-color:white;\">\n" + 
	      		"                    <div id=\"files\">\n" + 
	      		"                        <p ><b>DETAILS</b></p>\n" + 
	      		"                        <div class=\"well well-lg\">\n" + 
	      		"                            <table class=\"table table-striped\">\n" + 
	      		"                               \n" + 
	      		"                                <tbody>\n" + 
	      		"                                  <tr>\n" + 
	      		"                                    <td>Total Uploads :</td>\n" + 
	      		"                                    <td>"+upload+"</td>\n" + 
	      		"                            \n" + 
	      		"                                  </tr>\n" + 
	      		"                                  <tr>\n" + 
	      		"                                    <td>Total Downloads :</td>\n" + 
	      		"                                    <td>"+download+"</td>\n" + 
	      		"                                    \n" + 
	      		"                                  </tr>\n" + 
	      		"                                  <tr>\n" + 
	      		"                                    <td>Total Encrypted Files :</td>\n" + 
	      		"                                    <td>"+encrypted+"</td>\n" + 
	      		"                                    \n" + 
	      		"                                  </tr>\n" + 
	      		"                                  <tr>\n" + 
	      		"                                    <td>Total Decrypted Files :</td>\n" + 
	      		"                                    <td>"+decrypted+"</td>\n" + 
	      		"                                    \n" + 
	      		"                                  </tr>\n" + 
	      		"                                  <tr>\n" + 
	      		"                                    <td>Last signed in :</td>\n" + 
	      		"                                    <td>"+last_logged_in+"</td>\n" + 
	      		"                                   \n" + 
	      		"                                  </tr>\n" + 
	      		"                                </tbody>\n" + 
	      		"                              </table>\n" + 
	      		"                        </div>\n" + 
	      		"                    </div>\n" + 
	      		"                </div>\n" + 
	      		"        </div>\n" + 
	      		"    </div>\n" + 
	      		"    <div id=\"footer\">\n" + 
	      		"    \n" + 
	      		"    </div>\n" + 
	      		"</body>")
	    			.build();
	      
	}
	
}
