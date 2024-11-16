package controllers;

import models.Curso;
import views.Application;

import javax.swing.*;
import java.awt.*;

public class CursoController extends JFrame{
    public void menuCurso() {
        JFrame cursoFrame = new JFrame("Gerenciar Cursos");
        cursoFrame.setSize(1024,768);
        cursoFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cursoFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));

        JButton btnCadastrar = new JButton("Cadastrar Curso");
        JButton btnConsultar = new JButton("Consultar Curso");
        JButton btnVincular = new JButton("Vincular Alunos ou Professores");
        JButton btnVoltar = new JButton("Voltar ao menu principal");

        btnCadastrar.addActionListener(e -> cadastrarCurso());
        btnVoltar.addActionListener(e -> System.exit(0));

        panel.add(btnCadastrar);

        cursoFrame.add(panel);
        cursoFrame.setVisible(true);
    }

    private void cadastrarCurso() {
        String nome;
        int cargaHoraria;

        while (true) {
            nome = JOptionPane.showInputDialog("Digite o nome do curso:");
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Você deve informar o nome do curso.", "Cadastro de Curso", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        }

        while (true) {
            try {
                String cargaHorariaInput = JOptionPane.showInputDialog("Digite a carga horária do curso: ");
                if (cargaHorariaInput == null) {
                    JOptionPane.showMessageDialog(null, "O cadastro foi cancelado ao tentar enviar um campo vazio.", "Cadastro de Curso", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cargaHoraria = Integer.parseInt(cargaHorariaInput);
                if (cargaHoraria > 0) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Insira uma carga horária válida para o curso.", "Cadastro de Curso", JOptionPane.ERROR_MESSAGE);
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Insira um número válido para a carga horária do curso.", "Cadastro de Curso", JOptionPane.ERROR_MESSAGE);
            }
        }

        Application.cursos.add(new Curso(nome.trim(), cargaHoraria));
        JOptionPane.showMessageDialog(null, "O curso foi cadastrado com sucesso.", "Cadastro realizado", JOptionPane.PLAIN_MESSAGE);
    }
}
