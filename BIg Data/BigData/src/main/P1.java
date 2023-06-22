package main;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.opencsv.CSVReader;

public class P1 {

	public static void main(String[] args) {
		String readFileName = "lib/�������޴�ȹ����Ȳ(�ϰ赿��).csv";
		CSVReader csvReader;
		int count = 0;
		int GoldMIN = Integer.MAX_VALUE;
		int GoldMAX = Integer.MIN_VALUE;

		String[] SummerGold = { "����", Integer.toString(GoldMIN), "����", Integer.toString(GoldMAX) };
		String[] WinterGold = { "����", Integer.toString(GoldMIN), "����", Integer.toString(GoldMAX) };
		String[] TotalGold = { "����", Integer.toString(GoldMIN), "����", Integer.toString(GoldMAX) };
		
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

			System.out.printf("- �ϰ� �ø��� - \n\n �ݸ޴� �ּ� ȹ�� ���� : %s \n �ݸ޴� ����: %s �� \n �ݸ޴� �ִ� ȹ�� ���� : %s \n �ݸ޴� ����: %s ��\n\n �ϰ� �ø��� �ݸ޴� ���� : %d\n"
					,SummerGold[0],SummerGold[1],SummerGold[2],SummerGold[3],SummerTotal);
			System.out.println();
			System.out.printf("- ���� �ø��� - \n\n �ݸ޴� �ּ� ȹ�� ���� : %s \n �ݸ޴� ����: %s �� \n �ݸ޴� �ִ� ȹ�� ���� : %s \n �ݸ޴� ����: %s ��\n\n ���� �ø��� �ݸ޴� ���� : %d\n"
					,WinterGold[0],WinterGold[1],WinterGold[2],WinterGold[3],WinterTotal);
			System.out.println();
			System.out.printf("- �ø��� ���� - \n\n �ݸ޴� �ּ� ȹ�� ���� : %s \n �ݸ޴� ����: %s �� \n �ݸ޴� �ִ� ȹ�� ���� : %s \n �ݸ޴� ����: %s ��\n\n �ø��� �ݸ޴� ���� : %d\n"
					,TotalGold[0],TotalGold[1],TotalGold[2],TotalGold[3],Total);
		} catch (Exception e) {
			System.out.println(e + "���� �߻�!");
			e.printStackTrace();
		}

	}

}
