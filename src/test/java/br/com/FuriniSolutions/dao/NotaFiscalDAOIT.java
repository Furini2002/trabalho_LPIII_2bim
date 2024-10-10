package br.com.FuriniSolutions.dao;

import br.com.FuriniSolutions.bean.Cliente;
import br.com.FuriniSolutions.bean.NotaFiscal;
import br.com.FuriniSolutions.util.ConnectionsFactory;
import br.com.FuriniSolutions.util.DataUtil;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de testes para NotaFiscalDAO.
 */
public class NotaFiscalDAOIT {

    private NotaFiscalDAO notaFiscalDAO;
    private ClienteDAO clienteDAO;
    private Connection connection;

    @BeforeEach
    public void setUp() {
        System.out.println("NOTA FISCAL");
        // Inicializando a conexão com o banco de dados e os DAOs
        connection = ConnectionsFactory.createConnetionToMySQL();
        notaFiscalDAO = new NotaFiscalDAO(connection);
        clienteDAO = new ClienteDAO(connection);
    }
    
    @AfterEach
    public void tearDown() {
        // Fechando a conexão com o banco de dados após cada teste
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }


    @Test
    public void testCreateNotaFiscal() {
        System.out.println("TESTANDO O CREATE");

        // Criando cliente
        Cliente c = new Cliente();
        c.setNome("Ana");
        c.setEndereco("Rua das flores, 123");
        clienteDAO.create(c);

        // Criando nota fiscal e associando ao cliente
        NotaFiscal nf = new NotaFiscal();
        nf.setDataEmissao(DataUtil.dataAtual());
        nf.setCliente(c);

        notaFiscalDAO.create(nf);

        // Verificando se a nota fiscal foi criada e se o ID foi gerado
        System.out.println(nf);
        assertNotNull(nf.getId(), "O ID da NotaFiscal não deve ser nulo após a criação.");        
    }

    @Test
    public void testRetrieveNotaFiscal() {
        System.out.println("TESTANDO O RETRIEVE");

        // Recuperando nota fiscal pelo ID
        NotaFiscal nf = notaFiscalDAO.retrive(1);
        System.out.println(nf);
        assertNotNull(nf, "A NotaFiscal não deve ser nula.");
        
    }

    @Test
    public void testUpdateNotaFiscal() {
        System.out.println("TESTANDO O UPDATE");

        // Criando cliente inicial
        Cliente c = new Cliente();
        c.setNome("João Henrique");
        c.setEndereco("Rua das palmeiras, 321");
        clienteDAO.create(c);

        // Criando nota fiscal associada ao cliente
        NotaFiscal nf = new NotaFiscal();
        nf.setDataEmissao(DataUtil.dataAtual());
        nf.setCliente(c);
        notaFiscalDAO.create(nf);
        System.out.println(nf);

        // Criando um novo cliente e atualizando a nota fiscal para este novo cliente
        Cliente c1 = new Cliente("Henrique", "Rua America, 852");
        clienteDAO.create(c1);
        nf.setCliente(c1);
        notaFiscalDAO.update(nf);
        System.out.println(nf);

        // Verificando se o cliente foi atualizado corretamente na nota fiscal
        NotaFiscal updatedNotaFiscal = notaFiscalDAO.retrive(nf.getId());
        assertEquals(c1.getId(), updatedNotaFiscal.getCliente().getId(), "O cliente da nota fiscal deve ser atualizado.");
    }

    @Test
    public void testDeleteNotaFiscal() {
        System.out.println("TESTANDO O DELETE");

        // Criando cliente
        Cliente c = new Cliente();
        c.setNome("Cliente para deletar");
        c.setEndereco("Rua do Teste de Deleção, 123");
        clienteDAO.create(c);

        // Criando nota fiscal associada ao cliente
        NotaFiscal nf = new NotaFiscal();
        nf.setDataEmissao(DataUtil.dataAtual());
        nf.setCliente(c);
        notaFiscalDAO.create(nf);

        // Deletando a nota fiscal e verificando se foi deletada com sucesso
        boolean deleteSuccess = notaFiscalDAO.delete(nf.getId());
        assertTrue(deleteSuccess, "A NotaFiscal deve ser deletada com sucesso.");
    }

    @Test
    public void testFindAllNotaFiscal() {
        System.out.println("TESTANDO O FINDALL");

        // Buscando todas as notas fiscais e verificando se a lista não está vazia
        List<NotaFiscal> notasFiscais = notaFiscalDAO.findAll();
        assertNotNull(notasFiscais, "A lista de NotaFiscal não deve ser nula.");
        assertFalse(notasFiscais.isEmpty(), "A lista de NotaFiscal não deve estar vazia.");
        notasFiscais.forEach(notaFiscal -> System.out.println(notaFiscal));
    }
}
