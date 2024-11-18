package controllers;

import models.Curso;
import models.Estudante;
import models.Professor;
import views.Application;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RelatorioController extends JFrame {
    public void menuRelatorio() {
        JFrame relatorioFrame = new JFrame("Relatórios");
        relatorioFrame.setSize(1024, 768);
        relatorioFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        relatorioFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton btnRelatorioEstudantes = new JButton("Relatório de Estudantes");
        JButton btnRelatorioProfessores = new JButton("Relatório de Professores");
        JButton btnRelatorioCursos = new JButton("Relatório de Cursos");
        JButton btnSair = new JButton("Voltar");

        btnRelatorioEstudantes.addActionListener(e -> exibirRelatorioEstudantes());
        btnRelatorioProfessores.addActionListener(e -> exibirRelatorioProfessores());
        btnRelatorioCursos.addActionListener(e -> exibirRelatorioCurso());
        btnSair.addActionListener(e -> relatorioFrame.dispose());

        panel.add(btnRelatorioEstudantes);
        panel.add(btnRelatorioProfessores);
        panel.add(btnRelatorioCursos);
        panel.add(btnSair);

        relatorioFrame.add(panel);
        relatorioFrame.setVisible(true);
    }

    private void exibirRelatorioEstudantes() {
        StringBuilder relatorio = new StringBuilder("Relatório de Estudantes:\n");

        if (Application.estudantes.isEmpty()) {
            relatorio.append("Nenhum estudante cadastrado.\n");
        } else {
            for (Estudante estudante : Application.estudantes) {
                relatorio.append("Nome: ").append(estudante.getNome())
                        .append("\nIdade: ").append(estudante.getIdade())
                        .append("\nMatrícula: ").append(estudante.getMatricula())
                        .append("\nCursos: ");

                List<String> cursosEstudante = Application.cursos.stream()
                        .filter(curso -> curso.getEstudantesMatriculados().contains(estudante))
                        .map(Curso::getNomeCurso)
                        .toList();

                if (cursosEstudante.isEmpty()) {
                    relatorio.append("Nenhum");
                } else {
                    relatorio.append(String.join(", ", cursosEstudante));
                }
                relatorio.append("\n\n");
            }
        }

        JOptionPane.showMessageDialog(null, relatorio.toString(), "Relatório de Estudantes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exibirRelatorioProfessores() {
        StringBuilder relatorio = new StringBuilder("Relatório de Professores:\n");

        if (Application.professores.isEmpty()) {
            relatorio.append("Nenhum professor cadastrado.\n");
        } else {
            for (Professor professor : Application.professores) {
                relatorio.append("Nome: ").append(professor.getNome())
                        .append("\nIdade: ").append(professor.getIdade())
                        .append("\nEspecialidade: ").append(professor.getEspecialidade())
                        .append("\nCursos: ");

                List<String> cursosProfessor = Application.cursos.stream()
                        .filter(curso -> curso.getProfessor() != null &&
                                curso.getProfessor().equals(professor))
                        .map(Curso::getNomeCurso)
                        .toList();

                if (cursosProfessor.isEmpty()) {
                    relatorio.append("Nenhum");
                } else {
                    relatorio.append(String.join(", ", cursosProfessor));
                }
                relatorio.append("\n\n");
            }
        }

        JOptionPane.showMessageDialog(null, relatorio.toString(), "Relatório de Professores", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exibirRelatorioCurso() {
        if (Application.cursos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum curso cadastrado.", "Relatório de Curso", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cursoNome = JOptionPane.showInputDialog("Digite o nome do curso para ver os detalhes:");
        Curso cursoSelecionado = Application.cursos.stream()
                .filter(curso -> curso.getNomeCurso().equalsIgnoreCase(cursoNome))
                .findFirst()
                .orElse(null);

        if (cursoSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Curso não encontrado.", "Relatório de Curso", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder relatorio = new StringBuilder("Detalhes do Curso:\n");
        relatorio.append("Nome do Curso: ").append(cursoSelecionado.getNomeCurso()).append("\n");
        relatorio.append("Carga Horária: ").append(cursoSelecionado.getCargaHoraria()).append("\n");

        if (cursoSelecionado.getProfessor() != null) {
            relatorio.append("Professor Responsável: ").append(cursoSelecionado.getProfessor().getNome()).append("\n");
        } else {
            relatorio.append("Professor Responsável: Nenhum professor associado\n");
        }

        List<Estudante> estudantesMatriculados = cursoSelecionado.getEstudantesMatriculados();

        if (estudantesMatriculados.isEmpty()) {
            relatorio.append("Estudantes Matriculados: Nenhum estudante matriculado\n");
        } else {
            relatorio.append("Estudantes Matriculados:\n");
            for (Estudante estudante : estudantesMatriculados) {
                relatorio.append("- ").append(estudante.getNome()).append(" (Matrícula: ").append(estudante.getMatricula()).append(")\n");
            }
        }

        JOptionPane.showMessageDialog(null, relatorio.toString(), "Relatório de Curso", JOptionPane.INFORMATION_MESSAGE);
    }
}
