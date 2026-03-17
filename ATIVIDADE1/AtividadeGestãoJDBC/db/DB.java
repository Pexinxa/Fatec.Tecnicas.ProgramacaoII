package ATIVIDADE1.AtividadeGestãoJDBC.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection connection() throws SQLException{
        try{
            var jdbcUrl = "jdbc:postgresql://localhost:5433/db_fatec";
            var user = "postgres";
            var password = "";
            return DriverManager.getConnection(jdbcUrl,user,password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
