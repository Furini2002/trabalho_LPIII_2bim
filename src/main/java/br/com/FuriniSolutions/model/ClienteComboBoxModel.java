/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.FuriniSolutions.model;

import br.com.FuriniSolutions.bean.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author lucas
 */
public class ClienteComboBoxModel extends DefaultComboBoxModel<Cliente> {

    private List<Cliente> clientes;
    private Cliente selectedCliente;
    private List<ListDataListener> dataListeners = new ArrayList<>(); // Lista para armazenar os listeners

    public ClienteComboBoxModel(List<Cliente> clientes) {
        super();
        for (Cliente cliente : clientes) {
            this.addElement(cliente);
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof Cliente) {
            super.setSelectedItem(anItem);
        }
    }

    public void setClientes(List<Cliente> clientes) {
        removeAllElements(); // Limpa os elementos atuais

        for (Cliente cliente : clientes) {
            this.addElement(cliente); // Adiciona cada cliente ao modelo
        }
    }

    @Override
    public Object getSelectedItem() {
        return super.getSelectedItem(); 
    }

    
    
    
}
