package Date03;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math4.legacy.stat.correlation.PearsonsCorrelation;

public class Test2 {

	public static void main(String[] args) throws IOException{
	      File file = new File("lib/아파트매매수정1.csv");
	      BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
	      String[] title = buffer.readLine().split(",");
	      
	      System.out.println("<< 2010년 ~ 2023년 분석 >>");
	      List<double[]> PrintList = new ArrayList<>();
	      PrintList = CSVReader(file,2010,2023);
	      for(int i = 0; i < PrintList.size(); i++) {
	         for(int j =0; j < PrintList.size(); j++) {
	            double correlationbc = new PearsonsCorrelation().correlation(PrintList.get(i), PrintList.get(j));
	            System.out.println(title[i+1] +" / " + title[j+1]+ " : " + correlationbc);
	         }
	      }
	      	      
	      System.out.println("\n\n<< 2013년 ~ 2021년 분석 >>");
	      List<double[]> PrintList2 = new ArrayList<>();
	      PrintList2 = CSVReader(file,2013,2021);
	      for(int i = 0; i < PrintList2.size(); i++) {
	         for(int j =0; j < PrintList2.size(); j++) {
	            double correlationbc = new PearsonsCorrelation().correlation(PrintList2.get(i), PrintList2.get(j));
	            System.out.println(title[i+1] +" / " + title[j+1]+" : " + correlationbc);
	         }
	      }
	      	      
	      System.out.println("\n\n<< 2022년 ~ 2023년 분석 >>");
	      List<double[]> PrintList3 = new ArrayList<>();
	      PrintList3 = CSVReader(file,2022,2023);
	      for(int i = 0; i < PrintList3.size(); i++) {
	         for(int j =0; j < PrintList3.size(); j++) {
	            double correlationbc = new PearsonsCorrelation().correlation(PrintList3.get(i), PrintList3.get(j));
	            System.out.println(title[i+1] +" / " + title[j+1]+" : " + correlationbc);
	         }
	      }
	      
	      buffer.close();
	   }
	   
	   public static List<double[]> CSVReader(File file, int startyear, int endyear) throws IOException{
	      BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
	      String readtxt; 
	      readtxt = buffer.readLine();
	      List<double[]> doublelist = new ArrayList<>();

	      List<Double> Blist = new ArrayList<Double>();
	      List<Double> Clist = new ArrayList<Double>();
	      List<Double> Dlist = new ArrayList<Double>();
	      List<Double> Elist = new ArrayList<Double>();
	      List<Double> Flist = new ArrayList<Double>();
	      List<Double> Glist = new ArrayList<Double>();
	      List<Double> Hlist = new ArrayList<Double>();
	      List<Double> Ilist = new ArrayList<Double>();
	      List<Double> Jlist = new ArrayList<Double>();
	      List<Double> Klist = new ArrayList<Double>();
	      System.out.println();
	      while((readtxt=buffer.readLine()) != null) {
	         String[] field = readtxt.split(",");
	         if( ((startyear * 100) < Integer.parseInt(field[0])) && (Integer.parseInt(field[0]) < ((endyear + 1) * 100))) {
	            Blist.add(Double.parseDouble(field[1]));
	            Clist.add(Double.parseDouble(field[2]));
	            Dlist.add(Double.parseDouble(field[3]));
	            Elist.add(Double.parseDouble(field[4]));
	            Flist.add(Double.parseDouble(field[5]));
	            Glist.add(Double.parseDouble(field[6]));
	            Hlist.add(Double.parseDouble(field[7]));
	            Ilist.add(Double.parseDouble(field[8]));
	            Jlist.add(Double.parseDouble(field[9]));
	            Klist.add(Double.parseDouble(field[10]));
	         }
	      }
	      double[] ba = new double[Blist.size()];
	      double[] ca = new double[Clist.size()];
	      double[] da = new double[Dlist.size()];
	      double[] ea = new double[Elist.size()];
	      double[] fa = new double[Flist.size()];
	      double[] ga = new double[Glist.size()];
	      double[] ha = new double[Hlist.size()];
	      double[] ia = new double[Ilist.size()];
	      double[] ja = new double[Jlist.size()];
	      double[] ka = new double[Klist.size()];
	      for(int i = 0; i < Blist.size(); i++) {
	         ba[i] = Blist.get(i);
	         ca[i] = Clist.get(i);
	         da[i] = Dlist.get(i);
	         ea[i] = Elist.get(i);
	         fa[i] = Flist.get(i);
	         ga[i] = Glist.get(i);
	         ha[i] = Hlist.get(i);
	         ia[i] = Ilist.get(i);
	         ja[i] = Jlist.get(i);
	         ka[i] = Klist.get(i);
	      }
	      doublelist.add(ba); doublelist.add(ca); doublelist.add(da); doublelist.add(ea);
	      doublelist.add(fa); doublelist.add(ga); doublelist.add(ha); doublelist.add(ia);
	      doublelist.add(ja); doublelist.add(ka);
	      
	      buffer.close();
	      return doublelist;
	   }

	}