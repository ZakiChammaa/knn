//Zaki Chammaa
//6584233
//I declare Malek El-Baher as a buddy

import java.util.ArrayList;

public class Classifier{
	public static String findMin_classify(ArrayList<ArrayList<Double>> dataArray, int index, String f){
		double minDistance = distances.euclidean(dataArray.get(index), dataArray.get((dataArray.size()/2)));
		ArrayList<String> classifierList= FileHandler.getClassifier(f);
		int minIndex = 0;
		for(int i = (dataArray.size()/2); i < (dataArray.size()); i++){
			if(minDistance > distances.euclidean(dataArray.get(index), dataArray.get(i))){
				minDistance = distances.euclidean(dataArray.get(index), dataArray.get(i));
				minIndex = i;
			}
		}
		return classifierList.get(minIndex);
	}
	public static double compare(ArrayList<String> original, ArrayList<String> newList){
		double count = 0.0;
		for(int i = 0; i < original.size()/2; i++){
			if(original.get(i).equals(newList.get(i)))
				count++;
		}
		double ratio = (count / (newList.size()));
		return (ratio * 100.0);
	}
}