package br.com.escolarium.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 * Fábrica de avatares de usuário desenhados via Graphics2D (vetor),
 * sem depender de nenhum arquivo de imagem externo.
 *
 * Ilustração original e genérica (silhueta simples), inspirada no estilo
 * de avatar circular usado em telas de login do tipo Windows Vista/7 -
 * mas sem reproduzir nenhuma imagem/ícone protegido por direitos autorais.
 */
public final class AvatarFactory {

    private AvatarFactory() { }

    public enum Sexo { MASCULINO, FEMININO, NEUTRO }

    public static ImageIcon criarAvatar(Sexo sexo, int tamanho) {
        BufferedImage img = new BufferedImage(tamanho, tamanho, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color corFundo, corCabelo, corRoupa;
        switch (sexo) {
            case MASCULINO:
                corFundo = new Color(179, 214, 255);
                corCabelo = new Color(70, 50, 40);
                corRoupa = new Color(51, 102, 153);
                break;
            case FEMININO:
                corFundo = new Color(255, 200, 221);
                corCabelo = new Color(120, 72, 45);
                corRoupa = new Color(200, 60, 90);
                break;
            default:
                corFundo = new Color(220, 220, 220);
                corCabelo = new Color(160, 160, 160);
                corRoupa = new Color(130, 130, 130);
        }

        // Fundo circular
        g.setColor(corFundo);
        g.fill(new Ellipse2D.Float(0, 0, tamanho, tamanho));

        float cx = tamanho / 2f;

        // Corpo/ombros (tronco em forma de "sino" cortado embaixo do círculo)
        g.setColor(corRoupa);
        g.fillArc((int) (cx - tamanho * 0.42f), (int) (tamanho * 0.55f),
                (int) (tamanho * 0.84f), (int) (tamanho * 0.7f), 0, 180);

        // Pescoço
        g.setColor(new Color(235, 200, 170));
        g.fillRect((int) (cx - tamanho * 0.08f), (int) (tamanho * 0.46f),
                (int) (tamanho * 0.16f), (int) (tamanho * 0.12f));

        // Cabeça
        g.setColor(new Color(240, 205, 175));
        float diametroCabeca = tamanho * 0.38f;
        g.fill(new Ellipse2D.Float(cx - diametroCabeca / 2, tamanho * 0.16f, diametroCabeca, diametroCabeca));

        // Cabelo (formato varia por sexo: mais "coberto" no feminino)
        g.setColor(corCabelo);
        if (sexo == Sexo.FEMININO) {
            g.fillArc((int) (cx - diametroCabeca / 2 - 2), (int) (tamanho * 0.14f),
                    (int) diametroCabeca + 4, (int) (diametroCabeca * 0.9f), 0, 180);
            // mechas laterais mais longas
            g.fillRoundRect((int) (cx - diametroCabeca / 2 - 2), (int) (tamanho * 0.30f),
                    (int) (diametroCabeca * 0.22f), (int) (diametroCabeca * 0.55f), 8, 8);
            g.fillRoundRect((int) (cx + diametroCabeca / 2 - (diametroCabeca * 0.20f)), (int) (tamanho * 0.30f),
                    (int) (diametroCabeca * 0.22f), (int) (diametroCabeca * 0.55f), 8, 8);
        } else {
            g.fillArc((int) (cx - diametroCabeca / 2), (int) (tamanho * 0.15f),
                    (int) diametroCabeca, (int) (diametroCabeca * 0.75f), 0, 180);
        }

        // Gravata simples no masculino (detalhe que diferencia visualmente)
        if (sexo == Sexo.MASCULINO) {
            g.setColor(new Color(180, 40, 50));
            int[] xs = {(int) cx - 4, (int) cx + 4, (int) cx};
            int[] ys = {(int) (tamanho * 0.58f), (int) (tamanho * 0.58f), (int) (tamanho * 0.75f)};
            g.fillPolygon(xs, ys, 3);
        }

        // Borda do círculo
        g.setColor(new Color(255, 255, 255, 180));
        g.setStroke(new BasicStroke(Math.max(2, tamanho / 40f)));
        g.draw(new Ellipse2D.Float(1, 1, tamanho - 2, tamanho - 2));

        g.dispose();
        return new ImageIcon(img);
    }
}
