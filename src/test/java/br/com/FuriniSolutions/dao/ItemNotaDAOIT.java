package br.com.FuriniSolutions.dao;

import br.com.FuriniSolutions.bean.Cliente;
import br.com.FuriniSolutions.bean.ItemNota;
import br.com.FuriniSolutions.bean.NotaFiscal;
import br.com.FuriniSolutions.bean.Produto;
import br.com.FuriniSolutions.util.ConnectionsFactory;
import br.com.FuriniSolutions.util.DataUtil;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste para ItemNotaDAO.
 */
public class ItemNotaDAOIT {

    private Connection connection;
    private ItemNotaDAO itemNotaDAO;
    private ProdutoDAO produtoDAO;
    private NotaFiscalDAO notaFiscalDAO;
    private ClienteDAO clienteDAO;

    @BeforeEach
    public void setUp() {
        // Inicializando a conexão e os DAOs
        connection = ConnectionsFactory.createConnetionToMySQL();
        itemNotaDAO = new ItemNotaDAO(connection);
        produtoDAO = new ProdutoDAO(connection);
        notaFiscalDAO = new NotaFiscalDAO(connection);
        clienteDAO = new ClienteDAO(connection);
    }

    @AfterEach
    public void tearDown() {
        // Fechando a conexão após cada teste
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    @Test
    public void testCreate() {
        System.out.println("TESTANDO CREATE");

        // Criando produto
        Produto produto = new Produto();
        produto.setDescricao("Produto Teste");
        produto.setValor(20.0);
        produtoDAO.create(produto);

        // Criando cliente e nota fiscal
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setEndereco("Rua Exemplo");
        clienteDAO.create(cliente);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setDataEmissao(DataUtil.dataAtual());
        notaFiscal.setCliente(cliente);
        notaFiscalDAO.create(notaFiscal);

        // Criando item nota
        ItemNota itemNota = new ItemNota();
        itemNota.setProduto(produto);
        itemNota.setNotaFiscal(notaFiscal);
        itemNota.setQuantidade(5);
        itemNota.setValorItem(100.0);

        itemNotaDAO.create(itemNota);

        // Verificando se o ID foi gerado
        assertNotNull(itemNota.getId(), "O ID do ItemNota não deve ser nulo após a criação.");
        System.out.println(itemNota);
    }

    @Test
    public void testRetrieve() {
        System.out.println("TESTANDO RETRIEVE");

        // Criando e salvando um itemNota para teste
        Produto produto = new Produto();
        produto.setDescricao("Produto Teste");
        produto.setValor(20.0);
        produtoDAO.create(produto);

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setEndereco("Rua Exemplo");
        clienteDAO.create(cliente);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setDataEmissao(DataUtil.dataAtual());
        notaFiscal.setCliente(cliente);
        notaFiscalDAO.create(notaFiscal);

        ItemNota itemNota = new ItemNota();
        itemNota.setProduto(produto);
        itemNota.setNotaFiscal(notaFiscal);
        itemNota.setQuantidade(3);
        itemNota.setValorItem(60.0);
        itemNotaDAO.create(itemNota);

        // Recuperando o itemNota
        ItemNota retrievedItemNota = itemNotaDAO.retrive(itemNota.getId());
        assertNotNull(retrievedItemNota, "O ItemNota não deve ser nulo.");
        assertEquals(itemNota.getId(), retrievedItemNota.getId(), "O ID deve corresponder.");
    }

    @Test
    public void testUpdate() {
        System.out.println("TESTANDO UPDATE");

        // Criando produto e nota fiscal para teste
        Produto produto = new Produto();
        produto.setDescricao("Produto Teste");
        produto.setValor(30.0);
        produtoDAO.create(produto);

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setEndereco("Rua Exemplo");
        clienteDAO.create(cliente);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setDataEmissao(DataUtil.dataAtual());
        notaFiscal.setCliente(cliente);
        notaFiscalDAO.create(notaFiscal);

        ItemNota itemNota = new ItemNota();
        itemNota.setProduto(produto);
        itemNota.setNotaFiscal(notaFiscal);
        itemNota.setQuantidade(2);
        itemNota.setValorItem(60.0);
        itemNotaDAO.create(itemNota);

        // Atualizando o itemNota
        itemNota.setQuantidade(10);
        itemNotaDAO.update(itemNota);

        // Verificando se a atualização foi bem-sucedida
        ItemNota updatedItemNota = itemNotaDAO.retrive(itemNota.getId());
        assertEquals(10, updatedItemNota.getQuantidade(), "A quantidade deve ter sido atualizada.");
    }

    @Test
    public void testDelete() {
        System.out.println("TESTANDO DELETE");

        // Criando produto e nota fiscal para teste
        Produto produto = new Produto();
        produto.setDescricao("Produto Teste");
        produto.setValor(20.0);
        produtoDAO.create(produto);

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setEndereco("Rua Exemplo");
        clienteDAO.create(cliente);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setDataEmissao(DataUtil.dataAtual());
        notaFiscal.setCliente(cliente);
        notaFiscalDAO.create(notaFiscal);

        ItemNota itemNota = new ItemNota();
        itemNota.setProduto(produto);
        itemNota.setNotaFiscal(notaFiscal);
        itemNota.setQuantidade(5);
        itemNota.setValorItem(100.0);
        itemNotaDAO.create(itemNota);

        // Deletando o itemNota
        boolean result = itemNotaDAO.delete(itemNota.getId());
        assertTrue(result, "O ItemNota deve ser deletado com sucesso.");
    }

    @Test
    public void testFindAll() {
        System.out.println("TESTANDO FINDALL");

        List<ItemNota> itemNotas = itemNotaDAO.findAll();
        assertNotNull(itemNotas, "A lista de ItemNota não deve ser nula.");
        assertFalse(itemNotas.isEmpty(), "A lista de ItemNota não deve estar vazia.");
        itemNotas.forEach(itemNota -> System.out.println(itemNota));
    }
}
