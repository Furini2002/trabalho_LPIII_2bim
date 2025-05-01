/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.FuriniSolutions.util;

import br.com.FuriniSolutions.bean.ItemNota;
import br.com.FuriniSolutions.bean.NotaFiscal;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 *
 * @author lucas
 */
public class GeradorCupom {

    public static void criarCupomPDF(NotaFiscal nota) {
        try {
            //caminho da pasta download do windows
            Random r = new Random();
            String nomeArquivo = "cupom_fiscal_" + r.nextInt() + ".pdf";
            String caminhoArquivo = Paths.get(System.getProperty("user.home"), "Downloads", nomeArquivo).toString();

            // Configuração do PDF
            PdfWriter writer = new PdfWriter(caminhoArquivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document documento = new Document(pdf);

            // loja e CNPJ
            documento.add(new Paragraph("LOJA FURINI EIRELI ME").setBold().setFontSize(14));
            documento.add(new Paragraph("RUA BRIGADEIRO FARIA LIMA, 123"));
            documento.add(new Paragraph("CEP: 87.570-000 - UMUARAMA - PR"));
            documento.add(new Paragraph("CNPJ: 12.345.678/0001-99"));
            documento.add(new Paragraph("IE: 003.963.966"));
            
            documento.add(new Paragraph("------------------------------------------------------"));

            // cliente
            documento.add(new Paragraph("Cliente: " + nota.getCliente().toString()));
            documento.add(new Paragraph("Data da Compra: "
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            
            documento.add(new Paragraph("------------------------------------------------------"));

            // Itens da compra
            documento.add(new Paragraph("------- ITENS DA COMPRA -------").setBold());
            double totalGeral = 0;
            for (ItemNota item : nota.getListaItens()) {
                documento.add(new Paragraph(
                        String.format("%-20s %5d x R$ %.2f = R$ %.2f",
                                item.getProduto().toString(),
                                item.getQuantidade(),
                                item.getValorItem(),
                                (item.getQuantidade() * item.getValorItem()))
                ));
                totalGeral += (item.getQuantidade() * item.getValorItem());
            }

            documento.add(new Paragraph("\n"));

            // Total
            documento.add(new Paragraph(String.format("TOTAL: R$ %.2f", totalGeral)).setBold().setFontSize(12));

            documento.add(new Paragraph("\nObrigado pela sua compra! Volte sempre."));

            documento.close();
            System.out.println("Cupom gerado em: " + caminhoArquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String criarCupomTXT(NotaFiscal nota) {
        // Cabeçalho com espaços corretos
        String cabecalho = "LOJA FURINI EIRELI ME\n"
                + "RUA BRIGADEIRO FARIA LIMA, 123\n"
                + "CEP: 87.570-000 - UMUARAMA - PR\n"
                + "CNPJ: 12.345.678/0001-99\n"
                + "IE: 003.963.966\n"
                + "--------------------------------------\n";

        // Data no formato correto
        String data = "Data:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                + "\n-----------------------------------------\n";

        // Cliente formatado
        String cliente = "Cliente: " + nota.getCliente().toString() + "\n"
                + "--------------------------------------\n";

        // Cabeçalho de produtos
        String cabecalhoProdutos = "----- ITENS DA COMPRA ----\n";

        // Variáveis auxiliares para formatar os itens
        StringBuilder itensCompra = new StringBuilder();
        double totalGeral = 0;
        System.out.println(nota.getListaItens());

        // Loop para gerar as linhas dos itens da compra
        for (ItemNota item : nota.getListaItens()) {
            String itemStr = String.format("%-20s %5d x R$ %.2f = R$ %.2f\n",
                    item.getProduto().toString(),
                    item.getQuantidade(),
                    item.getValorItem(),
                    (item.getQuantidade() * item.getValorItem()));
            itensCompra.append(itemStr);
            totalGeral += item.getQuantidade() * item.getValorItem();
        }

        // Formatação do total
        String total = String.format("TOTAL: R$ %.2f\n", totalGeral);

        // Mensagem de agradecimento
        String agradecimento = "\nObrigado pela sua compra! Volte sempre.\n";

        // Exibir o cupom completo
        String resumo = (cabecalho + data + cliente + cabecalhoProdutos + itensCompra.toString() + total + agradecimento);
        
        return resumo;
    }
}
