package ATIVIDADE3.consumoapi.model;

import com.google.gson.annotations.SerializedName;

public class Socio {

    // Nomes dos campos batem exatamente com as chaves do array "qsa" da BrasilAPI
    @SerializedName("nome_socio")
    private String nomeSocio;

    @SerializedName("cnpj_cpf_do_socio")
    private String cnpjCpfDoSocio;

    @SerializedName("qualificacao_socio")
    private String qualificacaoSocio;

    // Construtor vazio necessário para o Gson
    public Socio() {}

    public String getNomeSocio() {
        return nomeSocio;
    }

    public void setNomeSocio(String nomeSocio) {
        this.nomeSocio = nomeSocio;
    }

    public String getCnpjCpfDoSocio() {
        return cnpjCpfDoSocio;
    }

    public void setCnpjCpfDoSocio(String cnpjCpfDoSocio) {
        this.cnpjCpfDoSocio = cnpjCpfDoSocio;
    }

    public String getQualificacaoSocio() {
        return qualificacaoSocio;
    }

    public void setQualificacaoSocio(String qualificacaoSocio) {
        this.qualificacaoSocio = qualificacaoSocio;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "nomeSocio='"         + nomeSocio         + '\'' +
                ", cnpjCpfDoSocio='"  + cnpjCpfDoSocio   + '\'' +
                ", qualificacaoSocio='" + qualificacaoSocio + '\'' +
                '}';
    }
}
