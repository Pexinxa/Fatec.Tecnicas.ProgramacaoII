package ATIVIDADE3.consumoapi.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsomeAPI {

    private static final String BASE_URL = "https://brasilapi.com.br/api/cnpj/v1/";

    public static String buscaCnpj(String cnpj) throws IOException, InterruptedException {

        String cnpjLimpo = cnpj.replaceAll("[.\\-/]", "").trim();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + cnpjLimpo))
                .GET()
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.err.println("API retornou status " + response.statusCode() +
                    " para o CNPJ: " + cnpjLimpo);
            return null;
        }

        return response.body();
    }
}
