package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Mural de Avisos/Comunicados - MOCK, sem persistência ainda.
 * TODO: criar Comunicado.java (model) + ComunicadoDAO.java e ligar aqui.
 * Ideia futura: filtrar avisos por turma/destinatário (hoje é geral).
 */
public class TelaMural extends JFrame {

    private JTextField txtTitulo;
    private JTextArea txtAreaMensagem;
    private JComboBox<String> cbDestinatario;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaAvisos;

    public TelaMural() {
        configurarJanela();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Mural de Avisos");
        setSize(650, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        painelEntrada.setBorder(new TitledBorder("Novo Comunicado"));

        UiUtils.add(painelEntrada, new JLabel("Título:"), 0, 0, 1, false);
        txtTitulo = new JTextField(25); UiUtils.add(painelEntrada, txtTitulo, 1, 0, 2, true);

        UiUtils.add(painelEntrada, new JLabel("Destinatário:"), 0, 1, 1, false);
        cbDestinatario = new JComboBox<>(new String[]{"Todos", "Responsáveis", "Professores", "Turma Específica"});
        UiUtils.add(painelEntrada, cbDestinatario, 1, 1, 1, true);

        UiUtils.add(painelEntrada, new JLabel("Mensagem:"), 0, 2, 1, false);
        txtAreaMensagem = new JTextArea(4, 25);
        txtAreaMensagem.setLineWrap(true);
        txtAreaMensagem.setWrapStyleWord(true);
        UiUtils.add(painelEntrada, new JScrollPane(txtAreaMensagem), 1, 2, 2, true);

        JButton btnPublicar = new JButton("Publicar Aviso");
        btnPublicar.addActionListener(e -> publicarAviso());
        UiUtils.add(painelEntrada, btnPublicar, 1, 3, 1, false);

        modeloLista = new DefaultListModel<>();
        // MOCK: avisos de exemplo
        modeloLista.addElement("[10/07/2026 - Todos] Reunião de pais dia 20/07 às 19h.");
        modeloLista.addElement("[08/07/2026 - Responsáveis] Entrega de boletim adiada para sexta-feira.");

        listaAvisos = new JList<>(modeloLista);
        JPanel painelLista = new JPanel(new BorderLayout());
        painelLista.setBorder(new TitledBorder("Avisos Publicados"));
        painelLista.add(new JScrollPane(listaAvisos), BorderLayout.CENTER);

        add(painelEntrada, BorderLayout.NORTH);
        add(painelLista, BorderLayout.CENTER);
    }

    private void publicarAviso() {
        String titulo = txtTitulo.getText().trim();
        String mensagem = txtAreaMensagem.getText().trim();
        if (titulo.isEmpty() || mensagem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha título e mensagem.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // MOCK - TODO: persistir via ComunicadoController/ComunicadoDAO
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        modeloLista.addElement("[" + data + " - " + cbDestinatario.getSelectedItem() + "] " + titulo + ": " + mensagem);
        txtTitulo.setText("");
        txtAreaMensagem.setText("");
    }
}
