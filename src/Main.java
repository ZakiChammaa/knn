//Zaki Chammaa
//6584233
//I declare Malek El-Baher as a buddy

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static String format(double n) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setRoundingMode(RoundingMode.FLOOR);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2);
        return format.format(n);
    }
	public static void main(String [] args){		
		ArrayList<ArrayList<Double>> dataArray = FileHandler.CDR("segmentations.txt");		//ArrayList containing the all the features
		ArrayList<ArrayList<Double>> distanceArray = ClusterReduction.array_of_distances(dataArray);	//ArrayList containing all the distances between the features
		double globalMin = ClusterReduction.D_min_global(dataArray);	//smallest distance between any 2 features
		int[] index_of_min = ClusterReduction.index_of_min(dataArray, distanceArray);
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input k value");
		double k = sc.nextDouble();
		sc.close();
		double epsilon = k * globalMin;
		boolean flag = true;
		System.out.println("The original number of features is: " + dataArray.size());
		do{
			ClusterReduction.Discard(index_of_min[0], index_of_min[1], index_of_min[2], index_of_min[3], dataArray, distanceArray, flag);
			
			
			distanceArray = ClusterReduction.array_of_distances(dataArray);
			
			
			
			flag = !flag;
			
			
			globalMin = ClusterReduction.D_min_global(dataArray);

			
			index_of_min = ClusterReduction.index_of_min(dataArray, distanceArray);
		}while(globalMin <= epsilon);
		System.out.println("The reduced number of features is: " + dataArray.size());
		
		FileHandler.write_to_file(dataArray, "reduced_features.txt");

		ArrayList<ArrayList<Double>> original_features = FileHandler.classifier("segmentations.txt");
		ArrayList<ArrayList<Double>> reduced_features = new ArrayList<ArrayList<Double>>();
		ArrayList<String> potentialClassifier = new ArrayList<String>();
		
		for(int i = 0; i < FileHandler.getClassifier("segmentations.txt").size(); i++){
			ArrayList<Double> tempArray = new ArrayList<Double>();
			for(int j = 0; j < dataArray.size(); j++)
				tempArray.add(dataArray.get(j).get(i));
			reduced_features.add(tempArray);
		}

		for(int i = 0; i < original_features.size()/2; i++)
			potentialClassifier.add(Classifier.findMin_classify(original_features, i, "segmentations.txt"));
		System.out.print("The ratio for the original set of features is: ");
		System.out.println(format(Classifier.compare(FileHandler.getClassifier("segmentations.txt"), potentialClassifier)) + "%");
		
		potentialClassifier.clear();
		
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < reduced_features.size()/2; i++)
			potentialClassifier.add(Classifier.findMin_classify(reduced_features, i, "segmentations.txt"));
		long endTime = System.currentTimeMillis();
		System.out.println(dataArray.size() + " " + (endTime - startTime) + " ms");
		
		System.out.print("The ratio for the reduced set of features is: ");
		System.out.println(format(Classifier.compare(FileHandler.getClassifier("segmentations.txt"), potentialClassifier)) + "%");
		
	}
}