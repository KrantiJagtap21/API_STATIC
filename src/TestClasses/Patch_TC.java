package TestClasses;

import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.Assert;
import Common_Method.CommonUtilityMethod;
import Common_Method.Patch_CommonMethod;
import Request_Repositiry.Patch_Req;
import io.restassured.path.json.JsonPath;

public class Patch_TC {
	public static void extractor() throws IOException
	{
		for (int i=0; i<5 ; i++ ) 
		{
			int statusCode = Patch_CommonMethod.ResponseStatusCode(
					Patch_Req.BaseURI(), 
					Patch_Req.Patch_Resource(), 
					Patch_Req.Patch_ReqBody());
	    if (statusCode==200)
	    {
	    	System.out.println(statusCode);
		    String ResponseBody = Patch_CommonMethod.ResponseBody(
					Patch_Req.BaseURI(), 
					Patch_Req.Patch_Resource(), 
					Patch_Req.Patch_ReqBody());
		    System.out.println(ResponseBody);
		    String RequestBody = Patch_Req.Patch_ReqBody();
		    CommonUtilityMethod.EvidenceCreator("Patch_TC1", RequestBody, ResponseBody, statusCode);
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
			LocalDateTime currentdate = LocalDateTime.now();
			String expecteddate = currentdate.toString().substring(0, 10);
			
			JsonPath JspResponse = new JsonPath(ResponseBody);
			String Res_name = JspResponse.getString("name");
			String Res_job = JspResponse.getString("job");
			String Res_updatedAt = JspResponse.getString("updatedAt").substring(0, 10);
					
	        //Validate the Response Body 
	        Assert.assertEquals(Res_name, Req_name);
	        Assert.assertEquals(Res_job, Req_job);
	        Assert.assertEquals(Res_updatedAt, expecteddate);
 
  }
}
