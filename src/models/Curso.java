package models;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nomeCurso;
    private int cargaHoraria;
    private Professor professor;
    private List<Estudante> estudantesMatriculados;

    public Curso(String nomeCurso, int cargaHoraria) {
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;
        this.estudantesMatriculados = new ArrayList<Estudante>();
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public List<Estudante> getEstudantesMatriculados() {
        return estudantesMatriculados;
    }

    public void adicionarEstudante(Estudante estudante) {
        estudantesMatriculados.add(estudante);
    }

    public void exibirDados() {
        System.out.println("Informações sobre o Curso:");
        System.out.println("- Nome do Curso: " + nomeCurso);
        System.out.println("- Carga Horária: " + cargaHoraria);
        if (professor != null) {
            System.out.println("- Professor Responsável: " + professor.getNome());
        } else {
            System.out.println("- Nenhum professor está responsável deste curso.");
        }
    }
}
