package br.com.escolarium.view;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Monta a "Ficha do Aluno" como um documento imprimível, usando
 * apenas a API nativa do Java (java.awt.print) - não precisa de
 * nenhuma biblioteca externa (nada de JasperReports/iText por enquanto).
 *
 * Se no futuro você quiser um relatório mais bonito (com logo da escola,
 * tabelas, etc.), o próximo passo natural é migrar isso pra JasperReports.
 */
public class FichaAlunoPrintable implements Printable {

    private final Map<String, String> dados;
    private static final Font FONTE_TITULO = new Font("SansSerif", Font.BOLD, 16);
    private static final Font FONTE_SECAO = new Font("SansSerif", Font.BOLD, 12);
    private static final Font FONTE_CAMPO = new Font("SansSerif", Font.PLAIN, 11);

    public FichaAlunoPrintable(Map<String, String> dados) {
        // LinkedHashMap preserva a ordem de inserção -> ordem de impressão previsível
        this.dados = new LinkedHashMap<>(dados);
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE; // documento de 1 página só
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        int larguraUtil = (int) pageFormat.getImageableWidth();
        int y = 0;

        // Cabeçalho
        g2d.setFont(FONTE_TITULO);
        g2d.drawString("Escolarium - Ficha de Cadastro do Aluno", 0, y += 20);
        g2d.setFont(FONTE_CAMPO);
        String dataEmissao = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        g2d.drawString("Emitido em: " + dataEmissao, 0, y += 16);
        g2d.drawLine(0, y += 8, larguraUtil, y);

        y += 20;

        // Seção: Dados do Aluno
        y = desenharSecao(g2d, "Dados do Aluno", new String[]{
                "Matrícula", "Sala", "Turma", "Aluno", "Identidade", "CPF"
        }, y, larguraUtil);

        y += 10;

        // Seção: Endereço
        y = desenharSecao(g2d, "Endereço", new String[]{
                "Endereço", "Complemento", "Bairro", "Cidade", "UF", "CEP", "Naturalidade"
        }, y, larguraUtil);

        y += 10;

        // Seção: Contato
        y = desenharSecao(g2d, "Contato", new String[]{
                "Nascimento", "Telefone Residencial", "Telefone Comercial", "Telefone Celular"
        }, y, larguraUtil);

        y += 10;

        // Seção: Responsável Financeiro
        y = desenharSecao(g2d, "Responsável Financeiro", new String[]{
                "Responsável", "Identidade Responsável", "CPF Responsável"
        }, y, larguraUtil);

        return PAGE_EXISTS;
    }

    private int desenharSecao(Graphics2D g2d, String tituloSecao, String[] campos, int y, int largura) {
        g2d.setFont(FONTE_SECAO);
        g2d.drawString(tituloSecao, 0, y += 18);
        g2d.setFont(FONTE_CAMPO);

        for (String campo : campos) {
            String valor = dados.getOrDefault(campo, "");
            if (valor == null || valor.trim().isEmpty()) {
                valor = "-";
            }
            g2d.drawString(campo + ":", 10, y += 16);
            g2d.drawString(valor, 160, y);
        }
        g2d.drawLine(0, y + 8, largura, y + 8);
        return y + 8;
    }
}
