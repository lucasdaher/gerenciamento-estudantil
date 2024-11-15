package controllers;

import models.Estudante;
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
        btnVoltar.addActionListener(e -> alunoFrame.dispose());

        panel.add(btnCadastrar);
        panel.add(btnConsultar);
        panel.add(btnVoltar);

        alunoFrame.add(panel);
        alunoFrame.setVisible(true);
    }

    private void cadastrarAluno() {
        String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
        String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno:");

        int idade;

        do {
            idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade do aluno:"));
        } while(idade <= 0);

        Application.estudantes.add(new Estudante(idade, nome, matricula));
        JOptionPane.showMessageDialog(null, "O aluno foi cadastrado com sucesso.", "Cadastro realizado", JOptionPane.PLAIN_MESSAGE);
    }
}
