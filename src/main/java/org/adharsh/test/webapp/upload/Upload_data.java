package org.adharsh.test.webapp.upload;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.Response;

@Path("{name}")
public class Upload_data {

	
	@GET
	@Path("upload")
	public Response upload_data(@PathParam("name") String mail) {
		
		
		return Response.ok().entity("<html>\n" + 
				"<head>\n" + 
				"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" + 
				"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" + 
				"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" + 
				"    <script>\n" + 
				"    $(function(){\n" + 
				"    $('#pwd').hide();\n" + 
				"    $('#pwdname').hide  ();\n" + 
				"    $('#check1').click(function() {\n" + 
				"    if ($(this).is(':checked')) {\n" + 
				"    $('#pwd').show();\n" + 
				"         $('#pwdname').show();\n" + 
				"    }\n" + 
				"    else{\n" + 
				"    $('#pwd').hide();\n" + 
				"        $('#pwdname').hide();\n" + 
				"    }\n" + 
				"    });\n" + 
				
				"$('#check').click(function() {\n" + 
				"    if ($(this).is(':checked')) {\n" + 
				"    $('#pwd').hide();\n" + 
				"         $('#pwdname').hide();\n" + 
				"    }\n" + 
				"    else{\n" + 
				"    $('#pwd').show();\n" + 
				"        $('#pwdname').show();\n" + 
				"    }\n" + 
				"    });\n" + 
				"    });\n" + 
				"    </script>\n" + 
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
				"        height: 400px;\n" + 
				"        font-size: 20px;\n" + 
				"        overflow-y: auto;\n" + 
				"        margin-top: 10px;\n" + 
				"     \n" + 
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
				"        <h1 align=\"center\" style=\"padding-top:20px;\"><b>Upload</b></h1>\n" + 
				"    </div>\n" + 
				"    <div id=\"body\" class=\"row\">\n" + 
				"        <div class=\"col-sm-2\"></div>\n" + 
				"        <div class=\"col-sm-8\" style=\"padding-top:50px;\">\n" + 
				"            <div class=\"well well-lg\" >\n" + 
				"                <form action='http://localhost:8080/webapp/webapi/"+mail+"/upload_file' method=\"post\">\n" + 
				"                    <div class=\"form-group\">\n" + 
				"                      <label for=\"file\">File Name:</label>\n" + 
				"                      <input type=\"text\" class=\"form-control\" id=\"filename\" placeholder=\"Enter file name\" name=\"filename\">\n" + 
				"                    </div>\n" + 
				"                    <div class=\"form-group\">\n" + 
				"                      <label  for=\"pwd\">Upload Name:</label>\n" + 
				"                      <input type=\"text\" class=\"form-control\" id=\"uploadname\" placeholder=\"Enter upload name \" name=\"uploadname\">\n" + 
				"                    </div>\n" + 
				"                    <div class=\"form-group\">\n" + 
				"                      <label id=\"pwdname\" for=\"pwd\">Password:</label>\n" + 
				"                      <input type=\"password\" class=\"form-control\" id=\"pwd\" placeholder=\"Enter password\" name=\"password\">\n" + 
				"                    </div>\n" + 
				"                    <div class=\"checkbox\">\n" + 
				"                      <label><input type=\"radio\" id=\"check\" name=\"checked\" value=\"off\" checked> Upload</label>\n" + 
				"                      <label><input type=\"radio\" id=\"check1\" name=\"checked\" value=\"on\"> Encrypt and Upload</label>\n" + 

				"                    </div>\n" + 
				"                    <button type=\"submit\" class=\"btn btn-default\">Upload</button>\n" + 
				"                  </form>\n" + 
				"            </div>\n" + 
				"        </div>\n" + 
				"        <div class=\"col-sm-2\"></div>\n" + 
				"    </div>\n" + 
				"    <div id=\"footer\">\n" + 
				"    \n" + 
				"    </div>\n" + 
				"</body>").build();
	}
}
