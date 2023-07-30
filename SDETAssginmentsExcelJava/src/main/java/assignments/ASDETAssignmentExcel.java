package assignments;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ASDETAssignmentExcel {

    FileInputStream excelFileReader = null;
    XSSFWorkbook excelWorkbook = null;
    XSSFSheet excelSheet = null;
    Row row = null;
    Cell cell = null;
    private static final String excelPath = System.getProperty("user.dir")+"\\testdata\\Employee_Details.xlsx";
    public static String SHEET_NAME_EMPLOYEE= "Employee";
    public static void main(String[] args) {
        ASDETAssignmentExcel readExcel = new ASDETAssignmentExcel();
        Map<String, String> employeeData = readExcel.getDataFromExcelSheet(excelPath, SHEET_NAME_EMPLOYEE);
        readExcel.printExcelData(employeeData);
    }

    public void connectExcel(String filePath, String sheetName) {
        File excelDataFile = new File(filePath);
        try {
            if (excelDataFile.exists()) {
                excelFileReader = new FileInputStream(excelDataFile);
                excelWorkbook = new XSSFWorkbook(excelFileReader);
                excelSheet = excelWorkbook.getSheet(sheetName);
                if(excelSheet == null)
                    System.out.println("SheetName doesn't exist " + sheetName);
            }else{
                System.out.println("Excel file Not Found " + filePath);
            }
        }catch(Exception e ){
            System.out.println("Exception while connecting Excel " + e.getStackTrace());
        }
    }
    public Map<String, String>  getDataFromExcelSheet(String filePath, String sheetName){

        connectExcel(filePath, sheetName);

        Map<String, String>  mapExcelData = new LinkedHashMap<>();
        Iterator<Row> rowIterator =  excelSheet.iterator();
        if(rowIterator == null)
            return null;
        int keyIndex = 0 ; int valueIndex = 1;
        String key;
        String value = "";
        while(rowIterator.hasNext())
        {
            row = rowIterator.next();
            if(row.getCell(keyIndex) != null && row.getCell(valueIndex) != null)
            {
                key = row.getCell(keyIndex).getStringCellValue();
                cell = row.getCell(valueIndex);
                switch(cell.getCellType())
                {
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        value = cell.toString();
                        break;
                    default:
                        break;
                }
                mapExcelData.put(key,value);
            }
        }
        return mapExcelData;
    }
    public void printExcelData(Map<String, String> map){
        System.out.println("***************Employee Details****************");
        for(String key: map.keySet())
        {
            String value = map.get(key);
            System.out.println(key  + " : " + value);
        }



    }



}
