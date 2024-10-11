package br.com.FuriniSolutions.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoSearchJListDemo extends JFrame {

    private JTextField searchField;
    private JList<String> resultList;
    private DefaultListModel<String> listModel;
    private List<String> produtos;

    public ProdutoSearchJListDemo() {
        initComponents();
    }

    private void initComponents() {
        // Simulação de uma lista de produtos
        produtos = new ArrayList<>();
        produtos.add("Produto A");
        produtos.add("Produto B");
        produtos.add("Produto C");
        produtos.add("Produto D");

        // Campo de pesquisa
        searchField = new JTextField(20);

        // Modelo da JList
        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Inicialmente, a lista é invisível
        resultList.setVisible(false);

        // Adicionar o KeyListener para capturar o texto digitado no JTextField
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchQuery = searchField.getText();
                if (searchQuery.length() >= 3) { // Começar a filtrar após 3 caracteres
                    filtrarProdutos(searchQuery);
                } else {
                    // Se menos de 3 caracteres, limpa e esconde a lista
                    resultList.setVisible(false);
                }
            }
        });

        // Adicionar um ouvinte de clique para a JList
        resultList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Verifica se é um duplo clique
                    String produtoSelecionado = resultList.getSelectedValue();
                    if (produtoSelecionado != null) {
                        JOptionPane.showMessageDialog(null, "Produto selecionado: " + produtoSelecionado);
                        searchField.setText(produtoSelecionado); // Define o texto no campo de pesquisa
                        resultList.setVisible(false); // Esconde a lista após a seleção
                    }
                }
            }
        });

        // Layout da janela
        setLayout(new BorderLayout());
        add(new JLabel("Pesquisar Produto:"), BorderLayout.NORTH);
        add(searchField, BorderLayout.CENTER);
        add(new JScrollPane(resultList), BorderLayout.SOUTH);

        // Configuração da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    // Método para filtrar os produtos com base na entrada do usuário
    private void filtrarProdutos(String query) {
        List<String> produtosFiltrados = new ArrayList<>();
        for (String produto : produtos) {
            if (produto.toLowerCase().contains(query.toLowerCase())) {
                produtosFiltrados.add(produto);
            }
        }
        
        if (!produtosFiltrados.isEmpty()) {
            updateList(produtosFiltrados);
            resultList.setVisible(true); // Mostra a lista quando há resultados
        } else {
            resultList.setVisible(false); // Esconde a lista quando não há resultados
        }
    }

    // Método para atualizar a JList exibida
    private void updateList(List<String> produtos) {
        listModel.clear();  // Limpa os itens atuais
        for (String produto : produtos) {
            listModel.addElement(produto);
        }
    }

    public static void main(String[] args) {
        // Executa a interface gráfica na thread principal
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProdutoSearchJListDemo(); // Inicia a aplicação
            }
        });
    }
}
