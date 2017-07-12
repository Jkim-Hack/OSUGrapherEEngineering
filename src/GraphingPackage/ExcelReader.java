package GraphingPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelReader 
{
	
	private SheetData data;
	private File fileName;
	
	

	
	
	
	public File getFileName()
	{
		return fileName;
		
	}
 
 
 
 
	public void setFileName(File fileName) 
	{
		this.fileName = fileName;
	}
 
 
 
 
	public SheetData getData() 
	{
		return data;
	}
 
 
 
 
	public void setData(SheetData data)
	{
		this.data = data;
	}
	
	public ExcelReader(File fileName) throws IOException
	{
		this.fileName = fileName;
		
		data = new SheetData();
		
		readData();
		
		
	}
 
 
	private void readData() throws IOException
	{
		//System.out.println(fileName.getPath());
		if(fileName == null)
		{
			System.out.println("Null Exception");
		}
		FileInputStream inputStream = new FileInputStream(new File(fileName.getPath()));
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(1);
		Iterator<Row> iterator = firstSheet.iterator();
		iterator.next();
		
		List<Double> container;
		
		
		while (iterator.hasNext()) 
		{
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			
			container = new ArrayList<Double>();
			
			
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				
				double cellContent = 0;
				switch(cell.getCellTypeEnum())
				{
				
					case STRING: cellContent = Double.parseDouble(cell.getStringCellValue());
					
					break;
					
					case NUMERIC: cellContent = (double)(cell.getNumericCellValue());
					break;
					
					
				
				
				}
				container.add(cellContent);
            }
           
			data.electrictyData.add(new Data(container));
		}
		
		workbook.close();
		inputStream.close();
	}
 
}
 
 
