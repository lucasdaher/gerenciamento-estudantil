package models;

public class Professor extends Pessoa {

    protected String especialidade;

    public Professor(int idade, String nome, String especialidade) {
        super(idade, nome);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public void exibirDados() {
        System.out.println("Informações do Professor:");
        System.out.println("- Nome: " + nome);
        System.out.println("- Idade: " + idade);
        System.out.println("- Especialidade: " + especialidade);
    }
}
