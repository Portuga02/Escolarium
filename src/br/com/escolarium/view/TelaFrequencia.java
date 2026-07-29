package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Tela de Frequência - dados MOCKADOS por enquanto.
 * TODO: substituir por consulta real (ex: FrequenciaDAO.listarPorMatricula(matricula)).
 */
public class TelaFrequencia extends JDialog {

    public TelaFrequencia(Frame parent, String matriculaAluno) {
        super(parent, "Frequência do Aluno", true);
        setSize(480, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Frequência - Matrícula: " + (matriculaAluno == null ? "(nenhum aluno selecionado)" : matriculaAluno));
        lblTitulo.setBorder(new EmptyBorder(10, 10, 0, 10));
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD));

        Object[][] dadosMock = {
                {"01/07/2026", "Quarta", "Presente"},
                {"03/07/2026", "Sexta", "Presente"},
                {"06/07/2026", "Segunda", "Falta"},
                {"07/07/2026", "Terça", "Presente"},
        };
        String[] colunas = {"Data", "Dia da Semana", "Situação"};

        JTable tabela = new JTable(dadosMock, colunas);
        tabela.setRowHeight(24);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblResumo = new JLabel("Total de faltas no período: 1");
        lblResumo.setBorder(new EmptyBorder(0, 10, 10, 10));

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        JPanel pnlRodape = new JPanel(new BorderLayout());
        pnlRodape.add(lblResumo, BorderLayout.WEST);
        JPanel pnlBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBotao.add(btnFechar);
        pnlRodape.add(pnlBotao, BorderLayout.EAST);

        add(lblTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(pnlRodape, BorderLayout.SOUTH);
    }
}
