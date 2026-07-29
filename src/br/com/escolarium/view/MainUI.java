package br.com.escolarium.view;

import br.com.escolarium.controller.AlunoController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.LinkedHashMap;
import java.util.Map;

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
    private JRadioButton rbSexoMasculino;
    private JRadioButton rbSexoFeminino;
    private JLabel lblFotoAluno;

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
    // HORÁRIOS ESCOLHIDOS (entrada + tabela)
    //=========================================
    private JTextField txtHorarioCod;
    private JComboBox<String> cbDiaSemana;
    private JTextField txtHorarioHora;
    private JButton btnAdd;
    private JCheckBox chkHorarioAleatorio;

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
    private JButton btnImprimir;
    private JButton btnIrPara;
    private JButton btnMensalidade;
    private JButton btnFrequencia;
    private JButton btnHistorico;

    // NAVEGAÇÃO
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;

    // Tamanho padrão dos ícones dos botões (em pixels)
    private static final int TAMANHO_ICONE_ACAO = 22;
    private static final int TAMANHO_ICONE_NAV = 18;

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
        getContentPane().setLayout(new BorderLayout(5, 5));

        JPanel painelPrincipal = new JPanel(new BorderLayout(5, 5));
        painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(painelPrincipal, BorderLayout.CENTER);

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

        chkMesmoEndereco = new JCheckBox("Considerar os mesmos dados do aluno?");
        adicionarComGrid(pnlResp, chkMesmoEndereco, 0, 0, 4, false);

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

        adicionarComGrid(pnlCampos, new JLabel("Sexo:"), 0, 5, 1, false);
        JPanel pnlSexo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rbSexoMasculino = new JRadioButton("Masculino");
        rbSexoFeminino = new JRadioButton("Feminino");
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(rbSexoMasculino);
        grupoSexo.add(rbSexoFeminino);
        rbSexoMasculino.addActionListener(e -> lblFotoAluno.setIcon(AvatarFactory.criarAvatar(AvatarFactory.Sexo.MASCULINO, 130)));
        rbSexoFeminino.addActionListener(e -> lblFotoAluno.setIcon(AvatarFactory.criarAvatar(AvatarFactory.Sexo.FEMININO, 130)));
        pnlSexo.add(rbSexoMasculino);
        pnlSexo.add(rbSexoFeminino);
        adicionarComGrid(pnlCampos, pnlSexo, 1, 5, 3, false);

        // Foto na direita - avatar dinâmico (muda conforme o sexo selecionado acima)
        JPanel pnlFoto = new JPanel(new BorderLayout(5, 5));
        pnlFoto.setBorder(new TitledBorder("Foto do Aluno"));
        pnlFoto.setPreferredSize(new Dimension(180, 210));

        lblFotoAluno = new JLabel(AvatarFactory.criarAvatar(AvatarFactory.Sexo.NEUTRO, 130), SwingConstants.CENTER);
        lblFotoAluno.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblDicaFoto = new JLabel("<html><center>Selecione o sexo ao lado<br>ou clique para inserir foto real</center></html>", SwingConstants.CENTER);
        lblDicaFoto.setFont(lblDicaFoto.getFont().deriveFont(10f));
        lblDicaFoto.setForeground(new Color(120, 120, 120));

        pnlFoto.add(lblFotoAluno, BorderLayout.CENTER);
        pnlFoto.add(lblDicaFoto, BorderLayout.SOUTH);

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

        JPanel pnlCentro = new JPanel(new GridBagLayout());
        UiUtils.add(pnlCentro, new JLabel("Outras informações do aluno"), 0, 0, 1, false);

        JButton btnDadosAnteriores = new JButton("Dados da Escola Anterior");
        btnDadosAnteriores.addActionListener(e -> new TelaDadosAnteriores(this, txtMatricula.getText()));
        UiUtils.add(pnlCentro, btnDadosAnteriores, 0, 1, 1, false);

        painel.add(pnlCentro, BorderLayout.CENTER);
        return painel;
    }

    //=========================================
    // 3. PAINEL INFERIOR (Tabelas e Botões)
    //=========================================
    private JPanel criarPainelInferior() {
        JPanel painel = new JPanel(new BorderLayout(5, 5));

        // TABELAS (Meio da tela)
        JPanel pnlTabelas = new JPanel(new GridLayout(1, 2, 10, 0));

        pnlTabelas.add(criarPainelHorariosEscolhidos());
        pnlTabelas.add(criarPainelControleMensalidade());
        pnlTabelas.setPreferredSize(new Dimension(100, 190));

        // RODAPÉ (Barra de Ferramentas)
        JPanel pnlRodape = new JPanel(new BorderLayout());
        pnlRodape.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        // Botões de Ação
        JPanel pnlAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        btnNovo = criarBotaoAcao("Novo", IconeFactory.Tipo.NOVO);
        btnExcluir = criarBotaoAcao("Excluir", IconeFactory.Tipo.EXCLUIR);
        btnSalvar = criarBotaoAcao("Salvar", IconeFactory.Tipo.SALVAR);
        btnImprimir = criarBotaoAcao("Imprimir", IconeFactory.Tipo.IMPRIMIR);
        btnIrPara = criarBotaoAcao("Ir Para", IconeFactory.Tipo.IR_PARA);
        btnMensalidade = criarBotaoAcao("Mensalidade", IconeFactory.Tipo.MENSALIDADE);
        btnFrequencia = criarBotaoAcao("Frequência", IconeFactory.Tipo.FREQUENCIA);
        btnHistorico = criarBotaoAcao("Histórico", IconeFactory.Tipo.HISTORICO);

        pnlAcoes.add(btnNovo);
        pnlAcoes.add(btnExcluir);
        pnlAcoes.add(btnSalvar);
        pnlAcoes.add(btnImprimir);
        pnlAcoes.add(btnIrPara);
        pnlAcoes.add(btnMensalidade);
        pnlAcoes.add(btnFrequencia);
        pnlAcoes.add(btnHistorico);

        configurarAcoesDosBotoes();

        // Botões de Navegação (apenas ícone, sem texto - como setas de "player")
        JPanel pnlNavegacao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 10));
        btnPrimeiro = criarBotaoNavegacao(IconeFactory.Tipo.SETA_PRIMEIRO);
        btnAnterior = criarBotaoNavegacao(IconeFactory.Tipo.SETA_ANTERIOR);
        btnProximo = criarBotaoNavegacao(IconeFactory.Tipo.SETA_PROXIMO);
        btnUltimo = criarBotaoNavegacao(IconeFactory.Tipo.SETA_ULTIMO);

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
    // AÇÕES DOS BOTÕES (por enquanto, Mensalidade/Frequência/Histórico/Ir Para
    // abrem telas com dados MOCKADOS - ver TODOs em cada classe de tela)
    //=========================================
    private void configurarAcoesDosBotoes() {
        btnNovo.addActionListener(e -> limparCampos());

        btnSalvar.addActionListener(e -> {
            // MOCK: por enquanto só confirma visualmente.
            // TODO: montar um objeto Aluno com os campos do formulário
            // e chamar controller.cadastrarAluno(aluno) de verdade aqui.
            JOptionPane.showMessageDialog(this,
                    "Dados salvos (simulação).\nAluno: " + txtAluno.getText(),
                    "Salvar", JOptionPane.INFORMATION_MESSAGE);
        });

        btnExcluir.addActionListener(e -> {
            int opcao = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja excluir este aluno?",
                    "Confirmar exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opcao == JOptionPane.YES_OPTION) {
                // MOCK: TODO chamar controller/DAO de exclusão de verdade
                limparCampos();
                JOptionPane.showMessageDialog(this, "Aluno excluído (simulação).");
            }
        });

        btnIrPara.addActionListener(e -> new TelaIrPara(this));

        btnMensalidade.addActionListener(e -> new TelaMensalidade(this, txtMatricula.getText()));
        btnFrequencia.addActionListener(e -> new TelaFrequencia(this, txtMatricula.getText()));
        btnHistorico.addActionListener(e -> new TelaHistorico(this, txtMatricula.getText()));

        btnImprimir.addActionListener(e -> imprimirFichaAluno());
    }

    // Monta os dados atuais do formulário e envia para a impressora (ou "Salvar como PDF",
    // dependendo do driver escolhido na caixa de diálogo do sistema operacional)
    private void imprimirFichaAluno() {
        Map<String, String> dados = new LinkedHashMap<>();
        dados.put("Matrícula", txtMatricula.getText());
        dados.put("Sala", txtSala.getText());
        dados.put("Turma", txtTurma.getText());
        dados.put("Aluno", txtAluno.getText());
        dados.put("Identidade", txtIdentidade.getText());
        dados.put("CPF", txtCpf.getText());

        dados.put("Endereço", txtEndereco.getText());
        dados.put("Complemento", txtComplemento.getText());
        dados.put("Bairro", txtBairro.getText());
        dados.put("Cidade", txtCidade.getText());
        dados.put("UF", txtUF.getText());
        dados.put("CEP", txtCEP.getText());
        dados.put("Naturalidade", txtNaturalidade.getText());

        dados.put("Nascimento", txtNascimento.getText());
        dados.put("Telefone Residencial", txtTelefoneResidencial.getText());
        dados.put("Telefone Comercial", txtTelefoneComercial.getText());
        dados.put("Telefone Celular", txtCelular.getText());

        dados.put("Responsável", txtRespNome.getText());
        dados.put("Identidade Responsável", txtRespIdentidade.getText());
        dados.put("CPF Responsável", txtRespCpf.getText());

        if (dados.get("Aluno") == null || dados.get("Aluno").trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha ao menos o nome do aluno antes de imprimir.",
                    "Imprimir", JOptionPane.WARNING_MESSAGE);
            return;
        }

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new FichaAlunoPrintable(dados));
        job.setJobName("Ficha do Aluno - " + dados.get("Aluno"));

        // Abre a caixa de diálogo padrão de impressão do sistema operacional
        if (job.printDialog()) {
            try {
                job.print();
                JOptionPane.showMessageDialog(this, "Ficha enviada para impressão com sucesso.",
                        "Imprimir", JOptionPane.INFORMATION_MESSAGE);
            } catch (PrinterException erro) {
                JOptionPane.showMessageDialog(this, "Erro ao imprimir: " + erro.getMessage(),
                        "Erro de Impressão", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Limpa todos os campos de texto do formulário (usado pelo botão "Novo")
    private void limparCampos() {
        JTextField[] campos = {
                txtMatricula, txtSala, txtTurma, txtAluno, txtIdentidade, txtCpf,
                txtRespNome, txtRespIdentidade, txtRespCpf,
                txtEndereco, txtComplemento, txtBairro, txtCidade, txtUF, txtCEP,
                txtNaturalidade, txtNascimento, txtTelefoneResidencial, txtTelefoneComercial, txtCelular,
                txtNomeMae, txtNomePai, txtCpfMae, txtCpfPai, txtTelefoneMae, txtTelefonePai,
                txtEmailMae, txtEmailPai, txtProfissaoMae, txtProfissaoPai
        };
        for (JTextField campo : campos) {
            campo.setText("");
        }
        chkMesmoEndereco.setSelected(false);
        txtAluno.requestFocus();
    }

    // Cria um botão de ação com ícone em cima e texto embaixo (estilo "toolbar")
    private JButton criarBotaoAcao(String texto, IconeFactory.Tipo tipo) {
        JButton btn = new JButton(texto, IconeFactory.criar(tipo, TAMANHO_ICONE_ACAO));
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btn.setFocusPainted(false);
        btn.setMargin(new Insets(4, 8, 4, 8));
        return btn;
    }

    // Cria um botão de navegação (apenas ícone, sem texto)
    private JButton criarBotaoNavegacao(IconeFactory.Tipo tipo) {
        JButton btn = new JButton(IconeFactory.criar(tipo, TAMANHO_ICONE_NAV));
        btn.setFocusPainted(false);
        btn.setMargin(new Insets(4, 10, 4, 10));
        return btn;
    }

    // PAINEL ESQUERDO: HORÁRIOS ESCOLHIDOS (entrada + tabela + checkbox)
    private JPanel criarPainelHorariosEscolhidos() {
        JPanel pnlHorarios = new JPanel(new BorderLayout(5, 5));
        pnlHorarios.setBorder(new TitledBorder("Horários Escolhidos"));

        // Linha de entrada: add | Cod | Dia da Semana | Hora
        JPanel pnlEntrada = new JPanel(new GridBagLayout());

        adicionarComGrid(pnlEntrada, new JLabel("Horário"), 0, 0, 1, false);
        adicionarComGrid(pnlEntrada, new JLabel("Cod"), 1, 0, 1, false);
        adicionarComGrid(pnlEntrada, new JLabel("Dia Semnana"), 2, 0, 1, false);
        adicionarComGrid(pnlEntrada, new JLabel("Hora"), 3, 0, 1, false);

        btnAdd = new JButton("add", IconeFactory.criar(IconeFactory.Tipo.NOVO, 14));
        btnAdd.setFocusPainted(false);
        txtHorarioCod = new JTextField(4);
        cbDiaSemana = new JComboBox<>(new String[]{"Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"});
        txtHorarioHora = new JTextField(6);

        adicionarComGrid(pnlEntrada, btnAdd, 0, 1, 1, false);
        adicionarComGrid(pnlEntrada, txtHorarioCod, 1, 1, 1, false);
        adicionarComGrid(pnlEntrada, cbDiaSemana, 2, 1, 1, true);
        adicionarComGrid(pnlEntrada, txtHorarioHora, 3, 1, 1, false);

        // Tabela de horários já escolhidos
        tabelaHorarios = new JTable(new Object[][]{}, new String[]{"Matrícula", "Dia da Semana", "Hora"});
        JScrollPane scrollHorarios = new JScrollPane(tabelaHorarios);

        chkHorarioAleatorio = new JCheckBox("Horário Aleatório");
        JPanel pnlRodapeHorarios = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlRodapeHorarios.add(chkHorarioAleatorio);

        pnlHorarios.add(pnlEntrada, BorderLayout.NORTH);
        pnlHorarios.add(scrollHorarios, BorderLayout.CENTER);
        pnlHorarios.add(pnlRodapeHorarios, BorderLayout.SOUTH);

        return pnlHorarios;
    }

    // PAINEL DIREITO: CONTROLE DE MENSALIDADE
    private JPanel criarPainelControleMensalidade() {
        JPanel pnlMensalidade = new JPanel(new BorderLayout(5, 5));
        pnlMensalidade.setBorder(new TitledBorder("Controle de Mensalidade"));

        JLabel lblTituloTabela = new JLabel("Horários Ocupados (Quadro Fixo) - Sala:001");
        lblTituloTabela.setBorder(new EmptyBorder(0, 5, 0, 0));

        tabelaMensalidades = new JTable(
                new Object[][]{}, // TODO: popular via controller com os horários ocupados
                new String[]{"Horários", "Alunos", "Seg", "Ter", "Qua", "Qui", "Sex"}
        );

        pnlMensalidade.add(lblTituloTabela, BorderLayout.NORTH);
        pnlMensalidade.add(new JScrollPane(tabelaMensalidades), BorderLayout.CENTER);

        return pnlMensalidade;
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
