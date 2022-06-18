package App;
import Vistas.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class MainApp {

    public static void main(String[] args) throws Exception {
        /*try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "GuillermoDB", "Admin1235");
            *//*
            Statement sqlStmn = con.createStatement();
            
            ResultSet roles = sqlStmn.executeQuery("select granted_role from user_role_privs");
            
            while(roles.next()) {
                System.out.println(roles.getString(1));
            }*/
            
        /*} catch(SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println("Hola");
            System.out.println(e.getMessage());
            System.out.println("Hola");
        }*/
        Login ventana = new Login();
        ventana.setVisible(true);
    }
    
}
