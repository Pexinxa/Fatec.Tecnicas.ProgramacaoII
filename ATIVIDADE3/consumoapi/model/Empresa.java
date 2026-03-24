package ATIVIDADE3.consumoapi.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private String cnpj;

    @SerializedName("razao_social")
    private String razaoSocial;

    @SerializedName("nome_fantasia")
    private String nomeFantasia;

    private String logradouro;

    // O Gson preenche esta lista automaticamente com o array "qsa" do JSON
    private List<Socio> qsa = new ArrayList<>();

    // Construtor vazio necessário para o Gson instanciar o objeto
    public Empresa() {}

    public String getCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public List<Socio> getQsa() {
        return qsa;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "cnpj='"         + cnpj        + '\'' +
                ", razaoSocial='"  + razaoSocial  + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", logradouro='"   + logradouro   + '\'' +
                ", socios="        + qsa          +
                '}';
    }
}
