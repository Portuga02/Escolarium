package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Tela Inicial do sistema - dashboard simples mostrando os alunos
 * cadastrados (MOCK por enquanto) e um atalho para abrir o Cadastro.
 *
 * TODO: substituir os dados mockados por controller.listarAlunos()
 * assim que o AlunoDAO/AlunoController tiverem o método de listagem.
 */
public class TelaInicial extends JFrame {

    private JTable tabelaAlunos;

    public TelaInicial() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Escolarium - Início");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(painelPrincipal, BorderLayout.CENTER);

        // Cabeçalho
        JLabel lblTitulo = new JLabel("Bem-vindo(a) ao Escolarium");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Tabela de alunos (MOCK)
        JPanel pnlTabela = new JPanel(new BorderLayout());
        pnlTabela.setBorder(new TitledBorder("Alunos Cadastrados"));

        Object[][] dadosMock = {
                {"50", "João da Silva", "9º Ano", "001"},
                {"51", "Maria Oliveira", "8º Ano", "002"},
                {"52", "Pedro Souza", "9º Ano", "001"},
        };
        String[] colunas = {"Matrícula", "Nome", "Turma", "Sala"};

        tabelaAlunos = new JTable(dadosMock, colunas);
        tabelaAlunos.setRowHeight(26);
        pnlTabela.add(new JScrollPane(tabelaAlunos), BorderLayout.CENTER);

        painelPrincipal.add(pnlTabela, BorderLayout.CENTER);

        // Rodapé com ações
        JPanel pnlAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton btnAbrirSelecionado = new JButton("Abrir Cadastro Selecionado");
        btnAbrirSelecionado.addActionListener(e -> {
            int linha = tabelaAlunos.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um aluno na tabela primeiro.",
                        "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // MOCK: abre o cadastro em branco; futuramente vamos carregar os dados do aluno clicado
            new MainUI();
        });

        JButton btnNovoCadastro = new JButton("+ Novo Cadastro", IconeFactory.criar(IconeFactory.Tipo.NOVO, 18));
        btnNovoCadastro.addActionListener(e -> new MainUI());

        pnlAcoes.add(btnAbrirSelecionado);
        pnlAcoes.add(btnNovoCadastro);

        painelPrincipal.add(pnlAcoes, BorderLayout.SOUTH);

        // Menu lateral com atalhos para os demais cadastros do sistema
        painelPrincipal.add(criarMenuLateral(), BorderLayout.WEST);
    }

    // Menu com atalhos para todas as telas do sistema, organizado por seção
    private JPanel criarMenuLateral() {
        JPanel pnlMenu = new JPanel();
        pnlMenu.setLayout(new BoxLayout(pnlMenu, BoxLayout.Y_AXIS));
        pnlMenu.setBorder(new TitledBorder("Menu do Sistema"));
        pnlMenu.setPreferredSize(new Dimension(190, 0));

        pnlMenu.add(criarRotulo("Acadêmico"));
        pnlMenu.add(criarBotaoMenu("Professores", () -> new TelaProfessor()));
        pnlMenu.add(criarBotaoMenu("Turmas", () -> new TelaTurma()));
        pnlMenu.add(criarBotaoMenu("Disciplinas", () -> new TelaDisciplina()));
        pnlMenu.add(criarBotaoMenu("Salas/Ambientes", () -> new TelaSala()));
        pnlMenu.add(criarBotaoMenu("Lançar Notas", () -> new TelaLancamentoNotas()));
        pnlMenu.add(criarBotaoMenu("Boletim", this::abrirBoletimPorMatricula));

        pnlMenu.add(Box.createVerticalStrut(10));
        pnlMenu.add(criarRotulo("Comunicação"));
        pnlMenu.add(criarBotaoMenu("Calendário Escolar", () -> new TelaCalendario()));
        pnlMenu.add(criarBotaoMenu("Mural de Avisos", () -> new TelaMural()));

        pnlMenu.add(Box.createVerticalStrut(10));
        pnlMenu.add(criarRotulo("Gestão"));
        pnlMenu.add(criarBotaoMenu("Relatórios Gerais", () -> new TelaRelatorios()));
        pnlMenu.add(criarBotaoMenu("Painel Financeiro", () -> new TelaFinanceiro()));
        pnlMenu.add(criarBotaoMenu("Usuários e Permissões", () -> new TelaUsuarios()));

        pnlMenu.add(Box.createVerticalStrut(10));
        pnlMenu.add(criarRotulo("Responsáveis"));
        pnlMenu.add(criarBotaoMenu("Portal do Responsável", () -> new TelaPortalResponsavel(null)));

        pnlMenu.add(Box.createVerticalGlue());
        return pnlMenu;
    }

    private JLabel criarRotulo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 11f));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setForeground(new Color(90, 90, 90));
        return lbl;
    }

    private JButton criarBotaoMenu(String texto, Runnable acao) {
        JButton btn = new JButton(texto);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(180, 30));
        btn.addActionListener(e -> acao.run());
        return btn;
    }

    private void abrirBoletimPorMatricula() {
        String matricula = JOptionPane.showInputDialog(this, "Digite a matrícula do aluno:",
                "Boletim", JOptionPane.QUESTION_MESSAGE);
        if (matricula != null && !matricula.trim().isEmpty()) {
            new TelaBoletim(this, matricula.trim());
        }
    }

    //=========================================
    // MAIN - ponto de entrada do sistema
    //=========================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaInicial::new);
    }
}
