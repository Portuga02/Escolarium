package estudojava.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import estudojava.entidades.Aluno;
import estudojava.entidades.Disciplina;

public class Executavel {
	public static void main(String[] args) {

		JTextField campoLogin = new JTextField(15);

		javax.swing.JPasswordField campoSenha = new javax.swing.JPasswordField(15);
		Object[] formularioLogin= {
				"Usuário:",campoLogin,
				"Senha:",campoSenha
		};
	}

	private static void executarCadastro() {
		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");

			mascaraCpf.setPlaceholderCharacter('_');
			mascaraData.setPlaceholderCharacter('_');

			JTextField campoNome = new JTextField(20);
			JTextField campoIdade = new JTextField(5);

			// Combobox de Sexo
			String[] opcoesSexo = { "Masculino", "Feminino" };
			JComboBox<String> comboSexo = new JComboBox<>(opcoesSexo);

			// Label que vai exibir a foto em tempo real
			JLabel labelFoto = new JLabel();
			labelFoto.setHorizontalAlignment(JLabel.CENTER);

			// Carrega as imagens apontando corretamente para a pasta "img/"
			ImageIcon iconeMasculino = carregarIcone("img/boy.jpg");
			ImageIcon iconeFeminino = carregarIcone("img/girl.jpg");

			// Define a foto inicial baseada na seleção padrão ("Masculino")
			labelFoto.setIcon(iconeMasculino);

			// Ouvinte para trocar a imagem em tempo real quando mudar o JComboBox
			comboSexo.addItemListener(e -> {
				if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
					String selecionado = (String) comboSexo.getSelectedItem();
					if ("Masculino".equals(selecionado)) {
						labelFoto.setIcon(iconeMasculino);
					} else {
						labelFoto.setIcon(iconeFeminino);
					}
				}
			});

			JFormattedTextField campoCPF = new JFormattedTextField(mascaraCpf);
			JTextField campoRG = new JTextField(15);
			JFormattedTextField campoNascimento = new JFormattedTextField(mascaraData);
			JTextField campoMae = new JTextField(20);
			JTextField campoPai = new JTextField(20);
			JTextField campoEscola = new JTextField(20);
			JTextField campoMatricula = new JTextField(10);

			JTextField campoNota1 = new JTextField(5);
			JTextField campoNota2 = new JTextField(5);
			JTextField campoNota3 = new JTextField(5);
			JTextField campoNota4 = new JTextField(5);

			// Painel principal do formulário (com divisões para caber os campos e a foto ao
			// lado)
			JPanel painelFormulario = new JPanel(new BorderLayout(10, 10));

			// Painel esquerdo com os campos em grade (14 linhas certinhas)
			JPanel painelCampos = new JPanel(new GridLayout(14, 2, 5, 5));
			painelCampos.add(new JLabel("Nome:"));
			painelCampos.add(campoNome);
			painelCampos.add(new JLabel("Idade:"));
			painelCampos.add(campoIdade);
			painelCampos.add(new JLabel("Sexo:"));
			painelCampos.add(comboSexo);
			painelCampos.add(new JLabel("CPF:"));
			painelCampos.add(campoCPF);
			painelCampos.add(new JLabel("RG:"));
			painelCampos.add(campoRG);
			painelCampos.add(new JLabel("Nascimento (DD/MM/AAAA):"));
			painelCampos.add(campoNascimento);
			painelCampos.add(new JLabel("Nome da Mãe:"));
			painelCampos.add(campoMae);
			painelCampos.add(new JLabel("Nome do Pai:"));
			painelCampos.add(campoPai);
			painelCampos.add(new JLabel("Escola:"));
			painelCampos.add(campoEscola);
			painelCampos.add(new JLabel("Matrícula:"));
			painelCampos.add(campoMatricula);
			painelCampos.add(new JLabel("Primeira Nota:"));
			painelCampos.add(campoNota1);
			painelCampos.add(new JLabel("Segunda Nota:"));
			painelCampos.add(campoNota2);
			painelCampos.add(new JLabel("Terceira Nota:"));
			painelCampos.add(campoNota3);
			painelCampos.add(new JLabel("Quarta Nota:"));
			painelCampos.add(campoNota4);

			painelFormulario.add(painelCampos, BorderLayout.CENTER);
			painelFormulario.add(labelFoto, BorderLayout.EAST); // Imagem fica posicionada à direita

			int opcao = JOptionPane.showConfirmDialog(null, painelFormulario, "Cadastro Escolar Interativo",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (opcao == JOptionPane.OK_OPTION) {
				Aluno aluno = new Aluno();
				aluno.setNome(campoNome.getText());
				aluno.setIdade(campoIdade.getText());
				aluno.setNumeroCPF(campoCPF.getText());
				aluno.setNumeroRG(campoRG.getText());
				aluno.setDataNascimento(campoNascimento.getText());
				aluno.setNomeMae(campoMae.getText());
				aluno.setNomePai(campoPai.getText());
				aluno.setNomeEscola(campoEscola.getText());
				aluno.setMatricula(campoMatricula.getText());

				String sexoSelecionado = (String) comboSexo.getSelectedItem();

				Disciplina disciplina = new Disciplina();
				disciplina.setDisciplinas("banco de dados");

				disciplina.setAluno(aluno);

				String sql = "INSERT INTO aluno (nome, idade, sexo, cpf, rg, data_nascimento, nome_mae, nome_pai, escola, matricula, media, resultado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				try (Connection conexaoDb = estudojava.myqlconnection.conexao.getConnection();
						PreparedStatement stmt = conexaoDb.prepareStatement(sql)) {

					if (conexaoDb != null) {
						stmt.setString(1, aluno.getNome());
						stmt.setString(2, aluno.getIdade());
						stmt.setString(3, sexoSelecionado);
						stmt.setString(4, aluno.getNumeroCPF());
						stmt.setString(5, aluno.getNumeroRG());
						stmt.setString(6, aluno.getDataNascimento());
						stmt.setString(7, aluno.getNomeMae());
						stmt.setString(8, aluno.getNomePai());
						stmt.setString(9, aluno.getNomeEscola());
						stmt.setString(10, aluno.getMatricula());
						stmt.setDouble(11, aluno.getMediaNotasSemestreal());
						stmt.setBoolean(12, aluno.getAlunoAprovado());

						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Sucesso! Dados salvos no MySQL do escolarium!");
					}
				} catch (Exception dbEx) {
					JOptionPane.showMessageDialog(null, "Erro ao gravar no banco: " + dbEx.getMessage());
				}

				JOptionPane.showMessageDialog(null,
						"--- DADOS PROCESSADOS ---\n" + "Nome: " + aluno.getNome() + "\nSexo: " + sexoSelecionado
								+ "\nCPF: " + aluno.getNumeroCPF() + "\nMédia: " + aluno.getMediaNotasSemestreal()
								+ "\nResultado: " + aluno.getAlunoAprovado());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Operação cancelada ou erro nos dados: " + e.getMessage());
		}
	}

	private static ImageIcon carregarIcone(String caminho) {
		try {

			java.io.File arquivo = new java.io.File(caminho);
			if (!arquivo.exists()) {
				System.err.println(
						"❌ ERRO: A imagem NÃO foi encontrada no caminho absoluto: " + arquivo.getAbsolutePath());
				return null;
			}

			ImageIcon original = new ImageIcon(caminho);

			if (original.getIconWidth() <= 0) {
				System.err.println("❌ ERRO: O arquivo existe, mas não é uma imagem válida: " + caminho);
				return null;
			}

			Image imagemRedimensionada = original.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
			return new ImageIcon(imagemRedimensionada);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void executarConsulta() {
		String sql = "SELECT * FROM aluno";
		StringBuilder resultados = new StringBuilder("--- ALUNOS CADASTRADOS NO ESCOLARIUM ---\n\n");

		try (Connection conexaoDb = estudojava.myqlconnection.conexao.getConnection();
				PreparedStatement stmt = conexaoDb.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			boolean encontrou = false;
			while (rs.next()) {
				encontrou = true;
				resultados.append("ID: ").append(rs.getInt("id")).append(" | Nome: ").append(rs.getString("nome"))
						.append(" | Sexo: ").append(rs.getString("sexo")).append(" | CPF: ").append(rs.getString("cpf"))
						.append(" | Média: ").append(rs.getDouble("media")).append(" | Status: ")
						.append(rs.getString("resultado"))
						.append("\n--------------------------------------------------\n");
			}

			if (!encontrou) {
				resultados.append("Nenhum aluno cadastrado no banco ainda.");
			}

			JOptionPane.showMessageDialog(null, resultados.toString(), "Consulta de Alunos",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + e.getMessage());
		}
	}

}