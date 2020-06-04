/**
 * Created by Siddharth Somvanshi on 9/3/2018.
 */
package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class queryMaker {
    @FXML
    Label status = new Label();
    String myDriver;
    String address;
    public queryMaker() throws Exception {
        configReader cr = new configReader();
        address = cr.GETserverAddress();
        myDriver = "com.mysql.jdbc.Driver";
    }
    public void queryExecuter(String date,String title, String content, String IC, String IT, String type, String f){
        try {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(address, "root", "");
            PreparedStatement st =conn.prepareStatement("insert into post values(?,?,?,?,?,?,?,?)");
            st.setString(1,null);//1 specifies the first parameter in the query
            st.setString(2,date);
            st.setString(3,title);
            st.setString(4,content);
            st.setString(5,IC);
            st.setString(6,IT);
            st.setString(7,type);
            st.setString(8,f);
            int val = st.executeUpdate();
            if(val>0){
                JOptionPane.showMessageDialog(null,"Post Successfully Published");
            }
        } catch (Exception e) {
            if (e.getCause() == null) {
               System.out.println(e.getMessage());
            }
        }
    }
}
