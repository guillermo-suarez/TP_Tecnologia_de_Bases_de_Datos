package Vistas;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class CargarFoja extends javax.swing.JFrame {
    
    private Connection con;
    private Statement sqlStmn;
    private CallableStatement sqlCallStmn;
    
    private String queryDatosFojaObra;
    private ResultSet datosFojaObra;
    private String queryFojasDet;
    private ResultSet fojasDet;

    public CargarFoja(Connection con, Statement stmn, int numObra, int idFoja) {
        this.con = con;
        this.sqlStmn = stmn;
        this.queryDatosFojaObra = "select o.numobra, o.nomobra, o.fecinicio, f.fecha, f.idobra from guillermodb.obra o " +
                                  "inner join guillermodb.foja f on o.idobra = f.idobra " +
                                  "where f.idfoja = " + String.valueOf(idFoja) + " and o.idobra = guillermodb.fun_getidobra(" + String.valueOf(numObra) + ")";
        this.queryFojasDet = "select fd.iditem, i.denitem, fd.avaacuanterior, fd.avaactual  from guillermodb.fojadet fd " +
                             "inner join guillermodb.item i on (fd.idobra = i.idobra and fd.iditem = i.iditem) " +
                             "where fd.idfoja = " + String.valueOf(idFoja) + " and fd.idobra = guillermodb.fun_getidobra(" + String.valueOf(numObra) + ") " +
                             "order by fd.iditem";
        initComponents();
        this.datosFojaObra = this.getDatosFojaObra();
        try {
            if(datosFojaObra.next()) {
                this.txtNumObra.setText(String.valueOf(datosFojaObra.getInt(1)));
                this.txtNomObra.setText(datosFojaObra.getString(2));
                this.txtFechaInicio.setText(datosFojaObra.getDate(3).toString());
                this.txtFechaFoja.setText(datosFojaObra.getDate(4).toString());
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        cargarTablaFojasDet();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNumObra = new javax.swing.JLabel();
        txtNumObra = new javax.swing.JTextField();
        txtNomObra = new javax.swing.JTextField();
        lblNomObra = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFoja = new javax.swing.JLabel();
        txtFechaFoja = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFojaDet = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Foja");
        setResizable(false);

        lblNumObra.setText("Número de obra:");

        txtNumObra.setEditable(false);

        txtNomObra.setEditable(false);

        lblNomObra.setText("Nombre de obra:");

        txtFechaInicio.setEditable(false);

        lblFechaInicio.setText("Fecha de inicio:");

        lblFechaFoja.setText("Fecha de foja:");

        txtFechaFoja.setEditable(false);

        tblFojaDet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Ítem", "Avance acum.", "Avance mes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFojaDet.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblFojaDet);
        if (tblFojaDet.getColumnModel().getColumnCount() > 0) {
            tblFojaDet.getColumnModel().getColumn(0).setResizable(false);
            tblFojaDet.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblFojaDet.getColumnModel().getColumn(1).setResizable(false);
            tblFojaDet.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblFojaDet.getColumnModel().getColumn(2).setResizable(false);
            tblFojaDet.getColumnModel().getColumn(2).setPreferredWidth(84);
            tblFojaDet.getColumnModel().getColumn(3).setResizable(false);
            tblFojaDet.getColumnModel().getColumn(3).setPreferredWidth(84);
        }

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNumObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNomObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFechaInicio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomObra)
                            .addComponent(txtNumObra)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFechaFoja)
                                .addGap(5, 5, 5)
                                .addComponent(txtFechaFoja, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAtras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumObra)
                    .addComponent(lblNumObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNomObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomObra, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaInicio)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaFoja, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnAtras))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int idObra = 0;
        try {
            this.sqlCallStmn = con.prepareCall("{CALL GuillermoDB.PRC_UPDATEFOJADET(?, ?, ?, ?)}");
            this.sqlCallStmn.registerOutParameter(4, Types.INTEGER);
            this.datosFojaObra = this.getDatosFojaObra();
            if(this.datosFojaObra.next()) {
                idObra = this.datosFojaObra.getInt(5);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        for(int i = 0; i < tblFojaDet.getRowCount(); i++) {
            try {
                this.sqlCallStmn.setInt(1, idObra);
                this.sqlCallStmn.setInt(2, Integer.parseInt((String) tblFojaDet.getValueAt(i, 0)));
                if(tblFojaDet.getValueAt(i, 3) != null) {
                    this.sqlCallStmn.setFloat(3, Float.parseFloat((String) tblFojaDet.getValueAt(i, 3)));
                } else {
                    this.sqlCallStmn.setFloat(3, 0.0F);
                }
                this.sqlCallStmn.execute();
                int vError = this.sqlCallStmn.getInt(4);
                switch(vError) {
                    case 1:
                        JOptionPane.showMessageDialog(this, "No se aceptan valores negativos.", "Error con el item Nº" + (String) tblFojaDet.getValueAt(i, 0), JOptionPane.ERROR_MESSAGE);
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(this, "Valor ínvalido: representa un avance total mayor al 100%.", "Error con el item Nº" + (String) tblFojaDet.getValueAt(i, 0), JOptionPane.ERROR_MESSAGE);
                        break;
                    default:
                        break;  
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        System.out.println("-");
        cargarTablaFojasDet();        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        MenuFojador vMenuF = new MenuFojador(this.con, this.sqlStmn);
        vMenuF.setVisible(true);
        vMenuF.verPanelCargarFoja();
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void cargarTablaFojasDet() {
        this.fojasDet = this.getFojasDet();
        DefaultTableModel tblFojasDetModel = (DefaultTableModel) tblFojaDet.getModel();
        tblFojasDetModel.setRowCount(0);
        try {
            while(fojasDet.next()) {
                String idItem = String.valueOf(fojasDet.getInt(1));
                String denItem = fojasDet.getString(2);
                String avaAcum = String.valueOf(fojasDet.getDouble(3));
                String avaMes = String.valueOf(fojasDet.getDouble(4));
                if(fojasDet.wasNull()) {
                    avaMes = null;
                }
                String[] tblData = {idItem, denItem, avaAcum, avaMes};
                tblFojasDetModel.addRow(tblData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private ResultSet getDatosFojaObra() {
        ResultSet datosFojaObra = null;
        try {
            datosFojaObra = sqlStmn.executeQuery(queryDatosFojaObra);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return datosFojaObra;
    }
    
    private ResultSet getFojasDet() {
        ResultSet fojasDet = null;
        try {
            fojasDet = sqlStmn.executeQuery(queryFojasDet);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return fojasDet;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFechaFoja;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblNomObra;
    private javax.swing.JLabel lblNumObra;
    private javax.swing.JTable tblFojaDet;
    private javax.swing.JTextField txtFechaFoja;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtNomObra;
    private javax.swing.JTextField txtNumObra;
    // End of variables declaration//GEN-END:variables
}
