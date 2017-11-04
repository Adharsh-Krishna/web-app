package org.adharsh.test.webapp.login_form;

 

import javax.ws.rs.GET;

import javax.ws.rs.Path;

  
import javax.ws.rs.core.Response;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuth;

import java.net.URI;
import java.net.URISyntaxException;
@Path("/check")

public class LoginForm {

	
	@GET
	public Response login() throws URISyntaxException {
		final String  app_key="cxyu2stydk7m6wx";
		final String app_secret="c7den18xdyl1s0s";
	
		DbxAppInfo appInfo = new DbxAppInfo(app_key,app_secret);
		DbxRequestConfig requestConfig = new DbxRequestConfig("encryption_decryption");
		DbxWebAuth webAuth = new DbxWebAuth(requestConfig, appInfo);
		
		DbxWebAuth.Request webAuthRequest = DbxWebAuth.newRequestBuilder().withNoRedirect().build();
		String authorizeUrl = webAuth.authorize(webAuthRequest);
		authorizeUrl="https://www.dropbox.com/oauth2/authorize?response_type=token&client_id=cxyu2stydk7m6wx";
		
		URI redirect_uri3= new URI(authorizeUrl+"&redirect_uri=http://localhost:8080/webapp/webapi/auth&force_reauthentication=true");
		
		
		
		return Response.seeOther(redirect_uri3).build();	

	}
}
