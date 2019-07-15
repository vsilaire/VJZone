/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vjzone;

import javafx.scene.control.Label;
import javax.swing.JFileChooser;
import java.io.File;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JPanel;
import vjzone.model.Analyzer;

/**
 *
 * @author Victor
 */
public class VJZone extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Scene scene;
    
    private Label directoryLabel;
    
    private Analyzer analyzer;
    
    @Override
    public void start(Stage primaryStage) {
        
        this.analyzer = Analyzer.create();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("VJZone");
        
        initRootLayout();
        
    }
    
    /**
     * inits and shows the root layout
     */
    public void initRootLayout(){
        
        try {
            //Load VJZone main view
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VJZone.class.getResource("view/MainView.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            this.scene = new Scene(rootLayout);
            primaryStage.setScene(this.scene);
            directoryLabel = (Label) rootLayout.lookup("#directoryLabel");
            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * @return the main view primary stage 
     */
    public Stage getPrimaryStage(){
        
        return this.primaryStage;
    }
    
    @FXML 
    protected void openFile(){
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileHidingEnabled(true);
        int result = fileChooser.showOpenDialog(new JPanel());
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            this.analyzer.setPath(path);
            
        }
    }
    
     @FXML 
    protected void openDirectory(){
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileHidingEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(new JPanel());
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            this.analyzer.setPath(path);
            //this.directoryLabel.setText(this.directoryLabel.getText() + path);
            
        }
    }
    
    @FXML
    protected void close(){
        System.exit(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
