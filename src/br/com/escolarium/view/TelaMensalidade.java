package br.com.escolarium.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Tela de Mensalidade - por enquanto com dados MOCKADOS.
 * TODO: substituir os dados fixos por uma consulta real
 * (ex: MensalidadeDAO.listarPorMatricula(matricula)).
 */
public class TelaMensalidade extends JDialog {

    public TelaMensalidade(Frame parent, String matriculaAluno) {
        super(parent, "Controle de Mensalidade", true);
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Mensalidades - Matrícula: " + (matriculaAluno == null ? "(nenhum aluno selecionado)" : matriculaAluno));
        lblTitulo.setBorder(new EmptyBorder(10, 10, 0, 10));
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD));

        // MOCK: dados fixos só pra visualizar a tela funcionando
        Object[][] dadosMock = {
                {"Janeiro/2026", "10/01/2026", "R$ 350,00", "Pago"},
                {"Fevereiro/2026", "10/02/2026", "R$ 350,00", "Pago"},
                {"Março/2026", "10/03/2026", "R$ 350,00", "Em aberto"},
        };
        String[] colunas = {"Mês Referência", "Vencimento", "Valor", "Status"};

        JTable tabela = new JTable(dadosMock, colunas);
        tabela.setRowHeight(24);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        JPanel pnlRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlRodape.add(btnFechar);

        add(lblTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(pnlRodape, BorderLayout.SOUTH);
    }
}
