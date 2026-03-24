package ATIVIDADE3.consumoapi.dao;

import ATIVIDADE3.consumoapi.db.DB;
import ATIVIDADE3.consumoapi.model.Empresa;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpresaDAO {
    private static final String SQL_INSERT =
            "INSERT INTO empresas (cnpj, razao_social, nome_fantasia, logradouro) " +
                    "VALUES (?, ?, ?, ?) " +
                    "ON CONFLICT (cnpj) DO NOTHING";   // evita erro se o CNPJ já existir

    public void salvar(Empresa empresa) throws SQLException {

        // try-with-resources: Connection e PreparedStatement são fechados automaticamente
        try (Connection conn = db.connection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, empresa.getCnpj());
            stmt.setString(2, empresa.getRazaoSocial());
            stmt.setString(3, empresa.getNomeFantasia());
            stmt.setString(4, empresa.getLogradouro());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("Aviso: empresa com CNPJ " + empresa.getCnpj() +
                        " já existe no banco. Nenhuma alteração realizada.");
            }
        }
    }
}
