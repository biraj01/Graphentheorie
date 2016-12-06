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
          Graph graph;
          BigGenerator tb = new BigGenerator(50, 800, "s", "t", true, 200, 300);

          graph = tb.getGraph();
          
          graph.display(true);

          try {
              //https://de.wikipedia.org/wiki/Flüsse_und_Schnitte_in_Netzwerken
              //Netzwerk
              //Ein Netzwerk (engl. network) N = ( G , u , s , t ) {\displaystyle N=(G,u,s,t)} N=(G,u,s,t)
              // besteht aus einem gerichteten Graphen mit zwei ausgezeichneten Knoten,
              // einer Quelle s und einer Senke t
              // t aus V, sowie einer Kapazitätsfunktion, die jeder Kante e ∈ E eine nichtnegative Kapazität zuweist,
              // e ↦ u ( e ). Hat die Kapazitätsfunktion ausschließlich ganzzahlige Werte, so existiert eine maximale
              // Flussfunktion (siehe folgende Definition), die ebenfalls nur ganzzahlige Werte hat.
            gf.saveGraph(graph, "BigNet_02_MarcelLange_BirajDhungel.gka", "directedWeighted");
          } catch (IOException e) {
            e.printStackTrace();
          }
          //File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\buttonBig.gka");
          //gf.zeichneGraph(file);
        }});

    }
      
  
}
