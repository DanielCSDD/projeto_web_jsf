package br.com.projeto.util;

import br.com.projeto.model.Contato;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class DownloadPDF {

    private String FONT_TEXTO_CABECALHO = "Times-Bold";
    private String FONT_TEXTO_CONTEUDO = "Times-Roman";
    private String TITULO_RELATORIO = "RELAÇÃO DOS CONTATOS";
    private int FONT_SIZE_TEXTO_TABELA = 10;
    private int FONT_SIZE_TITULO_COLUNA_TABELA = 10;

    public byte[] exportarPDF(ByteArrayOutputStream baos, List<Contato> contatos) throws IOException {
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Fonte customizada
        PdfFont font = PdfFontFactory.createFont(FONT_TEXTO_CABECALHO);

        // Cabeçalho
        Paragraph header = new Paragraph(TITULO_RELATORIO)
                .setFont(font)
                .setFontSize(20)
                .setFontColor(ColorConstants.BLUE)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(header);

        // Tabela com dados
        float[] columnWidths = {30f, 240f, 200f, 90f, 90f};
        Table table = new Table(UnitValue.createPointArray(columnWidths));
        table.addCell(new Cell().add(new Paragraph("ID").setFont(font).setFontSize(FONT_SIZE_TITULO_COLUNA_TABELA)));
        table.addCell(new Cell().add(new Paragraph("Nome").setFont(font).setFontSize(FONT_SIZE_TITULO_COLUNA_TABELA)));
        table.addCell(new Cell().add(new Paragraph("Email").setFont(font).setFontSize(FONT_SIZE_TITULO_COLUNA_TABELA)));
        table.addCell(new Cell().add(new Paragraph("Telefone").setFont(font).setFontSize(FONT_SIZE_TITULO_COLUNA_TABELA)));
        table.addCell(new Cell().add(new Paragraph("Celular").setFont(font).setFontSize(FONT_SIZE_TITULO_COLUNA_TABELA)));

        PdfFont dataFont = PdfFontFactory.createFont(FONT_TEXTO_CONTEUDO);
        for (Contato contato : contatos) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(contato.getId())).setFont(dataFont).setFontSize(FONT_SIZE_TEXTO_TABELA)));
            table.addCell(new Cell().add(new Paragraph(contato.getNome()).setFont(dataFont).setFontSize(FONT_SIZE_TEXTO_TABELA)));
            table.addCell(new Cell().add(new Paragraph(contato.getEmail()).setFont(dataFont).setFontSize(FONT_SIZE_TEXTO_TABELA)));
            table.addCell(new Cell().add(new Paragraph(contato.getTelefone()).setFont(dataFont).setFontSize(FONT_SIZE_TEXTO_TABELA)));
            table.addCell(new Cell().add(new Paragraph(contato.getCelular()).setFont(dataFont).setFontSize(FONT_SIZE_TEXTO_TABELA)));
        }
        document.add(table);

        // Rodapé
        Paragraph footer = new Paragraph("Página 1")
                .setFont(font)
                .setFontSize(12)
                .setTextAlignment(TextAlignment.RIGHT);
        document.add(footer);

        document.close();
        System.out.println("Relatório criado!");

        return baos.toByteArray();
    }
}
