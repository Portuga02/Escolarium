package br.com.escolarium.view;

import br.com.escolarium.controller.AlunoController;
import br.com.escolarium.model.Aluno;
import br.com.escolarium.model.Endereco;
import br.com.escolarium.model.Responsaveis;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainUI {

    public static void main(String[] args) {
        AlunoController controller = new AlunoController();

        // Configuração da Janela Principal (JFrame)
        JFrame frame = new JFrame("Escolarium - Cadastro de Alunos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // Centraliza na tela

        // Criando o painel de Abas (JTabbedPane)
        JTabbedPane painelDeAbas = new JTabbedPane();

        // --- ABA 2: ENDEREÇO ---
        JPanel painelEndereco = new JPanel();
        painelEndereco.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelEndereco.setLayout(new GridLayout(0, 1, 0, 0));
        
        painelEndereco.add(new JLabel("Cidade:"));
        JTextField txtCidade = new JTextField();
        painelEndereco.add(txtCidade);

        painelEndereco.add(new JLabel("Rua:"));
        JTextField txtRua = new JTextField();
        painelEndereco.add(txtRua);

        // --- ABA 3: RESPONSÁVEIS ---
        JPanel painelResponsaveis = new JPanel(new GridLayout(0, 2, 10, 10));
        painelResponsaveis.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelResponsaveis.add(new JLabel("Nome da Mãe:"));
        JTextField txtNomeMae = new JTextField();
        painelResponsaveis.add(txtNomeMae);

        painelResponsaveis.add(new JLabel("Nome do Pai:"));
        JTextField txtNomePai = new JTextField();
        painelResponsaveis.add(txtNomePai);

        painelResponsaveis.add(new JLabel("Telefone (Contato):"));
        JTextField txtTelefone = new JTextField();
        painelResponsaveis.add(txtTelefone);
        painelDeAbas.addTab("Endereço", painelEndereco);
        
                // --- ABA 1: DADOS DO ALUNO ---
                JPanel painelAluno = new JPanel(new GridLayout(7, 1, 18, 10)); // Grid com 2 colunas
                painelAluno.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens
                
                painelAluno.add(new JLabel("Nome do Aluno:"));
                JTextField txtNomeAluno = new JTextField();
                painelAluno.add(txtNomeAluno);
                
                        painelAluno.add(new JLabel("CPF:"));
                        JTextField txtCpfAluno = new JTextField();
                        painelAluno.add(txtCpfAluno);
                        
                                painelAluno.add(new JLabel("Matrícula:"));
                                JTextField txtMatricula = new JTextField();
                                painelAluno.add(txtMatricula);
                                
                                        painelAluno.add(new JLabel("Data Nasc. (DD/MM/AAAA):"));
                                        JTextField txtDataNasc = new JTextField();
                                        painelAluno.add(txtDataNasc);
                                        
                                                // Adicionando os painéis nas Abas
                                                painelDeAbas.addTab("Dados do Aluno", painelAluno);
                                                
                                                        // --- BOTÃO DE SALVAR ---
                                                        JButton btnSalvar = new JButton("Salvar Matrícula");
                                                        painelAluno.add(btnSalvar);
                                                        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
                                                        btnSalvar.setBackground(new Color(34, 139, 34)); // Verde bonito
                                                        btnSalvar.setForeground(Color.WHITE);
                                                        
                                                                // Ação do Botão Salvar (O "Event Listener")
                                                                btnSalvar.addActionListener(e -> {
                                                                    try {
                                                                        // 1. Coleta e converte a data do aluno
                                                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                                                        LocalDate dataNascimento = LocalDate.parse(txtDataNasc.getText(), formatter);
                                                        
                                                                        // 2. Instancia o Aluno
                                                                        Aluno aluno = new Aluno(
                                                                                txtNomeAluno.getText(), 
                                                                                0, // Idade pode ser calculada depois pela data
                                                                                txtCpfAluno.getText(), 
                                                                                txtMatricula.getText(), 
                                                                                dataNascimento, 
                                                                                "Série", "Turma"
                                                                        );
                                                        
                                                                        // 3. Instancia o Endereço
                                                                        Endereco endereco = new Endereco(txtCidade.getText(), txtRua.getText(), "", "", "", "", "", "");
                                                                        aluno.setEndereco(endereco);
                                                        
                                                                        // 4. Instancia os Responsáveis
                                                                        Responsaveis responsaveis = new Responsaveis();
                                                                        responsaveis.setNomeMae(txtNomeMae.getText());
                                                                        responsaveis.setNomePai(txtNomePai.getText());
                                                                        responsaveis.setTelefoneMae(txtTelefone.getText());
                                                                        aluno.setResponsaveis(responsaveis);
                                                        
                                                                        // 5. Salva no Controller
                                                                        controller.cadastrarAluno(aluno);
                                                                        
                                                                        JOptionPane.showMessageDialog(frame, "Aluno " + aluno.getNome() + " cadastrado com sucesso!");
                                                                        
                                                                        // Limpa os campos após salvar
                                                                        txtNomeAluno.setText("");
                                                                        txtCpfAluno.setText("");
                                                                        // (Você pode limpar os outros aqui também)
                                                        
                                                                    } catch (Exception ex) {
                                                                        JOptionPane.showMessageDialog(frame, "Erro ao salvar! Verifique se a data está no formato correto (DD/MM/AAAA).", "Erro", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                });
        painelDeAbas.addTab("Responsáveis", painelResponsaveis);

        // Montando o layout final da janela
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(painelDeAbas, BorderLayout.CENTER);

        // Exibe a janela
        frame.setVisible(true);
    }
}