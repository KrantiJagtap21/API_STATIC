package TestClasses;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import Common_Method.CommonUtilityMethod;
import Common_Method.Put_CommonMethod;
import Request_Repositiry.Put_Req;
import io.restassured.path.json.JsonPath;

public class Put_TC {
	public static void extractor() throws IOException
	{
		for (int i=0; i<5 ; i++ ) 
		{
			int statusCode = Put_CommonMethod.ResponseStatusCode(
					Put_Req.BaseURI(), 
					Put_Req.Put_Resource(), 
					Put_Req.Put_ReqBody());
	    if (statusCode==200)
	    {
	    	System.out.println(statusCode);
		    String ResponseBody = Put_CommonMethod.ResponseBody(
					Put_Req.BaseURI(), 
					Put_Req.Put_Resource(), 
					Put_Req.Put_ReqBody());
		    System.out.println(ResponseBody);
		    String RequestBody = Put_Req.Put_ReqBody();
		    CommonUtilityMethod.EvidenceCreator("Put_TC1", RequestBody, ResponseBody, statusCode);
		    validator(RequestBody, ResponseBody);
            break;	
	   }
	  else
	  {
		System.out.println("Invalid Status Code :" +statusCode+"\n\n" );
	  }
   }
}
	public static void validator(String RequestBody, String ResponseBody) {
			JsonPath JspRequest = new JsonPath(RequestBody);
			String Req_name= JspRequest.getString("name");
			String Req_job = JspRequest.getString("job");
					
			JsonPath JspResponse = new JsonPath(ResponseBody);
			String Res_name = JspResponse.getString("name");
			String Res_job = JspResponse.getString("job");
			
			//Validate the Response Body 
	        Assert.assertEquals(Res_name, Req_name);
	        Assert.assertEquals(Res_job, Req_job);
	         
  }
}
