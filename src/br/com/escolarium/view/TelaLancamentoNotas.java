package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Lançamento de Notas - MOCK, sem persistência ainda.
 * TODO: trocar os combos mockados por listas reais (alunos/disciplinas do banco)
 * e o botão Lançar por uma chamada a NotaController/NotaDAO.
 */
public class TelaLancamentoNotas extends JFrame {

    private JComboBox<String> cbAluno, cbDisciplina, cbBimestre;
    private JTextField txtNota;
    private JTable tabelaNotas;
    private DefaultTableModel modeloTabela;

    public TelaLancamentoNotas() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Lançamento de Notas");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        painelEntrada.setBorder(new TitledBorder("Nova Nota"));

        // MOCK: essas listas viriam do banco (alunos da turma, disciplinas cadastradas)
        cbAluno = new JComboBox<>(new String[]{"50 - João da Silva", "51 - Maria Oliveira", "52 - Pedro Souza"});
        cbDisciplina = new JComboBox<>(new String[]{"Matemática", "Português", "Ciências", "História"});
        cbBimestre = new JComboBox<>(new String[]{"1º Bimestre", "2º Bimestre", "3º Bimestre", "4º Bimestre"});
        txtNota = new JTextField(5);

        UiUtils.add(painelEntrada, new JLabel("Aluno:"), 0, 0, 1, false);
        UiUtils.add(painelEntrada, cbAluno, 1, 0, 3, true);
        UiUtils.add(painelEntrada, new JLabel("Disciplina:"), 0, 1, 1, false);
        UiUtils.add(painelEntrada, cbDisciplina, 1, 1, 1, true);
        UiUtils.add(painelEntrada, new JLabel("Bimestre:"), 2, 1, 1, false);
        UiUtils.add(painelEntrada, cbBimestre, 3, 1, 1, true);
        UiUtils.add(painelEntrada, new JLabel("Nota:"), 0, 2, 1, false);
        UiUtils.add(painelEntrada, txtNota, 1, 2, 1, false);

        JButton btnLancar = new JButton("Lançar Nota", IconeFactory.criar(IconeFactory.Tipo.NOVO, 16));
        btnLancar.addActionListener(e -> lancarNota());
        UiUtils.add(painelEntrada, btnLancar, 3, 2, 1, false);

        // Tabela de notas já lançadas nesta sessão (MOCK, some ao fechar a janela)
        modeloTabela = new DefaultTableModel(new String[]{"Aluno", "Disciplina", "Bimestre", "Nota"}, 0);
        tabelaNotas = new JTable(modeloTabela);
        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBorder(new TitledBorder("Notas Lançadas"));
        painelTabela.add(new JScrollPane(tabelaNotas), BorderLayout.CENTER);

        add(painelEntrada, BorderLayout.NORTH);
        add(painelTabela, BorderLayout.CENTER);
    }

    private void lancarNota() {
        String nota = txtNota.getText().trim();
        if (nota.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite a nota antes de lançar.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // MOCK - TODO: persistir de verdade via NotaController/NotaDAO
        modeloTabela.addRow(new Object[]{cbAluno.getSelectedItem(), cbDisciplina.getSelectedItem(),
                cbBimestre.getSelectedItem(), nota});
        txtNota.setText("");
    }

    // Modelo de tabela simples (evita depender de outra classe externa)
    private static class DefaultTableModel extends javax.swing.table.DefaultTableModel {
        DefaultTableModel(String[] colunas, int linhas) {
            super(colunas, linhas);
        }
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
