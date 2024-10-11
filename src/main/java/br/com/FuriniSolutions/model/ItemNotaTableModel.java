package br.com.FuriniSolutions.model;

import br.com.FuriniSolutions.bean.ItemNota;
import br.com.FuriniSolutions.bean.Produto;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lucas
 */
public class ItemNotaTableModel extends AbstractTableModel {

    private List<ItemNota> linhas = new ArrayList<>();
    private String[] colunas = {"Descrição", "Quant.", "Valor (R$)", "Total"};
    private DecimalFormat formatadorDecimal = new DecimalFormat("#,##0.00");

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        ItemNota item = linhas.get(linha);
        return switch (coluna) {            
            case 0 ->
                item.getProduto().getDescricao();
            case 1 ->
                item.getQuantidade();
            case 2 ->
                formatadorDecimal.format(item.getValorItem());
            case 3 ->
                formatadorDecimal.format((item.getQuantidade() * item.getValorItem()));
            default ->
                throw new IllegalArgumentException("Coluna inválida: " + coluna);
        };

    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    public ItemNota getProduto(int linha) {
        return linhas.get(linha);
    }

    public void setProdutos(List<ItemNota> itens) {
        this.linhas = itens;
    }
    
    public void add(ItemNota item) {
        int rowIndex = linhas.size();
        this.linhas.add(item);
        this.fireTableRowsInserted(rowIndex, rowIndex);
    }

    public void addList(List<ItemNota> itens) {
        this.linhas.clear();
        this.linhas.addAll(itens);
        this.fireTableDataChanged();
    }

    public void delete(ItemNota item) {
        int rowIndex = linhas.indexOf(item);
        this.linhas.remove(item);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

}
