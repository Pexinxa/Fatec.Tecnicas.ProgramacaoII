package ATIVIDADE2.BuscaCepApi.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsomeApi {
    public static String buscaCep(String cep) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200 || response.body().contains("\"erro\"")) {
            return null;
        }

        return response.body();
    }

}
