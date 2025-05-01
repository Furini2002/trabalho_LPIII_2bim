package teste;

import br.com.FuriniSolutions.bean.Cliente;
import br.com.FuriniSolutions.bean.ItemNota;
import br.com.FuriniSolutions.bean.NotaFiscal;
import br.com.FuriniSolutions.bean.Produto;
import br.com.FuriniSolutions.dao.ClienteDAO;
import br.com.FuriniSolutions.dao.NotaFiscalDAO;
import br.com.FuriniSolutions.dao.ProdutoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class testeJPQL {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("furini_db");
        EntityManager em = emf.createEntityManager();

        try {           

            NotaFiscalDAO dao = new NotaFiscalDAO();            
            ClienteDAO clidao = new ClienteDAO();
            ProdutoDAO prodao = new ProdutoDAO();
            /* teste atv 1
            Cliente c = new Cliente();
            c = clidao.retrieve(1);
            
            List<NotaFiscal> notasfiscais = dao.buscarNotasClienteEspecifico(c);
            for (NotaFiscal nf : notasfiscais) {
                System.out.println("Nota Fiscal: " + nf);               
            }*/
            
            /*
            List<Produto> produtos = prodao.buscarProdutoValorAcimaDe(50);
            for (Produto p : produtos) {
                System.out.println("Produto: " + p);               
            }*/
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } 
    }

}
