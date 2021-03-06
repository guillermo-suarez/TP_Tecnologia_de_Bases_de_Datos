/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import App.Numero_Letras;

public class CertiPago extends javax.swing.JFrame {
    
    private Connection con;
    private Statement sqlStmn;
    //private ResultSet datosMes;
    //private ResultSet datosAcum;
    private ResultSet datosObra;
    private ResultSet conceptos;
    private ListSelectionModel tblListCertiMesModel;
    private ListSelectionModel tblListCertiAcumModel;
    private String SQL;
    private Integer IdObra;
    private Double totTot;
    private Date fechaCerti;
    private Numero_Letras nl;

    public CertiPago(Connection con, Statement sqlStmn, Integer NumObra, Integer NroCerti) {
        initComponents();
        totTot = 0.0;
        nl = new Numero_Letras();
        this.con = con;
        this.sqlStmn = sqlStmn;
        tblListCertiMesModel = tblMes.getSelectionModel();
        tblListCertiAcumModel = tblAcumulado.getSelectionModel();
        SQL = new String();
        this.lblTitulo.setText("Certificado de Pago Nro: " + NroCerti.toString());//se establece el título
        this.lblNroObra.setText(NumObra.toString());
        
        try{
            cargarDatosObra(NumObra, NroCerti);
            if(this.IdObra != null) {
                buscarConceptos();
                cargarMontos(NumObra);
                cargarTablaMes(NroCerti);
                this.lblAPagar.setText(nl.Convertir(String.format("%.2f", totTot), true, "CENTAVOS"));//se convierte el monto total a palabras
                cargarTablaAcum(NroCerti);
                this.setVisible(true);
            } else {
                this.dispose();
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblNombreObra = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblLocalidad = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblNroObra = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblPlazo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMontoBase = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblMontoActual = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAcumulado = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMes = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        lblAPagar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Certificado de Pago");

        lblTitulo.setText("Certificado de Pago Nro: X");

        jLabel1.setText("Obra: ");

        lblNombreObra.setText("nombreobra");

        jLabel2.setText("Localidad:");

        lblLocalidad.setText("nombrelocalidad");

        jLabel3.setText("Fecha:");

        lblFecha.setText("fecha");

        jLabel4.setText("Nro Obra:");

        lblNroObra.setText("numobra");

        jLabel5.setText("Fecha Inicio:");

        lblFechaInicio.setText("fechainicio");

        jLabel6.setText("Plazo:");

        lblPlazo.setText("plazo");

        jLabel7.setText("Contrato Base:");

        lblMontoBase.setText("$$$$$$$$$$$$$");

        jLabel8.setText("Contrato a Fecha:");

        lblMontoActual.setText("$$$$$$$$$$$$$");

        tblAcumulado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Concepto", "Anterior", "Mes", "Acumulado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAcumulado);

        jLabel9.setText("Este Mes:");

        jLabel10.setText("Acumulado:");

        tblMes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Concepto", "Vivienda", "Infra", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblMes);

        jLabel11.setText("Total a pagar:");

        lblAPagar.setText("totalapagar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblMontoBase)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblMontoActual))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblNombreObra))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblLocalidad))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFecha)))
                                .addGap(153, 153, 153)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblPlazo))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblFechaInicio))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblNroObra))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(lblTitulo)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(0, 465, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreObra)
                    .addComponent(jLabel4)
                    .addComponent(lblNroObra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblLocalidad)
                    .addComponent(jLabel5)
                    .addComponent(lblFechaInicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblFecha)
                    .addComponent(jLabel6)
                    .addComponent(lblPlazo))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblMontoBase)
                    .addComponent(jLabel8)
                    .addComponent(lblMontoActual))
                .addGap(52, 52, 52)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblAPagar))
                .addGap(31, 31, 31)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
   
    private void cargarDatosObra(Integer NumObra, Integer NroCerti){ //Procedimiento que carga los label con los datos de la obra
        this.SQL = "SELECT o.idobra, l.nomloc, o.nomobra, o.plazo_mes, o.fecinicio, c.fechacert\n"
                 + "FROM GuillermoDB.obra o\n"
                 + "INNER JOIN GuillermoDB.localidad l ON o.idlocalidad = l.idlocalidad\n"
                 + "INNER JOIN GuillermoDB.certipago c ON o.idobra = c.idobra\n"
                 + "WHERE o.numobra =" + NumObra.toString()
                 + " AND c.nrocertificado = " + NroCerti.toString();
        try {
            this.datosObra = this.sqlStmn.executeQuery(SQL);
            if(this.datosObra.next()){
                this.IdObra = this.datosObra.getInt(1); //Este dato se usa luego para calcular los montos
                this.lblLocalidad.setText(this.datosObra.getString(2));
                this.lblNombreObra.setText(this.datosObra.getString(3));
                this.lblPlazo.setText(this.datosObra.getString(4) + " meses");
                this.lblFechaInicio.setText(this.datosObra.getDate(5).toString());
                this.fechaCerti = this.datosObra.getDate(6);
                this.lblFecha.setText(fechaCerti.toString());//se carga la fecha del certificado
                this.SQL = null;//Se limpia el SQL por las dudas
            }else{
                JOptionPane.showMessageDialog(this, "No se encontró un certificado de pago con esos datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarMontos(Integer NumObra){//Procedimiento que carga los label de los contratos
        try {
            CallableStatement proc = con.prepareCall("{CALL GuillermoDB.PRC_DIFMONTO(?,?,?,?,?)}");
            proc.setInt(1, NumObra);//se setea tipo de dato de pNumObra
            proc.setDate(2, this.fechaCerti);//se setea tipo de dato de pFechaBusq
            proc.registerOutParameter(3, Types.INTEGER); //se setea tipo de dato de pError
            proc.registerOutParameter(4, Types.DOUBLE); //se setea tipo de dato de vMontoBase
            proc.registerOutParameter(5, Types.DOUBLE); //se setea tipo de dato de vMontoRedet
            proc.execute();
            this.lblMontoBase.setText(String.format("$ %.2f", proc.getDouble(4)));//Se coloca en el label el valor base
            this.lblMontoActual.setText(String.format("$ %.2f", proc.getDouble(5)));//Se coloca en el label el valor actual
            proc.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarTablaMes(Integer NroCerti) throws SQLException{//Carga la tablas con los montos del mes
        //fila de avance de obra
        Double columnaViv = 0.0;
        Double columnaInfra = 0.0;
        Double columnaTot = 0.0;
        //filas de porcentajes
        String columna1 = new String();
        Double columna2 = 0.0;
        Double columna3 = 0.0;
        Double columna4 = 0.0;
        //fila de totales
        Double totViv = 0.0;
        Double totInfra = 0.0;
        DefaultTableModel tblCPMesModel = (DefaultTableModel) tblMes.getModel();
        tblCPMesModel.setRowCount(0);
        while(conceptos.next()) {
            if(conceptos.getInt(1) == 1){//Ve si la fila es de avance de obra
                columna1 = conceptos.getString(2);
                columnaViv = getMontoIndividualMes(NroCerti, "{? = call GuillermoDB.FUN_GETMONTOVIVIENDATOTAL(?,?)}");
                System.out.println(columnaViv);
                totViv = columnaViv;
                columnaInfra = getMontoIndividualMes(NroCerti, "{? = call GuillermoDB.FUN_GETMONTOINFRATOTAL(?,?)}");
                System.out.println(columnaInfra);
                totInfra = columnaInfra;
                columnaTot = columnaViv + columnaInfra;
                totTot = columnaTot;
                String[] tblData = {columna1, String.format("$ %.2f", columnaViv), String.format("$ %.2f", columnaInfra), String.format("$ %.2f", columnaTot)};
                tblCPMesModel.addRow(tblData);
            }else{//se agregan las demas filas que sean porcentajes
                columna1 = conceptos.getString(2);
                columna2 = (columnaViv * (conceptos.getDouble(3)/100));//se lo vuelve negativo porque se debe descontar del total
                totViv += columna2;
                columna3 = (columnaInfra * (conceptos.getDouble(3)/100));
                totInfra += columna3;
                columna4 = columna2 + columna3;
                totTot += columna4;
                String[] tblData = {columna1, String.format("$ %.2f", columna2), String.format("$ %.2f", columna3), String.format("$ %.2f", columna4)};
                tblCPMesModel.addRow(tblData);
            }
        }
        //Se agrega la fila de totales
        columna1 = "Total";
        String[] tblData = {columna1, String.format("$ %.2f", totViv),String.format("$ %.2f", totInfra), String.format("$ %.2f", totTot)};
        tblCPMesModel.addRow(tblData);    
    }
    
    
    private Double getMontoIndividualMes(Integer NroCerti, String consulta){//Busca los montos totales de vivienda y de infra
        Double resultado = null;
        try{
            CallableStatement func = con.prepareCall(consulta);
            func.setInt(2, this.IdObra);
            func.setInt(3, NroCerti);
            func.registerOutParameter(1, Types.DOUBLE);
            func.execute();
            resultado = func.getDouble(1);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    private ResultSet buscarConceptos(){//Busca los conceptos para armar la tabla de montos del mes
        ResultSet resultado = null;
        SQL = "SELECT c.idconcepto, c.denconcepto, co.porcentaje\n" +
              "FROM GuillermoDB.concepto c\n" +
              "INNER JOIN GuillermoDB.conceptosxobra co ON c.idconcepto = co.idconcepto\n" +
              "WHERE co.idobra = " + this.IdObra.toString();
        try{
            this.conceptos = this.sqlStmn.executeQuery(SQL);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    private void cargarTablaAcum(Integer NroCerti) throws SQLException{//Carga la tablas con los montos del mes
        ResultSet resultado;
        String columCon = new String();
        Double columAnt = 0.0;
        Double columMes = 0.0;
        Double columAcum = 0.0;
        Double totAnt = 0.0;
        Double totMes = 0.0;
        Double total = 0.0;
        DefaultTableModel tblCPMesModel = (DefaultTableModel) tblAcumulado.getModel();
        tblCPMesModel.setRowCount(0);
        resultado = getDatosAcum(NroCerti);
        while(resultado.next()){
            columCon = resultado.getString(1);
            columAnt = resultado.getDouble(2);
            totAnt += columAnt;
            columMes = resultado.getDouble(3);
            totMes += columMes;
            columAcum = columAnt + columMes;
            total += columAcum;
            String[] tblData = {columCon, String.format("$ %.2f", columAnt), String.format("$ %.2f", columMes), String.format("$ %.2f", columAcum)};
            tblCPMesModel.addRow(tblData);
        }
        //Se agrega la fila de totales
        columCon = "Total";
        String[] tblData = {columCon, String.format("$ %.2f", totAnt), String.format("$ %.2f", totMes), String.format("$ %.2f", total)};
        tblCPMesModel.addRow(tblData);    
    }
    
    private ResultSet getDatosAcum(Integer NroCerti){//Se busca unicamente los conceptos x certificado especificos de la obra y del certificado
        ResultSet resultado = null;
        SQL = "SELECT c.denconcepto, cc.importeacuant, cc.importe\n" +
              "FROM GuillermoDB.conceptosxcertif cc\n" +
              "INNER JOIN GuillermoDB.concepto c ON c.idconcepto = cc.idconcepto\n" +
              "WHERE cc.idobra = " + this.IdObra + " AND cc.nrocertificado = " + NroCerti.toString();
        try{
            resultado = sqlStmn.executeQuery(SQL);  
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAPagar;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblLocalidad;
    private javax.swing.JLabel lblMontoActual;
    private javax.swing.JLabel lblMontoBase;
    private javax.swing.JLabel lblNombreObra;
    private javax.swing.JLabel lblNroObra;
    private javax.swing.JLabel lblPlazo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblAcumulado;
    private javax.swing.JTable tblMes;
    // End of variables declaration//GEN-END:variables
}
