package br.edu.fatecpg.fatec.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_completo")
    private String nome;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String matricula;
    private String email;


    public Aluno() {
    }

    public Aluno( String nome, String cpf, String matricula, String email ) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
