//Zaki Chammaa
//6584233
//I declare Malek El-Baher as a buddy

import java.util.ArrayList;

public class distances{
	public static double var(ArrayList<Double> x){
		double variance = 0.0;
		double average = 0.0;
		for(int i = 0; i < x.size(); i++){
			average =  average + x.get(i);
		}
		average = average / x.size();
		for(int i = 0; i < x.size(); i++){
			variance = variance + ((x.get(i) - average) * (x.get(i) - average));
		}
		variance = variance / x.size();
		return variance;
	}
	public static double cov(ArrayList<Double> x, ArrayList<Double> y){
		double covariance = 0.0;
		double xAverage = 0.0;
		double yAverage = 0.0;
		
		for(int i = 0; i < x.size(); i++){
			xAverage =  xAverage + x.get(i);
		}
		xAverage = xAverage / x.size();
		
		for(int i = 0; i < y.size(); i++){
			yAverage =  yAverage + y.get(i);
		}
		yAverage = yAverage / y.size();
		
		for(int i = 0; i < x.size(); i++){
			covariance = covariance + ((x.get(i) - xAverage) * (y.get(i) - yAverage));
		}
		covariance = covariance / x.size();
		return covariance;
	}
	public static double p(ArrayList<Double> x, ArrayList<Double> y){
		return ((cov(x, y)) / (Math.sqrt(var(x)*var(y))));
	}
	public static double D(ArrayList<Double> x, ArrayList<Double> y){
		return(0.5 * (Math.pow(var(x) + var(y), 2.0) - Math.sqrt(Math.pow(var(x) + var(y), 2.0) - 4 * var(x) * var(y) * (1 - Math.pow(p(x, y), 2.0)))));
	}
	public static double euclidean(ArrayList<Double> x, ArrayList<Double> y){
		double distance = 0.0;
		for(int i = 0; i < x.size(); i++){
			distance = distance + Math.pow(x.get(i) - y.get(i), 2);
		}
		distance = Math.sqrt(distance);
		return distance;
	}
}