package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * Painel Financeiro completo (visao ADMIN/gestao) - MOCK, sem persistencia ainda.
 * Ganhou: indicador visual de status (bolinha colorida), filtro por status
 * e totalizador em R$ (nao so contagem de boletos).
 * TODO: criar Boleto.java (model) + FinanceiroDAO.java e ligar aqui.
 */
public class TelaFinanceiro extends JFrame {

    private DefaultTableModel modeloTabela;
    private JTable tabelaBoletos;
    private JLabel lblResumo;
    private JComboBox<String> cbFiltro;

    private final Vector<Vector<Object>> todosOsBoletos = new Vector<>();

    public TelaFinanceiro() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Painel Financeiro - Administração");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setBorder(new EmptyBorder(10, 10, 0, 10));

        JPanel pnlFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pnlFiltro.add(new JLabel("Mostrar:"));
        cbFiltro = new JComboBox<>(new String[]{"Todos", "Pago", "Em aberto", "Atrasado"});
        cbFiltro.addActionListener(e -> aplicarFiltro());
        pnlFiltro.add(cbFiltro);
        painelTopo.add(pnlFiltro, BorderLayout.WEST);

        JButton btnEmitirBoleto = new JButton("+ Emitir Boleto", IconeFactory.criar(IconeFactory.Tipo.NOVO, 16));
        btnEmitirBoleto.addActionListener(e -> emitirBoleto());
        painelTopo.add(btnEmitirBoleto, BorderLayout.EAST);

        modeloTabela = new DefaultTableModel(
                new String[]{"Matrícula", "Aluno", "Mês Referência", "Valor", "Status"}, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };

        adicionarBoletoInterno("50", "João da Silva", "Julho/2026", 350.00, "Pago");
        adicionarBoletoInterno("51", "Maria Oliveira", "Julho/2026", 350.00, "Em aberto");
        adicionarBoletoInterno("52", "Pedro Souza", "Junho/2026", 350.00, "Atrasado");
        adicionarBoletoInterno("55", "Ana Costa", "Fevereiro/2026", 350.00, "Atrasado");

        tabelaBoletos = new JTable(modeloTabela);
        tabelaBoletos.setRowHeight(26);
        tabelaBoletos.getColumnModel().getColumn(4).setCellRenderer(new RenderizadorStatus());

        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBorder(new TitledBorder("Boletos"));
        painelTabela.add(new JScrollPane(tabelaBoletos), BorderLayout.CENTER);

        JButton btnMarcarPago = new JButton("Marcar como Pago");
        btnMarcarPago.addActionListener(e -> marcarComoPago());
        lblResumo = new JLabel();
        lblResumo.setFont(lblResumo.getFont().deriveFont(Font.BOLD));
        JPanel pnlAcoes = new JPanel(new BorderLayout());
        pnlAcoes.add(lblResumo, BorderLayout.WEST);
        JPanel pnlBotaoAcao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnlBotaoAcao.add(btnMarcarPago);
        pnlAcoes.add(pnlBotaoAcao, BorderLayout.EAST);

        aplicarFiltro();

        add(painelTopo, BorderLayout.NORTH);
        add(painelTabela, BorderLayout.CENTER);
        add(pnlAcoes, BorderLayout.SOUTH);
    }

    private static class RenderizadorStatus extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                         boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String status = String.valueOf(value);
            IconeFactory.Tipo tipoIcone;
            switch (status) {
                case "Pago": tipoIcone = IconeFactory.Tipo.STATUS_PAGO; break;
                case "Atrasado": tipoIcone = IconeFactory.Tipo.STATUS_ATRASADO; break;
                default: tipoIcone = IconeFactory.Tipo.STATUS_EM_ABERTO;
            }
            label.setIcon(IconeFactory.criar(tipoIcone, 12));
            label.setIconTextGap(8);
            return label;
        }
    }

    private void adicionarBoletoInterno(String matricula, String aluno, String mes, double valor, String status) {
        Vector<Object> linha = new Vector<>();
        linha.add(matricula);
        linha.add(aluno);
        linha.add(mes);
        linha.add(valor);
        linha.add(status);
        todosOsBoletos.add(linha);
    }

    private void aplicarFiltro() {
        String filtro = (String) cbFiltro.getSelectedItem();
        modeloTabela.setRowCount(0);
        for (Vector<Object> linha : todosOsBoletos) {
            String status = String.valueOf(linha.get(4));
            if (filtro == null || "Todos".equals(filtro) || filtro.equals(status)) {
                modeloTabela.addRow(formatarLinhaParaExibicao(linha));
            }
        }
        atualizarResumo();
    }

    private Object[] formatarLinhaParaExibicao(Vector<Object> linha) {
        double valor = (double) linha.get(3);
        return new Object[]{linha.get(0), linha.get(1), linha.get(2), String.format("R$ %.2f", valor), linha.get(4)};
    }

    private void emitirBoleto() {
        String matricula = JOptionPane.showInputDialog(this, "Matrícula do aluno:", "Emitir Boleto", JOptionPane.QUESTION_MESSAGE);
        if (matricula == null || matricula.trim().isEmpty()) return;
        adicionarBoletoInterno(matricula.trim(), "(a definir)", "Julho/2026", 350.00, "Em aberto");
        aplicarFiltro();
    }

    private void marcarComoPago() {
        int linha = tabelaBoletos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um boleto na tabela.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String matricula = String.valueOf(modeloTabela.getValueAt(linha, 0));
        String mes = String.valueOf(modeloTabela.getValueAt(linha, 2));
        for (Vector<Object> l : todosOsBoletos) {
            if (l.get(0).equals(matricula) && l.get(2).equals(mes)) {
                l.set(4, "Pago");
                break;
            }
        }
        aplicarFiltro();
    }

    private void atualizarResumo() {
        int total = modeloTabela.getRowCount();
        double totalEmAberto = 0, totalAtrasado = 0;
        for (Vector<Object> l : todosOsBoletos) {
            String status = String.valueOf(l.get(4));
            double valor = (double) l.get(3);
            if ("Em aberto".equals(status)) totalEmAberto += valor;
            if ("Atrasado".equals(status)) totalAtrasado += valor;
        }
        lblResumo.setText(String.format("Exibindo: %d boletos  |  Em aberto: R$ %.2f  |  Atrasado: R$ %.2f",
                total, totalEmAberto, totalAtrasado));
    }
}
