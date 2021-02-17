import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;

/**
 * Класс ExReadExcelData
 */
public class ReadExcelData {

	/**
	 * @param args
	 * @throws IOException
	 */
// выбрать столбец для чтения данных (для проверки/тестировниая)
//	final int code = 0;    // код (строка)
//	final int gpoupID = 2;   // ID группы  (число)
	final int codeGroup = 3;   //+1 код группы  (число)
	//	final int group = 4;   // название группы (строка)
	final int dateStart = 5;   // +3 дата начала (дата)
	final int timeStart = 6;   // +4 время начала (время)
	final int dateEnd = 7;   // +5 дата завершения (дата)
	final int timeEnd = 8;   // +6 время завершения (время)
	final int classID = 9;   // ID аудитории (число)
	final int clasRum = 10;   // +7 №аудитории или вариант (ОнЛайн) (число/строка)
	final int typeLearn = 11;   // +8 тип занятия (строка)
	final int codeDirectionProgramm = 12;   // код-направление-программа (число-строка)
	//	final int courseID = 13;   // +2.1 ID курса (число) -
	final int discipline = 14;   // +2 предмет/дисциплина/программа (число/строка)
	final int period = 15;   // период (число)
	final int teacherID = 16;   // ID преподавателя (число)
	final int teacher = 17;   // +9 преподаватель (строка)
	final int periodDay = 18;   // период дней(число)
	final int academHour = 19;   // академических часов (число)
	final int academRecord = 20;   // академических записей (число)


	String fileName = "D:\\REPOSITORIES-2\\Primer_raspisania.xlsx";
//	String fileName = "fileToRead";
//	private LinkedList<String> columnStrData;

	/**
	 * * имя столбцов д/л group: groupid, groupcode,
	 * *    programm, datestart, timestart, dateend,
	 * *    timeend, classrum, typelessons
	 */

	static int columnIndex = 6;
	static int columnInt = 1;

	// основной метод класса для проверки считывания данных с таблицы
	public static void main(String[] args) throws IOException {
		ReadExcelData exr = new ReadExcelData();
		exr.getString(0);
//		exr.buildingTable();
//		exr.getDate(columnIndex);
//		exr.getTime(columnIndex);
	}

	private java.sql.Date columndataDateSql;
	List<java.sql.Date> columnListDateSql;
	private List<String> columndataStr;

	/**
	 * метод для построения таблицы из прчитанных данных и просмотра данных
	 * Вопросы:
	 * 1. формат даты считывается как ссылка
	 */

	/**
	 * метод должен получить определённые номера колонок, вызвать метод, который обработает тип ячейки
	 * и вернуть считанные данные
	 */
	public LinkedList<java.sql.Date> getDate(int columnIndex) {
		try {
			File f = new File(fileName);
			try (FileInputStream ios = new FileInputStream(f)) {
				XSSFWorkbook workbook = new XSSFWorkbook(ios);
				XSSFSheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				columnListDateSql = new LinkedList<>();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();

					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
							if (cell.getColumnIndex() == columnIndex) {// соответствие индекса столбца

								Date date = cell.getDateCellValue();
								columndataDateSql = new java.sql.Date(date.getTime());
								columnListDateSql.add(columndataDateSql);
							}
						}
					}
				}
			}
		}
//			ios.close();
		/*			просмотр прочитанного			 */
//			Iterator it = columndataDate.iterator();
//			while (it.hasNext()) {
//				System.out.println(it.next());
//			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (LinkedList<java.sql.Date>) columnListDateSql;
	}

	public List<String> getString(int columnIndex) {

		try {
			File f = new File(fileName);
			try (FileInputStream ios = new FileInputStream(f)) {
				XSSFWorkbook workbook = new XSSFWorkbook(ios);
				XSSFSheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				columndataStr = new LinkedList<>();

				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
							if (cell.getColumnIndex() == columnIndex) {// соответствие индекса столбца
								columndataStr.add(cell.getStringCellValue());
							}
						}
					}
				}
			}
			/*			просмотр прочитанного			 */
			Iterator it = columndataStr.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columndataStr;
	}


//	public void buildingTable() {
//		ExReadExcelColums exr = new ExReadExcelColums();
//
//		columnIndex = codeGroup;
//		List<?> colCodeGroup = exr.getDataStringDate(columnIndex);
//
//		columnIndex = discipline;
//		List<?> colProgram = exr.getDataStringDate(columnIndex);
//
//		columnIndex = dateStart;
//		List<?> colDataStart = exr.getDataStringDate(columnIndex);
////		String val = "dd.MM.yyyy";
////		SimpleDateFormat sdfDate = new SimpleDateFormat(val);
//
//		Iterator itCode = colCodeGroup.iterator();
//		Iterator itProg = colProgram.iterator();
//		Iterator itDateSt = colDataStart.iterator();
//
//		while (itCode.hasNext() &&
//			itProg.hasNext() &&
//			itDateSt.hasNext()) {
//			System.out.println(itCode.next() +
//				"   " + itProg.next() + "     " + itDateSt);
//		}
//	}
}
