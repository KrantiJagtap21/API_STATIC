# API_STATIC
This project is capable to automate execution and validation: POST,PUT, PATCH and GET.
Users can also construct the request body using parameters from Excel file for that I have created a common utility method using ApachePoi to read data from Excel
This project is built on the concept of data-driven and keyword driven.
      I have divided the project into four packages request repository, test class, common methods and driver class.
The test execution is driven by static  XML
I have created a method in common utility class so the project is capable to save the evidence of execution into text files which contain details of request sent, endpoint and response received.
