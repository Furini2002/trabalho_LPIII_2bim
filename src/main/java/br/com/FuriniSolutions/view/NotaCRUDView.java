/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.FuriniSolutions.view;

import br.com.FuriniSolutions.bean.Produto;
import br.com.FuriniSolutions.dao.ProdutoDAO;
import br.com.FuriniSolutions.model.ProdutoComboBoxModel;
import br.com.FuriniSolutions.model.ProdutoTableModel;
import br.com.FuriniSolutions.util.ConnectionsFactory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class NotaCRUDView extends javax.swing.JFrame {

    List<Produto> produtos = new ArrayList<>();

    public NotaCRUDView() {
        initComponents();

        // Campos não editáveis
        jtfId.setBackground(Color.LIGHT_GRAY);
        jtfId.setForeground(Color.DARK_GRAY);
        jtfId.setEditable(false);

        jtfValor.setBackground(Color.LIGHT_GRAY);
        jtfValor.setForeground(Color.DARK_GRAY);
        jtfValor.setEditable(false);

        jtfTotal.setBackground(Color.LIGHT_GRAY);
        jtfTotal.setForeground(Color.DARK_GRAY);
        jtfTotal.setEditable(false);

        jcbDescricao.requestFocus();

        // Carregar todos os produtos do banco de dados
        try ( Connection connection = ConnectionsFactory.createConnetionToMySQL()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtos = produtoDAO.findAll();  // Carregar todos os produtos inicialmente
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar produtos: " + e.getMessage());
        }

        // Configurando o ComboBox com todos os produtos
        ProdutoComboBoxModel comboBoxModel = new ProdutoComboBoxModel(produtos);
        jcbDescricao.setModel(comboBoxModel);
        jcbDescricao.setEditable(true);
        jcbDescricao.setSelectedItem(null);  // Iniciar sem seleção

        // Capturar a entrada no campo de texto do ComboBox
        JTextField textField = (JTextField) jcbDescricao.getEditor().getEditorComponent();

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchQuery = textField.getText();
                if (searchQuery.length() >= 4) {
                    filtrarProdutos(searchQuery);  // Filtrar os produtos conforme a entrada
                } else {
                    // Restaurar a lista completa se o campo de pesquisa estiver vazio
                    comboBoxModel.setProdutos(produtos);
                    jcbDescricao.setSelectedItem(null);
                }

                // Evitar que o ComboBox selecione automaticamente o primeiro item
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    jcbDescricao.setSelectedItem(null);
                }
            }
        });

        // Preencher campos ao selecionar um produto (somente quando o Enter é pressionado ou o item é clicado)
        jcbDescricao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getModifiers() == 0) {  // Ignorar eventos de seleção automática
                    Produto produtoSelecionado = (Produto) jcbDescricao.getSelectedItem();
                    if (produtoSelecionado != null) {
                        preencherCamposProduto(produtoSelecionado);
                    }
                }
            }
        });
    }

    // Método para filtrar produtos com base na entrada de texto
    private void filtrarProdutos(String query) {
        List<Produto> produtosFiltrados = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getDescricao().toLowerCase().contains(query.toLowerCase())) {
                produtosFiltrados.add(produto);
            }
        }

        // Atualizar o modelo do ComboBox com os produtos filtrados
        ProdutoComboBoxModel comboBoxModel = (ProdutoComboBoxModel) jcbDescricao.getModel();
        comboBoxModel.setProdutos(produtosFiltrados);

        // Limpar a seleção e mostrar o popup com os resultados filtrados
        jcbDescricao.setSelectedItem(null);
        if (!produtosFiltrados.isEmpty()) {
            jcbDescricao.showPopup();  // Mostrar sugestões filtradas
        }
    }

    // Método para preencher os campos de texto com base no produto selecionado
    private void preencherCamposProduto(Produto produto) {
        jtfId.setText(String.valueOf(produto.getId()));
        jtfValor.setText(String.valueOf(produto.getValor()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDescricao = new javax.swing.JLabel();
        jtfValor = new javax.swing.JTextField();
        lblValor = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProdutos = new javax.swing.JTable();
        btnCancelarProduto = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jtfId = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        lblValor1 = new javax.swing.JLabel();
        jtfQuantidade = new javax.swing.JTextField();
        lblValor2 = new javax.swing.JLabel();
        jtfTotal = new javax.swing.JTextField();
        jcbDescricao = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produto");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        lblDescricao.setText("Descrição");

        jtfValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfValorActionPerformed(evt);
            }
        });

        lblValor.setText("Valor (R$)");

        btnSalvar.setText("Salvar nota");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        tableProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableProdutos);

        btnCancelarProduto.setLabel("Cancelar");
        btnCancelarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProdutoActionPerformed(evt);
            }
        });

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        jLabel3.setText("Lista de itens da nota");

        jtfId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfIdActionPerformed(evt);
            }
        });

        lblID.setText("ID");

        lblValor1.setText("Quantidade");

        jtfQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfQuantidadeActionPerformed(evt);
            }
        });

        lblValor2.setText("Total (R$)");

        jtfTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTotalActionPerformed(evt);
            }
        });

        jcbDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDescricaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblValor1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtfQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblValor)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblValor2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblID)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblDescricao)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jcbDescricao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdicionar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelarProduto))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblID)
                    .addComponent(lblDescricao)
                    .addComponent(jcbDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValor)
                    .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValor2)
                    .addComponent(jtfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValor1)
                    .addComponent(jtfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnCancelarProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar)
                .addGap(7, 7, 7))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProdutoActionPerformed

    }//GEN-LAST:event_btnCancelarProdutoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed


    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void limparCampos() {
        jtfId.setText("");
        jtfValor.setText("");
    }

    private void jtfIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void jtfValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfValorActionPerformed

    private void jtfQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfQuantidadeActionPerformed

    private void jtfTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfTotalActionPerformed

    private void jcbDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbDescricaoActionPerformed

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
            java.util.logging.Logger.getLogger(NotaCRUDView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotaCRUDView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotaCRUDView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotaCRUDView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotaCRUDView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelarProduto;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<Produto> jcbDescricao;
    private javax.swing.JTextField jtfId;
    private javax.swing.JTextField jtfQuantidade;
    private javax.swing.JTextField jtfTotal;
    private javax.swing.JTextField jtfValor;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblValor1;
    private javax.swing.JLabel lblValor2;
    private javax.swing.JTable tableProdutos;
    // End of variables declaration//GEN-END:variables
}
