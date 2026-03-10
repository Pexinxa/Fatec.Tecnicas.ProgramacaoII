package AtividadeGestãoJDBC.view;

import AtividadeGestãoJDBC.db.DB;
import AtividadeGestãoJDBC.model.Tarefa;
import java.util.Scanner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n==== GESTÃO DE TAREFAS ====");
            System.out.println("1 - Criar Tarefa");
            System.out.println("2 - Listar Todas");
            System.out.println("3 - Listar por Categoria");
            System.out.println("4 - Marcar como Concluída");
            System.out.println("5 - Excluir Tarefa");
            System.out.println("6 - Sair");

            int op = entrada.nextInt();
            entrada.nextLine();

            switch (op) {
                case 1 -> criarTarefa();
                case 2 -> listarTodas();
                case 3 -> listarPorCategoria();
                case 4 -> concluirTarefa();
                case 5 -> excluirTarefa();
                case 6 -> System.exit(0);
            }
        }
    }

    private static void criarTarefa() {
        try (var conn = DB.connection()) {

            System.out.print("Título: ");
            String titulo = entrada.nextLine();

            System.out.print("Descrição: ");
            String descricao = entrada.nextLine();

            System.out.print("Categoria: ");
            String categoria = entrada.nextLine();

            String sql = "INSERT INTO tb_tarefas(titulo, descricao, categoria) VALUES(?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, titulo);
            stmt.setString(2, descricao);
            stmt.setString(3, categoria);
            stmt.execute();

            System.out.println("Tarefa criada com sucesso!");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void listarTodas() {
        try (var conn = DB.connection()) {

            String sql = "SELECT * FROM tb_tarefas";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            List<Tarefa> tarefas = new ArrayList<>();

            while (rs.next()) {
                tarefas.add(new Tarefa(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getString("categoria"),
                        rs.getString("status"),
                        rs.getTimestamp("data_criacao").toLocalDateTime()
                ));
            }

            tarefas.forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void listarPorCategoria() {
        try (var conn = DB.connection()) {

            System.out.print("Digite a categoria: ");
            String categoria = entrada.nextLine();

            String sql = "SELECT * FROM tb_tarefas WHERE categoria = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("titulo") + " - " +
                                rs.getString("status")
                );
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void concluirTarefa() {
        try (var conn = DB.connection()) {

            System.out.print("ID da tarefa: ");
            int id = entrada.nextInt();

            String sql = "UPDATE tb_tarefas SET status = 'CONCLUIDA' WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Tarefa concluída!");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void excluirTarefa() {
        try (var conn = DB.connection()) {

            System.out.print("ID da tarefa para excluir: ");
            int id = entrada.nextInt();

            String sql = "DELETE FROM tb_tarefas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Tarefa excluída!");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
