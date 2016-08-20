import org.json.*;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FormatChange {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("C:/Users/Xi/Desktop/file/Java/SparkFormat/src/rowsOrig.json"));
		JSONObject jsonObj = (JSONObject) obj;
		//String pageName = obj.getJSONObject("pageInfo").getString("pageName");

		JSONArray arr = (JSONArray) jsonObj.get("data");
		for (int i = 0; i < arr.size(); i++)
		{
			String temp = arr.get(i).toString();
			List<String> myList = new ArrayList<String>(Arrays.asList(temp.split("\","))); //crm cd desc affected
			String dateOCC = myList.get(5).substring(1);
			String timeOCC = myList.get(6).substring(1);
			String area = myList.get(7).substring(1);
			String areaName = myList.get(8).substring(1);
			String crimeCode = myList.get(10).substring(1);
			String crimeDesc = myList.get(11).substring(1);
			String loc = myList.get(14).substring(1);
			String loc1 = temp.substring(temp.indexOf('[', 15), temp.indexOf(']')+1); //cross street affected
			
			String jsonStr = "{\"dateOCC\": "+"\""+dateOCC+"\""+", "+"\"timeOCC\": " + timeOCC + ", "+"\"area\": " + area + ", "+"\"areaName\": "+"\""+areaName+"\""+", "+"\"crimeCode\": "+ crimeCode+ ", "+"\"crimeDesc\": "+"\""+crimeDesc+"\""+ ", "+"\"loc\": "+"\""+loc+"\""+ ", "+"\"loc1\": "+loc1+"}";

			System.out.println(jsonStr);
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("OrigChange.json", true)))) {
			    out.println(jsonStr);
			}catch (IOException e) {
			    //exception handling
			    System.out.println("Error, can not find the file!");
			}
			
		}
		
		
	}

}
