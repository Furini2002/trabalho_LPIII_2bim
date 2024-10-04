package br.com.FuriniSolutions.dao;

import br.com.FuriniSolutions.bean.Cliente;
import br.com.FuriniSolutions.bean.ItemNota;
import br.com.FuriniSolutions.bean.NotaFiscal;
import br.com.FuriniSolutions.bean.Produto;
import br.com.FuriniSolutions.util.ConnectionsFactory;
import br.com.FuriniSolutions.util.DataUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ItemNotaDAO implements Dao<Integer, ItemNota> { // <o tipo de dados da PK, o tipo de dados que ela vai informar ou receber>

    protected Connection con;

    public ItemNotaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void create(ItemNota entity) {
        String sql = "INSERT INTO itemnota (quantidade, valorItem, produto_id, notaFiscal_id) values (?, ?, ?, ?);";

        //try with resouces - fecha a conexao ao final
        try ( PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            query.setDouble(1, entity.getValorItem());
            query.setInt(2, entity.getQuantidade());
            query.setInt(3, entity.getProduto().getId());
            query.setInt(4, entity.getNotaFiscal().getId());
            query.executeUpdate();

            try ( ResultSet rs = query.getGeneratedKeys()) {
                if (rs.next()) {  // Move o cursor para a primeira linha, pois pro padrao vem antes
                    entity.setId(rs.getInt(1)); //coloca o id no itemnota
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ItemNota retrive(Integer pk) {
        ItemNota itemNota = null; //produto a ser retornado

        if (pk != null) {
            String sql = "SELECT id, quantidade, valorItem, produto_id, notaFiscal_id FROM itemnota WHERE id = ?";

            try ( PreparedStatement query = con.prepareStatement(sql)) {
                query.setInt(1, pk);//coloca a pk que foi colocada como parametro do metodo

                try ( ResultSet rs = query.executeQuery()) {

                    if (rs.next()) {//passa o cursor para a primeira linha
                        itemNota = new ItemNota();
                        itemNota.setId(rs.getInt("id"));
                        itemNota.setQuantidade(rs.getInt("quantidade"));
                        itemNota.setValorItem(rs.getDouble("valorItem"));

                        ProdutoDAO produtoDao = new ProdutoDAO(ConnectionsFactory.createConnetionToMySQL());
                        itemNota.setProduto(produtoDao.retrive(rs.getInt("produto_id")));

                        NotaFiscalDAO notaFiscalDao = new NotaFiscalDAO(ConnectionsFactory.createConnetionToMySQL());
                        itemNota.setNotaFiscal(notaFiscalDao.retrive(rs.getInt("notaFiscal_id")));
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return itemNota;
    }

    @Override
    public void update(ItemNota entity) {
        String sql = "UPDATE itemnota SET quantidade = ?, valorItem = ?, produto_id = ?, notaFiscal_id = ? WHERE id = ?";

        try ( PreparedStatement query = con.prepareStatement(sql)) {            
            query.setInt(1, entity.getQuantidade());
            query.setDouble(2, entity.getValorItem());
            query.setInt(3, entity.getProduto().getId());
            query.setInt(4, entity.getNotaFiscal().getId());
            query.setInt(5, entity.getId());
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer pk) {
        String sql = "DELETE FROM itemnota WHERE id = ?";

        try ( PreparedStatement query = con.prepareStatement(sql)) {
            query.setInt(1, pk);

            query.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<ItemNota> findAll() {
        List<ItemNota> itemnotas = new LinkedList<>();

        String sql = "SELECT id, quantidade, valorItem, produto_id, notaFiscal_id FROM itemnota";

        try ( PreparedStatement query = con.prepareStatement(sql)) {
            try ( ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    ItemNota itemNota = new ItemNota();
                    itemNota.setId(rs.getInt("id"));
                    itemNota.setQuantidade(rs.getInt("quantidade"));
                    itemNota.setValorItem(rs.getDouble("valorItem"));

                    ProdutoDAO produtoDao = new ProdutoDAO(ConnectionsFactory.createConnetionToMySQL());
                    itemNota.setProduto(produtoDao.retrive(rs.getInt("produto_id")));

                    NotaFiscalDAO notaFiscalDao = new NotaFiscalDAO(ConnectionsFactory.createConnetionToMySQL());
                    itemNota.setNotaFiscal(notaFiscalDao.retrive(rs.getInt("notaFiscal_id")));
                    
                    itemnotas.add(itemNota);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return itemnotas;
    }

}
