package Request_Repositiry;

import java.io.IOException;
import java.util.ArrayList;

import Common_Method.CommonUtilityMethod;

public class Patch_Req {
	public static String BaseURI() {
		String baseURI = "https://reqres.in/";
		return baseURI;
	}
	public static String Patch_Resource() {
		String Patch_Resource ="api/users/2";
		return Patch_Resource;
	}
	public static String Patch_ReqBody() throws IOException {
			ArrayList<String> Req_Data=CommonUtilityMethod.ReadDataExcel("PatchAPI","TC2");
			System.out.println(Req_Data); 
			String Req_name=Req_Data.get(1);
			String Req_job=Req_Data.get(2);
			String RequestBody ="{\r\n"
					+ "    \"name\": \""+Req_name+"\",\r\n"
					+ "    \"job\": \""+Req_job+"\"\r\n"
					+ "}";
		    return RequestBody;
		}
}
