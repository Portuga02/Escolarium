package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Gestão de Usuários e Permissões - MOCK, sem persistência ainda.
 * TODO: criar Usuario.java (model, com enum Perfil: ADMIN/SECRETARIA/PROFESSOR)
 * + UsuarioDAO.java e ligar aqui. A senha jamais deve ser salva em texto puro -
 * quando for persistir de verdade, usar hash (ex: BCrypt).
 */
public class TelaUsuarios extends JFrame {

    private JTextField txtNome, txtLogin;
    private JPasswordField txtSenha;
    private JComboBox<String> cbPerfil;
    private DefaultTableModel modeloTabela;
    private JTable tabelaUsuarios;

    public TelaUsuarios() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Gestão de Usuários e Permissões");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        painelEntrada.setBorder(new TitledBorder("Novo Usuário"));

        UiUtils.add(painelEntrada, new JLabel("Nome:"), 0, 0, 1, false);
        txtNome = new JTextField(18); UiUtils.add(painelEntrada, txtNome, 1, 0, 1, true);

        UiUtils.add(painelEntrada, new JLabel("Login:"), 2, 0, 1, false);
        txtLogin = new JTextField(12); UiUtils.add(painelEntrada, txtLogin, 3, 0, 1, true);

        UiUtils.add(painelEntrada, new JLabel("Senha:"), 0, 1, 1, false);
        txtSenha = new JPasswordField(18); UiUtils.add(painelEntrada, txtSenha, 1, 1, 1, true);

        UiUtils.add(painelEntrada, new JLabel("Perfil:"), 2, 1, 1, false);
        cbPerfil = new JComboBox<>(new String[]{"Administrador", "Secretaria", "Professor"});
        UiUtils.add(painelEntrada, cbPerfil, 3, 1, 1, true);

        JButton btnCriar = new JButton("Criar Usuário");
        btnCriar.addActionListener(e -> criarUsuario());
        UiUtils.add(painelEntrada, btnCriar, 3, 2, 1, false);

        modeloTabela = new DefaultTableModel(new String[]{"Nome", "Login", "Perfil"}, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        // MOCK: usuário admin padrão de exemplo
        modeloTabela.addRow(new Object[]{"Administrador do Sistema", "admin", "Administrador"});

        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setRowHeight(24);
        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBorder(new TitledBorder("Usuários Cadastrados"));
        painelTabela.add(new JScrollPane(tabelaUsuarios), BorderLayout.CENTER);

        JButton btnRemover = new JButton("Remover Selecionado");
        btnRemover.addActionListener(e -> removerUsuario());
        JPanel pnlAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnlAcoes.add(btnRemover);

        add(painelEntrada, BorderLayout.NORTH);
        add(painelTabela, BorderLayout.CENTER);
        add(pnlAcoes, BorderLayout.SOUTH);
    }

    private void criarUsuario() {
        String nome = txtNome.getText().trim();
        String login = txtLogin.getText().trim();
        char[] senha = txtSenha.getPassword();

        if (nome.isEmpty() || login.isEmpty() || senha.length == 0) {
            JOptionPane.showMessageDialog(this, "Preencha nome, login e senha.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // MOCK - TODO: persistir via UsuarioController/DAO, com senha em hash (nunca texto puro)
        modeloTabela.addRow(new Object[]{nome, login, cbPerfil.getSelectedItem()});
        txtNome.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
    }

    private void removerUsuario() {
        int linha = tabelaUsuarios.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário na tabela.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int opcao = JOptionPane.showConfirmDialog(this, "Remover este usuário?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            modeloTabela.removeRow(linha);
        }
    }
}
