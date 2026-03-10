package AtividadeGestãoJDBC.model;

import java.time.LocalDateTime;

public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private String categoria;
    private String status;
    private LocalDateTime dataCriacao;

    public Tarefa(int id, String titulo, String descricao, String categoria, String status, LocalDateTime dataCriacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", status='" + status + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
