package br.com.FuriniSolutions.model;

import br.com.FuriniSolutions.bean.ItemNota;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lucas
 */
public class RelatorioItemNotaTableModel extends AbstractTableModel {

    private List<Object[]> linhas = new ArrayList<>();
    private String[] colunas = {"Descrição", "Quant. Total", "Total Vendido (R$)"};
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
        Object[] item = linhas.get(linha);
        return switch (coluna) {
            case 0 ->
                item[0]; // descricao
            case 1 ->
                item[1]; // quantidade total vendida
            case 2 ->
                formatadorDecimal.format(item[2]); //valor total vendido            
            default ->
                throw new IllegalArgumentException("Coluna inválida: " + coluna);
        };

    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    public void setDados(List<Object[]> itens) {
        this.linhas = itens;
        fireTableDataChanged();
    }

    public void add(Object[] item) {
        int rowIndex = linhas.size();
        this.linhas.add(item);
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    public void addList(List<Object[]> itens) {
        this.linhas.clear();
        this.linhas.addAll(itens);
        fireTableDataChanged();
    }

    public void delete(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < linhas.size()) {
            this.linhas.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }

    public Object[] getLinha(int linha) {
        return linhas.get(linha);
    }

    public List<Object[]> getList() {
        return this.linhas;
    }

    public void clear() {
        linhas.clear();
        fireTableDataChanged();
    }
}
