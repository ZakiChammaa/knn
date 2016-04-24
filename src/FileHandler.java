//Zaki Chammaa
//6584233
//I declare Malek El-Baher as a buddy

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileHandler{
	
	public static ArrayList<ArrayList<Double>> CDR(String f){
		ArrayList<ArrayList<Double>> dataArray = new ArrayList<ArrayList<Double>>();
		try{
		 	for(int i = 1; i < 20; i++){
		 		BufferedReader br = new BufferedReader(new FileReader(f));
		 		ArrayList<Double> featureArray = new ArrayList<Double>();
		 		String str;
		 		String[] ar;
		 		
			    while ((str = br.readLine()) != null){
			        ar = str.split(",");
			        featureArray.add(Double.valueOf(ar[i]));
			    }
			    dataArray.add(featureArray);
			    br.close(); 
	        }    
	    }catch (IOException e){
	        System.out.println("File Read Error");
	    }
		return dataArray;
	}
	
	public static ArrayList<ArrayList<Double>> classifier(String f){

		ArrayList<ArrayList<Double>> dataArray = new ArrayList<ArrayList<Double>>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(f));
			for(int i = 0; i < 2100; i++){
				ArrayList<Double> featureArray = new ArrayList<Double>();
				String str = br.readLine();
				if(str == null)
					break;
				String[] ar = str.split(",");
				for(int j = 1; j < 20; j++)
					featureArray.add(Double.valueOf(ar[j]));
				dataArray.add(featureArray);
			}
			br.close();
		}catch(Exception e){
			System.out.println("File Read Error");
		}
		return dataArray;
	}
	
	public static ArrayList<String> getClassifier(String f){
		ArrayList<String> ArrayClassifier = new ArrayList<String>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(f));
			String str;
			String[] ar;

			while ((str = br.readLine()) != null){
				ar = str.split(",");
				ArrayClassifier.add((ar[0]));
			}
			br.close();   
		}catch (IOException e){
			System.out.println("File Read Error");
		}
		return ArrayClassifier;
	}
	public static void write_to_file(ArrayList<ArrayList<Double>> dataArray, String f){
		try{
			PrintWriter writer = new PrintWriter(f, "UTF-8");
			for(int i = 0; i < dataArray.size(); i++)
				writer.println(dataArray.get(i));
			writer.close();
		}catch(Exception e){
			System.out.println("File write error");
		}
	}
}
