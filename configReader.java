/**
 * Created by Siddharth Somvanshi on 9/4/2018.
 */
package sample;

import javafx.fxml.FXML;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class configReader {
    @FXML
    HTMLEditor editor = new HTMLEditor();
    File config;
    Scanner get;
    public configReader() throws Exception {
        try {
            config = new File("c:\\blogConsole\\config.txt");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"File Not Found or File Loading Error");
        }
    }
    public String GETserverAddress() throws IOException{
        get = new Scanner(config);
        String value = get.nextLine();
        return value;
    }
    public String GETDefaultLocation() throws IOException{
        get = new Scanner(config);
        get.nextLine();
        String value = get.nextLine();
        return value;
    }
    public void save(String content) throws Exception {
        String path = GETDefaultLocation();
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(path));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        fc.setTitle("Save File");
        File name = fc.showSaveDialog(null);
        if(name!=null){
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(name.getAbsoluteFile()));
                String lines[] = content.split("\\r?\\n");
                for (String line : lines){
                    bw.write(line);
                    bw.write(System.lineSeparator());
                }
                bw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(null,"File SuccessFully Saved");
    }
}
