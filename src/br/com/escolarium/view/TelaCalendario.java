package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Calendário Escolar - MOCK, sem persistência ainda.
 * TODO: criar EventoEscolar.java (model) + EventoDAO.java e ligar aqui.
 * Ideia de evolução futura: trocar a tabela simples por um componente
 * de calendário visual (mês/semana), mas isso já é avançado pra Swing puro.
 */
public class TelaCalendario extends JFrame {

    private JTextField txtData, txtTitulo;
    private JComboBox<String> cbTipo;
    private DefaultTableModel modeloTabela;
    private JTable tabelaEventos;

    public TelaCalendario() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Calendário Escolar");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        painelEntrada.setBorder(new TitledBorder("Novo Evento"));

        UiUtils.add(painelEntrada, new JLabel("Data (dd/mm/aaaa):"), 0, 0, 1, false);
        txtData = new JTextField(10); UiUtils.add(painelEntrada, txtData, 1, 0, 1, false);

        UiUtils.add(painelEntrada, new JLabel("Título:"), 2, 0, 1, false);
        txtTitulo = new JTextField(20); UiUtils.add(painelEntrada, txtTitulo, 3, 0, 1, true);

        UiUtils.add(painelEntrada, new JLabel("Tipo:"), 0, 1, 1, false);
        cbTipo = new JComboBox<>(new String[]{"Feriado", "Prova", "Reunião", "Evento", "Recesso"});
        UiUtils.add(painelEntrada, cbTipo, 1, 1, 1, true);

        JButton btnAdicionar = new JButton("Adicionar", IconeFactory.criar(IconeFactory.Tipo.NOVO, 16));
        btnAdicionar.addActionListener(e -> adicionarEvento());
        UiUtils.add(painelEntrada, btnAdicionar, 3, 1, 1, false);

        modeloTabela = new DefaultTableModel(new String[]{"Data", "Título", "Tipo"}, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        // MOCK: alguns eventos de exemplo já carregados
        modeloTabela.addRow(new Object[]{"15/07/2026", "Início do 2º Semestre", "Evento"});
        modeloTabela.addRow(new Object[]{"07/09/2026", "Independência do Brasil", "Feriado"});
        modeloTabela.addRow(new Object[]{"20/09/2026", "Prova Bimestral - Matemática", "Prova"});

        tabelaEventos = new JTable(modeloTabela);
        tabelaEventos.setRowHeight(24);
        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBorder(new TitledBorder("Eventos Cadastrados"));
        painelTabela.add(new JScrollPane(tabelaEventos), BorderLayout.CENTER);

        add(painelEntrada, BorderLayout.NORTH);
        add(painelTabela, BorderLayout.CENTER);
    }

    private void adicionarEvento() {
        if (txtData.getText().trim().isEmpty() || txtTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha data e título do evento.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // MOCK - TODO: persistir via EventoController/EventoDAO
        modeloTabela.addRow(new Object[]{txtData.getText(), txtTitulo.getText(), cbTipo.getSelectedItem()});
        txtData.setText("");
        txtTitulo.setText("");
    }
}
