package Common_Method;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonUtilityMethod {
		public static void EvidenceCreator(String Filename, String RequestBody, String ResponseBody, int StatusCode)
				throws IOException {
			File TextFile = new File(""H:\\API\\Evidence\\" + Filename + ".txt");
			System.out.println("New blank text file of name :" + TextFile.getName());

			FileWriter dataWrite = new FileWriter(TextFile);
			dataWrite.write("Request Body is : " + RequestBody + "\n\n");
			dataWrite.write("Status Code is : " + StatusCode + "\n\n");
			dataWrite.write("Response Body is : " + ResponseBody);
			dataWrite.close();
			System.out.println("Request body and response body is written in file :" + TextFile.getName());

		}

		public static ArrayList<String> ReadDataExcel(String sheetname, String TestCaseName) throws IOException {
			ArrayList<String> ArrayData = new ArrayList<String>();
			// create the object of file input stream to locate the excel file
			FileInputStream Fis = new FileInputStream("H:\\API\\RestAssuredDataDrivenAll.xlsx");
			// Step2: Open the Excel file by creating the object XSSFWorkbook
			XSSFWorkbook WorkBook = new XSSFWorkbook(Fis);
			// Step3: Open the Desired Excel Sheet
			int CountOfSheet = WorkBook.getNumberOfSheets();
			for (int i = 0; i < CountOfSheet; i++) {
				String SheetName = WorkBook.getSheetName(i);
				// Step no4: Access the desired sheet
				if (SheetName.equalsIgnoreCase(sheetname)) {
					// Use XSSFSheet to save the sheet into the variable
					XSSFSheet Sheet = WorkBook.getSheetAt(i);
					// Create iterate through rows and find out in which column the testcase names
					// are found
					Iterator<Row> Rows = Sheet.iterator();
					Row FirstRow = Rows.next();
					// Create the iterator through the cells of first row to find out which cells
					// contains testcase name
					Iterator<Cell> CellsofFirstRows = FirstRow.cellIterator();
					int k = 0;
					int TC_column = 0;
					while (CellsofFirstRows.hasNext()) {
						Cell CellValue = CellsofFirstRows.next();
						if (CellValue.getStringCellValue().equalsIgnoreCase("TestCaseName")) {
							TC_column = k;
							break;
						}
						k++;
					}
					// verify the row where the desired test case is found and fetch the entire row
					while (Rows.hasNext()) {
						Row Datarow = Rows.next();
						String TCName = Datarow.getCell(TC_column).getStringCellValue();
						if (TCName.equalsIgnoreCase(TestCaseName)) {
							Iterator<Cell> CellValues = Datarow.cellIterator();
							while (CellValues.hasNext()) 
							{
								String Data = "";
								Cell CurrentCell = CellValues.next();
								try
								{
									String StringData = CurrentCell.getStringCellValue();
									Data = StringData;
								}
								catch(IllegalStateException e)
								{
								    double doubledata = CurrentCell.getNumericCellValue();
								    Data = Double.toString(doubledata);
								}
								ArrayData.add(Data);
							}
							break;
						}
					}

				}

			}
			return ArrayData;
		}
	}
