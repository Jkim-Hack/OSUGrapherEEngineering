package GraphingPackage;

import java.io.File;
import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.shape.Rectangle;

public class Graph
{

	protected File fileName;
	protected double DataMass;
	protected double mass;
	protected String title;
	protected ExcelReader excelReader;
	protected List<Data> electricityData;
	protected List<StatData> electricityData1;
	protected double cycleOne;
	protected double cycleTwo;
	protected double cycleThree;
	protected int Channel;
	protected int Stat;
	
	
	public Graph(File fileName, double value, String title, 
			double cycleOne, double cycleTwo, double cycleThree, int Channel)
	{
		excelReader = null;
		
		try
		{
			excelReader = new ExcelReader(fileName, cycleOne, cycleTwo, cycleThree, Channel);
			electricityData = excelReader.getData().electrictyData;
			electricityData1 = excelReader.getData().electrictyData1;
		}
		
		catch(Exception ioException)
		{
			ioException.printStackTrace();
		}
		
		this.mass = value;
		this.title = title;
		this.cycleOne = cycleOne;
		this.cycleTwo = cycleTwo;
		this.cycleThree = cycleThree;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getValue()
	{
		return mass;
	}

	public void setValue(double value) {
		this.mass = value;
	}

	public File getFile()
	{
		return fileName;
	}
	
	public void setFile(File fileName)
	{
		this.fileName = fileName;
		
	}
	
	public double getCycleOne()
	{
		return cycleOne;
	}

	public void setCycleOne(double cycleOne) 
	{
		this.cycleOne = cycleOne;
	}

	public double getCycleTwo()
	{
		return cycleTwo;
	}

	public void setCycleTwo(double cycleTwo) 
	{
		this.cycleTwo = cycleTwo;
	}

	public double getCycleThree()
	{
		return cycleThree;
	}

	public void setCycleThree(double cycleThree)
	{
		this.cycleThree = cycleThree;
	}

	public LineChart<Number, Number> display()
	{
		
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		
		final LineChart<Number,Number> defaultGraph = new LineChart<Number,Number>(xAxis,yAxis);
		return defaultGraph;
		
		
		
	}
	public XYChart.Series filler() { 	
		XYChart.Series seriess = new XYChart.Series();
		return seriess;
	}
	
	public XYChart.Series filler1() { 	
		XYChart.Series seriess = new XYChart.Series();
		return seriess;
	}
	
	public XYChart.Series filler2() { 	
		XYChart.Series seriess = new XYChart.Series();
		return seriess;
	}
	
	public XYChart.Series series() { 	
		XYChart.Series seriess = new XYChart.Series();
		XYChart.Data data = new XYChart.Data();
		Rectangle rect = new Rectangle(0,0);
		rect.setVisible(false);
		data.setNode(rect);
		seriess.getData().addAll(data);
		return seriess;
	}
	
	public XYChart.Series series1() { 	
		XYChart.Series seriess = new XYChart.Series();
		XYChart.Data data = new XYChart.Data();
		Rectangle rect = new Rectangle(0,0);
		rect.setVisible(false);
		data.setNode(rect);
		seriess.getData().addAll(data);
		return seriess;
	}
	
	public XYChart.Series series2() { 	
		XYChart.Series seriess = new XYChart.Series();
		XYChart.Data data = new XYChart.Data();
		Rectangle rect = new Rectangle(0,0);
		rect.setVisible(false);
		data.setNode(rect);
		seriess.getData().addAll(data);
		return seriess;
	}
	
	public XYChart.Series seriesdis() { 	
		XYChart.Series seriess = new XYChart.Series();
		XYChart.Data data = new XYChart.Data();
		Rectangle rect = new Rectangle(0,0);
		rect.setVisible(false);
		data.setNode(rect);
		seriess.getData().addAll(data);
		return seriess;
	}
	
	public XYChart.Series series1dis() { 	
		XYChart.Series seriess = new XYChart.Series();
		XYChart.Data data = new XYChart.Data();
		Rectangle rect = new Rectangle(0,0);
		rect.setVisible(false);
		data.setNode(rect);
		seriess.getData().addAll(data);
		return seriess;
	}
	public XYChart.Series series2dis() { 	
		XYChart.Series seriess = new XYChart.Series();
		XYChart.Data data = new XYChart.Data();
		Rectangle rect = new Rectangle(0,0);
		rect.setVisible(false);
		data.setNode(rect);
		seriess.getData().addAll(data);
		return seriess;
	}
	
	@Override
	public String toString()
	{
		return title;
	}



}
