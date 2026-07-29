package br.com.escolarium.view;

import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * Fábrica de ícones desenhados via Graphics2D (vetor), sem depender
 * de nenhum arquivo de imagem externo (.png/.jpg).
 *
 * Vantagem: funciona em qualquer máquina/Eclipse sem precisar
 * copiar pastas de recursos (resources) junto com o projeto.
 *
 * Se no futuro você quiser trocar por ícones "de verdade" (ex: baixados
 * de um pacote como o Material Icons), basta usar:
 *   new ImageIcon(getClass().getResource("/icones/salvar.png"))
 * no lugar das chamadas a esta classe.
 */
public final class IconeFactory {

    private IconeFactory() { }

    public enum Tipo {
        NOVO, EXCLUIR, SALVAR, IMPRIMIR, IR_PARA,
        MENSALIDADE, FREQUENCIA, HISTORICO,
        SETA_PRIMEIRO, SETA_ANTERIOR, SETA_PROXIMO, SETA_ULTIMO,
        SALA_COMUM, LABORATORIO, QUADRA, AUDITORIO, BIBLIOTECA,
        TURNO_MANHA, TURNO_TARDE, TURNO_NOITE,
        STATUS_PAGO, STATUS_EM_ABERTO, STATUS_ATRASADO,
        SECAO_ACADEMICO, SECAO_COMUNICACAO, SECAO_GESTAO, SECAO_RESPONSAVEIS
    }

    public static ImageIcon criar(Tipo tipo, int tamanho) {
        BufferedImage img = new BufferedImage(tamanho, tamanho, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        switch (tipo) {
            case NOVO:         desenharNovo(g, tamanho); break;
            case EXCLUIR:      desenharExcluir(g, tamanho); break;
            case SALVAR:       desenharSalvar(g, tamanho); break;
            case IMPRIMIR:     desenharImprimir(g, tamanho); break;
            case IR_PARA:      desenharIrPara(g, tamanho); break;
            case MENSALIDADE:  desenharMensalidade(g, tamanho); break;
            case FREQUENCIA:   desenharFrequencia(g, tamanho); break;
            case HISTORICO:    desenharHistorico(g, tamanho); break;
            case SETA_PRIMEIRO: desenharSeta(g, tamanho, -1, true); break;
            case SETA_ANTERIOR: desenharSeta(g, tamanho, -1, false); break;
            case SETA_PROXIMO:  desenharSeta(g, tamanho, 1, false); break;
            case SETA_ULTIMO:   desenharSeta(g, tamanho, 1, true); break;
            case SALA_COMUM:    desenharSalaComum(g, tamanho); break;
            case LABORATORIO:   desenharLaboratorio(g, tamanho); break;
            case QUADRA:        desenharQuadra(g, tamanho); break;
            case AUDITORIO:     desenharAuditorio(g, tamanho); break;
            case BIBLIOTECA:    desenharBiblioteca(g, tamanho); break;
            case TURNO_MANHA:   desenharSol(g, tamanho, false); break;
            case TURNO_TARDE:   desenharSol(g, tamanho, true); break;
            case TURNO_NOITE:   desenharLua(g, tamanho); break;
            case STATUS_PAGO:       desenharBolinha(g, tamanho, new Color(46, 160, 67)); break;
            case STATUS_EM_ABERTO:  desenharBolinha(g, tamanho, new Color(230, 170, 30)); break;
            case STATUS_ATRASADO:   desenharBolinha(g, tamanho, new Color(192, 57, 43)); break;
            case SECAO_ACADEMICO:      desenharCapelo(g, tamanho); break;
            case SECAO_COMUNICACAO:    desenharSino(g, tamanho); break;
            case SECAO_GESTAO:         desenharCifrao(g, tamanho); break;
            case SECAO_RESPONSAVEIS:   desenharDuasPessoas(g, tamanho); break;
        }

        g.dispose();
        return new ImageIcon(img);
    }

    // ---------- NOVO (+) ----------
    private static void desenharNovo(Graphics2D g, int s) {
        g.setColor(new Color(46, 139, 87)); // verde
        int espessura = Math.max(2, s / 6);
        int margem = s / 5;
        g.fillRoundRect(s / 2 - espessura / 2, margem, espessura, s - margem * 2, espessura, espessura);
        g.fillRoundRect(margem, s / 2 - espessura / 2, s - margem * 2, espessura, espessura, espessura);
    }

    // ---------- EXCLUIR (lixeira) ----------
    private static void desenharExcluir(Graphics2D g, int s) {
        g.setColor(new Color(192, 57, 43)); // vermelho
        int corpoX = s / 4, corpoY = s / 3;
        int corpoW = s - corpoX * 2, corpoH = s - corpoY - s / 8;
        g.fill(new RoundRectangle2D.Float(corpoX, corpoY, corpoW, corpoH, 3, 3));
        // tampa
        g.fillRect(s / 6, corpoY - s / 10, s - s / 3, Math.max(2, s / 12));
        // alça
        g.fillRoundRect(s / 2 - s / 8, corpoY - s / 5, s / 4, s / 8, 2, 2);
        // linhas verticais internas (efeito de grade)
        g.setColor(Color.WHITE);
        int linhaW = Math.max(1, s / 16);
        for (int i = 0; i < 3; i++) {
            int x = corpoX + corpoW / 4 * (i + 1) - linhaW / 2;
            g.fillRect(x, corpoY + corpoH / 6, linhaW, corpoH - corpoH / 3);
        }
    }

    // ---------- SALVAR (disquete) ----------
    private static void desenharSalvar(Graphics2D g, int s) {
        int margem = s / 8;
        g.setColor(new Color(41, 98, 173)); // azul
        Path2D disco = new Path2D.Float();
        disco.moveTo(margem, margem);
        disco.lineTo(s - margem - s / 6, margem);
        disco.lineTo(s - margem, margem + s / 6);
        disco.lineTo(s - margem, s - margem);
        disco.lineTo(margem, s - margem);
        disco.closePath();
        g.fill(disco);
        // etiqueta branca
        g.setColor(Color.WHITE);
        g.fillRect(margem + s / 10, margem + s / 10, s - margem * 2 - s / 5, s / 3);
        // slot metálico
        g.setColor(new Color(220, 230, 240));
        g.fillRect(s / 3, margem, s / 4, s / 6);
    }

    // ---------- IMPRIMIR ----------
    private static void desenharImprimir(Graphics2D g, int s) {
        g.setColor(new Color(90, 90, 90));
        // corpo da impressora
        g.fillRoundRect(s / 6, s / 3, s - s / 3, s / 3, 3, 3);
        // folha saindo em cima
        g.setColor(Color.WHITE);
        g.fillRect(s / 3, s / 8, s / 3, s / 4);
        g.setColor(new Color(90, 90, 90));
        g.drawRect(s / 3, s / 8, s / 3, s / 4);
        // bandeja/folha embaixo
        g.setColor(Color.WHITE);
        g.fillRect(s / 4, s - s / 4, s / 2, s / 8);
        g.setColor(new Color(150, 150, 150));
        g.drawRect(s / 4, s - s / 4, s / 2, s / 8);
        // luz indicadora
        g.setColor(new Color(46, 160, 67));
        g.fillOval(s - s / 3, s / 2 - s / 20, s / 10, s / 10);
    }

    // ---------- IR PARA (lupa) ----------
    private static void desenharIrPara(Graphics2D g, int s) {
        g.setColor(new Color(70, 70, 70));
        int diametro = (int) (s * 0.6);
        Ellipse2D lente = new Ellipse2D.Float(s / 8f, s / 8f, diametro, diametro);
        g.setStroke(new BasicStroke(Math.max(2, s / 10f)));
        g.draw(lente);
        g.drawLine((int) (s / 8 + diametro * 0.75), (int) (s / 8 + diametro * 0.75), s - s / 8, s - s / 8);
    }

    // ---------- MENSALIDADE ($) ----------
    private static void desenharMensalidade(Graphics2D g, int s) {
        g.setColor(new Color(39, 141, 88));
        g.fillOval(s / 10, s / 10, s - s / 5, s - s / 5);
        g.setColor(Color.WHITE);
        Font f = new Font("Serif", Font.BOLD, (int) (s * 0.55));
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();
        String texto = "$";
        int x = (s - fm.stringWidth(texto)) / 2;
        int y = (s - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString(texto, x, y);
    }

    // ---------- FREQUÊNCIA (check-list) ----------
    private static void desenharFrequencia(Graphics2D g, int s) {
        g.setColor(new Color(60, 60, 60));
        g.drawRoundRect(s / 6, s / 8, s - s / 3, s - s / 4, 3, 3);
        g.setStroke(new BasicStroke(Math.max(1.5f, s / 14f)));
        // linhas
        for (int i = 0; i < 3; i++) {
            int y = s / 3 + i * (s / 4);
            g.drawLine(s / 3, y, s - s / 4, y);
        }
        // check verde na primeira linha
        g.setColor(new Color(46, 160, 67));
        int y0 = s / 3;
        g.drawLine(s / 5, y0, s / 3 - 2, y0 + s / 10);
        g.drawLine(s / 3 - 2, y0 + s / 10, s / 5 + s / 6, y0 - s / 10);
    }

    // ---------- HISTÓRICO (livro) ----------
    private static void desenharHistorico(Graphics2D g, int s) {
        g.setColor(new Color(120, 81, 45)); // marrom
        Path2D esquerda = new Path2D.Float();
        esquerda.moveTo(s / 2f, s / 6f);
        esquerda.lineTo(s / 8f, s / 5f);
        esquerda.lineTo(s / 8f, s - s / 6f);
        esquerda.lineTo(s / 2f, s - s / 8f);
        esquerda.closePath();
        g.fill(esquerda);

        Path2D direita = new Path2D.Float();
        direita.moveTo(s / 2f, s / 6f);
        direita.lineTo(s - s / 8f, s / 5f);
        direita.lineTo(s - s / 8f, s - s / 6f);
        direita.lineTo(s / 2f, s - s / 8f);
        direita.closePath();
        g.fill(direita);

        g.setColor(new Color(220, 200, 170));
        g.setStroke(new BasicStroke(1f));
        g.drawLine((int) (s / 2f), (int) (s / 6f), (int) (s / 2f), (int) (s - s / 8f));
    }

    // ---------- SETAS DE NAVEGAÇÃO ----------
    // direcao: -1 = esquerda, 1 = direita | dupla = true adiciona a barra (|< ou >|)
    private static void desenharSeta(Graphics2D g, int s, int direcao, boolean dupla) {
        g.setColor(new Color(50, 50, 50));
        int margem = s / 5;
        Path2D triangulo = new Path2D.Float();
        if (direcao < 0) {
            triangulo.moveTo(s - margem, margem);
            triangulo.lineTo(margem, s / 2f);
            triangulo.lineTo(s - margem, s - margem);
        } else {
            triangulo.moveTo(margem, margem);
            triangulo.lineTo(s - margem, s / 2f);
            triangulo.lineTo(margem, s - margem);
        }
        triangulo.closePath();
        g.fill(triangulo);

        if (dupla) {
            int barraX = direcao < 0 ? margem - Math.max(2, s / 12) : s - margem;
            g.fillRect(Math.max(0, barraX), margem, Math.max(2, s / 12), s - margem * 2);
        }
    }

    // ---------- SALAS / AMBIENTES ----------
    private static void desenharSalaComum(Graphics2D g, int s) {
        g.setColor(new Color(120, 90, 60));
        g.fillRect(s / 6, s / 2, s * 2 / 3, s / 10);
        g.fillRect(s / 5, s / 2 + s / 10, s / 12, s / 3);
        g.fillRect(s - s / 5 - s / 12, s / 2 + s / 10, s / 12, s / 3);
        g.setColor(new Color(70, 130, 180));
        g.fillRect(s / 8, s / 6, s * 3 / 4, s / 4);
    }

    private static void desenharLaboratorio(Graphics2D g, int s) {
        g.setColor(new Color(90, 160, 90));
        Path2D tubo = new Path2D.Float();
        tubo.moveTo(s * 0.4f, s * 0.15f);
        tubo.lineTo(s * 0.4f, s * 0.55f);
        tubo.lineTo(s * 0.25f, s * 0.85f);
        tubo.lineTo(s * 0.75f, s * 0.85f);
        tubo.lineTo(s * 0.6f, s * 0.55f);
        tubo.lineTo(s * 0.6f, s * 0.15f);
        tubo.closePath();
        g.fill(tubo);
        g.setColor(new Color(50, 90, 50));
        g.fillRect((int) (s * 0.35f), (int) (s * 0.1f), (int) (s * 0.3f), (int) (s * 0.08f));
    }

    private static void desenharQuadra(Graphics2D g, int s) {
        g.setColor(new Color(220, 120, 40));
        g.fillOval(s / 4, s / 4, s / 2, s / 2);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(Math.max(1, s / 20f)));
        g.drawLine(s / 2, s / 4, s / 2, s * 3 / 4);
        g.drawOval(s / 3, s / 3, s / 3, s / 3);
    }

    private static void desenharAuditorio(Graphics2D g, int s) {
        g.setColor(new Color(150, 30, 40));
        g.fillRoundRect(s / 6, s / 6, s * 2 / 3, s * 2 / 3, s / 8, s / 8);
        g.setColor(Color.WHITE);
        for (int i = 0; i < 2; i++) {
            g.fillRoundRect(s / 6 + s / 12, s / 4 + i * s / 3, s / 2, s / 8, 4, 4);
        }
    }

    private static void desenharBiblioteca(Graphics2D g, int s) {
        Color[] cores = {new Color(150, 50, 50), new Color(50, 100, 150), new Color(60, 140, 90)};
        int largura = s / 6;
        for (int i = 0; i < 3; i++) {
            g.setColor(cores[i]);
            g.fillRect(s / 6 + i * (largura + 3), s / 5, largura, s * 3 / 5);
        }
    }

    // ---------- TURNOS ----------
    private static void desenharSol(Graphics2D g, int s, boolean poente) {
        Color cor = poente ? new Color(230, 130, 40) : new Color(250, 190, 30);
        g.setColor(cor);
        g.fillOval(s / 3, s / 3, s / 3, s / 3);
        g.setStroke(new BasicStroke(Math.max(1.5f, s / 20f)));
        for (int i = 0; i < 8; i++) {
            double ang = Math.toRadians(i * 45);
            int x1 = (int) (s / 2 + Math.cos(ang) * s * 0.32);
            int y1 = (int) (s / 2 + Math.sin(ang) * s * 0.32);
            int x2 = (int) (s / 2 + Math.cos(ang) * s * 0.46);
            int y2 = (int) (s / 2 + Math.sin(ang) * s * 0.46);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    private static void desenharLua(Graphics2D g, int s) {
        g.setColor(new Color(60, 70, 110));
        Ellipse2D cheia = new Ellipse2D.Float(s / 4f, s / 5f, s / 2f, s / 2f);
        Ellipse2D corte = new Ellipse2D.Float(s / 4f + s / 8f, s / 5f - s / 10f, s / 2f, s / 2f);
        Path2D lua = new Path2D.Float(Path2D.WIND_EVEN_ODD);
        lua.append(cheia, false);
        lua.append(corte, false);
        g.fill(lua);
    }

    // ---------- STATUS (bolinha colorida) ----------
    private static void desenharBolinha(Graphics2D g, int s, Color cor) {
        g.setColor(cor);
        int margem = s / 4;
        g.fillOval(margem, margem, s - margem * 2, s - margem * 2);
        g.setColor(cor.darker());
        g.setStroke(new BasicStroke(1f));
        g.drawOval(margem, margem, s - margem * 2, s - margem * 2);
    }

    // ---------- ÍCONES DE SEÇÃO (menu lateral) ----------
    private static void desenharCapelo(Graphics2D g, int s) {
        g.setColor(new Color(40, 40, 40));
        int[] xs = {s / 2, s - s / 8, s / 2, s / 8};
        int[] ys = {s / 4, s / 2, s * 3 / 4, s / 2};
        g.fillPolygon(xs, ys, 4);
        g.fillRect(s / 2 - s / 20, s / 2, s / 10, s / 4);
        g.setColor(new Color(200, 170, 40));
        g.fillOval(s / 2 - s / 20, s * 3 / 4, s / 10, s / 10);
    }

    private static void desenharSino(Graphics2D g, int s) {
        g.setColor(new Color(210, 160, 40));
        g.fillArc(s / 4, s / 5, s / 2, s / 2, 0, 180);
        g.fillRect(s / 4, s / 2, s / 2, s / 6);
        g.fillOval(s / 2 - s / 16, s * 3 / 4, s / 8, s / 8);
        g.fillRect(s / 2 - s / 20, s / 10, s / 10, s / 8);
    }

    private static void desenharCifrao(Graphics2D g, int s) {
        g.setColor(new Color(39, 141, 88));
        g.fillOval(s / 8, s / 8, s - s / 4, s - s / 4);
        g.setColor(Color.WHITE);
        Font f = new Font("Serif", Font.BOLD, (int) (s * 0.5));
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();
        String texto = "$";
        g.drawString(texto, (s - fm.stringWidth(texto)) / 2, (s + fm.getAscent()) / 2 - 2);
    }

    private static void desenharDuasPessoas(Graphics2D g, int s) {
        g.setColor(new Color(70, 110, 170));
        g.fillOval(s / 8, s / 6, s / 3, s / 3);
        g.fillArc(s / 12, s / 2, s * 5 / 12, s / 2, 0, 180);
        g.setColor(new Color(190, 80, 100));
        g.fillOval(s / 2, s / 6, s / 3, s / 3);
        g.fillArc(s / 2 - s / 24, s / 2, s * 5 / 12, s / 2, 0, 180);
    }
}
