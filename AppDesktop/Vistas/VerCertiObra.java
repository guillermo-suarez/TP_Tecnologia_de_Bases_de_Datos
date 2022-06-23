package Vistas;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class VerCertiObra extends javax.swing.JFrame {
    
    private Connection con;
    private Statement sqlStmn;
    
    private String queryDatosCabecera;
    private ResultSet datosCabecera;
    
    private String queryDatosTabla;
    private ResultSet datosTabla;

    public VerCertiObra(Connection con, Statement sqlStmn, Integer numObra, Integer nroCertipago) {
        this.con = con;
        this.sqlStmn = sqlStmn;
        
        this.queryDatosCabecera = "select co.nrocerobra, cp.nrocertificado, cp.fechacert, o.nomobra, o.numobra " +
                                  "from GuillermoDB.certiobra co " +
                                  "inner join GuillermoDB.certipago cp on (co.nrocertificado = cp.nrocertificado and co.idobra = cp.idobra) " +
                                  "inner join GuillermoDB.obra o on (co.idobra = o.idobra) " +
                                  "where cp.idobra = GuillermoDB.FUN_GETIDOBRA(" + numObra.toString() + ") " +
                                  "and cp.nrocertificado = " + nroCertipago.toString();
        
        this.queryDatosTabla = "select i.denitem, i.idtipoitem, fd.avaacuanterior, fd.avaactual, fd.monto " + 
                               "from GuillermoDB.fojadet fd " +
                               "inner join GuillermoDB.item i on (fd.idobra = i.idobra and fd.iditem = i.iditem) " +
                               "inner join GuillermoDB.certiobra co on fd.idfoja = co.idfoja " +
                               "where co.idobra = GuillermoDB.FUN_GETIDOBRA(" + numObra.toString() + ") " +
                               "and co.nrocertificado = " + nroCertipago.toString();
        initComponents();
        try {
            this.datosCabecera = sqlStmn.executeQuery(queryDatosCabecera);
            if(!datosCabecera.next()) {
                this.setVisible(false);
                JOptionPane.showMessageDialog(this, "No se encontró un certificado de obra con esos datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                this.setVisible(true);
                cargarDatosCabecera();
                cargarTablaItems();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCertiObra = new javax.swing.JPanel();
        lblNroCertiobra = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemsCertiObra = new javax.swing.JTable();
        lblNroCertipago = new javax.swing.JLabel();
        txtNroCertipago = new javax.swing.JTextField();
        lblNomObra = new javax.swing.JLabel();
        txtNomObra = new javax.swing.JTextField();
        txtFechaCertipago = new javax.swing.JTextField();
        lblNumObra = new javax.swing.JLabel();
        txtNumObra = new javax.swing.JTextField();
        lblFechaCertipago = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Certificado de Obra");
        setPreferredSize(new java.awt.Dimension(820, 484));
        setResizable(false);

        lblNroCertiobra.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNroCertiobra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNroCertiobra.setText("Certificado de Obra Nº");

        tblItemsCertiObra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre del Ítem", "Monto vivienda", "Monto infraestructura", "% anterior", "% del mes", "% total", "Monto del mes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItemsCertiObra.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblItemsCertiObra);
        if (tblItemsCertiObra.getColumnModel().getColumnCount() > 0) {
            tblItemsCertiObra.getColumnModel().getColumn(0).setResizable(false);
            tblItemsCertiObra.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblItemsCertiObra.getColumnModel().getColumn(1).setResizable(false);
            tblItemsCertiObra.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblItemsCertiObra.getColumnModel().getColumn(2).setResizable(false);
            tblItemsCertiObra.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblItemsCertiObra.getColumnModel().getColumn(3).setResizable(false);
            tblItemsCertiObra.getColumnModel().getColumn(3).setPreferredWidth(63);
            tblItemsCertiObra.getColumnModel().getColumn(4).setResizable(false);
            tblItemsCertiObra.getColumnModel().getColumn(4).setPreferredWidth(63);
            tblItemsCertiObra.getColumnModel().getColumn(5).setResizable(false);
            tblItemsCertiObra.getColumnModel().getColumn(5).setPreferredWidth(62);
            tblItemsCertiObra.getColumnModel().getColumn(6).setResizable(false);
            tblItemsCertiObra.getColumnModel().getColumn(6).setPreferredWidth(150);
        }

        lblNroCertipago.setText("Certificado de Pago Nº:");

        txtNroCertipago.setEditable(false);

        lblNomObra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNomObra.setText("Nombre de la Obra:");

        txtNomObra.setEditable(false);

        txtFechaCertipago.setEditable(false);

        lblNumObra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumObra.setText("Número de la Obra:");

        txtNumObra.setEditable(false);

        lblFechaCertipago.setText("Fecha del Certificado:");

        javax.swing.GroupLayout pnlCertiObraLayout = new javax.swing.GroupLayout(pnlCertiObra);
        pnlCertiObra.setLayout(pnlCertiObraLayout);
        pnlCertiObraLayout.setHorizontalGroup(
            pnlCertiObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCertiObraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCertiObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(lblNroCertiobra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCertiObraLayout.createSequentialGroup()
                        .addGroup(pnlCertiObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblNumObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNomObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNroCertipago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCertiObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCertiObraLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblFechaCertipago, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaCertipago, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNomObra, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlCertiObraLayout.createSequentialGroup()
                                .addComponent(txtNroCertipago, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                .addGap(397, 397, 397))
                            .addComponent(txtNumObra))))
                .addContainerGap())
        );
        pnlCertiObraLayout.setVerticalGroup(
            pnlCertiObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCertiObraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNroCertiobra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCertiObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlCertiObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNroCertipago)
                        .addComponent(txtFechaCertipago))
                    .addComponent(lblNroCertipago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFechaCertipago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCertiObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNomObra)
                    .addComponent(lblNomObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCertiObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumObra, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCertiObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCertiObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void cargarDatosCabecera() {
        try {
            this.datosCabecera = sqlStmn.executeQuery(queryDatosCabecera);
            if(datosCabecera.next()) {
                this.lblNroCertiobra.setText("Certificado de Obra Nº" + String.valueOf(datosCabecera.getInt(1)));
                this.txtNroCertipago.setText(String.valueOf(datosCabecera.getInt(2)));
                this.txtFechaCertipago.setText(datosCabecera.getDate(3).toString());
                this.txtNomObra.setText(datosCabecera.getString(4));
                this.txtNumObra.setText(String.valueOf(datosCabecera.getInt(5)));
            }
            this.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarTablaItems() {
        try {
            this.datosTabla = sqlStmn.executeQuery(queryDatosTabla);
            DefaultTableModel tblItemsModel = (DefaultTableModel) tblItemsCertiObra.getModel();
            tblItemsModel.setRowCount(0);
            String nombreItem, montoV, montoI, porcAcu, porcMes, porcTotal, montoItem;
            double totalV = 0.0, totalI = 0.0, totalT = 0.0;
            while(datosTabla.next()) {
                nombreItem = datosTabla.getString(1);
                if(datosTabla.getInt(2) == 1) {
                    montoV = String.format("$ %.2f", datosTabla.getDouble(5)*100.00/datosTabla.getDouble(4));
                    totalV += datosTabla.getDouble(5)*100.00/datosTabla.getDouble(4);
                    montoI = "$ 0.00";
                } else {
                    montoV = "$ 0.00";
                    montoI = String.format("$ %.2f", datosTabla.getDouble(5)*100.00/datosTabla.getDouble(4));
                    totalI += datosTabla.getDouble(5)*100.00/datosTabla.getDouble(4);
                }
                porcAcu = String.valueOf(datosTabla.getDouble(3)) + "%";
                porcMes = String.valueOf(datosTabla.getDouble(4)) + "%";
                porcTotal = String.valueOf(datosTabla.getDouble(3) + datosTabla.getDouble(4)) + "%";
                montoItem = "$ " + String.valueOf(datosTabla.getDouble(5));
                totalT += datosTabla.getDouble(5);
                
                String[] tblData = {nombreItem, montoV, montoI, porcAcu, porcMes, porcTotal, montoItem};
                
                tblItemsModel.addRow(tblData);
            }
            String[] lastRow = {"TOTAL", String.format("$ %.2f", totalV), String.format("$ %.2f", totalI), null, null, null, String.format("$ %.2f", totalT)};
            tblItemsModel.addRow(lastRow);
            this.setVisible(true);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFechaCertipago;
    private javax.swing.JLabel lblNomObra;
    private javax.swing.JLabel lblNroCertiobra;
    private javax.swing.JLabel lblNroCertipago;
    private javax.swing.JLabel lblNumObra;
    private javax.swing.JPanel pnlCertiObra;
    private javax.swing.JTable tblItemsCertiObra;
    private javax.swing.JTextField txtFechaCertipago;
    private javax.swing.JTextField txtNomObra;
    private javax.swing.JTextField txtNroCertipago;
    private javax.swing.JTextField txtNumObra;
    // End of variables declaration//GEN-END:variables
}
