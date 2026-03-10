package BuscaCepApi.view;

import BuscaCepApi.model.Endereco;
import BuscaCepApi.service.ConsomeApi;  
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import java.io.IOException;

import static java.lang.System.*;


public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(in);
        Gson gson = new Gson();

        ArrayList<Endereco> listaEnderecos = new ArrayList<>();

        int opcao = 0;

        while (opcao != 4) {

            out.println("\n===== MENU =====");
            out.println("1 - Buscar endereço");
            out.println("2 - Excluir endereço");
            out.println("3 - Histórico de buscas");
            out.println("4 - Sair");
            out.print("Escolha: ");

            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {

                case 1:

                    out.print("Digite o CEP: ");
                    String cep = entrada.nextLine();

                    boolean existe = false;

                    for (Endereco e : listaEnderecos) {
                        if (e.getCep().equals(cep)) {
                            out.println("Endereço já está na lista:");
                            out.println(e);
                            existe = true;
                            break;
                        }
                    }

                    if (!existe) {
                        try {

                            String retorno = ConsomeApi.buscaCep(cep);

                            if (retorno == null) {
                                out.println("CEP inválido ou não encontrado!");
                                break;
                            }

                            Endereco end = gson.fromJson(retorno, Endereco.class);

                            listaEnderecos.add(end);

                            out.println("Endereço encontrado:");
                            out.println(end);

                        } catch (IOException | InterruptedException e) {
                            out.println("Erro ao buscar CEP. Verifique sua conexão.");
                        }
                    }

                    break;

                case 2:

                    out.print("Digite o CEP para excluir: ");
                    String cepExcluir = entrada.nextLine();

                    listaEnderecos.removeIf(e -> e.getCep().equals(cepExcluir));

                    out.println("Se existia, foi removido.");

                    break;

                case 3:

                    if (listaEnderecos.isEmpty()) {
                        out.println("Nenhum endereço no histórico.");
                    } else {
                        out.println("\nHistórico de buscas:");
                        for (Endereco e : listaEnderecos) {
                            out.println(e);
                        }
                    }

                    break;

                case 4:
                    out.println("Encerrando sistema...");
                    break;

                default:
                    out.println("Opção inválida. Tente novamente.");

            }
        }
    }
}
