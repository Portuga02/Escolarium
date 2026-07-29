# Escolarium

Sistema de gestão escolar (projeto de estudo em Java/Swing + MySQL).

## Como importar no Eclipse

1. Abra o Eclipse.
2. `File > Import > General > Existing Projects into Workspace`.
3. Selecione a pasta descompactada deste projeto (`Escolarium`).
4. Clique em `Finish`.

O `.project` e `.classpath` já estão incluídos, então o Eclipse deve reconhecer a estrutura automaticamente. Se não reconhecer, transforme manualmente em Java Project e marque a pasta `src` como *source folder*.

## Como rodar

O ponto de entrada do sistema é a tela de login:

```
br.com.escolarium.view.TelaLogin
```

Rode o `main()` dessa classe (clique com botão direito > Run As > Java Application).

Fluxo de navegação: `TelaLogin` → `TelaInicial` (dashboard com menu lateral) → demais telas.

## Estrutura de pacotes

```
br.com.escolarium
├── controller   → AlunoController (regra de negócio)
├── dao          → AlunoDAO (acesso ao banco MySQL)
├── factory      → ConexaoFactory (conexão JDBC), TesteConexao (classe de teste)
├── model        → Aluno, Endereco, Responsaveis, DadosAnteriores
└── view         → todas as telas (Swing)
```

## Status atual (importante!)

A maior parte das telas de gestão (Professor, Turma, Disciplina, Sala,
Calendário, Mural, Relatórios, Financeiro, Usuários, Boletim, Frequência,
Histórico, Dados Anteriores) está **funcional visualmente, mas com dados
MOCKADOS** — ou seja, ainda não persistem no banco de dados. Cada uma
dessas classes tem comentários `// TODO` indicando exatamente onde entraria
a chamada ao DAO/Controller correspondente.

Hoje, apenas o fluxo de **Aluno** (model `Aluno` + `AlunoDAO` +
`AlunoController`) tem conexão real com o banco MySQL implementada
(`INSERT` funcionando via `AlunoDAO.cadastrar()`).

## Configuração do banco de dados

Edite as credenciais em `ConexaoFactory.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/escolarium";
private static final String USUARIO = "root";
private static final String SENHA = "";
```

Não esqueça de ter o driver JDBC do MySQL (`mysql-connector-j`) no
classpath do projeto — adicione o `.jar` em `Build Path > Add External JARs`.

## Funcionalidades já 100% reais (sem mock)

- Botão **Novo** no cadastro de aluno: limpa os campos do formulário.
- Botão **Imprimir**: gera a Ficha do Aluno e abre o diálogo nativo de
  impressão do sistema operacional (pode salvar como PDF).
- Avatar dinâmico (Masculino/Feminino) no cadastro de Aluno e de Professor,
  desenhado via código (`AvatarFactory`) — sem depender de nenhum arquivo
  de imagem externo.
- Ícones dos botões e das telas (`IconeFactory`) — também desenhados via
  código, sem dependências externas.

## Próximos passos sugeridos

1. Conectar de verdade o DAO das outras entidades (Professor, Turma,
   Disciplina, Sala) seguindo o mesmo padrão do `AlunoDAO`.
2. Criar uma superclasse `TelaCadastroBase` (estudo de herança) para
   reduzir a duplicação de código entre as telas de cadastro simples.
3. Validar campos (CPF, CEP, e-mail) com máscaras (`JFormattedTextField`).
