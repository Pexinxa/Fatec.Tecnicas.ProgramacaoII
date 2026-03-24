package ATIVIDADE3.consumoapi.dao;

import ATIVIDADE3.consumoapi.db.DB;
import  ATIVIDADE3.consumoapi.model.Socio;
import ATIVIDADE3.consumoapi.model.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SocioDAO {

    private static final String SQL_INSERT =
            "INSERT INTO socios (cnpj_empresa, nome_socio, cnpj_cpf_do_socio, qualificacao_socio) " +
                    "VALUES (?, ?, ?, ?)";

    public void salvarTodos(Empresa empresa) throws SQLException {

        List<Socio> socios = empresa.getQsa();

        if (socios == null || socios.isEmpty()) {
            System.out.println("Nenhum sócio encontrado para cadastrar.");
            return;
        }

        // try-with-resources garante fechamento mesmo em caso de exceção
        try (Connection conn = db.connection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            for (Socio socio : socios) {
                stmt.setString(1, empresa.getCnpj());
                stmt.setString(2, socio.getNomeSocio());
                stmt.setString(3, socio.getCnpjCpfDoSocio());
                stmt.setString(4, socio.getQualificacaoSocio());
                stmt.addBatch();   // adiciona ao lote em vez de executar um por um
            }

            stmt.executeBatch();   // executa todos de uma vez
            System.out.println(socios.size() + " sócio(s) salvos com sucesso.");
        }
    }
}
