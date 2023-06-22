package main;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.opencsv.CSVReader;

public class Test1 {
	public static void main(String[] args) {
		String readFileName = "lib/04월 서울시 교통량 조사자료(2023).csv";		
		
		CSVReader csvReader;
		int count = 1;
		
		Integer[] hour = new Integer[24];
		Integer[] hourMIN = new Integer[24];
		Integer[] hourMAX = new Integer[24];
		
		for(int i = 0;i <= 23;i++) {
			hour[i] = 0;
			hourMIN[i] = Integer.MAX_VALUE;
			hourMAX[i] = Integer.MIN_VALUE;
		}
		
		try {
			csvReader = new CSVReader(new InputStreamReader(new FileInputStream(readFileName), "CP949"));
			String[] nextLine;
			csvReader.readNext();
			while((nextLine= csvReader.readNext()) != null) {
				if(count == 61) {
					break;
				}else {
					count++;
					for(int i = 0;i <= 23;i++) {
						hour[i] += Integer.parseInt(nextLine[i+6]);
						
						if(hourMIN[i] > Integer.parseInt(nextLine[i+6])) {
							hourMIN[i] = Integer.parseInt(nextLine[i+6]);
						}
						if(hourMAX[i] < Integer.parseInt(nextLine[i+6])) {
							hourMAX[i] = Integer.parseInt(nextLine[i+6]);
						}
					}	
				}									
			}
			
			for (int i = 0; i < hour.length; i++) {
				System.out.printf("< %d 시 >\n",i);
				System.out.printf("  총: %d 대\t",hour[i]);	
				System.out.printf("  최소: %d 대\t",hourMIN[i]);
				System.out.printf("  최대: %d 대\t",hourMAX[i]);
				System.out.printf("  평균: %d 대\n\n",hour[i]/count);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
