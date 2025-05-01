package br.com.FuriniSolutions.util;

import javax.swing.*;
import java.awt.*;

public class LoadingDialog extends JDialog {

    private JProgressBar progressBar;

    public LoadingDialog(Frame parent) {
        super(parent, "Carregando", true); 
        initComponents();
    }

    private void initComponents() {
        // Cria um painel com margens para organizar os componentes
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label para exibir uma mensagem de carregamento
        JLabel label = new JLabel("Carregando, por favor aguarde...");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        // Configura a barra de progresso em modo indeterminado
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLUE);
        progressBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(progressBar, BorderLayout.CENTER);

        // Adiciona o painel ao conteúdo do diálogo
        getContentPane().add(panel);

        // Ajusta o tamanho do diálogo conforme o conteúdo e centraliza em relação à janela pai
        pack();
        setLocationRelativeTo(getParent());
    }
    
}
