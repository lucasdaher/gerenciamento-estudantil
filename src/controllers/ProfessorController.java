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
        panel.setLayout(new GridLayout(4,1));

        JButton btnCadastrar = new JButton("Cadastrar Professor");

        btnCadastrar.addActionListener(e-> cadastrarProfessor());

        panel.add(btnCadastrar);

        professorFrame.add(panel);
        professorFrame.setVisible(true);
    }

    private void cadastrarProfessor() {
        String nome;
        int idade;
        String especialidade;

        while (true) {
            nome = JOptionPane.showInputDialog("Digite o nome do professor:");
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Você deve informar o nome do professor.", "Cadastro de Professores", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        }

        while (true) {
            try {
                String idadeInput = JOptionPane.showInputDialog("Digite a idade do professor: ");
                if (idadeInput == null) {
                    JOptionPane.showMessageDialog(null, "O cadastro foi cancelado ao tentar enviar um campo vazio.", "Cadastro de Professores", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                idade = Integer.parseInt(idadeInput);
                if (idade > 0 && idade < 130) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Insira uma idade válida para o professor.", "Cadastro de Professores", JOptionPane.ERROR_MESSAGE);
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Insira um número válido para a idade.", "Cadastro de Professores", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            especialidade = JOptionPane.showInputDialog("Digite a especialidade do professor:");
            if (especialidade == null || especialidade.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Você deve informar uma especialidade para o professor.", "Cadastro de Professores", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        }

        Application.professores.add(new Professor(idade, nome.trim(), especialidade.trim()));
        JOptionPane.showMessageDialog(null, "O professor foi cadastrado com sucesso.", "Cadastro realizado", JOptionPane.PLAIN_MESSAGE);
    }

}
