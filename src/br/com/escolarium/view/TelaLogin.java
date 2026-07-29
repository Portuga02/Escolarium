package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Tela de Login - visual inspirado no estilo "Windows Vista/7 Welcome Screen":
 * fundo com gradiente, avatar circular grande, cartao central arredondado.
 *
 * O avatar muda automaticamente conforme o sexo selecionado (Masculino/Feminino),
 * usando icones desenhados por codigo (AvatarFactory) - sem depender de nenhuma
 * imagem externa.
 *
 * MOCK: aceita qualquer usuario/senha preenchidos.
 * TODO: validar contra tabela de usuarios no banco (ver TelaUsuarios.java).
 */
public class TelaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JLabel lblAvatar;

    private static final int TAMANHO_AVATAR = 110;
    private static final Color AZUL_ESCURO = new Color(20, 55, 100);
    private static final Color AZUL_CLARO = new Color(120, 170, 220);

    public TelaLogin() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Escolarium - Login");
        setSize(420, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setContentPane(new PainelGradiente());
        getContentPane().setLayout(new GridBagLayout());

        JPanel cartao = criarCartaoLogin();
        getContentPane().add(cartao);
    }

    // Painel de fundo com gradiente azul (referencia visual ao Vista Welcome Screen)
    private static class PainelGradiente extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, AZUL_ESCURO, 0, getHeight(), AZUL_CLARO);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private JPanel criarCartaoLogin() {
        JPanel cartao = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 235));
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 24, 24));
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        cartao.setOpaque(false);
        cartao.setPreferredSize(new Dimension(330, 400));
        cartao.setLayout(new GridBagLayout());
        cartao.setBorder(new EmptyBorder(25, 30, 25, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.gridx = 0;

        // Avatar
        lblAvatar = new JLabel(AvatarFactory.criarAvatar(AvatarFactory.Sexo.NEUTRO, TAMANHO_AVATAR));
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        cartao.add(lblAvatar, gbc);

        // Titulo
        JLabel lblTitulo = new JLabel("Escolarium", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(AZUL_ESCURO);
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 6, 10, 6);
        cartao.add(lblTitulo, gbc);

        // Campo usuario
        txtUsuario = new JTextField(18);
        estilizarCampo(txtUsuario);
        gbc.gridy = 2;
        gbc.insets = new Insets(6, 6, 6, 6);
        cartao.add(envolverComRotulo("Usuário:", txtUsuario), gbc);

        // Campo senha
        txtSenha = new JPasswordField(18);
        estilizarCampo(txtSenha);
        gbc.gridy = 3;
        cartao.add(envolverComRotulo("Senha:", txtSenha), gbc);

        // Botao entrar
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(AZUL_ESCURO);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEntrar.setPreferredSize(new Dimension(180, 34));
        btnEntrar.addActionListener(e -> autenticar());
        gbc.gridy = 4;
        gbc.insets = new Insets(18, 6, 6, 6);
        cartao.add(btnEntrar, gbc);

        getRootPane().setDefaultButton(btnEntrar);

        return cartao;
    }

    private JPanel envolverComRotulo(String texto, JComponent campo) {
        JPanel pnl = new JPanel(new BorderLayout(0, 2));
        pnl.setOpaque(false);
        JLabel lbl = new JLabel(texto);
        lbl.setFont(lbl.getFont().deriveFont(11f));
        lbl.setForeground(new Color(90, 90, 90));
        pnl.add(lbl, BorderLayout.NORTH);
        pnl.add(campo, BorderLayout.CENTER);
        return pnl;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
    }

    private void autenticar() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha usuário e senha.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // MOCK: qualquer usuario/senha preenchidos loga.
        // TODO: validar de verdade contra o banco (tabela usuarios).
        dispose();
        new TelaInicial();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaLogin::new);
    }
}
