/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package App;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

/**
 *
 * @author Guillermo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        // Seteo el driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        
        // Seteo la conexión
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "GuillermoDB", "Admin12345");
        
        String query = "select monto as ABC from fojadet";
        Statement stmn = con.createStatement();
        ResultSet rs = stmn.executeQuery(query);
        //String format = "%-7s%-7s%-7s%-15s%-10s%-6s%n";
        //System.out.printf(format, "IDFOJA", "IDITEM", "IDOBRA", "AVAACUANTERIOR", "AVAACTUAL", "MONTO");
        while(rs.next()) {
            System.out.println(/*format, rs.getInt(1), rs.getInt("IDITEM"), rs.getInt("IDOBRA"),
                                      rs.getDouble("AVAACUANTERIOR"), rs.getFloat(5), */rs.getDouble("ABC"));
        }
        
        
        // Creo y seteo el objeto que va a llamar al procedimiento
        CallableStatement proc = con.prepareCall("{CALL PRC_UPDATEFOJADET(?,?,?,?)}");
        CallableStatement func = con.prepareCall("{? = call FUN_GETAVANCEOBRA(?)}");
        
        // Seteo el TIPO DE DATO del/de los parametros de salida
        proc.registerOutParameter(4, Types.INTEGER);
        // En este caso solo el 4to parametro es de salida y se espera que devuelva un entero
        
        func.registerOutParameter(1, Types.DOUBLE);
        
        // Seteo los VALORES de los parametros de entrada
        proc.setInt(1, 10100);        // pIdObra = 10100
        proc.setInt(2, 1);            // pIdItem = 1
        proc.setDouble(3, 100.00);    // pPorcAvanz = -1.00
        
        int numobra = 4800;
        func.setInt(2, numobra);
        
        // Ejecuto el procedimiento
        proc.execute();
        func.execute();
        
        // Recupero el valor del parametro de salida
        int vError = proc.getInt(4);
        double vPorcAvanzado = func.getDouble(1);
        
        // Esto sería equivalente a llamar, en PL/SQL, a:
        //      prc_updatefojadet(10100, 1, -10.00, vError);
        
        // Imprimo en la consola de NetBeans el código de error
        System.out.println("Código de error: " + vError);
        System.out.println("Porcentaje avanzado de la obra con Nº " + numobra + ": " + vPorcAvanzado);
        
        // Cierro el objeto
        proc.close();
        func.close();
        
        // Cierro el objeto de la consulta
        stmn.close();
        
        // Cierro la conexion
        con.close();
    }
    
}
