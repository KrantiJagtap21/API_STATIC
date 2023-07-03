package TestClasses;
import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.Assert;

import Common_Method.CommonUtilityMethod;
import Common_Method.Post_CommonMethod;
import Request_Repositiry.Post_Req;
import io.restassured.path.json.JsonPath;

public class Post_TC {
	public static void extractor() throws IOException
	{
		for (int i=0; i<5 ; i++ ) 
		{
			int statusCode = Post_CommonMethod.ResponseStatusCode(
					Post_Req.BaseURI(), 
					Post_Req.Post_Resource(), 
					Post_Req.Post_ReqBody());
	    if (statusCode==201)
	    {
	    	System.out.println(statusCode);
		    String ResponseBody = Post_CommonMethod.ResponseBody(
					Post_Req.BaseURI(), 
					Post_Req.Post_Resource(), 
					Post_Req.Post_ReqBody());
		    System.out.println(ResponseBody);
		    String RequestBody = Post_Req.Post_ReqBody();
		    CommonUtilityMethod.EvidenceCreator("Post_TC1", RequestBody, ResponseBody, statusCode);
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
			String Res_createdAt = JspResponse.getString("createdAt");
			Res_createdAt = Res_createdAt.substring(0,10);
			
	        //Validate the Response Body 
	        Assert.assertEquals(Res_name, Req_name);
	        Assert.assertEquals(Res_job, Req_job);
	        Assert.assertEquals(Res_createdAt, expecteddate);
 
  }
}

