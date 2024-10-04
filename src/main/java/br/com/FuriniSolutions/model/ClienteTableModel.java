package br.com.FuriniSolutions.model;

import br.com.FuriniSolutions.bean.Cliente;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lucas
 */
public class ClienteTableModel extends AbstractTableModel {

    private List<Cliente> linhas = new LinkedList<Cliente>();
    private String[] colunas = {"ID", "Nome", "Endereço"};

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //metodo para colocar os valores nas colunas
    @Override
    public Object getValueAt(int linha, int coluna) {
        Cliente cliente = linhas.get(linha);

        switch (coluna) {
            case 0 -> {
                return cliente.getId();
            }
            case 1 -> {
                return cliente.getNome();
            }
            case 2 -> {
                return cliente.getEndereco();
            }
            default -> throw new AssertionError();
        }

    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    //adiciona a categoria na tabela
    public void add(Cliente cliente) {
        this.linhas.add(cliente);
        this.fireTableDataChanged();
    }

    //adiciona uma lista de categorias na lista desta classe
    public void addList(List<Cliente> clientes) {
        this.linhas = clientes;
        this.fireTableDataChanged();
    }

    //deleta uma categoria da lista da tabela
    public void delete(Cliente cliente) {
        this.linhas.remove(cliente);
        this.fireTableDataChanged();
    }
    
    public Cliente getCliente(int linha) {
        return linhas.get(linha);
    }

}
