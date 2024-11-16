package views;

import controllers.CursoController;
import controllers.EstudanteController;
import controllers.ProfessorController;
import models.Curso;
import models.Estudante;
import models.Professor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Application extends JFrame {
    public static List<Estudante> estudantes = new ArrayList<Estudante>();
    public static List<Professor> professores = new ArrayList<Professor>();
    public static List<Curso> cursos = new ArrayList<Curso>();

    EstudanteController estudanteController = new EstudanteController();
    ProfessorController professorController = new ProfessorController();
    CursoController cursoController = new CursoController();

    public Application() {
        setTitle("Gerenciamento Estudantil");
        setSize(1024, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));

        JButton btnAluno = new JButton("Gerenciar Alunos");
        JButton btnProfessor = new JButton("Gerenciar Professores");
        JButton btnCurso = new JButton("Gerenciar Cursos");
        JButton btnSair = new JButton("Sair do programa");

        btnAluno.addActionListener(e -> estudanteController.menuAluno());
        btnProfessor.addActionListener(e -> professorController.menuProfessor());
        btnCurso.addActionListener(e -> cursoController.menuCurso());
        btnSair.addActionListener(e -> System.exit(0));

        panel.add(btnAluno);
        panel.add(btnProfessor);
        panel.add(btnCurso);
        panel.add(btnSair);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application app = new Application();
            app.setVisible(true);
        });
    }

}
