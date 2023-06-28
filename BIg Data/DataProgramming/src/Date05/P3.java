package Date05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P3 {

	public static void main(String[] args) {
		File file = new File("lib/따릉이.csv");
		IQR(file);  
	}
	
	public static void IQR(File file) {
		BufferedReader buffer;
		try {
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
			List<Integer> list = new ArrayList<Integer>();
			
			while(buffer.readLine() != null) {
				String[] text = buffer.readLine().split(",");
				for(int i = 0; i< text.length;i++) {
					if(i == 1) {
					list.add(Integer.parseInt(text[i]));
					}
				}				
			}
			
			Collections.sort(list);
			int indexQ1 = (int) Math.ceil(list.size() * 0.25) - 1;
			double q1 = list.get(indexQ1);
			int indexQ2 = (int) Math.ceil(list.size() * 0.5) - 1;			
			double q2 = list.get(indexQ2);
			int indexQ3 = (int) Math.ceil(list.size() * 0.75) - 1;
			double q3 = list.get(indexQ3);

			System.out.println("Q1: " + q1);
			System.out.println("Q2: " + q2);
			System.out.println("Q3: " + q3);
			System.out.println("IQR(Q3-Q1): " + (q3-q1));
			System.out.println("Q1-1.5*IQR: " + (q1-1.5*(q3-q1)));
			System.out.println("Q3+1.5*IQR: " + (q3+1.5*(q3-q1)));


			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
 