package main;

import java.io.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVfiles {
	public static void main(String[] args) {
		String readFileName = "lib/����������繫��ǥ�ص�����.csv";
		String writeFileName = "lib/test.csv";
		
		CSVReader csvReader;
		
		try {
			csvReader = new CSVReader(new InputStreamReader(new FileInputStream(readFileName), "CP949"));
			String[] nextLine;
			while((nextLine= csvReader.readNext()) != null) {
				System.out.println(nextLine.length + " : " + String.join("|", nextLine));
			}
		}catch(Exception e) {
			System.out.println(e + "���� �߻�!");
			e.printStackTrace();
		}
		
		try {
			CSVWriter cw = new CSVWriter(new FileWriter(writeFileName));
			String[] data = {"abc","def", "ghi"};
			cw.writeNext(data);
			cw.close();
		}catch(Exception e) {
			System.out.println(e + "���� �߻�!");
			e.printStackTrace();
		}
	}

}
