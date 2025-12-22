package Utilimp;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;


public class registerdataUtil{
	
	@DataProvider(name="LoginTestdata")
	 
	public Object[][] registrationDataProvider() throws Exception {

	    String path = "src/test/resources/Testdata/Details.csv";

	    List<Map<String, String>> csvData =
	    		Csvdata.readCSVAsMap(path);

	    Object[][] data = new Object[csvData.size()][1];

	    for (int i = 0; i < csvData.size(); i++) {
	        data[i][0] = csvData.get(i);   // each row as Map
	    }

	    return data;
	}
	
}
