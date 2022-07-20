import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        //realizando uma conexão HTTP e buscar os top 250 filmes

        //utilizando arquivo.properties
        // Properties chave = ArquivoConfiguracao.getProp();
        // String url = "https://imdb-api.com/en/API/Top250Movies/";
        // url += chave.getProperty("prop.chaveacesso");

        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-03-24&end_date=2022-07-18";
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoImdb();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        //pegar, extrair somente o dados que nos interessam (título, poster e a classificação) - parsear  
        var geradora = new GeradoraDeFigurinhas();

        for(int i = 0; i<3; i++){
            
            Conteudo conteudo = conteudos.get(i);

            try{
               InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
                String nomeArquivo = conteudo.getTitulo() + ".png";

                geradora.cria(inputStream, nomeArquivo);

                System.out.println(conteudo.getTitulo());
                System.out.println(); 

            } catch (FileNotFoundException fne){
                System.out.println("Imagem não encontrada!");
            }
        }
    }

        //exibir e manipular os dados da forma como preferirmos
        
}



