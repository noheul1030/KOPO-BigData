package Date05;

import java.util.Arrays;

public class P1 {

	public static void main(String[] args) {
		 double[] data = { 160, 165, 180, 170, 175, 172, 168, 123, 227 }; 

	      double percentile25 = calculatePercentile(data, 0.25);
	      double percentile50 = calculatePercentile(data, 0.50);
	      double percentile75 = calculatePercentile(data, 0.75);

	      double IQR = percentile75 - percentile25;

	      double min = percentile25 - 1.5 * IQR;
	      double max = percentile75 + 1.5 * IQR;

	      System.out.println("Q1: " + percentile25);
	      System.out.println("Q2: " + percentile50);
	      System.out.println("Q3: " + percentile75);

	      System.out.println("min: " + min);
	      System.out.println("max: " + max);
	}
	 public static double calculatePercentile(double[] data, double percentile) {
	      Arrays.sort(data); // 데이터 정렬
	      double position = percentile * (data.length - 1); // 위치 계산

	      int lowerIndex = (int) Math.floor(position);
	      int upperIndex = (int) Math.ceil(position);

	      if (lowerIndex == upperIndex) {
	         return data[lowerIndex];
	      } else {
	         double lowerValue = data[lowerIndex];
	         double upperValue = data[upperIndex];
	         return lowerValue + (position - lowerIndex) * (upperValue - lowerValue);
	      }
	   }
	}