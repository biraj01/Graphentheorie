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

import algorithm.BigGenerator;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class SampleController {
	
	
	  	@FXML
	    private Button openbtn;

	    @FXML
	    private Button savebtn;

	    @FXML
	    private Button bigGeneratorbtn;
	    
	   
	    
	    @FXML
	    public void open(){
	    	openbtn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					FileChooser chooser = new FileChooser();
					chooser.setTitle("Open .gka file");
					File choosedFile = chooser.showOpenDialog(null);
					ReadGraph graph = new ReadGraph();
					graph.zeichneGraph(choosedFile);
				}
				
			});
			
	    	}

		@FXML
		public void save(){
			savebtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
				}});

	}
		@FXML
		public void bigGenerator(){
			bigGeneratorbtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					ReadGraph gf = new ReadGraph();
					Graph graph = new SingleGraph("random euclidean");
					// dimension, directed?, randomDirection?, threshold, rangeMin, rangeMax, nodes
					algorithm.BigGenerator bg = new BigGenerator(3, true, true, 0.45, 0, 200, 100);
					bg.addSink(graph);
					bg.initEvents();
					bg.initEdgeWeights(graph);

					bg.end();
					graph.display(false);
					try {
						gf.saveGraph(graph, "buttonBig.gka", "directedWeighted");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}});

		}
	    
	
}
