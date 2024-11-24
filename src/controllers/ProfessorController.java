package controllers;

import models.Professor;
import views.Application;

import javax.swing.*;
import java.awt.*;

public class ProfessorController extends JFrame {
    public void menuProfessor() {
        JFrame professorFrame = new JFrame("Gerenciar Professores");
        professorFrame.setSize(1024,768);
        professorFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        professorFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));

        JButton btnCadastrar = new JButton("Cadastrar Professor");
        JButton btnConsultar = new JButton("Consultar Professor");
        JButton btnVoltar = new JButton("Voltar ao menu principal");

        btnCadastrar.addActionListener(e-> cadastrarProfessor());
        btnConsultar.addActionListener(e-> consultarProfessor());
        btnVoltar.addActionListener(e -> professorFrame.dispose());

        panel.add(btnCadastrar);
        panel.add(btnConsultar);
        panel.add(btnVoltar);

        professorFrame.add(panel);
        professorFrame.setVisible(true);
    }

    private void cadastrarProfessor() {
        int idade;
        String especialidade;
        String nome;

        while(true) {
            nome = JOptionPane.showInputDialog("Digite o nome do professor:");

            for (Professor professor : Application.professores) {
                if (professor.getNome().equals(nome)) {
                    JOptionPane.showMessageDialog(null
                            , "Um professor com este nome já está cadastrado."
                            , "Cadastro de Professores"
                            , JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    break;
                }
            }

            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null
                        , "Você deve informar o nome do professor."
                        , "Cadastro de Professores"
                        , JOptionPane.ERROR_MESSAGE);

            } else {
                break;
            }
        }

        while (true) {
            try {
                String idadeInput = JOptionPane.showInputDialog("Digite a idade do professor: ");
                if (idadeInput == null) {
                    JOptionPane.showMessageDialog(null
                            , "O cadastro foi cancelado ao tentar enviar um campo vazio."
                            , "Cadastro de Professor"
                            , JOptionPane.ERROR_MESSAGE);
                    return;
                }
                idade = Integer.parseInt(idadeInput);
                if (idade > 0 && idade < 130) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null
                            , "Insira uma idade válida para o professor."
                            , "Cadastro de Professor"
                            , JOptionPane.ERROR_MESSAGE);
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null
                        , "Insira um número válido para a idade."
                        , "Cadastro de Professor"
                        , JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            especialidade = JOptionPane.showInputDialog("Digite a especialidade do professor:");
            if (especialidade == null) {
                JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
            }
            if (especialidade.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null
                        , "Você deve informar uma especialidade para o professor."
                        , "Cadastro de Professores"
                        , JOptionPane.ERROR_MESSAGE);

            } else {
                break;
            }
        }

        Application.professores.add(new Professor(idade, nome.trim(), especialidade.trim()));
        JOptionPane.showMessageDialog(null
                , "O professor foi cadastrado com sucesso."
                , "Cadastro realizado"
                , JOptionPane.PLAIN_MESSAGE);
    }

    private void consultarProfessor() {
        String nomeProfessor = JOptionPane.showInputDialog("Digite o nome do professor:");
        Professor professorEncontrado = null;

        for (Professor professor : Application.professores) {
            if (professor.getNome().equals(nomeProfessor)) {
                professorEncontrado = professor;
                break;
            }
        }

        if (professorEncontrado != null) {
            int opcao = JOptionPane.showOptionDialog(
                    null,
                    "Professor encontrado: " + professorEncontrado.getNome() + "\nIdade: " + professorEncontrado.getIdade() + "\nEspecialidade: " + professorEncontrado.getEspecialidade(),
                    "Consultar Professor",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Editar", "Excluir", "Cancelar"},
                    "Cancelar"
            );

            if (opcao == 0) {
                String novoNome = JOptionPane.showInputDialog("Digite o novo nome do professor:");
                int novaIdade = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova idade do professor:"));
                professorEncontrado.setNome(novoNome);
                professorEncontrado.setIdade(novaIdade);
                JOptionPane.showMessageDialog(null, "As informações deste professor foram editadas com sucesso.", "Editar Professor", JOptionPane.PLAIN_MESSAGE);
            } else if (opcao == 1) {
                Application.professores.remove(professorEncontrado);
                JOptionPane.showMessageDialog(null, "Professor excluído com sucesso!", "Excluir Professor", JOptionPane.PLAIN_MESSAGE);
            } else if (opcao == 2) {
                JOptionPane.showMessageDialog(null, "Você saiu da consulta de professor.", "Operação Cancelada", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "A consulta não encontrou um professor com este nome.", "Consultar Professor", JOptionPane.ERROR_MESSAGE);
        }
    }

}
