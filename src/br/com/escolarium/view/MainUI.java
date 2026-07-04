package br.com.escolarium.view;

import br.com.escolarium.controller.AlunoController;
import br.com.escolarium.model.Aluno;
import br.com.escolarium.model.Endereco;
import br.com.escolarium.model.Responsaveis;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainUI {

    public static void main(String[] args) {

        // Visual moderno do sistema operacional
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        AlunoController controller = new AlunoController();

        JFrame frame = new JFrame("Escolarium - Gestão Acadêmica Completa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JTabbedPane painelDeAbas = new JTabbedPane();
        painelDeAbas.setBorder(new EmptyBorder(10, 10, 0, 10));

        // ==========================================
        // ABA 1: DADOS DO ALUNO
        // ==========================================
        JPanel painelAluno = new JPanel(new GridLayout(0, 2, 10, 15));
        painelAluno.setBorder(new EmptyBorder(20, 20, 20, 20));

        painelAluno.add(new JLabel("Nome Completo:"));
        JTextField txtNomeAluno = new JTextField();
        painelAluno.add(txtNomeAluno);

        painelAluno.add(new JLabel("Idade:"));
        // Truque para o campo não ficar esticado:
        JPanel panelIdade = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JTextField txtIdade = new JTextField(5); // Tamanho reduzido
        panelIdade.add(txtIdade);
        painelAluno.add(panelIdade);

        painelAluno.add(new JLabel("CPF:"));
        JPanel panelCpf = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JTextField txtCpfAluno = new JTextField(15);
        panelCpf.add(txtCpfAluno);
        painelAluno.add(panelCpf);

        painelAluno.add(new JLabel("Matrícula:"));
        JTextField txtMatricula = new JTextField();
        painelAluno.add(txtMatricula);

        painelAluno.add(new JLabel("Data Nasc. (Apenas números):"));
        JPanel panelData = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JFormattedTextField txtDataNascAluno = null;
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            txtDataNascAluno = new JFormattedTextField(mascaraData);
            txtDataNascAluno.setColumns(10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        panelData.add(txtDataNascAluno);
        painelAluno.add(panelData);

        painelAluno.add(new JLabel("Série:"));
        String[] opcoesSerie = {
                "Selecione a Série...",
                "1º Ano - Ensino Fundamental I", "2º Ano - Ensino Fundamental I", "3º Ano - Ensino Fundamental I",
                "4º Ano - Ensino Fundamental I", "5º Ano - Ensino Fundamental I",
                "6º Ano - Ensino Fundamental II", "7º Ano - Ensino Fundamental II", "8º Ano - Ensino Fundamental II",
                "9º Ano - Ensino Fundamental II",
                "1º Ano - Ensino Médio", "2º Ano - Ensino Médio", "3º Ano - Ensino Médio"
        };
        JComboBox<String> comboSerie = new JComboBox<>(opcoesSerie);
        painelAluno.add(comboSerie);

        painelAluno.add(new JLabel("Turma:"));
        JPanel panelTurma = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JTextField txtTurma = new JTextField(5);
        panelTurma.add(txtTurma);
        painelAluno.add(panelTurma);

        painelDeAbas.addTab("Dados do Aluno", new JScrollPane(painelAluno));

        // ==========================================
        // ABA 2: ENDEREÇO
        // ==========================================
        JPanel painelEndereco = new JPanel(new GridLayout(0, 2, 10, 15));
        painelEndereco.setBorder(new EmptyBorder(20, 20, 20, 20));

        painelEndereco.add(new JLabel("Logradouro (Rua/Av/Tv):"));
        JTextField txtLogradouro = new JTextField();
        painelEndereco.add(txtLogradouro);

        painelEndereco.add(new JLabel("Nome da Rua:"));
        JTextField txtRua = new JTextField();
        painelEndereco.add(txtRua);

        painelEndereco.add(new JLabel("Número da Residência:"));
        JTextField txtNumeroResidencia = new JTextField();
        painelEndereco.add(txtNumeroResidencia);

        painelEndereco.add(new JLabel("Bairro:"));
        JTextField txtBairro = new JTextField();
        painelEndereco.add(txtBairro);

        painelEndereco.add(new JLabel("CEP:"));
        JTextField txtCep = new JTextField();
        painelEndereco.add(txtCep);

        painelEndereco.add(new JLabel("Ponto de Referência:"));
        JTextField txtPontoReferencia = new JTextField();
        painelEndereco.add(txtPontoReferencia);

        painelEndereco.add(new JLabel("Cidade:"));
        JTextField txtCidade = new JTextField("Recife"); // Padrão otimizado
        painelEndereco.add(txtCidade);

        painelEndereco.add(new JLabel("UF:"));
        JTextField txtUf = new JTextField("PE"); // Padrão otimizado
        painelEndereco.add(txtUf);

        painelDeAbas.addTab("Endereço", new JScrollPane(painelEndereco));

        // ==========================================
        // ABA 3: RESPONSÁVEIS
        // ==========================================
        JPanel painelResponsaveis = new JPanel(new GridLayout(0, 2, 10, 15));
        painelResponsaveis.setBorder(new EmptyBorder(20, 20, 20, 20));

        painelResponsaveis.add(new JLabel("Nome da Mãe:"));
        JTextField txtNomeMae = new JTextField();
        painelResponsaveis.add(txtNomeMae);

        painelResponsaveis.add(new JLabel("Nome do Pai:"));
        JTextField txtNomePai = new JTextField();
        painelResponsaveis.add(txtNomePai);

        painelResponsaveis.add(new JLabel("CPF da Mãe:"));
        JTextField txtCpfMae = new JTextField();
        painelResponsaveis.add(txtCpfMae);

        painelResponsaveis.add(new JLabel("CPF do Pai:"));
        JTextField txtCpfPai = new JTextField();
        painelResponsaveis.add(txtCpfPai);

        painelResponsaveis.add(new JLabel("Telefone da Mãe:"));
        JTextField txtTelefoneMae = new JTextField();
        painelResponsaveis.add(txtTelefoneMae);

        painelResponsaveis.add(new JLabel("Telefone do Pai:"));
        JTextField txtTelefonePai = new JTextField();
        painelResponsaveis.add(txtTelefonePai);

        painelResponsaveis.add(new JLabel("Responsável Financeiro:"));
        JTextField txtRespFinanceiro = new JTextField();
        painelResponsaveis.add(txtRespFinanceiro);

        painelResponsaveis.add(new JLabel("Email da Mãe:"));
        JTextField txtEmailMae = new JTextField();
        painelResponsaveis.add(txtEmailMae);

        painelResponsaveis.add(new JLabel("Email do Pai:"));
        JTextField txtEmailPai = new JTextField();
        painelResponsaveis.add(txtEmailPai);

        painelResponsaveis.add(new JLabel("Profissão da Mãe:"));
        JTextField txtProfissaoMae = new JTextField();
        painelResponsaveis.add(txtProfissaoMae);

        painelResponsaveis.add(new JLabel("Profissão do Pai:"));
        JTextField txtProfissaoPai = new JTextField();
        painelResponsaveis.add(txtProfissaoPai);

        painelResponsaveis.add(new JLabel("Grau de Parentesco:"));
        JTextField txtGrauParentesco = new JTextField();
        painelResponsaveis.add(txtGrauParentesco);

        painelResponsaveis.add(new JLabel("Estado Civil:"));
        JTextField txtEstadoCivil = new JTextField();
        painelResponsaveis.add(txtEstadoCivil);

        painelResponsaveis.add(new JLabel("Data Nasc. Mãe (DD/MM/AAAA):"));
        JTextField txtDataNascMae = new JTextField();
        painelResponsaveis.add(txtDataNascMae);

        painelResponsaveis.add(new JLabel("Data Nasc. Pai (DD/MM/AAAA):"));
        JTextField txtDataNascPai = new JTextField();
        painelResponsaveis.add(txtDataNascPai);

        JScrollPane scrollResponsaveis = new JScrollPane(painelResponsaveis);
        scrollResponsaveis.getVerticalScrollBar().setUnitIncrement(16);
        painelDeAbas.addTab("Responsáveis", scrollResponsaveis);

        // ==========================================
        // BOTÃO DE SALVAR E AÇÃO
        // ==========================================
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.setBorder(new EmptyBorder(0, 10, 10, 20));

        JButton btnSalvar = new JButton("Salvar Matrícula");
        btnSalvar.setPreferredSize(new Dimension(160, 40));
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSalvar.setBackground(new Color(34, 139, 34));
        btnSalvar.setForeground(Color.WHITE);
        painelBotao.add(btnSalvar);

        // Referência final para uso dentro do Lambda
        final JFormattedTextField dataNascRef = txtDataNascAluno;

        btnSalvar.addActionListener(e -> {
            try {
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                // Valida idade vazia
                int idade = txtIdade.getText().isEmpty() ? 0 : Integer.parseInt(txtIdade.getText());
                
                // Pega a data da máscara e remove os espaços vazios
                String dataDigitada = dataNascRef.getText().replace("_", "").trim();
                LocalDate dataNascimento = dataDigitada.length() == 10 ? LocalDate.parse(dataDigitada, formatador) : LocalDate.now();

                // 1. Instancia o Aluno (Pegando a série do JComboBox!)
                Aluno aluno = new Aluno(
                        txtNomeAluno.getText(),
                        idade,
                        txtCpfAluno.getText(),
                        txtMatricula.getText(),
                        dataNascimento,
                        comboSerie.getSelectedItem().toString(), 
                        txtTurma.getText()
                );

                // 2. Instancia o Endereço
                Endereco endereco = new Endereco(
                        txtCidade.getText(), txtRua.getText(), txtBairro.getText(),
                        txtCep.getText(), txtLogradouro.getText(), txtNumeroResidencia.getText(),
                        txtPontoReferencia.getText(), txtUf.getText()
                );
                aluno.setEndereco(endereco);

                // 3. Instancia os Responsáveis
                Responsaveis responsaveis = new Responsaveis();
                responsaveis.setNomeMae(txtNomeMae.getText());
                responsaveis.setNomePai(txtNomePai.getText());
                responsaveis.setCpfMae(txtCpfMae.getText());
                responsaveis.setCpfPai(txtCpfPai.getText());
                responsaveis.setTelefoneMae(txtTelefoneMae.getText());
                responsaveis.setTelefonePai(txtTelefonePai.getText());
                responsaveis.setResponsavelFinaceiro(txtRespFinanceiro.getText());
                responsaveis.setEmailMae(txtEmailMae.getText());
                responsaveis.setEmailPai(txtEmailPai.getText());
                responsaveis.setProfissaoMae(txtProfissaoMae.getText());
                responsaveis.setProfissaoPai(txtProfissaoPai.getText());
                responsaveis.setGrauParentesco(txtGrauParentesco.getText());
                responsaveis.setEstadoCivil(txtEstadoCivil.getText());

                if (!txtDataNascMae.getText().isEmpty()) {
                    responsaveis.setDataNascimentoMae(LocalDate.parse(txtDataNascMae.getText(), formatador));
                }
                if (!txtDataNascPai.getText().isEmpty()) {
                    responsaveis.setDataNascimentoPai(LocalDate.parse(txtDataNascPai.getText(), formatador));
                }

                aluno.setResponsaveis(responsaveis);

                // 4. Salva no Controller
                controller.cadastrarAluno(aluno);

                JOptionPane.showMessageDialog(frame, "Matrícula de " + aluno.getNome() + " salva com sucesso!\nSérie: " + aluno.getSerie(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao salvar!\nVerifique se as datas e a idade estão corretas.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        frame.add(painelDeAbas, BorderLayout.CENTER);
        frame.add(painelBotao, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}