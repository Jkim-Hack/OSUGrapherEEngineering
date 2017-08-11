package GraphingPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class VoltageVsChrgeCapacity extends Graph
{
	

	public VoltageVsChrgeCapacity(File fileName, double value, String title,
			double cycleOne, double cycleTwo, double cycleThree, int Channel, int Stat) 
	{
		
		super(fileName, value, title, cycleOne, cycleTwo, cycleThree, Channel, Stat);
		
	}
	
	
	
	public double FindGreatestCC()
	{
	
		
		
		double greatestDoubleCC = ((electricityData.get(0).getCharge_Capacity()) * 1000)/mass;

		for(int i = 1; i < electricityData.size(); i ++)
		{
		
			double chargeGetin = (electricityData.get(i).getCharge_Capacity()) * 1000;
			double chargein = chargeGetin/mass;
			double current = electricityData.get(i).getCurrent();
			
			
			if( greatestDoubleCC < chargein )
			{
				if (current < 0)
				{
					break;
				}
				greatestDoubleCC = chargein;
			}
		 }
		
		 return greatestDoubleCC;
						
	 }

public double FindGreatestV()
{

	
	


double greatestDoubleV = electricityData.get(0).getVoltage();

for(int i = 0; i < electricityData.size(); i ++)	{
	
		double voltagein = electricityData.get(i).getVoltage();
		double current = electricityData.get(i).getCurrent();
		

		if( greatestDoubleV < voltagein ){
			if (current < 0){
				break;
		}
			greatestDoubleV = voltagein;
	}
}
	return greatestDoubleV;
					
}

public double FindLeastCC()
{

	
	


double leastDoubleCC = electricityData.get(0).getCharge_Capacity();

for(int i = 0; i < electricityData.size(); i ++)	{
	
		double chargeGetin = (electricityData.get(i).getCharge_Capacity()) * 1000;
		double chargein = chargeGetin/mass;
		double current = electricityData.get(i).getCurrent();
		
		if (current == 0){
				
			if (leastDoubleCC == chargein){
					return leastDoubleCC;
			
			}
				
			break;
			
		}
	}
return leastDoubleCC;
}

public double FindLeastV()
{


	

	

double leastDoubleV = electricityData.get(0).getVoltage();

for(int i = 0; i < electricityData.size(); i ++)	{
	
	double voltagein = electricityData.get(i).getVoltage();
	double current = electricityData.get(i).getCurrent();
		
		if (current == 0){
				
			if (leastDoubleV == voltagein){
					return leastDoubleV;
			
			}
				
			break;
			
		}
	}

return leastDoubleV;

}
	

	public final LineChart<Number,Number> display()
	{		
	

	

	

// DISPLAY
// DISPLAY

	

	
/*System.out.println(FindLeastV());
System.out.println(FindLeastCC());
System.out.println(FindGreatestCC());
System.out.println(FindGreatestV());
*/


//defining the axes
final NumberAxis xAxis = new NumberAxis(FindLeastCC(), FindGreatestCC() + (FindGreatestCC()/4), (FindGreatestCC())/6);
final NumberAxis yAxis = new NumberAxis(0, FindGreatestV() + (FindGreatestV()/4), (FindGreatestV())/6);
xAxis.setLabel("Charge Capacity (mAh/g)");
yAxis.setLabel("Voltage (V)");

//creating the chart

final LineChart<Number,Number> lineChart = 
        new LineChart<Number,Number>(xAxis,yAxis);
        
lineChart.setTitle("Voltage vs Charge Capacity");
lineChart.getStylesheets().add("GraphingPackage/Chart.css");

return lineChart;
	}
	

public XYChart.Series filler(){ 	
final XYChart.Series filler = new XYChart.Series();
return filler;
}
public XYChart.Series filler1(){ 	
final XYChart.Series filler1 = new XYChart.Series();
return filler1;
}
public XYChart.Series filler2(){ 	
final XYChart.Series filler2 = new XYChart.Series();
return filler2;
}
//defining a series
public XYChart.Series series(){ 	
final XYChart.Series series = new XYChart.Series();
series.setName("Cycle " + cycleOne);


//populating the series with data

if(excelReader != null)
{
	
	
	
	List<Data> currentByCycle = new ArrayList<Data>();
	for(int i = 0; i < electricityData.size(); i++)
	{
		
		if(electricityData.get(i).getCycle_Number() == cycleOne)
		{
			currentByCycle.add(electricityData.get(i));
		}
		
		
	}
	
	
	
	
	for(int i = 0; i < currentByCycle.size();)
	{
		if(currentByCycle.get(i).getCurrent() <= 0)
		{
			currentByCycle.remove(i);
		}
		else
		{
			i++;
		}
	}
	
	
	

	for(int i = 0; i < currentByCycle.size(); i ++)
	{

		double chargeGet = (currentByCycle.get(i).getCharge_Capacity()) * 1000 ;

		double charge = chargeGet/mass;
		
		


		//System.out.println(charge);

		double voltage = currentByCycle.get(i).getVoltage();

		XYChart.Data data = new XYChart.Data(charge,voltage);
		Rectangle rect = new Rectangle(0,0);
		rect.setVisible(false);
		data.setNode(rect);
		series.getData().addAll(data);

	}

}
return series;
}


public XYChart.Series series1() { 	
final XYChart.Series series1 = new XYChart.Series();
series1.setName("Cycle " + cycleTwo);


//populating the series with data

if(excelReader != null)
{
	
	
	
	List<Data> currentByCycle = new ArrayList<Data>();
	for(int i = 0; i < electricityData.size(); i++)
	{
		
		if(electricityData.get(i).getCycle_Number() == cycleTwo)
		{
			currentByCycle.add(electricityData.get(i));
		}
		
		
	}
	
	
	
	
	for(int i = 0; i < currentByCycle.size();)
	{
		if(currentByCycle.get(i).getCurrent() <= 0)
		{
			currentByCycle.remove(i);
		}
		else
		{
			i++;
		}
	}
	
	
	

	for(int i = 0; i < currentByCycle.size(); i ++)
	{

		double chargeGet = (currentByCycle.get(i).getCharge_Capacity()) * 1000 ;

		double charge = chargeGet/mass;
		
		//System.out.println(charge);

		double voltage = currentByCycle.get(i).getVoltage();

		XYChart.Data data1 = new XYChart.Data(charge,voltage);
		Rectangle rect = new Rectangle(0,0);
		rect.setVisible(false);
		data1.setNode(rect);
		series1.getData().addAll(data1);

	}

}
return series1;
}

public XYChart.Series series2() { 	

final XYChart.Series series2 = new XYChart.Series();
series2.setName("Cycle " + cycleThree);


//populating the series with data


if(excelReader != null)
{
	
	
	
	List<Data> currentByCycle = new ArrayList<Data>();
	for(int i = 0; i < electricityData.size(); i++)
	{
		
		if(electricityData.get(i).getCycle_Number() == cycleThree)
		{
			currentByCycle.add(electricityData.get(i));
		}
		
		
	}
	
	
	
	
	for(int i = 0; i < currentByCycle.size();)
	{
		if(currentByCycle.get(i).getCurrent() <= 0)
		{
			currentByCycle.remove(i);
		}
		else
		{
			i++;
		}
	}
	
	
	

	for(int i = 0; i < currentByCycle.size(); i ++)
	{

		double chargeGet = (currentByCycle.get(i).getCharge_Capacity()) * 1000 ;

		double charge = chargeGet/mass;
		
		//System.out.println(charge);

		double voltage = currentByCycle.get(i).getVoltage();

		XYChart.Data data2 = new XYChart.Data(charge,voltage);
		Rectangle rect = new Rectangle(0,0);
		rect.setVisible(false);
		data2.setNode(rect);
		series2.getData().addAll(data2);

	}


}
return series2;
}

public XYChart.Series seriesdis() { 	

final XYChart.Series seriesdis = new XYChart.Series();



//populating the series with data

	if(excelReader != null)
	{
		
		
		
		List<Data> currentByCycle = new ArrayList<Data>();
		for(int i = 0; i < electricityData.size(); i++)
		{
			
			if(electricityData.get(i).getCycle_Number() == cycleOne)
			{
				currentByCycle.add(electricityData.get(i));
			}
			
			
		}
		
		
		
		
		for(int i = 0; i < currentByCycle.size();)
		{
			if(currentByCycle.get(i).getCurrent() >= 0)
			{
				currentByCycle.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		
		

		for(int i = 0; i < currentByCycle.size(); i ++)
		{
	
			double chargeGet = (currentByCycle.get(i).getDischarge_Capacity()) * 1000 ;

			double charge = chargeGet/mass;

			//System.out.println(charge);
	
			double voltage = currentByCycle.get(i).getVoltage();
	
			XYChart.Data data3 = new XYChart.Data(charge,voltage);
			Rectangle rect = new Rectangle(0,0);
			rect.setVisible(false);
			data3.setNode(rect);
			seriesdis.getData().add(data3);

		}
	}
	return seriesdis;
}

public XYChart.Series series1dis() { 	

final XYChart.Series series1dis = new XYChart.Series();

//populating the series with data

	if(excelReader != null)
	{
		
		
		
		List<Data> currentByCycle = new ArrayList<Data>();
		for(int i = 0; i < electricityData.size(); i++)
		{
			
			if(electricityData.get(i).getCycle_Number() == cycleTwo)
			{
				currentByCycle.add(electricityData.get(i));
			}
			
			
		}
		
		
		
		
		for(int i = 0; i < currentByCycle.size();)
		{
			if(currentByCycle.get(i).getCurrent() >= 0)
			{
				currentByCycle.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		
		

		for(int i = 0; i < currentByCycle.size(); i ++)
		{
	
			double chargeGet = (currentByCycle.get(i).getDischarge_Capacity()) * 1000 ;

			double charge = chargeGet/mass;

			//System.out.println(charge);
	
			double voltage = currentByCycle.get(i).getVoltage();
	
			XYChart.Data data4 = new XYChart.Data(charge,voltage);
			Rectangle rect = new Rectangle(0,0);
			rect.setVisible(false);
			data4.setNode(rect);
			series1dis.getData().add(data4);

		}
	

	}
return series1dis;	
}

public XYChart.Series series2dis() { 	

	final XYChart.Series series2dis = new XYChart.Series();

	
	//populating the series with data

		if(excelReader != null)
		{
			
			
			
			List<Data> currentByCycle = new ArrayList<Data>();
			for(int i = 0; i < electricityData.size(); i++)
			{
				
				if(electricityData.get(i).getCycle_Number() == cycleThree)
				{
					currentByCycle.add(electricityData.get(i));
				}
				
				
			}
			
			
			
			
			for(int i = 0; i < currentByCycle.size();)
			{
				if(currentByCycle.get(i).getCurrent() >= 0)
				{
					currentByCycle.remove(i);
				}
				else
				{
					i++;
				}
			}
			
			
			

			for(int i = 0; i < currentByCycle.size(); i ++)
			{
		
				double chargeGet = (currentByCycle.get(i).getDischarge_Capacity()) * 1000 ;

				double charge = chargeGet/mass;

				//System.out.println(charge);
		
				double voltage = currentByCycle.get(i).getVoltage();
		
				XYChart.Data data5 = new XYChart.Data(charge,voltage);
				Rectangle rect = new Rectangle(0,0);
				rect.setVisible(false);
				data5.setNode(rect);
				series2dis.getData().add(data5);
			
			}
		

		}
		
		return series2dis;
		
	}


}
	
			
		
	



	
		

