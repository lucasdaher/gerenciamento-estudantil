package controllers;

import models.Curso;
import models.Estudante;
import models.Professor;
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
        panel.setLayout(new GridLayout(5,1));

        JButton btnCadastrar = new JButton("Cadastrar Curso");
        JButton btnConsultar = new JButton("Consultar Curso");
        JButton btnVincularEstudante = new JButton("Matricular Estudante");
        JButton btnVincularProfessor = new JButton("Vincular Professor");
        JButton btnVoltar = new JButton("Voltar ao menu principal");

        btnCadastrar.addActionListener(e -> cadastrarCurso());
        btnConsultar.addActionListener(e -> consultarCurso());
        btnVincularEstudante.addActionListener(e -> matricularEstudante());
        btnVincularProfessor.addActionListener(e -> vincularProfessor());
        btnVoltar.addActionListener(e -> cursoFrame.dispose());

        panel.add(btnCadastrar);
        panel.add(btnConsultar);
        panel.add(btnVincularEstudante);
        panel.add(btnVincularProfessor);
        panel.add(btnVoltar);

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

    private void consultarCurso() {
        String nomeCurso = JOptionPane.showInputDialog("Digite o nome do curso:");
        Curso cursoEncontrado = null;

        for (Curso curso : Application.cursos) {
            if (curso.getNomeCurso().equals(nomeCurso)) {
                cursoEncontrado = curso;
                break;
            }
        }

        if (cursoEncontrado != null) {
            int opcao = JOptionPane.showOptionDialog(
                    null,
                    "Curso encontrado: " + cursoEncontrado.getNomeCurso() + "\nCarga Horária: " + cursoEncontrado.getCargaHoraria(),
                    "Consultar Curso",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Editar", "Excluir", "Cancelar"},
                    "Cancelar"
            );

            if (opcao == 0) {
                String novoNome = JOptionPane.showInputDialog("Digite o novo nome do curso:");
                int novaCargaHoraria = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova carga horária do curso:"));

                cursoEncontrado.setNomeCurso(novoNome);
                cursoEncontrado.setCargaHoraria(novaCargaHoraria);

                JOptionPane.showMessageDialog(null
                        , "As informações deste curso foram editadas com sucesso."
                        , "Editar Curso"
                        , JOptionPane.PLAIN_MESSAGE
                );
            } else if (opcao == 1) {
                Application.cursos.remove(cursoEncontrado);
                JOptionPane.showMessageDialog(null
                        , "o curso foi excluído com sucesso!"
                        , "Excluir Curso"
                        , JOptionPane.PLAIN_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(null, "O curso informado não foi encontrado.", "Consultar Curso", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void matricularEstudante() {
        if (Application.cursos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum curso cadastrado.", "Vinculação de Estudantes", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (Application.estudantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum estudante cadastrado.", "Vinculação de Estudantes", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cursoNome = JOptionPane.showInputDialog("Digite o nome do curso para vincular estudantes:");
        Curso cursoSelecionado = Application.cursos.stream()
                .filter(curso -> curso.getNomeCurso().equalsIgnoreCase(cursoNome))
                .findFirst()
                .orElse(null);

        if (cursoSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Curso não encontrado.", "Vinculação de Estudantes", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String matriculaEstudante = JOptionPane.showInputDialog("Digite a matrícula do estudante:");
        Estudante estudanteSelecionado = Application.estudantes.stream()
                .filter(estudante -> estudante.getMatricula().equalsIgnoreCase(matriculaEstudante))
                .findFirst()
                .orElse(null);

        if (estudanteSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Estudante não encontrado.", "Vinculação de Estudantes", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cursoSelecionado.getEstudantesMatriculados().contains(estudanteSelecionado)) {
            JOptionPane.showMessageDialog(null, "O estudante já está matriculado neste curso.", "Vinculação de Estudantes", JOptionPane.WARNING_MESSAGE);
            return;
        }

        cursoSelecionado.adicionarEstudante(estudanteSelecionado);
        JOptionPane.showMessageDialog(null, "Estudante " + estudanteSelecionado.getNome() + " matriculado no curso " + cursoSelecionado.getNomeCurso() + ".", "Vinculação de Estudantes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void vincularProfessor() {
        if (Application.cursos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum curso cadastrado.", "Associação de Professores", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (Application.professores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum professor cadastrado.", "Associação de Professores", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cursoNome = JOptionPane.showInputDialog("Digite o nome do curso para associar um professor:");
        Curso cursoSelecionado = Application.cursos.stream()
                .filter(curso -> curso.getNomeCurso().equalsIgnoreCase(cursoNome))
                .findFirst()
                .orElse(null);

        if (cursoSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Curso não encontrado.", "Associação de Professores", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nomeProfessor = JOptionPane.showInputDialog("Digite o nome do professor:");
        Professor professorSelecionado = Application.professores.stream()
                .filter(professor -> professor.getNome().equalsIgnoreCase(nomeProfessor))
                .findFirst()
                .orElse(null);

        if (professorSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Professor não encontrado.", "Associação de Professores", JOptionPane.ERROR_MESSAGE);
            return;
        }

        cursoSelecionado.setProfessor(professorSelecionado);
        JOptionPane.showMessageDialog(null, "Professor " + professorSelecionado.getNome() + " associado ao curso " + cursoSelecionado.getNomeCurso() + ".", "Associação de Professores", JOptionPane.INFORMATION_MESSAGE);
    }
}
