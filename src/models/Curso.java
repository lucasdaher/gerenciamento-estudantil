package models;

public class Curso {
    private String nomeCurso;
    private int cargaHoraria;
    private Professor professor;

    public Curso(String nomeCurso, int cargaHoraria) {
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void exibirDados() {
        System.out.println("Informações sobre o Curso:");
        System.out.println("- Nome do Curso: " + nomeCurso);
        System.out.println("- Carga Horária: " + cargaHoraria);
    }
}
