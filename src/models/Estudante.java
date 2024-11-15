package models;

public class Estudante extends Pessoa {
    protected String matricula;

    public Estudante(int idade, String nome, String matricula) {
        super(idade, nome);
        this.matricula = matricula;
    }

    @Override
    public void exibirDados() {
        System.out.println("Informações do Estudante:");
        System.out.println("- Nome: " + nome);
        System.out.println("- Idade: " + idade);
        System.out.println("- Matricula: " + matricula);
    }
}
