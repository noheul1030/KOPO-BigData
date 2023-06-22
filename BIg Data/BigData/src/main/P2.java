package main;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.*;

public class P2 {
	public static void main(String[] args) {
		 String writeFileName = "lib/국가별메달획득현황test.xlsx"; 
		try {
			String file = "lib/국가별메달획득현황(하계동계).xlsx";
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("국가별메달획득현황(하계동계)");

			System.out.println();
			System.out.println("- 역대 올림픽 국가별 메달 획득 갯수 -");
			System.out.println();
			
			int MIN = Integer.MAX_VALUE;
			int MAX = Integer.MIN_VALUE;

			String[] Olympic = { "국가", Integer.toString(MIN), "국가", Integer.toString(MAX) };
			
			for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++) {
				XSSFRow rows = sheet.getRow(row);
				if (rows != null) {
					String value = "";
					int cells = rows.getPhysicalNumberOfCells();
					
					XSSFCell cell = rows.getCell(0);
					XSSFCell cell2 = rows.getCell(cells-1);
					
					if (cell != null) {
						switch (cell.getCellType()) {
						case NUMERIC:
							value = "  " + (int) cell.getNumericCellValue() + " : ";
							break;

						case STRING:
							value = "  " + cell.getStringCellValue() + " : ";
							break;

						case BLANK:
							value = "  " + cell.getBooleanCellValue() + " : ";
							break;

						case ERROR:
							value = "  " + cell.getErrorCellValue() + " : ";
							break;

						default:
							break;
						}
					}
					
					if (cell2 != null) {
						switch (cell2.getCellType()) {
						case NUMERIC:
							value += (int) cell2.getNumericCellValue() + " 개";
							break;
							
						case STRING:
							value += cell2.getStringCellValue() + "";
							break;
							
						case BLANK:
							value += cell2.getBooleanCellValue() + "";
							break;
							
						case ERROR:
							value += cell2.getErrorCellValue() + "";
							break;
							
						default:
							break;
						}					
					}
					System.out.print(value + "  ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//public class P2 {
//	public static void main(String[] args) {
//		try {
//			String file = "lib/국가별메달획득현황(하계동계).xlsx";		
//			FileInputStream fis = new FileInputStream(file);
//			XSSFWorkbook workbook = new XSSFWorkbook(fis);
//			XSSFSheet sheet = workbook.getSheet("국가별메달획득현황(하계동계)");
//			
//			for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++) {
//				XSSFRow rows = sheet.getRow(row);
//				if (rows != null) {
//					String value ="";
//					int cells = rows.getPhysicalNumberOfCells();
//					for (int column = 0; column <= cells; column++) {
//						XSSFCell cell = rows.getCell(column);
//						if (cell != null) {
//							switch (cell.getCellType()) {
//							case NUMERIC:
//								value = (int)cell.getNumericCellValue() + "";
//								break;
//								
//							case STRING:
//								value = cell.getStringCellValue() + "";
//								break;
//								
//							case BLANK:
//								value = cell.getBooleanCellValue() + "";
//								break;
//								
//							case ERROR:
//								value = cell.getErrorCellValue() + "";
//								break;
//								
//							default:
//								break;
//							}
//							System.out.print(value + "  ");
//						}
//					}
//				}
//				System.out.println();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
