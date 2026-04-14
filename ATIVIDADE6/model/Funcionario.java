package model;

public class Funcionario {
    private String nome;
    private String departamento;
    private double salario;
    private int anosDeServico;

    public Funcionario(String nome, String departamento, double salario, int anosDeServico) {
        this.nome = nome;
        this.departamento = departamento;
        this.salario = salario;
        this.anosDeServico = anosDeServico;
    }

    public String getNome() {
        return nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public double getSalario() {
        return salario;
    }

    public int getAnosDeServico() {
        return anosDeServico;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setAnosDeServico(int anosDeServico) {
        this.anosDeServico = anosDeServico;
    }

    @Override
    public String toString() {
        return "model.Funcionario{" +
                "nome='" + nome + '\'' +
                ", departamento='" + departamento + '\'' +
                ", salario=" + salario +
                ", anosDeServico=" + anosDeServico +
                '}';
    }
}
