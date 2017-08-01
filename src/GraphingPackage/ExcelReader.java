package GraphingPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelReader 
{
	
	private SheetData data;
	private File fileName;
	protected MainMenu Cycle1;
	protected MainMenu Cycle2;
	protected MainMenu Cycle3;

	public MainMenu getCycle1() {
		return Cycle1;
	}

	public void setCycle1(MainMenu cycle1) {
		Cycle1 = cycle1;
	}

	public MainMenu getCycle2() {
		return Cycle2;
	}

	public void setCycle2(MainMenu cycle2) {
		Cycle2 = cycle2;
	}

	public MainMenu getCycle3() {
		return Cycle3;
	}

	public void setCycle3(MainMenu cycle3) {
		Cycle3 = cycle3;
	}
	
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
 
 

	@SuppressWarnings("incomplete-switch")
	private void readData() throws IOException
	{
		//System.out.println(fileName.getPath());

		
		XSSFWorkbook workbook = new XSSFWorkbook(fileName.getPath());
		Sheet firstSheet = workbook.getSheetAt(1);
		Sheet secondSheet = workbook.getSheetAt(2);
		Iterator<Row> iterator = firstSheet.iterator();
		iterator.next();
		
		Iterator<Row> iterator2 = secondSheet.iterator();
		iterator2.next();
		
		List<Double> container;
		List<Double> container1;
		
		System.out.println(Cycle1.getCycle1());
		System.out.println(Cycle2.getCycle2());
		System.out.println(Cycle3.getCycle3());
		//use for loop here instead of while
		
		
		while (iterator.hasNext()) 
		{
			Row nextRow = iterator.next();
			
			container = new ArrayList<Double>();
			
			boolean isCycle = false;
			
			for(int i = 5; i <= 12; i++)
			{
				Cell currentCell = nextRow.getCell(i);
				
				double cellContent = 0;
				
				double cycleCell = nextRow.getCell(5).getNumericCellValue();
				
				switch(currentCell.getCellTypeEnum())
				{
					case NUMERIC: cellContent = (double)(currentCell.getNumericCellValue());
					break;
				}
				
				if(cycleCell == Cycle1.getCycle1() || cycleCell == Cycle2.getCycle2() || cycleCell == Cycle3.getCycle3())
				{
					isCycle = true;
					container.add(cellContent);
				}
					
				
            }
			
			
           
			if(isCycle)
			{	
				data.electrictyData.add(new Data(container));
			}
			
			
		}
		
		
		while (iterator2.hasNext()) 
		{
			Row nextRow = iterator2.next();
			
			container1 = new ArrayList<Double>();
			
			
			
			for(int i = 0; i <= 14; i++)
			{
				Cell currentCell = nextRow.getCell(i);
				
				double cellContent1 = 0;
				
				switch(currentCell.getCellTypeEnum())
				{
					case NUMERIC: cellContent1 = (double)(currentCell.getNumericCellValue());
					break;
				}
				
					container1.add(cellContent1);
				}
					
				data.electrictyData1.add(new StatData(container1));
			
            }
			
		workbook.close();
		}
		
	
		//inputStream.close();
	}
 

 
 
