package Date03;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math4.legacy.stat.correlation.PearsonsCorrelation;

import com.opencsv.CSVReader;

public class Test {

	public static void main(String[] args) {
		
		String readFileName = "lib/아파트매매수정.csv";
		
		CSVReader csvReader;
		int count = -1;
		
		List<String> alist = new ArrayList<String>();
		List<Double> blist = new ArrayList<Double>();
		List<Double> clist = new ArrayList<Double>();
		List<Double> dlist = new ArrayList<Double>();
		List<Double> elist = new ArrayList<Double>();
		List<Double> flist = new ArrayList<Double>();
		List<Double> glist = new ArrayList<Double>();
		List<Double> hlist = new ArrayList<Double>();
		List<Double> ilist = new ArrayList<Double>();
		List<Double> jlist = new ArrayList<Double>();
		List<Double> klist = new ArrayList<Double>();
		
		String[] title;
		
		try {
			csvReader = new CSVReader(new InputStreamReader(new FileInputStream(readFileName), "CP949"));
			String[] nextLine;
			
			while((nextLine = csvReader.readNext()) != null) {
				if(count == -1) {
					title =  nextLine;
				}else {
				alist.add(nextLine[0]);
				blist.add(Double.parseDouble(nextLine[1]));
				clist.add(Double.parseDouble(nextLine[2]));
				dlist.add(Double.parseDouble(nextLine[3]));
				elist.add(Double.parseDouble(nextLine[4]));
				flist.add(Double.parseDouble(nextLine[5]));
				glist.add(Double.parseDouble(nextLine[6]));
				hlist.add(Double.parseDouble(nextLine[7]));
				ilist.add(Double.parseDouble(nextLine[8]));
				jlist.add(Double.parseDouble(nextLine[9]));
				klist.add(Double.parseDouble(nextLine[10]));
				}
				count ++;
			}
			String[] aa = new String[alist.size()];
		      double[] ba = new double[blist.size()];
		      double[] ca = new double[blist.size()];
		      double[] da = new double[blist.size()];
		      double[] ea = new double[blist.size()];
		      double[] fa = new double[blist.size()];
		      double[] ga = new double[blist.size()];
		      double[] ha = new double[blist.size()];
		      double[] ia = new double[blist.size()];
		      double[] ja = new double[blist.size()];
		      double[] ka = new double[blist.size()];
		      for(int i = 0; i < blist.size(); i++) {
		         aa[i] = alist.get(i);
		         ba[i] = blist.get(i);
		         ca[i] = clist.get(i);
		         da[i] = dlist.get(i);
		         ea[i] = elist.get(i);
		         fa[i] = flist.get(i);
		         ga[i] = glist.get(i);
		         ha[i] = hlist.get(i);
		         ia[i] = ilist.get(i);
		         ja[i] = jlist.get(i);
		         ka[i] = klist.get(i);
		      }
		      
		      List<double[]> aLs = new ArrayList<double[]>();
		      aLs.add(ba); aLs.add(ca); aLs.add(da); aLs.add(ea); aLs.add(fa); aLs.add(ga);
		      aLs.add(ha); aLs.add(ia); aLs.add(ja); aLs.add(ka);
		      System.out.println("2010년 01월 부터 2023년 04월까지의 상관관계 분석");
		      for(int i = 0; i<10; i++) {
		         for(int j =0; j<10; j++) {
		            double correlationbc = new PearsonsCorrelation().correlation(aLs.get(i), aLs.get(j));
		            System.out.println(nextLine[i+1] +"\t"+ "   " + nextLine[j+1]+"   \t" + "   의 상관관계분석 : " + correlationbc);
		         }
		      }


		}catch(Exception e) {
			System.out.println(e + "에러 발생!");
			e.printStackTrace();
		}
		
		
		// double[] x = { 1, 2, 3, 4, 5 };
//		double[] x = { 78.6, 78.7, 78.6, 77.8, 77.3, 77, 76.6, 76.5, 77.1, 77.6, 78.3, 79.2, 80.4, 81.8, 82.8, 83.2,
//				83.5, 83.8, 84.2, 84.9, 85.5, 85.4, 85, 84.3, 85.1, 85.2, 85, 84.7, 84.4, 84.1, 83.4, 83.3, 83.3, 83.3,
//				82.9, 82, 83, 83, 83.5, 83.8, 83.9, 83.7, 84.3, 84.6, 85.2, 85.7, 85.7, 85.7, 86.3, 86.8, 87.4, 87.4,
//				87.4, 87.4, 87.6, 88.2, 88.8, 89.4, 89.7, 89.9, 90.5, 91.1, 92, 92.7, 93.3, 93.8, 94.7, 95.4, 96, 96.4,
//				96.4, 95.8, 95.7, 95.9, 95.7, 95.7, 95.7, 96, 96.6, 97.2, 98.1, 98.8, 98.9, 98.6, 98.3, 98.5, 98.7,
//				98.6, 98.9, 99.4, 100.1, 100.2, 100.3, 100.3, 100, 99.6, 99.8, 100.1, 100.2, 100.2, 99.9, 99.7, 99.6,
//				100.8, 102.2, 102.4, 101.6, 100.8, 100.3, 99.9, 99.2, 99, 98.8, 99.1, 99.6, 99.9, 100.3, 100.8, 101.7,
//				102.7, 103.4, 104.7, 105.5, 105.5, 106.3, 108.3, 110.7, 112.1, 113, 114.5, 117.1, 120, 123, 125.2,
//				126.8, 128.5, 130.7, 133.6, 136.8, 140, 142.6, 144.1, 143.1, 141.7, 140.9, 140.5, 141, 141.7, 140.3,
//				139.5, 135.8, 132.9, 130, 125.6, 120.5, 117.3, 116.4, 117.6, 118.9, 119.9 };
//		double[] y = { 90.6, 90.5, 89.8, 88, 86.7, 85.9, 84.9, 84.3, 84.8, 84.9, 85.5, 86.2, 87.2, 88.2, 88.2, 87.7,
//				87.3, 86.8, 86.6, 86.8, 86.8, 86.4, 85.5, 84.8, 84.8, 84.5, 84, 83.2, 82.8, 82.2, 81.2, 80.7, 80.4,
//				80.2, 79.8, 79, 79.2, 79.3, 79.7, 80, 80.3, 80, 79.9, 80.4, 81, 81.5, 81.4, 81.3, 81.8, 82.5, 83, 82.9,
//				82.8, 82.7, 82.9, 83.4, 84.1, 84.6, 84.8, 84.9, 85.4, 86, 86.9, 87.7, 88.4, 88.9, 89.9, 90.6, 91.3,
//				91.7, 91.9, 91.4, 91.4, 91.5, 91.6, 91.8, 92.2, 92.8, 93.7, 94.7, 95.6, 96.5, 96.6, 96.2, 95.9, 95.9,
//				96.3, 96.4, 97, 98.1, 99.3, 99.7, 99.8, 100.2, 100, 99.9, 100.7, 101.5, 102, 102.4, 102.4, 102.5, 103.1,
//				105.5, 108.3, 108.8, 107.8, 107, 106.2, 105.6, 104.9, 104.8, 105, 105.9, 106.9, 107.7, 108.3, 109.2,
//				110.3, 111.5, 112.6, 114.7, 116.4, 116.3, 117.4, 120.5, 124.6, 126.9, 128.1, 129.7, 132.3, 135.4, 140.1,
//				143.4, 146, 148.4, 152, 156.6, 161.5, 166.5, 170.2, 171.9, 170.2, 167.9, 166.2, 165.5, 166.5, 167.2,
//				165.1, 163.6, 157.6, 153.1, 148.2, 141.3, 134.2, 130, 129.3, 131.5, 133.6, 135.3 };
//		double[] y2 = { -10, -20, -30, -40, -50 };
//
//		double correlation = new PearsonsCorrelation().correlation(y, x);
//		System.out.println(correlation);

		//double correlation2 = new PearsonsCorrelation().correlation(y2, x);
		//System.out.println(correlation2);
	}

	}
