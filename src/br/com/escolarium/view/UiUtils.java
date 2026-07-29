package br.com.escolarium.view;

import javax.swing.*;
import java.awt.*;

/**
 * Utilitário compartilhado entre as telas do sistema, pra não repetir
 * o mesmo código de GridBagLayout em cada classe (o mesmo padrão que
 * já existe no MainUI, só que reaproveitável).
 */
public final class UiUtils {

    private UiUtils() { }

    public static void add(JPanel painel, Component comp, int x, int y, int width, boolean estica) {
        GridBagConstraints gbc = new GridBagConstraints(); // sempre uma instância nova!
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;
        if (estica) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
        }
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        painel.add(comp, gbc);
    }

    /** Painel de rodapé padrão com botões Salvar/Cancelar. */
    public static JPanel criarRodapeSalvarCancelar(Runnable acaoSalvar, Runnable acaoCancelar) {
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");
        btnSalvar.addActionListener(e -> acaoSalvar.run());
        btnCancelar.addActionListener(e -> acaoCancelar.run());
        pnl.add(btnSalvar);
        pnl.add(btnCancelar);
        return pnl;
    }
}
