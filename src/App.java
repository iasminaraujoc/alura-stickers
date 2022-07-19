import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
//import java.util.Properties;
// import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        //realizando uma conexão HTTP e buscar os top 250 filmes

        //utilizando arquivo.properties
        // Properties chave = ArquivoConfiguracao.getProp();
        // String url = "https://imdb-api.com/en/API/Top250Movies/";
        // url += chave.getProperty("prop.chaveacesso");

        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //pegar, extrair somente o dados que nos interessam (título, poster e a classificação) - parsear

        var parser = new JsonParser();
        List<Map<String, String>> listaDefilmes = parser.parse(body);
        var geradora = new GeradoraDeFigurinhas();

        int contador = 0;
        for (Map<String, String> filme: listaDefilmes){
            
            
            try{
                String urlImagem = filme.get("image");
                InputStream inputStream = new URL(urlImagem).openStream();

                String titulo = filme.get("title");
                String nomeArquivo = titulo + ".png";

                geradora.cria(inputStream, nomeArquivo);

                System.out.println(filme.get("title"));
                System.out.println();
            } catch (FileNotFoundException fne){
                System.out.println("Imagem não encontrada! ");
                contador++;
            }
            

            // System.out.println();
            // Scanner leitor = new Scanner(System.in);
            // int nota = leitor.nextInt();
            // System.out.println(nota);
            // System.out.println(filme.get("imDbRating"));
            // System.out.println();
        }

        //exibir e manipular os dados da forma como preferirmos
        System.out.println(contador);
    }

}
