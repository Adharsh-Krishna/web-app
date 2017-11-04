package org.adharsh.test.webapp.auth;



import javax.ws.rs.GET;


import javax.ws.rs.Path;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import javax.ws.rs.Produces;


@Path("/auth")
public class Auth_code {

	@GET

	@Produces({MediaType.TEXT_HTML})
	public Response getCode()  {
	
		
	
	
		String url="http://localhost:8080/webapp/webapi/operation";
		System.out.println(url);
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
	     		"</style>\n" + 
	     		"<body class=\"container-fluid\">\n" + 
	     		"    <div id=\"header\">\n" + 
	     		"    \n" + 
	     		"    </div>\n" + 
	     		"    <div id=\"body\">\n" + 
	     		"        <div class=\"row\">\n" + 
	     		"                <div class=\"col-sm-4\" style=\"background-color:white;\"></div>\n" + 
	     		"                <div class=\"col-sm-4\" style=\"background-color:white;\">\n" + 
	     		"                    <form action=\""+url+"\" method=\"post\">\n" + 
	     		"                        <div class=\"form-group\">\n" + 
	     		"                          <h2 id=\"heading\" align=\"center\"><b> Copy and Paste the Acess Token below </b></h2>\n" + 
	     		"                          <input type=\"text\" class=\"form-control\" id=\"token\" placeholder=\"Enter token here\" name=\"token\" >\n" + 
	     		"                        </div>\n" + 
	     		"                        <div class=\"row\" style=\"padding-top:50px;\">\n" + 
	     		"                            <div class=\"col-sm-6\" >\n" + 
	     		"                                <button type=\"submit\" class=\"btn btn-default\" style=\"float:left;\">Submit</button>\n" + 
	     		"                            </div>\n" + 
	     		"                            <div class=\"col-sm-6\" >\n" + 
	     		"                                <button type=\"reset\" class=\"btn btn-default\" style=\"float:right;\">Reset</button>\n" + 
	     		"                            </div>\n" + 
	     		"                        </div>\n" + 
	     		"                        \n" + 
	     		"                    </form>\n" + 
	     		"                </div>\n" + 
	     		"                <div class=\"col-sm-4\" style=\"background-color:white;\"></div>\n" + 
	     		"        </div>\n" + 
	     		"    </div>\n" + 
	     		"    <div id=\"footer\">\n" + 
	     		"    \n" + 
	     		"    </div>\n" + 
	     		"</body>")
	    		 .build();
	
	}
	
	
}
