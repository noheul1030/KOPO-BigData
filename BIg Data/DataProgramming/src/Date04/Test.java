/*package Date04;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		File file = new File("lib/따릉이.csv"); // 파일 경로를 적절히 수정하세요.

		try {
			BufferedReader buffer = new BufferedReader(
					new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
			String readtxt;

			List<String[]> cencerList = new ArrayList<String[]>();
			String[] list;
			buffer.readLine();

			while ((readtxt = buffer.readLine()) != null) {
				list = readtxt.split(",");
				cencerList.add(list);
			}

			buffer.close();

			// 데이터 추출
			double[][] data = new double[cencerList.size()][3];
			double[] target = new double[cencerList.size()];
			for (int i = 0; i < cencerList.size(); i++) {
				String[] row = cencerList.get(i);
				for (int j = 0; j < row.length; j++) {
					if (j != 0 && j != 1) {
						data[i][j] = Double.parseDouble(row[j]);
					}
				}
				target[i] = Double.parseDouble(row[1]); // 대여건수를 타겟 변수로 설정
			}

			 // 회귀분석 수행
            OLSMultipleLinearRegression multiRegression = new OLSMultipleLinearRegression();
            multiRegression.newSampleData(target, data);
            double[] coefficients = multiRegression.estimateRegressionParameters();

            double N = 50.0;
            System.out.println("Intercept = " + coefficients[0]);
            System.out.println("Prediction for " + N + "= " + multiRegression.predict(new double[]{N}));
            System.out.println("R square " + multiRegression.calculateRSquared());
            System.out.println("Significance Level " + multiRegression.calculateSignificance());

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
*/
package Date04;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.opencsv.CSVReader;

public class Test {

   private static int coefficientCount = 3;
   private static int lineCount = 407;
   private static String readFileName = "lib/따릉이.csv";
   private static double significanceLevel = 0.05; // 유의 수준 설정
    
   public static void main(String[] args) {
      CSVReader csvReader;
      double[][] xArray = new double[lineCount][coefficientCount];
      double[] yArray = new double[lineCount];

      try {
         csvReader = new CSVReader(new InputStreamReader(new FileInputStream(readFileName), "CP949"));

         String[] nextLine;
         if ((nextLine = csvReader.readNext()) == null) {
            System.out.println("파일을 찾지 못 했습니다.");
         }
         

         // 파일 읽어서 필요한 값 구하기
         int count = 0;
         while ((nextLine = csvReader.readNext()) != null) {

            for (int i = 0; i < coefficientCount; i++) {
               xArray[count][i] = Double.parseDouble(nextLine[i + 2]);
            }
            yArray[count] = Double.parseDouble(nextLine[1]);

            count++;
         }
         csvReader.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      multipleRegressionTest(xArray, yArray);

   }

  

   public static void multipleRegressionTest(double[][] xArray, double[] yArray) {
      OLSMultipleLinearRegression multipleLinearRegression = new OLSMultipleLinearRegression();
      multipleLinearRegression.newSampleData(yArray, xArray);
      double[] coefficients = multipleLinearRegression.estimateRegressionParameters(); // 모든 계수들의 집합

      System.out.println(" 1.coefficient"+"\n");
      System.out.println(" y-intercept = " + coefficients[0]); // y(종속변수)절편
      System.out.println(" Storage (GB) = " + coefficients[1]);
      System.out.println(" RAM (GB) = " + coefficients[2]);
      System.out.println(" Screen Size (inches) = " + coefficients[3]);
      System.out.println("\n"+" 2.R square = " + multipleLinearRegression.calculateRSquared()+"\n"); // 결정계수
      System.out.println("\n"+" 3.Adj R square = " + multipleLinearRegression.calculateAdjustedRSquared()+"\n"); // 조정된 결정계수

      double[] standardErrors = multipleLinearRegression.estimateRegressionParametersStandardErrors();   // 표준오차
      System.out.println("\n"+" 4.standardErrors"+"\n");
      System.out.println(" y-intercept = " + standardErrors[0]);
      System.out.println(" Storage (GB) = " + standardErrors[1]);
      System.out.println(" RAM (GB) = " + standardErrors[2]);
      System.out.println(" Screen Size (inches) = " + standardErrors[3]);
      
      
       // t-통계량과 유의 수준을 이용한 유의성 계산
        TDistribution tDistribution = new TDistribution(xArray.length - xArray[0].length);
        double[] tValues = new double[standardErrors.length];
        double[] pValues = new double[standardErrors.length];
        for (int i = 0; i < standardErrors.length; i++) {
            tValues[i] = multipleLinearRegression.estimateRegressionParameters()[i] / standardErrors[i];
            pValues[i] = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(tValues[i])));
        }
        
      System.out.println("\n"+" 5.t-value"+"\n");
      System.out.println(" y-intercept = " + tValues[0]);
      System.out.println(" Storage (GB) = " + tValues[1]);
      System.out.println(" RAM (GB) = " + tValues[2]);
      System.out.println(" Screen Size (inches) = " + tValues[3]);

      System.out.println("\n"+" 6.P-value "+"\n");
      System.out.println(" y-intercept = " + pValues[0]);
      System.out.println(" Storage (GB) = " + pValues[1]);
      System.out.println(" RAM (GB) = " + pValues[2]);
      System.out.println(" Screen Size (inches) = " + pValues[3]);
      
        // 유의 수준과 비교하여 회귀 계수의 유의성 판단
      System.out.println("\n"+" 7. significance result"+"\n");
        for (int i = 0; i < pValues.length; i++) {
            if (pValues[i] < 0.05) {
                System.out.println("Regression coefficient " + (i + 1) + " is significant.");
            } else {
                System.out.println("Regression coefficient " + (i + 1) + " is not significant.");
            }
        }             
   }
}