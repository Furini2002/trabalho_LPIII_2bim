/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.FuriniSolutions.view;

import br.com.FuriniSolutions.bean.Cliente;
import br.com.FuriniSolutions.dao.ClienteDAO;
import br.com.FuriniSolutions.model.ClienteTableModel;
import br.com.FuriniSolutions.util.ConnectionsFactory;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class ClienteCRUDView extends javax.swing.JFrame {

    //ClienteDAO clienteDao = new ClienteDAO(ConnectionsFactory.createConnetionToMySQL());
    //private List<Observer> observers;
    private ClienteTableModel tbm;
    private Cliente clienteSelecionado;

    private enum OperationType {
        SAVE, EDIT
    };
    private OperationType type = OperationType.SAVE;

    public ClienteCRUDView() {
        initComponents();

        //diferenciando o jtf do id, pois não e editavel
        jtfId.setBackground(Color.LIGHT_GRAY);
        jtfId.setForeground(Color.DARK_GRAY);
        jtfId.setEditable(false);

        jtfNome.requestFocus();

        tbm = new ClienteTableModel();
        jtblCliente.setModel(tbm);

        try ( Connection con = ConnectionsFactory.createConnetionToMySQL()) {
            ClienteDAO dao = new ClienteDAO(con);
            tbm.addList(dao.findAll());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);

        jtblCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = jtblCliente.getSelectedRow();
                Cliente cliente = tbm.getCliente(linha);

                clienteSelecionado = cliente;

                if (jtfId.getText().trim().isEmpty()) {
                    btnSalvar.setEnabled(false);
                    btnCancelar.setEnabled(false);

                    btnEditar.setEnabled(true);
                    btnExcluir.setEnabled(true);
                }

            }

        });

    }

    private void populaForm(Cliente cliente) {
        jtfId.setText(String.valueOf(cliente.getId()));
        jtfNome.setText(cliente.getNome());
        jtfEndereco.setText(cliente.getEndereco());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtfNome = new javax.swing.JTextField();
        jtfEndereco = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblCliente = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jtfId = new javax.swing.JTextField();
        jlblID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cliente");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setText("Nome");

        jtfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNomeActionPerformed(evt);
            }
        });

        jLabel2.setText("Endereco");

        btnExcluir.setLabel("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnEditar.setLabel("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jtblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtblCliente);

        btnCancelar.setLabel("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalvar.setLabel("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel3.setText("Lista de clientes");

        jtfId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfIdActionPerformed(evt);
            }
        });

        jlblID.setText("ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jtfEndereco))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jlblID)
                        .addGap(18, 18, 18)
                        .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jtfNome)))
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnEditar)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNomeActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um item na tabela");
        } else {
            int confirmacao = JOptionPane.showConfirmDialog(this,
                    "Tem certeza de que deseja excluir este item?",
                    "Confirmação de Exclusão",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {

                try ( Connection con = ConnectionsFactory.createConnetionToMySQL()) {
                    ClienteDAO clienteDao = new ClienteDAO(con);

                    try {
                        if (clienteDao.delete(clienteSelecionado.getId())) {
                            tbm.delete(clienteSelecionado);
                            clienteSelecionado = null;

                            btnSalvar.setEnabled(true);
                            btnCancelar.setEnabled(true);

                            btnEditar.setEnabled(false);
                            btnExcluir.setEnabled(false);

                            limparCampos();
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Erro ao deletar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

            }
        }

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.type = OperationType.EDIT;

        btnSalvar.setEnabled(true);
        btnCancelar.setEnabled(true);

        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);

        populaForm(clienteSelecionado);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (jtfNome.getText().isEmpty()) {
            int resposta = JOptionPane.showConfirmDialog(null, "O nome do cliente não pode ser nulo", "Confirmação", JOptionPane.OK_OPTION);
        } else {
            if (this.type == OperationType.EDIT) { //edita no banco               
                if (clienteSelecionado == null) {
                    JOptionPane.showMessageDialog(this, "Selecione um item na tabela");
                } else {
                    int confirmacao = JOptionPane.showConfirmDialog(this,
                            "Tem certeza de que deseja salvar as alterações este item?",
                            "Confirmação de Edição",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacao == JOptionPane.YES_OPTION) {

                        clienteSelecionado.setNome(jtfNome.getText());
                        clienteSelecionado.setEndereco(jtfEndereco.getText());

                        try ( Connection con = ConnectionsFactory.createConnetionToMySQL()) {
                            ClienteDAO dao = new ClienteDAO(con);
                            try {
                                dao.update(clienteSelecionado);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "Erro ao salvar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }

                        tbm.fireTableDataChanged();

                        limparCampos();
                        this.type = OperationType.SAVE;

                        btnSalvar.setEnabled(true);
                        btnCancelar.setEnabled(true);

                        btnEditar.setEnabled(false);
                        btnExcluir.setEnabled(false);

                    } else {
                        limparCampos();
                    }

                }
            } else { //cadastra no banco                
                Cliente cliente = new Cliente();
                cliente.setNome(jtfNome.getText());
                cliente.setEndereco(jtfEndereco.getText());

                try ( Connection con = ConnectionsFactory.createConnetionToMySQL()) {
                    ClienteDAO dao = new ClienteDAO(con);
                    try {
                        dao.create(cliente);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Erro ao editar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

                limparCampos();
                tbm.add(cliente);
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void limparCampos() {
        jtfId.setText("");
        jtfNome.setText("");
        jtfEndereco.setText("");
    }

    private void jtfIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        jtblCliente.clearSelection();

        btnSalvar.setEnabled(true);
        btnCancelar.setEnabled(true);

        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }//GEN-LAST:event_formMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClienteCRUDView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteCRUDView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteCRUDView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteCRUDView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteCRUDView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlblID;
    private javax.swing.JTable jtblCliente;
    private javax.swing.JTextField jtfEndereco;
    private javax.swing.JTextField jtfId;
    private javax.swing.JTextField jtfNome;
    // End of variables declaration//GEN-END:variables
}
