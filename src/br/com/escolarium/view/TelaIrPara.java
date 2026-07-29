package br.com.escolarium.view;

import javax.swing.*;
import java.awt.*;

/**
 * Diálogo "Ir Para" - permite digitar uma matrícula e buscar o aluno.
 * MOCK: por enquanto só mostra uma mensagem, ainda não busca no banco.
 * TODO: no lugar do JOptionPane, chamar controller.buscarPorMatricula(matricula)
 * e, se encontrar, preencher os campos do MainUI com os dados retornados.
 */
public class TelaIrPara extends JDialog {

    public TelaIrPara(Frame parent) {
        super(parent, "Ir Para", true);
        setSize(320, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Digite a matrícula do aluno:"), gbc);

        JTextField txtMatricula = new JTextField(15);
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtMatricula, gbc);

        JButton btnBuscar = new JButton("Buscar");
        JButton btnCancelar = new JButton("Cancelar");

        btnBuscar.addActionListener(e -> {
            String matricula = txtMatricula.getText().trim();
            if (matricula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite uma matrícula antes de buscar.",
                        "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // MOCK - aqui entraria a busca real no banco
            JOptionPane.showMessageDialog(this,
                    "Buscando aluno de matrícula " + matricula + "...\n" +
                    "(funcionalidade de busca real ainda será conectada ao banco)",
                    "Ir Para", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
        btnCancelar.addActionListener(e -> dispose());

        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlBotoes.add(btnBuscar);
        pnlBotoes.add(btnCancelar);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(pnlBotoes, gbc);
    }
}
