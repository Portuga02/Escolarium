# 🏫 Escolarium - Sistema de Gestão Escolar

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)
![Swing](https://img.shields.io/badge/Java_Swing-007396?style=for-the-badge&logo=java&logoColor=white)

O **Escolarium** é um sistema desktop desenvolvido em Java para gerenciamento de matrículas e dados de alunos. O projeto foi construído com foco em **Clean Code**, **Arquitetura em Camadas (MVC)** e fortes princípios de **Programação Orientada a Objetos (POO)**.

---

## ✨ Funcionalidades

- **Cadastro Completo de Alunos:** Gerenciamento de dados vitais como Nome, CPF, Matrícula, Idade, Série e Data de Nascimento (`LocalDate`).
- **Composição de Dados:** Endereços completos (Logradouro, Bairro, CEP, Cidade, UF) e dados de Responsáveis Financeiros modelados em entidades separadas.
- **Interface Gráfica (GUI):** Interface intuitiva construída com Java Swing, utilizando `JTabbedPane` para organização do formulário em abas funcionais.
- **Validação de Entrada:** Tratamento de exceções (`try-catch`) e validações de tipos para garantir a integridade dos dados durante o cadastro.

---

## 🏗️ Arquitetura do Projeto

O sistema foi estruturado separando as responsabilidades para garantir escalabilidade e fácil manutenção:

* **`br.com.escolarium.model`:** Contém as classes de entidade (`Aluno`, `Endereco`, `Responsaveis`). Aplica conceitos de Encapsulamento e Composição.
* **`br.com.escolarium.controller`:** Responsável pelas regras de negócio e controle de fluxo de dados (armazenamento em memória via `ArrayList`).
* **`br.com.escolarium.view`:** Contém as interfaces visuais e a interação com o usuário (`MainUI`), desacoplada da lógica de armazenamento.

---

## 🚀 Como Executar

### Pré-requisitos
- **Java Development Kit (JDK):** Versão 17 ou superior.
- **IDE Recomendada:** Eclipse IDE (com plugin WindowBuilder opcional para edição visual).

### Passos
1. Clone este repositório:
   ```bash
   git clone [https://github.com/Portuga02/escolarium.git](https://github.com/Portuga02/escolarium.git)
