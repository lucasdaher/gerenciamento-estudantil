package controllers;

import models.Estudante;
import models.Professor;
import views.Application;

import javax.swing.*;
import java.awt.*;

public class EstudanteController extends JFrame {
    public void menuAluno() {
        JFrame alunoFrame = new JFrame("Gerenciar Alunos");
        alunoFrame.setSize(1024,768);
        alunoFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        alunoFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));

        JButton btnCadastrar = new JButton("Cadastrar Novo Aluno");
        JButton btnConsultar = new JButton("Consultar Aluno");
        JButton btnVoltar = new JButton("Voltar ao menu principal");

        btnCadastrar.addActionListener(e -> cadastrarAluno());
        btnConsultar.addActionListener(e -> consultarAluno());
        btnVoltar.addActionListener(e -> alunoFrame.dispose());

        panel.add(btnCadastrar);
        panel.add(btnConsultar);
        panel.add(btnVoltar);

        alunoFrame.add(panel);
        alunoFrame.setVisible(true);
    }

    private void cadastrarAluno() {
        String nome;
        int idade;
        String matricula;

        while (true) {
            nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome não pode estar vazio. Por favor, tente novamente.", "Cadastro de Alunos", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        }

        while (true) {
            try {
                String idadeInput = JOptionPane.showInputDialog("Digite a idade do aluno: ");
                if (idadeInput == null) {
                    JOptionPane.showMessageDialog(null, "O cadastro foi cancelado ao tentar enviar um campo vazio.", "Cadastro de Alunos", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                idade = Integer.parseInt(idadeInput);
                if (idade > 0 && idade < 130) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Insira uma idade válida para o aluno:", "Cadastro de Alunos", JOptionPane.ERROR_MESSAGE);
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Insira um número válido para a idade.", "Cadastro de Alunos", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno:");

            if (matricula == null || matricula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Matrícula não pode estar vazia. Por favor, tente novamente.", "Cadastro de Alunos", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            String matriculaFinal = matricula;
            boolean matriculaExiste = Application.estudantes.stream()
                    .anyMatch(estudante -> estudante.getMatricula().equalsIgnoreCase(matriculaFinal));

            if (matriculaExiste) {
                JOptionPane.showMessageDialog(null, "Já existe um aluno com essa matrícula. Tente outra.", "Cadastro de Alunos", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        }

        Application.estudantes.add(new Estudante(idade, nome.trim(), matricula.trim()));
        JOptionPane.showMessageDialog(null, "O aluno foi cadastrado com sucesso.", "Cadastro realizado", JOptionPane.PLAIN_MESSAGE);
    }

    private void consultarAluno() {
        String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno:");
        Estudante estudanteEncontrado = null;

        for (Estudante estudante : Application.estudantes) {
            if (estudante.getMatricula().equals(matricula)) {
                estudanteEncontrado = estudante;
                break;
            }
        }

        if (estudanteEncontrado != null) {
            int opcao = JOptionPane.showOptionDialog(
                    null,
                    "Aluno encontrado: " + estudanteEncontrado.getNome() + "\nIdade: " + estudanteEncontrado.getIdade() + "\nMatrícula: " + estudanteEncontrado.getMatricula(),
                    "Consultar Aluno",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Editar", "Excluir", "Cancelar"},
                    "Cancelar"
            );

            if (opcao == 0) {
                String novoNome = JOptionPane.showInputDialog("Digite o novo nome do estudante:");
                int novaIdade = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova idade do estudante:"));
                estudanteEncontrado.setNome(novoNome);
                estudanteEncontrado.setIdade(novaIdade);
                JOptionPane.showMessageDialog(null, "As informações deste aluno foram editadas com sucesso.", "Editar Aluno", JOptionPane.PLAIN_MESSAGE);
            } else if (opcao == 1) {
                Application.estudantes.remove(estudanteEncontrado);
                JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!", "Excluir Aluno", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado.", "Consultar Aluno", JOptionPane.ERROR_MESSAGE);
        }
    }
}
