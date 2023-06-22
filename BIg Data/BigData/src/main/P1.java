package main;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.opencsv.CSVReader;

public class P1 {

	public static void main(String[] args) {
		String readFileName = "lib/국가별메달획득현황(하계동계).csv";
		CSVReader csvReader;
		int count = 0;
		int GoldMIN = Integer.MAX_VALUE;
		int GoldMAX = Integer.MIN_VALUE;

		String[] SummerGold = { "국가", Integer.toString(GoldMIN), "국가", Integer.toString(GoldMAX) };
		String[] WinterGold = { "국가", Integer.toString(GoldMIN), "국가", Integer.toString(GoldMAX) };
		String[] TotalGold = { "국가", Integer.toString(GoldMIN), "국가", Integer.toString(GoldMAX) };
		
		int SummerTotal = 0;
		int WinterTotal = 0;
		int Total = 0;
		

		try {
			csvReader = new CSVReader(new InputStreamReader(new FileInputStream(readFileName), "CP949"));
			String[] nextLine;
			csvReader.readNext();
			while ((nextLine = csvReader.readNext()) != null) {
				count++;
				if (count == 153) {
					break;
				} else {
					SummerTotal += Integer.parseInt(nextLine[2]);
					WinterTotal += Integer.parseInt(nextLine[7]);
					Total += Integer.parseInt(nextLine[12]);

					if (Integer.parseInt(SummerGold[1]) > Integer.parseInt(nextLine[2])) {
						SummerGold[0] = nextLine[0];
						SummerGold[1] = nextLine[2];
					}
					if (Integer.parseInt(SummerGold[3]) < Integer.parseInt(nextLine[2])) {
						SummerGold[2] = nextLine[0];
						SummerGold[3] = nextLine[2];
					}
					if (Integer.parseInt(WinterGold[1]) > Integer.parseInt(nextLine[7])) {
						WinterGold[0] = nextLine[0];
						WinterGold[1] = nextLine[7];
					}
					if (Integer.parseInt(WinterGold[3]) < Integer.parseInt(nextLine[7])) {
						WinterGold[2] = nextLine[0];
						WinterGold[3] = nextLine[7];
					}
					if (Integer.parseInt(TotalGold[1]) > Integer.parseInt(nextLine[12])) {
						TotalGold[0] = nextLine[0];
						TotalGold[1] = nextLine[12];
					}
					if (Integer.parseInt(TotalGold[3]) < Integer.parseInt(nextLine[12])) {
						TotalGold[2] = nextLine[0];
						TotalGold[3] = nextLine[12];
					}
				}
			}

			System.out.printf("- 하계 올림픽 - \n\n 금메달 최소 획득 국가 : %s \n 금메달 갯수: %s 개 \n 금메달 최대 획득 국가 : %s \n 금메달 갯수: %s 개\n\n 하계 올림픽 금메달 총합 : %d\n"
					,SummerGold[0],SummerGold[1],SummerGold[2],SummerGold[3],SummerTotal);
			System.out.println();
			System.out.printf("- 동계 올림픽 - \n\n 금메달 최소 획득 국가 : %s \n 금메달 갯수: %s 개 \n 금메달 최대 획득 국가 : %s \n 금메달 갯수: %s 개\n\n 동계 올림픽 금메달 총합 : %d\n"
					,WinterGold[0],WinterGold[1],WinterGold[2],WinterGold[3],WinterTotal);
			System.out.println();
			System.out.printf("- 올림픽 총합 - \n\n 금메달 최소 획득 국가 : %s \n 금메달 갯수: %s 개 \n 금메달 최대 획득 국가 : %s \n 금메달 갯수: %s 개\n\n 올림픽 금메달 총합 : %d\n"
					,TotalGold[0],TotalGold[1],TotalGold[2],TotalGold[3],Total);
		} catch (Exception e) {
			System.out.println(e + "에러 발생!");
			e.printStackTrace();
		}

	}

}
