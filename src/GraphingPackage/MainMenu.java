/*
Copyright (c) <2017>, <John Kim and Chris Yao>
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies,
either expressed or implied, of the FreeBSD Project.



Copyright 2017 John Kim and Chris Yao

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

/*
 This also uses monitorjbl's "Excel-Streaming-Reader" API https://github.com/monitorjbl/excel-streaming-reader
 AND kerner1000's "Javafx-Chart-Zooming" API https://github.com/kerner1000/javafx-chart-zooming	
 */

package GraphingPackage;

 
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import AlertBox.Alert;


public class MainMenu extends Application 
{
 
	//Instance Variables
	
	public File fileName;

	private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

	public FileChoose fileChooser = new FileChoose();
		
	

	
    @SuppressWarnings("restriction")
	@Override public void start(Stage primaryStage)
    {	
    	
    	
    	//Display Panes
        VBox topContainer = new VBox();
        VBox midinserts = new VBox();
        VBox GraphChoose = new VBox();
        
        
        midinserts.setSpacing(10);
        GraphChoose.setSpacing(20);
        
        
        Label labelMass = new Label("Insert Mass in Grams (g): ");
        labelMass.setFont(Font.font ("Segoe UI", 12));
        
        Label saveLabel = new Label(" Saved!");
        saveLabel.setVisible(false);
        
        //Textbox for File 
        
        
       
        
        //Creates Drop Down Boxes 
        Label topLabel = new Label("Choose Graph: 						    ");
        ChoiceBox<Graph> box1 = new ChoiceBox<Graph>();
        
        
        
        /*
        Label botLeft = new Label("Top Left Pane 						    	    ");
        ChoiceBox<Graph> box2 = new ChoiceBox<Graph>();
        
        Label botRight = new Label("Top Right Pane 						    ");
        ChoiceBox<Graph> box3 = new ChoiceBox<Graph>();
        */
       
        
       
        
        
       
        Label fileLabel = new Label("Chosen File:");
        TextField fileField = new TextField("File -> Open File...");
        
        Label SheetField = new Label("Enter Amount of Channel Sheets: ");
        TextField SheetText = new TextField("1");
       
        
    
        
        
        
        Label Cycles = new Label("Enter Three Cycles: ");
        TextField insertCycle1 = new TextField();
        TextField insertCycle2 = new TextField();
        TextField insertCycle3 = new TextField();
        
        
       
        //Mass textfield
        Button createGraph = new Button();
        createGraph.setDisable(true);
        
        TextField insertMass = new TextField();
        Button pseudoSave = new Button("Apply");
        
        
        
        pseudoSave.addEventHandler(ActionEvent.ACTION , ActionEvent -> 
        {
        	
        	
        			saveLabel.setVisible(false);
        	  	
        	
        	

        	 long start = System.currentTimeMillis();
        	 isDouble(insertMass, insertMass.getText());
        	 
        	 setUpValidation(SheetText);
            
        	 int Channel = 0;
        	 
        	 
        	 
        	 try {
        	 
        	 Channel = toSheetInt(SheetText.getText());
        	 
        	 }
        	 catch (Exception e) {
        		 Alert alert1 = new Alert();
        		 alert1.displayBox("Error in input fields");
        	 }
             
             
             //double cycleOne = 0;
            // double cycleTwo = 0;
            // double cycleThree = 0;
             
             setUpValidation(insertCycle1);
             setUpValidation(insertCycle2);
             setUpValidation(insertCycle3);
             setUpValidation(insertMass);
            // double mass = 0;
             double cycleOne = 0;
             double cycleTwo = 0;
             double cycleThree = 0;
             double mass = 0;
             
             
             
             try
             {
            	 cycleOne = toCycleDouble(insertCycle1.getText());
            	 cycleTwo = toCycleDouble(insertCycle2.getText());
            	 cycleThree = toCycleDouble(insertCycle3.getText());
            	 mass = toMassDouble(insertMass.getText());
             }
             catch (Exception e) 
             {
            	 Alert alert = new Alert();
            	 alert.displayBox("Error in input fields");
            	 
             }
             
        
            
        	
        	String ChargeCap = "Voltage vs Charge Capacity & Discharge Capacity";
        	
        	 List<Graph> graphs = new ArrayList<Graph>();
             graphs.add(new VoltageVsChrgeCapacity(fileName, mass, ChargeCap ,
            		 cycleOne,cycleTwo,cycleThree, Channel));
             graphs.add(new CycleNumberDC(fileName, mass, "Discharge Capacity vs Cycle Number",
            		 cycleOne,cycleTwo,cycleThree, Channel));
             graphs.add(new CoulombicEff(fileName, mass, "Coulombic Efficiency vs Cycle Number",
            		 cycleOne,cycleTwo,cycleThree, Channel));
             
             box1.getItems().clear();
            // box2.getItems().clear();
            // box3.getItems().clear();  
             
             box1.getItems().addAll(graphs);
            // box2.getItems().addAll(graphs);
            // box3.getItems().addAll(graphs);  
           
             createGraph.setDisable(false);
             long end = System.currentTimeMillis();
             saveLabel.setVisible(true);
             System.out.println(end - start);
        });
                
       
        	
        //Menu drop down bar
        MenuBar ddMenu = new MenuBar();
        Menu files = new Menu("File");
        MenuItem openFile = new MenuItem("Open File...");	
       
        
       openFile.setOnAction((ActionEvent event) -> {
        	
            //File Chooser class
    	   
        	FileChoose file;
        	file = new FileChoose();
            file.chooseFile(primaryStage);
            fileName = file.getFileName();
            fileField.setText(fileName.getName());

            //File Chooser class
            
       });
       
       
       fileField.setEditable(false);
       
       files.getItems().add(openFile);
      
       
       
       ddMenu.getMenus().addAll(files);
       
    	//Button for the graph window created
    	
    	
    	createGraph.setText("Create Graphs");
    	createGraph.setFont(Font.font ("Segoe UI", 16));
    	createGraph.setOnAction((ActionEvent event) ->
    	{
    		
    		MainGraphs graph = new MainGraphs(box1.getValue());
    		graph.displayGraphs();
    		
        });
    	
    	createGraph.setPrefWidth(250);
       	createGraph.setPrefHeight(70);
    	
    	
    	
       //The primary master window is created
       
    	BorderPane pane = new BorderPane();
    	
       	pane.setRight(GraphChoose);
       	GraphChoose.setMargin(createGraph, new Insets(280,0,0,0));
       	GraphChoose.setPadding(new Insets(20,20,20,20));
       	GraphChoose.setAlignment(Pos.TOP_RIGHT);
       	GraphChoose.getChildren().addAll(topLabel,box1, createGraph);
       	box1.setPrefWidth(250);
       //	box2.setPrefWidth(250);
       //	box3.setPrefWidth(250);
       
       	pane.setTop(topContainer);
       	topContainer.getChildren().add(ddMenu);
       	
       	pane.setLeft(midinserts);
       	midinserts.setPadding(new Insets(20, 20, 20, 20));
       	midinserts.getChildren().addAll(fileLabel, fileField, labelMass ,insertMass, Cycles, insertCycle1, insertCycle2, insertCycle3, 
       			SheetField, SheetText, pseudoSave, saveLabel);

       	
       InputStream in = this.getClass().getClassLoader().getResourceAsStream("favicon.PNG");
       
       
       
       Scene scene = new Scene(pane, 600, 497);
      
       Image icon = new Image(in);
       
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Battery Data Processing Software (Dedicated to ARBIN Cycler) v1.0");
        primaryStage.setScene(scene);
        primaryStage.show();
         
        
        
    }
    
    
    
    
    
    
    private boolean isDouble(TextField input, String mass)
    {
    	
    	try
    	{
    		double dataMass = Double.parseDouble(input.getText());
    		return true;
    		
    	} catch(NumberFormatException e){
    		return false;
    	}
    }

    
    public double toMassDouble(String mass)
    {
    	double dataMass = Double.parseDouble(mass);
    	return dataMass;
    }
    
    public int toSheetInt(String number)
    {
    	int sheet = Integer.parseInt(number);
    	return sheet;
    }
 
    public double toCycleDouble(String Cycle)
    {
    	double AllCycle = Double.parseDouble(Cycle);
    	return AllCycle;
    }
    
    private void setUpValidation(final TextField tf) { 
        tf.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                validate(tf);
            }

        });

        validate(tf);
    }

    private void validate(TextField tf) {
    	
    	 ObservableList<String> styleClass = tf.getStyleClass();
         if (tf.getText().trim().length()==0 ) {
             if (! styleClass.contains("error")) {
                 styleClass.add("error");
             }
         } else {
             // remove all occurrences:
             styleClass.removeAll(Collections.singleton("error"));                    
         }

    }
    
    
	public static void main(String[] args)
    {
	
        launch(args);
    }
}
 
