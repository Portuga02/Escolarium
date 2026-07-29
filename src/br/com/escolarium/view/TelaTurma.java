package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Cadastro de Turma - MOCK, sem persistencia ainda.
 * Ganhou um icone (sol/lua) que troca automaticamente conforme o Turno.
 * TODO: criar Turma.java (model), TurmaDAO.java e ligar aqui.
 */
public class TelaTurma extends JFrame {

    private JTextField txtNomeTurma, txtCapacidade, txtSala;
    private JComboBox<String> cbSerie, cbTurno;
    private JTextField txtProfessorResponsavel;
    private JLabel lblIconeTurno;

    public TelaTurma() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Cadastro de Turma");
        setSize(480, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));

        UiUtils.add(painel, new JLabel("Nome da Turma:"), 0, 0, 1, false);
        txtNomeTurma = new JTextField(15); UiUtils.add(painel, txtNomeTurma, 1, 0, 1, true);

        UiUtils.add(painel, new JLabel("Série:"), 0, 1, 1, false);
        cbSerie = new JComboBox<>(new String[]{"6º Ano", "7º Ano", "8º Ano", "9º Ano", "1º EM", "2º EM", "3º EM"});
        UiUtils.add(painel, cbSerie, 1, 1, 1, true);

        UiUtils.add(painel, new JLabel("Turno:"), 0, 2, 1, false);
        cbTurno = new JComboBox<>(new String[]{"Manhã", "Tarde", "Noite"});
        cbTurno.addActionListener(e -> atualizarIconeTurno());
        UiUtils.add(painel, cbTurno, 1, 2, 1, true);
        lblIconeTurno = new JLabel(IconeFactory.criar(IconeFactory.Tipo.TURNO_MANHA, 22));
        UiUtils.add(painel, lblIconeTurno, 2, 2, 1, false);

        UiUtils.add(painel, new JLabel("Professor Responsável:"), 0, 3, 1, false);
        txtProfessorResponsavel = new JTextField(20); UiUtils.add(painel, txtProfessorResponsavel, 1, 3, 1, true);

        UiUtils.add(painel, new JLabel("Capacidade Máxima:"), 0, 4, 1, false);
        txtCapacidade = new JTextField(5); UiUtils.add(painel, txtCapacidade, 1, 4, 1, false);

        UiUtils.add(painel, new JLabel("Sala Vinculada:"), 0, 5, 1, false);
        txtSala = new JTextField(10); UiUtils.add(painel, txtSala, 1, 5, 1, false);

        add(painel, BorderLayout.CENTER);
        add(UiUtils.criarRodapeSalvarCancelar(this::salvar, this::dispose), BorderLayout.SOUTH);
    }

    private void atualizarIconeTurno() {
        String turno = (String) cbTurno.getSelectedItem();
        IconeFactory.Tipo tipo;
        switch (turno) {
            case "Tarde": tipo = IconeFactory.Tipo.TURNO_TARDE; break;
            case "Noite": tipo = IconeFactory.Tipo.TURNO_NOITE; break;
            default:      tipo = IconeFactory.Tipo.TURNO_MANHA;
        }
        lblIconeTurno.setIcon(IconeFactory.criar(tipo, 22));
    }

    private void salvar() {
        // MOCK - TODO: instanciar Turma e chamar TurmaController/DAO
        JOptionPane.showMessageDialog(this,
                "Turma \"" + txtNomeTurma.getText() + "\" salva (simulação).",
                "Salvar", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
