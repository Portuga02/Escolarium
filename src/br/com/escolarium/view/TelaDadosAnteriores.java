package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Dados de Escola Anterior - MOCK, sem persistência ainda.
 * Espelha os campos da classe br.com.escolarium.model.DadosAnteriores.
 * TODO: dar get/set em DadosAnteriores.java e ligar aqui de verdade
 * (dá pra abrir a partir de um botão na aba "Outras Informações" do MainUI).
 */
public class TelaDadosAnteriores extends JDialog {

    private JTextField txtNomeEscola;
    private JTextField txtSerieAnterior;
    private JComboBox<String> cbTurno;
    private JTextField txtMatriculaAnterior;
    private JTextField txtDataMatricula;

    public TelaDadosAnteriores(Frame parent, String matriculaAluno) {
        super(parent, "Dados da Escola Anterior", true);
        setSize(450, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblAluno = new JLabel("Aluno matrícula: " + (matriculaAluno == null || matriculaAluno.isEmpty() ? "(nenhum selecionado)" : matriculaAluno));
        lblAluno.setFont(lblAluno.getFont().deriveFont(Font.ITALIC));
        UiUtils.add(painel, lblAluno, 0, 0, 2, false);

        UiUtils.add(painel, new JLabel("Nome da Escola:"), 0, 1, 1, false);
        txtNomeEscola = new JTextField(20); UiUtils.add(painel, txtNomeEscola, 1, 1, 1, true);

        UiUtils.add(painel, new JLabel("Série Anterior Matriculado:"), 0, 2, 1, false);
        txtSerieAnterior = new JTextField(15); UiUtils.add(painel, txtSerieAnterior, 1, 2, 1, true);

        UiUtils.add(painel, new JLabel("Turno:"), 0, 3, 1, false);
        cbTurno = new JComboBox<>(new String[]{"Manhã", "Tarde", "Noite"});
        UiUtils.add(painel, cbTurno, 1, 3, 1, true);

        UiUtils.add(painel, new JLabel("Matrícula Anterior:"), 0, 4, 1, false);
        txtMatriculaAnterior = new JTextField(15); UiUtils.add(painel, txtMatriculaAnterior, 1, 4, 1, true);

        UiUtils.add(painel, new JLabel("Data da Matrícula (dd/mm/aaaa):"), 0, 5, 1, false);
        txtDataMatricula = new JTextField(10); UiUtils.add(painel, txtDataMatricula, 1, 5, 1, false);

        add(painel, BorderLayout.CENTER);
        add(UiUtils.criarRodapeSalvarCancelar(this::salvar, this::dispose), BorderLayout.SOUTH);
    }

    private void salvar() {
        // MOCK - TODO: instanciar DadosAnteriores (depois de criar os get/set)
        // e associar ao Aluno atual via aluno.setDadosAnteriores(...)
        JOptionPane.showMessageDialog(this,
                "Dados da escola \"" + txtNomeEscola.getText() + "\" salvos (simulação).",
                "Salvar", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
