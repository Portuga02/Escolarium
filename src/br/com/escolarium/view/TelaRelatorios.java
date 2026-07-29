package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Relatórios Gerais - MOCK, dados fixos por enquanto.
 * TODO: cada relatório vai virar uma consulta agregada real (COUNT, GROUP BY, etc.)
 * no respectivo DAO. Por enquanto só troca a tabela conforme o tipo escolhido.
 */
public class TelaRelatorios extends JFrame {

    private JComboBox<String> cbTipoRelatorio;
    private JTable tabelaResultado;
    private JPanel painelTabelaContainer;

    public TelaRelatorios() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Relatórios Gerais");
        setSize(600, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelTopo.setBorder(new TitledBorder("Selecione o Relatório"));

        cbTipoRelatorio = new JComboBox<>(new String[]{
                "Alunos por Turma", "Inadimplência", "Frequência Abaixo de 75%", "Média Geral por Disciplina"
        });
        JButton btnGerar = new JButton("Gerar Relatório");
        btnGerar.addActionListener(e -> gerarRelatorio());

        painelTopo.add(new JLabel("Tipo:"));
        painelTopo.add(cbTipoRelatorio);
        painelTopo.add(btnGerar);

        painelTabelaContainer = new JPanel(new BorderLayout());
        painelTabelaContainer.setBorder(new TitledBorder("Resultado"));
        atualizarTabela(new Object[][]{}, new String[]{"Selecione um relatório e clique em Gerar"});

        add(painelTopo, BorderLayout.NORTH);
        add(painelTabelaContainer, BorderLayout.CENTER);
    }

    private void gerarRelatorio() {
        String tipo = (String) cbTipoRelatorio.getSelectedItem();
        // MOCK - TODO: trocar cada bloco por uma consulta real no DAO correspondente
        switch (tipo) {
            case "Alunos por Turma":
                atualizarTabela(new Object[][]{
                        {"9º Ano A", 28}, {"9º Ano B", 25}, {"8º Ano A", 30}
                }, new String[]{"Turma", "Quantidade de Alunos"});
                break;
            case "Inadimplência":
                atualizarTabela(new Object[][]{
                        {"52", "Pedro Souza", "Março/2026", "R$ 350,00"},
                        {"55", "Ana Costa", "Fevereiro/2026", "R$ 350,00"}
                }, new String[]{"Matrícula", "Aluno", "Mês em Aberto", "Valor"});
                break;
            case "Frequência Abaixo de 75%":
                atualizarTabela(new Object[][]{
                        {"55", "Ana Costa", "68%"}, {"60", "Lucas Lima", "71%"}
                }, new String[]{"Matrícula", "Aluno", "Frequência"});
                break;
            case "Média Geral por Disciplina":
                atualizarTabela(new Object[][]{
                        {"Matemática", "7.8"}, {"Português", "8.2"}, {"Ciências", "7.5"}
                }, new String[]{"Disciplina", "Média da Escola"});
                break;
        }
    }

    private void atualizarTabela(Object[][] dados, String[] colunas) {
        painelTabelaContainer.removeAll();
        tabelaResultado = new JTable(dados, colunas);
        tabelaResultado.setRowHeight(24);
        painelTabelaContainer.add(new JScrollPane(tabelaResultado), BorderLayout.CENTER);
        painelTabelaContainer.revalidate();
        painelTabelaContainer.repaint();
    }
}
