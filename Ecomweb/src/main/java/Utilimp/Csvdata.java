package Utilimp;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;

public class Csvdata {
	 
	public static List<Map<String, String>> readCSVAsMap(String path)
	        throws Exception {

	    CSVReader reader = new CSVReader(new FileReader(path));
	    List<String[]> rows = reader.readAll();

	    String[] header = rows.get(0);
	    List<Map<String, String>> data = new ArrayList<>();

	    for (int i = 1; i < rows.size(); i++) {
	        Map<String, String> rowMap = new HashMap<>();
	        for (int j = 0; j < header.length; j++) {
	            rowMap.put(header[j], rows.get(i)[j]);
	        }
	        data.add(rowMap);
	    }
	    return data;
	}

	}

