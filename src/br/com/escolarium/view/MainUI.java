package br.com.escolarium.view;

import br.com.escolarium.controller.AlunoController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MainUI extends JFrame {

    //=========================================
    // CONTROLLER
    //=========================================
    private AlunoController controller;

    //=========================================
    // CAMPOS SUPERIORES (Aluno e Responsável)
    //=========================================
    private JTextField txtMatricula;
    private JTextField txtSala;
    private JTextField txtTurma;
    private JTextField txtAluno;
    private JTextField txtIdentidade;
    private JTextField txtCpf;
    
    private JTextField txtRespNome;
    private JTextField txtRespIdentidade;
    private JTextField txtRespCpf;
    private JCheckBox chkMesmoEndereco;

    //=========================================
    // DADOS DO ALUNO (Aba 1)
    //=========================================
    private JTextField txtEndereco;
    private JTextField txtComplemento;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JTextField txtUF;
    private JTextField txtCEP;
    private JTextField txtNaturalidade;
    private JTextField txtNascimento;
    private JTextField txtTelefoneResidencial;
    private JTextField txtTelefoneComercial;
    private JTextField txtCelular;

    //=========================================
    // RESPONSÁVEIS (Aba 2)
    //=========================================
    private JTextField txtNomeMae;
    private JTextField txtNomePai;
    private JTextField txtCpfMae;
    private JTextField txtCpfPai;
    private JTextField txtTelefoneMae;
    private JTextField txtTelefonePai;
    private JTextField txtEmailMae;
    private JTextField txtEmailPai;
    private JTextField txtProfissaoMae;
    private JTextField txtProfissaoPai;

    //=========================================
    // TABELAS
    //=========================================
    private JTable tabelaHorarios;
    private JTable tabelaMensalidades;

    //=========================================
    // BOTÕES DE AÇÃO (Rodapé)
    //=========================================
    private JButton btnNovo;
    private JButton btnExcluir;
    private JButton btnSalvar;
    private JButton btnIrPara;
    private JButton btnMensalidade;
    private JButton btnFrequencia;
    private JButton btnHistorico;
    
    // NAVEGAÇÃO
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;

    //=========================================
    // COMPONENTES
    //=========================================
    private JTabbedPane abas;

    //=========================================
    // CONSTRUTOR
    //=========================================
    public MainUI() {
        // controller = new AlunoController(); // Descomente quando a classe existir
        configurarJanela();
        setVisible(true);
    }

    //=========================================
    // CONFIGURAÇÃO DA JANELA
    //=========================================
    private void configurarJanela() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Cadastro de Alunos");
        setSize(1100, 750); // Tamanho ajustado para caber o layout da foto
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        JPanel painelPrincipal = new JPanel(new BorderLayout(5, 5));
        painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(painelPrincipal, BorderLayout.CENTER);

        painelPrincipal.add(criarPainelSuperior(), BorderLayout.NORTH);
        painelPrincipal.add(criarPainelCentral(), BorderLayout.CENTER);
        painelPrincipal.add(criarPainelInferior(), BorderLayout.SOUTH);
    }

    //=========================================
    // 1. PAINEL SUPERIOR (Dividido Lado a Lado)
    //=========================================
    private JPanel criarPainelSuperior() {
        JPanel painel = new JPanel(new GridLayout(1, 2, 10, 0));

        // ESQUERDA: DADOS BÁSICOS DO ALUNO
        JPanel pnlAluno = new JPanel(new GridBagLayout());
        pnlAluno.setBorder(new TitledBorder("Aluno"));

        adicionarComGrid(pnlAluno, new JLabel("Matrícula:"), 0, 0, 1, false);
        txtMatricula = new JTextField(5); adicionarComGrid(pnlAluno, txtMatricula, 1, 0, 1, true);
        adicionarComGrid(pnlAluno, new JLabel("Sala:"), 2, 0, 1, false);
        txtSala = new JTextField(5); adicionarComGrid(pnlAluno, txtSala, 3, 0, 1, true);
        adicionarComGrid(pnlAluno, new JLabel("Turmas:"), 4, 0, 1, false);
        txtTurma = new JTextField(8); adicionarComGrid(pnlAluno, txtTurma, 5, 0, 1, true);

        adicionarComGrid(pnlAluno, new JLabel("Aluno:"), 0, 1, 1, false);
        txtAluno = new JTextField(); adicionarComGrid(pnlAluno, txtAluno, 1, 1, 5, true);

        adicionarComGrid(pnlAluno, new JLabel("Identidade:"), 0, 2, 1, false);
        txtIdentidade = new JTextField(); adicionarComGrid(pnlAluno, txtIdentidade, 1, 2, 2, true);
        adicionarComGrid(pnlAluno, new JLabel("CPF:"), 3, 2, 1, false);
        txtCpf = new JTextField(); adicionarComGrid(pnlAluno, txtCpf, 4, 2, 2, true);

        // DIREITA: RESPONSÁVEL FINANCEIRO
        JPanel pnlResp = new JPanel(new GridBagLayout());
        pnlResp.setBorder(new TitledBorder("Responsável Financeiro"));

        adicionarComGrid(pnlResp, new JCheckBox("Considerar os mesmos dados do aluno?"), 0, 0, 4, false);
        
        adicionarComGrid(pnlResp, new JLabel("Responsável:"), 0, 1, 1, false);
        txtRespNome = new JTextField(); adicionarComGrid(pnlResp, txtRespNome, 1, 1, 3, true);
        
        adicionarComGrid(pnlResp, new JLabel("Identidade:"), 0, 2, 1, false);
        txtRespIdentidade = new JTextField(); adicionarComGrid(pnlResp, txtRespIdentidade, 1, 2, 1, true);
        adicionarComGrid(pnlResp, new JLabel("CPF:"), 2, 2, 1, false);
        txtRespCpf = new JTextField(); adicionarComGrid(pnlResp, txtRespCpf, 3, 2, 1, true);

        painel.add(pnlAluno);
        painel.add(pnlResp);

        return painel;
    }

    // O método auxiliar que garante que não ocorra o erro de duplicidade
    private void adicionarComGrid(JPanel pnl, Component comp, int x, int y, int width, boolean estica) {
        GridBagConstraints gbc = new GridBagConstraints(); // SEMPRE CRIA UM NOVO
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        if (estica) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
        }
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        pnl.add(comp, gbc);
    }
    //=========================================
    // 2. PAINEL CENTRAL (ABAS)
    //=========================================
    private JPanel criarPainelCentral() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(new EmptyBorder(10, 0, 10, 0));

        abas = new JTabbedPane();
        abas.setFont(new Font("Segoe UI", Font.BOLD, 12));

        abas.addTab("Dados do Aluno", criarAbaDadosAluno());
        abas.addTab("Dados do Responsável", criarAbaResponsavel());
        abas.addTab("Cursos Contratados", criarAbaCursos());
        abas.addTab("Outras Informações", criarAbaOutrasInformacoes());

        painel.add(abas, BorderLayout.CENTER);
        return painel;
    }

 // ABA 1: DADOS DO ALUNO COM O MÉTODO AUXILIAR
    private JPanel criarAbaDadosAluno() {
        JPanel painel = new JPanel(new BorderLayout());
        JPanel pnlCampos = new JPanel(new GridBagLayout());
        pnlCampos.setBorder(new EmptyBorder(15, 15, 15, 15));

        adicionarComGrid(pnlCampos, new JLabel("Endereço:"), 0, 0, 1, false);
        txtEndereco = new JTextField(); adicionarComGrid(pnlCampos, txtEndereco, 1, 0, 3, true);
        adicionarComGrid(pnlCampos, new JLabel("Complemento:"), 4, 0, 1, false);
        txtComplemento = new JTextField(10); adicionarComGrid(pnlCampos, txtComplemento, 5, 0, 1, true);

        adicionarComGrid(pnlCampos, new JLabel("Bairro:"), 0, 1, 1, false);
        txtBairro = new JTextField(); adicionarComGrid(pnlCampos, txtBairro, 1, 1, 3, true);
        adicionarComGrid(pnlCampos, new JLabel("Nascimento:"), 4, 1, 1, false);
        txtNascimento = new JTextField(10); adicionarComGrid(pnlCampos, txtNascimento, 5, 1, 1, true);

        adicionarComGrid(pnlCampos, new JLabel("Cidade:"), 0, 2, 1, false);
        txtCidade = new JTextField(); adicionarComGrid(pnlCampos, txtCidade, 1, 2, 3, true);
        adicionarComGrid(pnlCampos, new JLabel("Telefone Res.:"), 4, 2, 1, false);
        txtTelefoneResidencial = new JTextField(10); adicionarComGrid(pnlCampos, txtTelefoneResidencial, 5, 2, 1, true);

        adicionarComGrid(pnlCampos, new JLabel("UF:"), 0, 3, 1, false);
        txtUF = new JTextField(3); adicionarComGrid(pnlCampos, txtUF, 1, 3, 1, true);
        adicionarComGrid(pnlCampos, new JLabel("CEP:"), 2, 3, 1, false);
        txtCEP = new JTextField(8); adicionarComGrid(pnlCampos, txtCEP, 3, 3, 1, true);
        adicionarComGrid(pnlCampos, new JLabel("Telefone Com.:"), 4, 3, 1, false);
        txtTelefoneComercial = new JTextField(10); adicionarComGrid(pnlCampos, txtTelefoneComercial, 5, 3, 1, true);

        adicionarComGrid(pnlCampos, new JLabel("Naturalidade:"), 0, 4, 1, false);
        txtNaturalidade = new JTextField(); adicionarComGrid(pnlCampos, txtNaturalidade, 1, 4, 3, true);
        adicionarComGrid(pnlCampos, new JLabel("Telefone Cel.:"), 4, 4, 1, false);
        txtCelular = new JTextField(10); adicionarComGrid(pnlCampos, txtCelular, 5, 4, 1, true);

        // Foto na direita
        JPanel pnlFoto = new JPanel(new BorderLayout());
        pnlFoto.setBorder(new TitledBorder("Foto do Aluno"));
        pnlFoto.setPreferredSize(new Dimension(180, 180));
        pnlFoto.add(new JLabel("<html><center>[Clique para inserir<br>a foto]</center></html>", SwingConstants.CENTER), BorderLayout.CENTER);

        painel.add(pnlCampos, BorderLayout.CENTER);
        painel.add(pnlFoto, BorderLayout.EAST);
        return painel;
    }

    // ABA 2: DADOS DOS RESPONSÁVEIS COM O MÉTODO AUXILIAR
    private JPanel criarAbaResponsavel() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));

        adicionarComGrid(painel, new JLabel("Nome da Mãe:"), 0, 0, 1, false);
        txtNomeMae = new JTextField(); adicionarComGrid(painel, txtNomeMae, 1, 0, 3, true);
        adicionarComGrid(painel, new JLabel("CPF da Mãe:"), 4, 0, 1, false);
        txtCpfMae = new JTextField(15); adicionarComGrid(painel, txtCpfMae, 5, 0, 1, true);

        adicionarComGrid(painel, new JLabel("Profissão:"), 0, 1, 1, false);
        txtProfissaoMae = new JTextField(15); adicionarComGrid(painel, txtProfissaoMae, 1, 1, 1, true);
        adicionarComGrid(painel, new JLabel("Telefone:"), 2, 1, 1, false);
        txtTelefoneMae = new JTextField(15); adicionarComGrid(painel, txtTelefoneMae, 3, 1, 1, true);
        adicionarComGrid(painel, new JLabel("E-mail:"), 4, 1, 1, false);
        txtEmailMae = new JTextField(15); adicionarComGrid(painel, txtEmailMae, 5, 1, 1, true);

        adicionarComGrid(painel, new JSeparator(), 0, 2, 6, true);

        adicionarComGrid(painel, new JLabel("Nome do Pai:"), 0, 3, 1, false);
        txtNomePai = new JTextField(); adicionarComGrid(painel, txtNomePai, 1, 3, 3, true);
        adicionarComGrid(painel, new JLabel("CPF do Pai:"), 4, 3, 1, false);
        txtCpfPai = new JTextField(15); adicionarComGrid(painel, txtCpfPai, 5, 3, 1, true);

        adicionarComGrid(painel, new JLabel("Profissão:"), 0, 4, 1, false);
        txtProfissaoPai = new JTextField(15); adicionarComGrid(painel, txtProfissaoPai, 1, 4, 1, true);
        adicionarComGrid(painel, new JLabel("Telefone:"), 2, 4, 1, false);
        txtTelefonePai = new JTextField(15); adicionarComGrid(painel, txtTelefonePai, 3, 4, 1, true);
        adicionarComGrid(painel, new JLabel("E-mail:"), 4, 4, 1, false);
        txtEmailPai = new JTextField(15); adicionarComGrid(painel, txtEmailPai, 5, 4, 1, true);

        return painel;
    }

    private JPanel criarAbaCursos() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.add(new JLabel("Cursos contratados", SwingConstants.CENTER), BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarAbaOutrasInformacoes() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.add(new JLabel("Outras informações", SwingConstants.CENTER), BorderLayout.CENTER);
        return painel;
    }

    //=========================================
    // 3. PAINEL INFERIOR (Tabelas e Botões)
    //=========================================
    private JPanel criarPainelInferior() {
        JPanel painel = new JPanel(new BorderLayout(5, 5));

        // TABELAS (Meio da tela)
        JPanel pnlTabelas = new JPanel(new GridLayout(1, 2, 10, 0));
        
        JPanel pnlHorarios = new JPanel(new BorderLayout());
        pnlHorarios.setBorder(new TitledBorder("Horários Escolhidos"));
        tabelaHorarios = new JTable(new Object[][]{}, new String[]{"Matrícula", "Dia da Semana", "Hora"});
        pnlHorarios.add(new JScrollPane(tabelaHorarios), BorderLayout.CENTER);
        
        JPanel pnlMensalidade = new JPanel(new BorderLayout());
        pnlMensalidade.setBorder(new TitledBorder("Controle de Mensalidade"));
        tabelaMensalidades = new JTable(new Object[][]{}, new String[]{"Horários", "Alunos", "Seg", "Ter", "Qua", "Qui", "Sex"});
        pnlMensalidade.add(new JScrollPane(tabelaMensalidades), BorderLayout.CENTER);

        pnlTabelas.add(pnlHorarios);
        pnlTabelas.add(pnlMensalidade);
        pnlTabelas.setPreferredSize(new Dimension(100, 160));

        // RODAPÉ (Barra de Ferramentas)
        JPanel pnlRodape = new JPanel(new BorderLayout());
        pnlRodape.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        // Botões de Ação
        JPanel pnlAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        btnNovo = new JButton("Novo");
        btnExcluir = new JButton("Excluir");
        btnSalvar = new JButton("Salvar");
        btnIrPara = new JButton("Ir Para");
        btnMensalidade = new JButton("Mensalidade");
        btnFrequencia = new JButton("Frequência");
        btnHistorico = new JButton("Histórico");

        pnlAcoes.add(btnNovo);
        pnlAcoes.add(btnExcluir);
        pnlAcoes.add(btnSalvar);
        pnlAcoes.add(btnIrPara);
        pnlAcoes.add(btnMensalidade);
        pnlAcoes.add(btnFrequencia);
        pnlAcoes.add(btnHistorico);

        // Botões de Navegação
        JPanel pnlNavegacao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 10));
        btnPrimeiro = new JButton("|<");
        btnAnterior = new JButton("<");
        btnProximo = new JButton(">");
        btnUltimo = new JButton(">|");

        pnlNavegacao.add(btnPrimeiro);
        pnlNavegacao.add(btnAnterior);
        pnlNavegacao.add(btnProximo);
        pnlNavegacao.add(btnUltimo);

        pnlRodape.add(pnlAcoes, BorderLayout.WEST);
        pnlRodape.add(pnlNavegacao, BorderLayout.EAST);

        painel.add(pnlTabelas, BorderLayout.CENTER);
        painel.add(pnlRodape, BorderLayout.SOUTH);

        return painel;
    }

    //=========================================
    // MAIN
    //=========================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainUI();
        });
    }
}