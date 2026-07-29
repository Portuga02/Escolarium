package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Tela de Histórico Escolar - dados MOCKADOS por enquanto.
 * TODO: substituir por consulta real (ex: HistoricoDAO.listarPorMatricula(matricula)).
 */
public class TelaHistorico extends JDialog {

    public TelaHistorico(Frame parent, String matriculaAluno) {
        super(parent, "Histórico Escolar", true);
        setSize(520, 380);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Histórico - Matrícula: " + (matriculaAluno == null ? "(nenhum aluno selecionado)" : matriculaAluno));
        lblTitulo.setBorder(new EmptyBorder(10, 10, 0, 10));
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD));

        Object[][] dadosMock = {
                {"2025", "8º Ano", "Matemática", "8.5", "Aprovado"},
                {"2025", "8º Ano", "Português", "9.0", "Aprovado"},
                {"2025", "8º Ano", "Ciências", "7.2", "Aprovado"},
                {"2026", "9º Ano", "Matemática", "-", "Cursando"},
        };
        String[] colunas = {"Ano Letivo", "Série", "Disciplina", "Nota", "Situação"};

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
