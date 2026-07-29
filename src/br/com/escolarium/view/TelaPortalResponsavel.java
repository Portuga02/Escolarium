package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Portal do Responsável - MOCK, dados fixos por enquanto.
 * Visão simplificada e SOMENTE LEITURA pra pai/mãe: notas, frequência
 * e mensalidades do filho - sem acesso às telas administrativas.
 * TODO: no login (TelaLogin), diferenciar o perfil do usuário e,
 * se for "responsável", abrir esta tela em vez da TelaInicial.
 */
public class TelaPortalResponsavel extends JFrame {

    public TelaPortalResponsavel(String nomeAluno) {
        configurarJanela(nomeAluno);
        setVisible(true);
    }

    private void configurarJanela(String nomeAluno) {
        setTitle("Portal do Responsável");
        setSize(650, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Acompanhamento de: " + (nomeAluno == null ? "João da Silva" : nomeAluno));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setBorder(new EmptyBorder(10, 10, 0, 10));

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Notas", criarAbaNotas());
        abas.addTab("Frequência", criarAbaFrequencia());
        abas.addTab("Financeiro", criarAbaFinanceiro());

        add(lblTitulo, BorderLayout.NORTH);
        add(abas, BorderLayout.CENTER);
    }

    private JPanel criarAbaNotas() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));
        Object[][] dados = {
                {"Matemática", "8.0", "7.5"}, {"Português", "9.0", "8.5"}, {"Ciências", "7.0", "7.0"}
        };
        JTable tabela = new JTable(dados, new String[]{"Disciplina", "1º Bim", "2º Bim"});
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarAbaFrequencia() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel lbl = new JLabel("Frequência geral no ano: 94%", SwingConstants.CENTER);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 14f));
        painel.add(lbl, BorderLayout.NORTH);
        Object[][] dados = {
                {"01/07/2026", "Presente"}, {"03/07/2026", "Presente"}, {"06/07/2026", "Falta"}
        };
        JTable tabela = new JTable(dados, new String[]{"Data", "Situação"});
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarAbaFinanceiro() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));
        Object[][] dados = {
                {"Junho/2026", "R$ 350,00", "Pago"}, {"Julho/2026", "R$ 350,00", "Em aberto"}
        };
        JTable tabela = new JTable(dados, new String[]{"Mês", "Valor", "Status"});
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);
        return painel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPortalResponsavel(null));
    }
}
