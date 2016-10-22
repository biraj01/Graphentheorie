package application;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.xml.crypto.KeySelector.Purpose;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
public class SampleController {
	
	
	  	@FXML
	    private Button openbtn;

	    @FXML
	    private Button savebtn;

	    @FXML
	    private Button breadthfirstbtn;
	    
	   
	    
	    @FXML
	    public void open(){
//	    	System.out.println("hello");
	    	openbtn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					FileChooser chooser = new FileChooser();
					chooser.setTitle("Open .gka file");
					File choosedFile = chooser.showOpenDialog(null);
					VisualizeGraph visualize = new VisualizeGraph(choosedFile);
					visualize.init();
					JFrame frame = new JFrame();
					frame.setSize(1000, 1000);
					frame.getContentPane().add(visualize);
					frame.setVisible(true);
					//Demoread demo = new Demoread();
					//demo.makeGraph(choosedFile);
						System.out.println(choosedFile.getPath());
					}
				
			});
			
	    	}
	    
	
}
