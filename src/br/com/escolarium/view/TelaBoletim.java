package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Boletim do Aluno - MOCK, dados fixos por enquanto.
 * TODO: substituir pelos dados reais vindos de NotaDAO (mesma origem
 * de dados que vai alimentar a TelaHistorico, mas focado no ano atual).
 */
public class TelaBoletim extends JDialog {

    public TelaBoletim(Frame parent, String matriculaAluno) {
        super(parent, "Boletim do Aluno", true);
        setSize(550, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Boletim - Matrícula: " + (matriculaAluno == null || matriculaAluno.isEmpty() ? "(nenhum aluno selecionado)" : matriculaAluno));
        lblTitulo.setBorder(new EmptyBorder(10, 10, 0, 10));
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD));

        Object[][] dadosMock = {
                {"Matemática", "8.0", "7.5", "-", "-", "Cursando"},
                {"Português", "9.0", "8.5", "-", "-", "Cursando"},
                {"Ciências", "7.0", "7.0", "-", "-", "Cursando"},
                {"História", "8.5", "9.0", "-", "-", "Cursando"},
        };
        String[] colunas = {"Disciplina", "1º Bim", "2º Bim", "3º Bim", "4º Bim", "Situação"};

        JTable tabela = new JTable(dadosMock, colunas);
        tabela.setRowHeight(24);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        JPanel pnlRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlRodape.add(btnFechar);

        add(lblTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(pnlRodape, BorderLayout.SOUTH);
    }
}
