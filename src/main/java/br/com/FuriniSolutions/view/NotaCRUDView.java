/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.FuriniSolutions.view;

import br.com.FuriniSolutions.bean.ItemNota;
import br.com.FuriniSolutions.bean.Produto;
import br.com.FuriniSolutions.dao.ProdutoDAO;
import br.com.FuriniSolutions.model.ItemNotaTableModel;
import br.com.FuriniSolutions.util.ConnectionsFactory;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class NotaCRUDView extends javax.swing.JFrame {

    private List<Produto> produtos = new ArrayList<>();
    private Produto produtoSelecionado = new Produto();
    private DecimalFormat formatadorDecimal = new DecimalFormat("#,##0.00");

    public NotaCRUDView() {
        initComponents();
        ItemNotaTableModel tablemodel = new ItemNotaTableModel();
        tableProdutos.setModel(tablemodel);

        // Campos não editáveis
        jtfIdProduto.setBackground(Color.LIGHT_GRAY);
        jtfIdProduto.setForeground(Color.DARK_GRAY);
        jtfIdProduto.setEditable(false);

        jtfValor.setBackground(Color.LIGHT_GRAY);
        jtfValor.setForeground(Color.DARK_GRAY);
        jtfValor.setEditable(false);

        jtfTotal.setBackground(Color.LIGHT_GRAY);
        jtfTotal.setForeground(Color.DARK_GRAY);
        jtfTotal.setEditable(false);
        
        jtfIDCliente.setBackground(Color.LIGHT_GRAY);
        jtfIDCliente.setForeground(Color.DARK_GRAY);
        jtfIDCliente.setEditable(false);       

        //adicionando a lista do produto ao panel
        menuProdutos.add(panelPesquisaProdutos);

        // Adicionar o listener para o campo de descrição
        jtfDescricaoProduto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                buscarProdutos(jtfDescricaoProduto.getText().trim());  // Chama a função de busca
            }
        });

        // Adicionar mouse listener para selecionar o item na lista
        jltProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {  // Seleção com duplo clique
                    produtoSelecionado = produtos.get(jltProdutos.getSelectedIndex());
                    preencherCamposProduto(produtoSelecionado);  // Preenche os campos com o produto selecionado
                    menuProdutos.setVisible(false);  // Esconde o menu após a seleção
                }
            }
        });

        //adicionando listener na quantidade para permitir apenas numeros
        jtfQuantidade.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                // Permite números, backspace e delete
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    evt.consume(); // Ignora o evento de tecla se não for número ou backspace/delete
                }
            }
        });

    }

    private void buscarProdutos(String search) {
        try ( Connection connection = ConnectionsFactory.createConnetionToMySQL()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);

            // Se o campo de busca estiver vazio, busca todos os produtos
            if (search.trim().isEmpty()) {
                produtos = produtoDAO.findAll();  // Busca todos os produtos
            } else {
                produtos = produtoDAO.buscarPorDescricao(search);  // Filtra pela descrição
            }

            // Limitar a exibição de no máximo 5 itens
            int maxItems = Math.min(produtos.size(), 5);

            // Atualizando o JList com os produtos encontrados
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (int i = 0; i < maxItems; i++) {
                listModel.addElement(produtos.get(i).getDescricao());
            }
            jltProdutos.setModel(listModel);

            menuProdutos.show(jtfDescricaoProduto, 0, jtfDescricaoProduto.getHeight());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar produtos: " + e.getMessage());
        }
    }

    private void preencherCamposProduto(Produto produto) {
        jtfIdProduto.setText(String.valueOf(produto.getId()));
        jtfValor.setText(String.valueOf(formatadorDecimal.format(produto.getValor())));
        jtfDescricaoProduto.setText(String.valueOf(produto.getDescricao()));
        jtfTotal.setText(formatadorDecimal.format(produto.getValor()));
        jtfQuantidade.requestFocus();

    }

    private void limparCampos() {
        jtfIdProduto.setText("");
        jtfDescricaoProduto.setText("");
        jtfQuantidade.setText("");
        jtfTotal.setText("");
        jtfValor.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuProdutos = new javax.swing.JPopupMenu();
        panelPesquisaProdutos = new javax.swing.JPanel();
        jspProdutos = new javax.swing.JScrollPane();
        jltProdutos = new javax.swing.JList<>();
        jLayerCliente = new javax.swing.JLayeredPane();
        btnCancelarProduto = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        jtfIdProduto = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblValor1 = new javax.swing.JLabel();
        jtfValor = new javax.swing.JTextField();
        jtfQuantidade = new javax.swing.JTextField();
        lblValor = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jtfTotal = new javax.swing.JTextField();
        jtfDescricaoProduto = new javax.swing.JTextField();
        jLayerProduto = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jtfIDCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfINomeCliente = new javax.swing.JTextField();
        jLayerTable = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProdutos = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();

        menuProdutos.setBorder(null);
        menuProdutos.setFocusable(false);

        jspProdutos.setBorder(null);

        jspProdutos.setViewportView(jltProdutos);

        javax.swing.GroupLayout panelPesquisaProdutosLayout = new javax.swing.GroupLayout(panelPesquisaProdutos);
        panelPesquisaProdutos.setLayout(panelPesquisaProdutosLayout);
        panelPesquisaProdutosLayout.setHorizontalGroup(
            panelPesquisaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
            .addGroup(panelPesquisaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspProdutos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
        );
        panelPesquisaProdutosLayout.setVerticalGroup(
            panelPesquisaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 93, Short.MAX_VALUE)
            .addGroup(panelPesquisaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produto");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLayerCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Produto"));

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

        jtfIdProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfIdProdutoActionPerformed(evt);
            }
        });

        lblID.setText("ID");

        lblDescricao.setText("Descrição");

        lblValor1.setText("Quantidade");

        jtfValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfValorActionPerformed(evt);
            }
        });

        jtfQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfQuantidadeActionPerformed(evt);
            }
        });
        jtfQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfQuantidadeKeyReleased(evt);
            }
        });

        lblValor.setText("Valor (R$)");

        lblTotal.setText("Total (R$)");

        jtfTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTotalActionPerformed(evt);
            }
        });

        jtfDescricaoProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfDescricaoProdutoMouseClicked(evt);
            }
        });
        jtfDescricaoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDescricaoProdutoActionPerformed(evt);
            }
        });
        jtfDescricaoProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfDescricaoProdutoKeyReleased(evt);
            }
        });

        jLayerCliente.setLayer(btnCancelarProduto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerCliente.setLayer(btnAdicionar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerCliente.setLayer(jtfIdProduto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerCliente.setLayer(lblID, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerCliente.setLayer(lblDescricao, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerCliente.setLayer(lblValor1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerCliente.setLayer(jtfValor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerCliente.setLayer(jtfQuantidade, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerCliente.setLayer(lblValor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerCliente.setLayer(lblTotal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerCliente.setLayer(jtfTotal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerCliente.setLayer(jtfDescricaoProduto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayerClienteLayout = new javax.swing.GroupLayout(jLayerCliente);
        jLayerCliente.setLayout(jLayerClienteLayout);
        jLayerClienteLayout.setHorizontalGroup(
            jLayerClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayerClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayerClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayerClienteLayout.createSequentialGroup()
                        .addComponent(lblValor1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayerClienteLayout.createSequentialGroup()
                        .addComponent(lblID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfDescricaoProduto))
                    .addGroup(jLayerClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarProduto)))
                .addContainerGap())
        );
        jLayerClienteLayout.setVerticalGroup(
            jLayerClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayerClienteLayout.createSequentialGroup()
                .addGroup(jLayerClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblID)
                    .addComponent(lblDescricao)
                    .addComponent(jtfDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayerClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValor)
                    .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal)
                    .addComponent(jtfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValor1)
                    .addComponent(jtfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayerClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnCancelarProduto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayerProduto.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel1.setText("ID");

        jLabel2.setText("Nome");

        jLayerProduto.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerProduto.setLayer(jtfIDCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerProduto.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerProduto.setLayer(jtfINomeCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayerProdutoLayout = new javax.swing.GroupLayout(jLayerProduto);
        jLayerProduto.setLayout(jLayerProdutoLayout);
        jLayerProdutoLayout.setHorizontalGroup(
            jLayerProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayerProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfINomeCliente)
                .addContainerGap())
        );
        jLayerProdutoLayout.setVerticalGroup(
            jLayerProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayerProdutoLayout.createSequentialGroup()
                .addGroup(jLayerProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jtfINomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Lista de itens da nota");

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

        btnSalvar.setText("Salvar nota");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLayerTable.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerTable.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerTable.setLayer(btnSalvar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayerTableLayout = new javax.swing.GroupLayout(jLayerTable);
        jLayerTable.setLayout(jLayerTableLayout);
        jLayerTableLayout.setHorizontalGroup(
            jLayerTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayerTableLayout.createSequentialGroup()
                .addGroup(jLayerTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayerTableLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayerTableLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvar))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jLayerTableLayout.setVerticalGroup(
            jLayerTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayerTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayerProduto)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLayerTable))
                    .addComponent(jLayerCliente))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayerProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayerCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayerTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProdutoActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnCancelarProdutoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        if (produtoSelecionado != null && !jtfQuantidade.getText().isEmpty()) {
            ItemNota itemNota = new ItemNota();
            itemNota.setProduto(produtoSelecionado);
            itemNota.setQuantidade(Integer.parseInt(jtfQuantidade.getText()));
            itemNota.setValorItem(produtoSelecionado.getValor());

            ItemNotaTableModel model = (ItemNotaTableModel) tableProdutos.getModel();
            model.add(itemNota);
            limparCampos();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto e insira a quantidade.");
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void jtfIdProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIdProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdProdutoActionPerformed

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

    private void jtfDescricaoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDescricaoProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDescricaoProdutoActionPerformed

    private void jtfDescricaoProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDescricaoProdutoKeyReleased

    }//GEN-LAST:event_jtfDescricaoProdutoKeyReleased

    private void jtfQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfQuantidadeKeyReleased
        try {
            if (!jtfQuantidade.getText().isEmpty() && !jtfValor.getText().isEmpty()) {
                int quantidade = Integer.parseInt(jtfQuantidade.getText());

                double valor = formatadorDecimal.parse(jtfValor.getText()).doubleValue();

                // Calcula o total e formata o resultado
                double total = quantidade * valor;
                jtfTotal.setText(formatadorDecimal.format(total));
            } else {
                double valorUnitario = formatadorDecimal.parse(jtfValor.getText()).doubleValue();
                jtfTotal.setText(formatadorDecimal.format(valorUnitario));
            }
        } catch (NumberFormatException | ParseException e) {
            jtfTotal.setText("");
            System.out.println("Erro ao converter valores: " + e.getMessage());
        }
    }//GEN-LAST:event_jtfQuantidadeKeyReleased

    private void jtfDescricaoProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfDescricaoProdutoMouseClicked
        if (evt.getClickCount() == 2) {  // Seleção com duplo clique
            buscarProdutos(jtfDescricaoProduto.getText().trim());
        }
    }//GEN-LAST:event_jtfDescricaoProdutoMouseClicked

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayerCliente;
    private javax.swing.JLayeredPane jLayerProduto;
    private javax.swing.JLayeredPane jLayerTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> jltProdutos;
    private javax.swing.JScrollPane jspProdutos;
    private javax.swing.JTextField jtfDescricaoProduto;
    private javax.swing.JTextField jtfIDCliente;
    private javax.swing.JTextField jtfINomeCliente;
    private javax.swing.JTextField jtfIdProduto;
    private javax.swing.JTextField jtfQuantidade;
    private javax.swing.JTextField jtfTotal;
    private javax.swing.JTextField jtfValor;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblValor1;
    private javax.swing.JPopupMenu menuProdutos;
    private javax.swing.JPanel panelPesquisaProdutos;
    private javax.swing.JTable tableProdutos;
    // End of variables declaration//GEN-END:variables
}
