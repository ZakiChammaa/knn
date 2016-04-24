//Zaki Chammaa
//6584233
//I declare Malek El-Baher as a buddy

import java.util.ArrayList;
import java.util.Collections;

public class ClusterReduction{
	
	public static double D_min_global(ArrayList<ArrayList<Double>> dataArray){
		double globalMin = distances.D(dataArray.get(0), dataArray.get(1));
		for(int i =0; i < dataArray.size()-1; i++){
			for(int j = i; j < dataArray.size(); j++){
				if(globalMin > distances.D(dataArray.get(i), dataArray.get(j)) && distances.D(dataArray.get(i), dataArray.get(j)) > 0.0){
					globalMin = distances.D(dataArray.get(i), dataArray.get(j));
				}
			}
		}
		return globalMin;
	}
	
	public static int[] index_of_min(ArrayList<ArrayList<Double>> dataArray, ArrayList<ArrayList<Double>> distanceArray){
		double globalMin = distances.D(dataArray.get(0), dataArray.get(1));
		int indexVa = 0, indexVb = 0, distanceIndexA = 0, distanceIndexB = 0;
		int[] array_of_indices = new int[4];
		for(int i = 0; i < dataArray.size()-1; i++){
			for(int j = i; j < dataArray.size(); j++){
				if(globalMin > distances.D(dataArray.get(i), dataArray.get(j)) && distances.D(dataArray.get(i), dataArray.get(j)) > 0.0){
					globalMin = distances.D(dataArray.get(i), dataArray.get(j));
					indexVa = i;
					indexVb = j;
				}
			}
		}
		for(int i = 0; i < distanceArray.size(); i++){
			for(int j = 0; j < distanceArray.get(i).size(); j++){
				if(distanceArray.get(i).get(j) == globalMin){
					distanceIndexA = i;
					distanceIndexB = j;
				}
			}
		}
		array_of_indices[0] = indexVa;
		array_of_indices[1] = indexVb;
		array_of_indices[2] = distanceIndexA;
		array_of_indices[3] = distanceIndexB;
		return array_of_indices;
	}
	
	public static ArrayList<ArrayList<Double>> array_of_distances(ArrayList<ArrayList<Double>> dataArray){
		ArrayList<ArrayList<Double>> distanceArray = new ArrayList<ArrayList<Double>>();
		for(int i = 0; i < dataArray.size()-1; i++){
			ArrayList<Double> tempDistances = new ArrayList<Double>();
			for(int j = i+1; j < dataArray.size(); j++){
				if(distances.var(dataArray.get(i)) == 0.0){
					dataArray.remove(i);
					i--;
					break;
				}
				else if(distances.var(dataArray.get(j)) == 0.0){
					dataArray.remove(j);
					j--;
				}
				else{
					tempDistances.add(distances.D(dataArray.get(i), dataArray.get(j)));	
				}
			}
			distanceArray.add(tempDistances);
		}
		return distanceArray;
	}
	
	public static void Discard(int indexVa, int indexVb, int dist_ind_a, int dist_ind_b, ArrayList<ArrayList<Double>> dataArray, ArrayList<ArrayList<Double>> distanceArray, boolean flag){
		ArrayList<Double> distancesVa = new ArrayList<Double>();
		ArrayList<Double> distancesVb = new ArrayList<Double>();
		int K = 2;
		double averageA, averageB;
		
		for(int i = 0; i < distanceArray.get(dist_ind_a).size(); i++){
			if(distanceArray.get(dist_ind_a).get(i) >= 0.0)
				distancesVa.add(distanceArray.get(dist_ind_a).get(i));
		}
		for(int i = 0; i < distanceArray.get(dist_ind_b).size(); i++){
			if(distanceArray.get(dist_ind_b).get(i) >= 0.0)
				distancesVb.add(distanceArray.get(dist_ind_b).get(i));
		}
		Collections.sort(distancesVa);
		Collections.sort(distancesVb);
		
		do{
			averageA = 0.0;
			averageB = 0.0;
			if(K <= distancesVa.size()){
				for(int i = 0; i < K; i++)
					averageA = averageA + distancesVa.get(i);
				averageA = averageA / K;
			}
			else{
				for(int i = 0; i < distancesVa.size(); i++)
					averageA = averageA + distancesVa.get(i);
				averageA = averageA / distancesVa.size();
			}
			
			if(K <= distancesVb.size()){
				for(int i = 0; i < K; i++)
					averageB = averageB + distancesVb.get(i);
				averageB = averageB / K;
			}
			else{
				for(int i = 0; i < distancesVb.size(); i++)
					averageB = averageB + distancesVb.get(i);
				averageB = averageB / distancesVb.size();
			}
			if(Math.abs(averageA - averageB) > 1){
				if(averageA > averageB){
					if(flag == true){
						dataArray.remove(indexVa);
					}
					else{
						dataArray.remove(indexVb);
					}
				}
				else if(averageA < averageB){
					if(flag == true){
						dataArray.remove(indexVa);
					}
					else{
						dataArray.remove(indexVb);
					}
				}
				break;
			}
			else
				K++;
			
			if(K >= dataArray.size()){
				if(flag == true)
					dataArray.remove(indexVa);
				else
					dataArray.remove(indexVb);
			}
		}while(K < dataArray.size());
	}
}