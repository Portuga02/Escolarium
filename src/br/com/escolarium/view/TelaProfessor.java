package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Cadastro de Professor - MOCK, sem persistencia ainda.
 * Ganhou avatar dinamico (Masculino/Feminino), igual ao Cadastro de Aluno.
 * TODO: criar Professor.java (model), ProfessorDAO.java e ligar aqui.
 */
public class TelaProfessor extends JFrame {

    private JTextField txtNome, txtCpf, txtEmail, txtTelefone, txtFormacao, txtDisciplinas;
    private JRadioButton rbSexoMasculino, rbSexoFeminino;
    private JLabel lblAvatar;

    public TelaProfessor() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Cadastro de Professor");
        setSize(560, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));

        UiUtils.add(painel, new JLabel("Nome:"), 0, 0, 1, false);
        txtNome = new JTextField(20); UiUtils.add(painel, txtNome, 1, 0, 1, true);

        UiUtils.add(painel, new JLabel("CPF:"), 0, 1, 1, false);
        txtCpf = new JTextField(15); UiUtils.add(painel, txtCpf, 1, 1, 1, true);

        UiUtils.add(painel, new JLabel("E-mail:"), 0, 2, 1, false);
        txtEmail = new JTextField(20); UiUtils.add(painel, txtEmail, 1, 2, 1, true);

        UiUtils.add(painel, new JLabel("Telefone:"), 0, 3, 1, false);
        txtTelefone = new JTextField(15); UiUtils.add(painel, txtTelefone, 1, 3, 1, true);

        UiUtils.add(painel, new JLabel("Formação:"), 0, 4, 1, false);
        txtFormacao = new JTextField(20); UiUtils.add(painel, txtFormacao, 1, 4, 1, true);

        UiUtils.add(painel, new JLabel("Disciplina(s):"), 0, 5, 1, false);
        txtDisciplinas = new JTextField(20); UiUtils.add(painel, txtDisciplinas, 1, 5, 1, true);

        UiUtils.add(painel, new JLabel("Sexo:"), 0, 6, 1, false);
        JPanel pnlSexo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rbSexoMasculino = new JRadioButton("Masculino");
        rbSexoFeminino = new JRadioButton("Feminino");
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(rbSexoMasculino);
        grupoSexo.add(rbSexoFeminino);
        rbSexoMasculino.addActionListener(e -> lblAvatar.setIcon(AvatarFactory.criarAvatar(AvatarFactory.Sexo.MASCULINO, 110)));
        rbSexoFeminino.addActionListener(e -> lblAvatar.setIcon(AvatarFactory.criarAvatar(AvatarFactory.Sexo.FEMININO, 110)));
        pnlSexo.add(rbSexoMasculino);
        pnlSexo.add(rbSexoFeminino);
        UiUtils.add(painel, pnlSexo, 1, 6, 1, false);

        JPanel pnlFoto = new JPanel(new BorderLayout());
        pnlFoto.setBorder(new TitledBorder("Foto"));
        pnlFoto.setPreferredSize(new Dimension(150, 150));
        lblAvatar = new JLabel(AvatarFactory.criarAvatar(AvatarFactory.Sexo.NEUTRO, 110), SwingConstants.CENTER);
        pnlFoto.add(lblAvatar, BorderLayout.CENTER);

        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
        painelConteudo.add(painel, BorderLayout.CENTER);
        painelConteudo.add(pnlFoto, BorderLayout.EAST);

        add(painelConteudo, BorderLayout.CENTER);
        add(UiUtils.criarRodapeSalvarCancelar(this::salvar, this::dispose), BorderLayout.SOUTH);
    }

    private void salvar() {
        // MOCK - TODO: instanciar Professor e chamar ProfessorController/DAO
        JOptionPane.showMessageDialog(this,
                "Professor \"" + txtNome.getText() + "\" salvo (simulação).",
                "Salvar", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
