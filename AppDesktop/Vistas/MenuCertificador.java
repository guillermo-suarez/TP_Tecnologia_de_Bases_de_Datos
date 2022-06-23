package Vistas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class MenuCertificador extends javax.swing.JFrame {
    
    private ListSelectionModel tblListFojasModel;
     private ListSelectionModel tblListCertificadosModel;
    private Connection con;
    private Statement sqlStmn;
    private String certiSeleccionado;
    private String numObraSeleccionado;
    private CallableStatement callSqlStmn;
    private ResultSet fojasCertificables;
    private ResultSet certificadosAbiertos;
    private List<String> numObras = new ArrayList();
    
    private String queryFojasCertificables = "SELECT f.idfoja, o.nomobra, o.numobra FROM GuillermoDB.FOJA f " +
                                             "INNER JOIN GuillermoDB.OBRA o ON o.idobra = f.idobra " +
                                             "WHERE GuillermoDB.fun_estacertificada(f.idfoja) = 0 " +
                                             "AND GuillermoDB.fun_fojaestavacia(f.idfoja) = 0" + 
                                             "ORDER BY f.idfoja";
    private String queryCertificadosAbiertos = "SELECT o.numobra, o.nomobra, cp.nrocertificado FROM GuillermoDB.Obra o " +
                                               "INNER JOIN GuillermoDB.certipago cp on cp.idobra = o.idobra " +
                                               "INNER JOIN GuillermoDB.certiobra co on (co.nrocertificado = cp.nrocertificado AND co.idobra = o.idobra) " +
                                               "WHERE GuillermoDB.fun_estacertificada(co.idfoja) = 1";
    
    public MenuCertificador(Connection con, Statement sqlStmn) {
        this.con = con;
        this.sqlStmn = sqlStmn;
        initComponents();
        this.verPanelConsultas();
        tblListFojasModel = tblFojas.getSelectionModel();  
        tblListCertificadosModel = tblCertificados.getSelectionModel();         
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlConsultas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumObraCP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNumCertipago = new javax.swing.JTextField();
        btnVerCertiPago = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNumObraCO = new javax.swing.JTextField();
        txtNumCertiobra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnVerCertiObra = new javax.swing.JButton();
        pnlCrearCertificados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFojas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnCrearCertificados = new javax.swing.JButton();
        pnlCerrarCertificado = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCertificados = new javax.swing.JTable();
        btnCerrarCertificado = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuSesion = new javax.swing.JMenu();
        itemCerrarSesion = new javax.swing.JMenuItem();
        menuCertificados = new javax.swing.JMenu();
        itemCrearCertificados = new javax.swing.JMenuItem();
        itemCerrarCertificados = new javax.swing.JMenuItem();
        menuConsultas = new javax.swing.JMenu();
        itemVerConsultas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú de Certificador");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ver certificado de pago");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Número de Obra:");

        jLabel6.setText("Número de Certificado de Pago:");

        btnVerCertiPago.setText("Ver");
        btnVerCertiPago.setAlignmentX(0.5F);
        btnVerCertiPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerCertiPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVerCertiPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumObraCP)
                            .addComponent(txtNumCertipago))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumObraCP)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNumCertipago))
                .addGap(18, 18, 18)
                .addComponent(btnVerCertiPago)
                .addGap(121, 121, 121))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ver certificado de obra");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Número de Obra:");

        jLabel8.setText("Número de Certificado de Obra:");

        btnVerCertiObra.setText("Ver");
        btnVerCertiObra.setAlignmentX(0.5F);
        btnVerCertiObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerCertiObraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVerCertiObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumObraCO, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(txtNumCertiobra))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumObraCO)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNumCertiobra))
                .addGap(18, 18, 18)
                .addComponent(btnVerCertiObra)
                .addGap(121, 121, 121))
        );

        javax.swing.GroupLayout pnlConsultasLayout = new javax.swing.GroupLayout(pnlConsultas);
        pnlConsultas.setLayout(pnlConsultasLayout);
        pnlConsultasLayout.setHorizontalGroup(
            pnlConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlConsultasLayout.setVerticalGroup(
            pnlConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tblFojas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Foja", "Obra", "Nro Obra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFojas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFojasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFojas);
        if (tblFojas.getColumnModel().getColumnCount() > 0) {
            tblFojas.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblFojas.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jLabel1.setText("Lista de fojas certificables");

        btnCrearCertificados.setText("Crear certificado");
        btnCrearCertificados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCertificadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCrearCertificadosLayout = new javax.swing.GroupLayout(pnlCrearCertificados);
        pnlCrearCertificados.setLayout(pnlCrearCertificadosLayout);
        pnlCrearCertificadosLayout.setHorizontalGroup(
            pnlCrearCertificadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrearCertificadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCrearCertificadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                    .addGroup(pnlCrearCertificadosLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCrearCertificados, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlCrearCertificadosLayout.setVerticalGroup(
            pnlCrearCertificadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrearCertificadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCrearCertificadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnCrearCertificados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tblCertificados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro Obra", "Obra", "Certificado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCertificados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCertificados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCertificadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCertificados);
        if (tblCertificados.getColumnModel().getColumnCount() > 0) {
            tblCertificados.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblCertificados.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblCertificados.getColumnModel().getColumn(2).setPreferredWidth(15);
        }

        btnCerrarCertificado.setText("Cerrar Certificado");
        btnCerrarCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarCertificadoActionPerformed(evt);
            }
        });

        jLabel2.setText("Certificados pendientes de cerrar");

        javax.swing.GroupLayout pnlCerrarCertificadoLayout = new javax.swing.GroupLayout(pnlCerrarCertificado);
        pnlCerrarCertificado.setLayout(pnlCerrarCertificadoLayout);
        pnlCerrarCertificadoLayout.setHorizontalGroup(
            pnlCerrarCertificadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCerrarCertificadoLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(pnlCerrarCertificadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlCerrarCertificadoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrarCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        pnlCerrarCertificadoLayout.setVerticalGroup(
            pnlCerrarCertificadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCerrarCertificadoLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(pnlCerrarCertificadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrarCertificado)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        menuSesion.setText("Sesión");

        itemCerrarSesion.setText("Cerrar Sesión");
        itemCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCerrarSesionActionPerformed(evt);
            }
        });
        menuSesion.add(itemCerrarSesion);

        jMenuBar1.add(menuSesion);

        menuCertificados.setText("Certificados");

        itemCrearCertificados.setText("Crear certificados");
        itemCrearCertificados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCrearCertificadosActionPerformed(evt);
            }
        });
        menuCertificados.add(itemCrearCertificados);

        itemCerrarCertificados.setText("Cerrar certificados");
        itemCerrarCertificados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCerrarCertificadosActionPerformed(evt);
            }
        });
        menuCertificados.add(itemCerrarCertificados);

        jMenuBar1.add(menuCertificados);

        menuConsultas.setText("Consultas");

        itemVerConsultas.setText("Ver consultas");
        itemVerConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVerConsultasActionPerformed(evt);
            }
        });
        menuConsultas.add(itemVerConsultas);

        jMenuBar1.add(menuConsultas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCrearCertificados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlCerrarCertificado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlConsultas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCrearCertificados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlCerrarCertificado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemCrearCertificadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCrearCertificadosActionPerformed
        this.verPanelCrearCertificados();
        try{
            cargarTablaFojas();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_itemCrearCertificadosActionPerformed

    private void cargarTablaFojas() throws SQLException{
        this.fojasCertificables = this.getFojasCertificables();
        DefaultTableModel tblFojasModel = (DefaultTableModel) tblFojas.getModel();
        tblFojasModel.setRowCount(0);
        while(fojasCertificables.next())
        {
            String idFoja = String.valueOf(fojasCertificables.getInt(1));
            String nomObra = fojasCertificables.getString(2);
            String numObra = fojasCertificables.getString(3);
            String[] tblData = {idFoja, nomObra, numObra};
            tblFojasModel.addRow(tblData);
        }
        deselectTblFojas();
    }
    
     private void cargarTablaCertificados() throws SQLException{
        this.certificadosAbiertos = this.getCertificadosAbiertos();
        DefaultTableModel tblCertificadosModel = (DefaultTableModel) tblCertificados.getModel();
        tblCertificadosModel.setRowCount(0);
        while(certificadosAbiertos.next())
        {
            String numObra = String.valueOf(certificadosAbiertos.getInt(1));
            String nomObra = certificadosAbiertos.getString(2);
            String nroCerti = String.valueOf(certificadosAbiertos.getInt(3));
            String[] tblData = {numObra, nomObra, nroCerti};
            tblCertificadosModel.addRow(tblData);
        }
        deselectTblCertificados();
    }
     
    private void deselectTblCertificados(){
        this.tblCertificados.clearSelection();
        this.btnCerrarCertificado.setEnabled(false);
    }
    
    private void deselectTblFojas(){
        this.tblFojas.clearSelection();
        this.btnCrearCertificados.setEnabled(false);
    }
    
    private ResultSet getFojasCertificables(){
        ResultSet fojasCertificables = null;
        try {
            fojasCertificables = sqlStmn.executeQuery(queryFojasCertificables);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return fojasCertificables;
    }
    
    private ResultSet getCertificadosAbiertos(){
        ResultSet certificadosAbiertos = null;
        try {
            certificadosAbiertos = sqlStmn.executeQuery(queryCertificadosAbiertos);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return certificadosAbiertos;
    }
   
    private void itemCerrarCertificadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCerrarCertificadosActionPerformed
        this.verPanelCerrarCertificados();
        try {
            cargarTablaCertificados();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_itemCerrarCertificadosActionPerformed

    private void btnCrearCertificadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCertificadosActionPerformed
        try{
            callSqlStmn = con.prepareCall("{CALL GuillermoDB.PRC_CREARCERTIFICADOS(?, ?, ?)}");
            callSqlStmn.registerOutParameter(2, Types.INTEGER);
            callSqlStmn.registerOutParameter(3, Types.INTEGER);
            for(String numObra: numObras) {
                System.out.println(numObra);
                callSqlStmn.setInt(1, Integer.valueOf(numObra));
                callSqlStmn.execute();
                VerCertiObra certiObra = new VerCertiObra(this.con, this.sqlStmn, Integer.parseInt(numObra), callSqlStmn.getInt(3));
                certiObra.setVisible(true);
            }
            cargarTablaFojas();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearCertificadosActionPerformed

    private void tblFojasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFojasMouseClicked
        if(this.tblListFojasModel.getSelectedItemsCount() > 0){
            this.btnCrearCertificados.setEnabled(true);
            this.numObras.clear();
            int[] index = tblFojas.getSelectedRows();
            for(int i = 0; i < tblFojas.getSelectedRowCount(); i++){
                String numObra = String.valueOf(tblFojas.getValueAt(index[i], 2));
                if(!this.numObras.contains(numObra))
                {
                    this.numObras.add(numObra);
                }
            }
        }
    }//GEN-LAST:event_tblFojasMouseClicked

    private void btnCerrarCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCertificadoActionPerformed
        try {
            callSqlStmn = con.prepareCall("{CALL GuillermoDB.PRC_CERRARCERT(?, ?, ?)}");
            callSqlStmn.registerOutParameter(3, Types.INTEGER);
            callSqlStmn.setInt(1, Integer.valueOf(numObraSeleccionado));
            callSqlStmn.setInt(2, Integer.valueOf(certiSeleccionado));
            callSqlStmn.execute();
            cargarTablaCertificados();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCerrarCertificadoActionPerformed

    private void tblCertificadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCertificadosMouseClicked
        if(this.tblListCertificadosModel.getSelectedItemsCount() == 1) {
            btnCerrarCertificado.setEnabled(true);
            numObraSeleccionado = String.valueOf(tblCertificados.getValueAt(tblCertificados.getSelectedRow(), 0));
            certiSeleccionado = String.valueOf(tblCertificados.getValueAt(tblCertificados.getSelectedRow(), 2));
        } else {
            btnCerrarCertificado.setEnabled(false);
        }
    }//GEN-LAST:event_tblCertificadosMouseClicked

    private void itemCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCerrarSesionActionPerformed
        if(JOptionPane.showConfirmDialog(this, "¿Seguro que desea cerrar sesión?", "Cerrar sesión",
                                         JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                if(this.sqlStmn != null) {
                    this.sqlStmn.close();
                }
                if(this.callSqlStmn != null) {
                    this.callSqlStmn.close();
                }
                if(this.con != null) {
                    this.con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            Login vLogin = new Login();
            vLogin.setVisible(true);
            this.dispose();
        } else {
            
        }
    }//GEN-LAST:event_itemCerrarSesionActionPerformed

    private void itemVerConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVerConsultasActionPerformed
        this.verPanelConsultas();
    }//GEN-LAST:event_itemVerConsultasActionPerformed

    private void btnVerCertiPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerCertiPagoActionPerformed
        try {
            Integer numObra = Integer.parseInt(this.txtNumObraCP.getText());
            Integer nroCertiPago = Integer.parseInt(this.txtNumCertipago.getText());
            CertiPago certiPago = new CertiPago(this.con, this.sqlStmn, numObra, nroCertiPago);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerCertiPagoActionPerformed

    private void btnVerCertiObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerCertiObraActionPerformed
        try {
            Integer numObra = Integer.parseInt(this.txtNumObraCO.getText());
            Integer nroCertiObra = Integer.parseInt(this.txtNumCertiobra.getText());
            VerCertiObra certiObra = new VerCertiObra(this.con, this.sqlStmn, numObra, nroCertiObra);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerCertiObraActionPerformed

    private void verPanelConsultas() {
        this.pnlCerrarCertificado.setVisible((false));
        this.pnlCrearCertificados.setVisible(false);
        this.pnlConsultas.setVisible(true);
    }
    
    private void verPanelCrearCertificados(){
        this.pnlCerrarCertificado.setVisible((false));
        this.pnlCrearCertificados.setVisible(true);
        this.pnlConsultas.setVisible(false);
    }
    
    private void verPanelCerrarCertificados() {
        this.pnlCerrarCertificado.setVisible((true));
        this.pnlCrearCertificados.setVisible(false);
        this.pnlConsultas.setVisible(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarCertificado;
    private javax.swing.JButton btnCrearCertificados;
    private javax.swing.JButton btnVerCertiObra;
    private javax.swing.JButton btnVerCertiPago;
    private javax.swing.JMenuItem itemCerrarCertificados;
    private javax.swing.JMenuItem itemCerrarSesion;
    private javax.swing.JMenuItem itemCrearCertificados;
    private javax.swing.JMenuItem itemVerConsultas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu menuCertificados;
    private javax.swing.JMenu menuConsultas;
    private javax.swing.JMenu menuSesion;
    private javax.swing.JPanel pnlCerrarCertificado;
    private javax.swing.JPanel pnlConsultas;
    private javax.swing.JPanel pnlCrearCertificados;
    private javax.swing.JTable tblCertificados;
    private javax.swing.JTable tblFojas;
    private javax.swing.JTextField txtNumCertiobra;
    private javax.swing.JTextField txtNumCertipago;
    private javax.swing.JTextField txtNumObraCO;
    private javax.swing.JTextField txtNumObraCP;
    // End of variables declaration//GEN-END:variables

    
}
