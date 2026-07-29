package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Cadastro de Sala/Ambiente - MOCK, sem persistencia ainda.
 * Ganhou um icone que troca automaticamente conforme o Tipo selecionado.
 * TODO: criar Sala.java (model) + SalaDAO.java e ligar aqui.
 */
public class TelaSala extends JFrame {

    private JTextField txtCodigo, txtCapacidade;
    private JComboBox<String> cbTipo;
    private JCheckBox chkProjetor, chkArCondicionado;
    private JLabel lblIconeTipo;

    private static final Map<String, IconeFactory.Tipo> MAPA_ICONES = new HashMap<>();
    static {
        MAPA_ICONES.put("Sala Comum", IconeFactory.Tipo.SALA_COMUM);
        MAPA_ICONES.put("Laboratório", IconeFactory.Tipo.LABORATORIO);
        MAPA_ICONES.put("Quadra", IconeFactory.Tipo.QUADRA);
        MAPA_ICONES.put("Auditório", IconeFactory.Tipo.AUDITORIO);
        MAPA_ICONES.put("Biblioteca", IconeFactory.Tipo.BIBLIOTECA);
    }

    public TelaSala() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Cadastro de Sala / Ambiente");
        setSize(480, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));

        UiUtils.add(painel, new JLabel("Código da Sala:"), 0, 0, 1, false);
        txtCodigo = new JTextField(10); UiUtils.add(painel, txtCodigo, 1, 0, 1, true);

        UiUtils.add(painel, new JLabel("Tipo:"), 0, 1, 1, false);
        cbTipo = new JComboBox<>(new String[]{"Sala Comum", "Laboratório", "Quadra", "Auditório", "Biblioteca"});
        cbTipo.addActionListener(e -> atualizarIcone());
        UiUtils.add(painel, cbTipo, 1, 1, 1, true);

        UiUtils.add(painel, new JLabel("Capacidade:"), 0, 2, 1, false);
        txtCapacidade = new JTextField(5); UiUtils.add(painel, txtCapacidade, 1, 2, 1, false);

        chkProjetor = new JCheckBox("Possui projetor/TV");
        UiUtils.add(painel, chkProjetor, 0, 3, 2, false);

        chkArCondicionado = new JCheckBox("Possui ar-condicionado");
        UiUtils.add(painel, chkArCondicionado, 0, 4, 2, false);

        JPanel pnlIcone = new JPanel(new BorderLayout());
        pnlIcone.setBorder(new TitledBorder("Ambiente"));
        pnlIcone.setPreferredSize(new Dimension(130, 130));
        lblIconeTipo = new JLabel(IconeFactory.criar(IconeFactory.Tipo.SALA_COMUM, 80), SwingConstants.CENTER);
        pnlIcone.add(lblIconeTipo, BorderLayout.CENTER);

        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
        painelConteudo.add(painel, BorderLayout.CENTER);
        painelConteudo.add(pnlIcone, BorderLayout.EAST);

        add(painelConteudo, BorderLayout.CENTER);
        add(UiUtils.criarRodapeSalvarCancelar(this::salvar, this::dispose), BorderLayout.SOUTH);
    }

    private void atualizarIcone() {
        String tipoSelecionado = (String) cbTipo.getSelectedItem();
        IconeFactory.Tipo tipo = MAPA_ICONES.getOrDefault(tipoSelecionado, IconeFactory.Tipo.SALA_COMUM);
        lblIconeTipo.setIcon(IconeFactory.criar(tipo, 80));
    }

    private void salvar() {
        // MOCK - TODO: instanciar Sala e chamar SalaController/DAO
        JOptionPane.showMessageDialog(this,
                "Sala \"" + txtCodigo.getText() + "\" salva (simulação).",
                "Salvar", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
