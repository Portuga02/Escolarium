package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Cadastro de Disciplina - MOCK, sem persistência ainda.
 * TODO: criar Disciplina.java (model), DisciplinaDAO.java e ligar aqui.
 */
public class TelaDisciplina extends JFrame {

    private JTextField txtNome, txtCargaHoraria, txtProfessorResponsavel;
    private JTextArea txtAreaEmenta;

    public TelaDisciplina() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Cadastro de Disciplina");
        setSize(480, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));

        UiUtils.add(painel, new JLabel("Nome da Disciplina:"), 0, 0, 1, false);
        txtNome = new JTextField(20); UiUtils.add(painel, txtNome, 1, 0, 1, true);

        UiUtils.add(painel, new JLabel("Carga Horária (h):"), 0, 1, 1, false);
        txtCargaHoraria = new JTextField(5); UiUtils.add(painel, txtCargaHoraria, 1, 1, 1, false);

        UiUtils.add(painel, new JLabel("Professor Responsável:"), 0, 2, 1, false);
        txtProfessorResponsavel = new JTextField(20); UiUtils.add(painel, txtProfessorResponsavel, 1, 2, 1, true);

        UiUtils.add(painel, new JLabel("Ementa/Resumo:"), 0, 3, 1, false);
        txtAreaEmenta = new JTextArea(5, 20);
        txtAreaEmenta.setLineWrap(true);
        txtAreaEmenta.setWrapStyleWord(true);
        JScrollPane scrollEmenta = new JScrollPane(txtAreaEmenta);
        UiUtils.add(painel, scrollEmenta, 1, 3, 1, true);

        add(painel, BorderLayout.CENTER);
        add(UiUtils.criarRodapeSalvarCancelar(this::salvar, this::dispose), BorderLayout.SOUTH);
    }

    private void salvar() {
        // MOCK - TODO: instanciar Disciplina e chamar DisciplinaController/DAO
        JOptionPane.showMessageDialog(this,
                "Disciplina \"" + txtNome.getText() + "\" salva (simulação).",
                "Salvar", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
