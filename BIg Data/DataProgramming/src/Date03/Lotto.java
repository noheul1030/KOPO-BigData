package Date03;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Lotto {

	public static void main(String[] args) {
	    try {
	    	File file = new File("lib/로또.csv");
	    	lotto(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void lotto(File file) {
		try {
			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
			String readtxt; 
			
			HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();	
			
			String[] LottoNumber;
			int count = 0;
			
			while((readtxt = buffer.readLine()) != null) {	
				count++;
				LottoNumber = readtxt.split(",");
				for (int i = 0; i < 6; i++) {
					int key = Integer.parseInt(LottoNumber[i]);
					map.put(key,map.getOrDefault(key, 0)+1);
				}
			}
			
			System.out.println(" << 로또의 각 번호별 빈도수와 현 회차까지의 확률 >>");
			System.out.println();
			
			int min = 0;
			int max = 0;
			double maxValue = Double.MIN_VALUE;
			double minValue = Double.MAX_VALUE;
			
			for (Map.Entry<Integer, Integer> lotto : map.entrySet()) {
				int key = lotto.getKey();
				int value = lotto.getValue();
				
				if((double)count/value < minValue) {
					min = key;
					minValue = (double)count/value;
				}
				if((double)count/value > maxValue) {
					max = key;
					maxValue = (double)count/value;
				}
				System.out.println(String.format("%d 의 빈도수 : %d 개 \t 확률 : %.2f",key, value, (double)count/value));			
			}
			System.out.println();
			System.out.println("최대 확률 숫자 : " + max);
			System.out.println("최소 확률 숫자 : " + min);
						
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
