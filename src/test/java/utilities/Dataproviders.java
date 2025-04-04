package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviders {

	//DataProviders
	
	@DataProvider(name="Logindata")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\OpenCartLogin_Details.xlsx";
		
		ExcelUtility xlutil= new ExcelUtility(path);
		
		int totalrows= xlutil.getRowCount("Sheet1");
		int totalcols= xlutil.getCellCount("Sheet1",1);
		
		String loginData[][]=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				loginData[i-j][j]=xlutil.getCellData("Sheet1",i,j);
			}
		}
		return loginData;
		
		
		
		
		
		
		
	}
	
	
	
	
}
