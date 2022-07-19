import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    void cria(InputStream inputStream, String nomeArquivo) throws Exception{
        //leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        // InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_3.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //criar nova imagem em memória com transparência e com tamanho novo
        //queremos trabalhar com largura e altura para chegar ao resuktado final
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();

        //somente a altura será redimensionada, porque queremos uma imagem com um texto
        int novaAltura = altura + 200;
        BufferedImage novaImagem =  new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original pra nova imagem (em memória)
        //passamos de imagem para gráficos, utilizando o getGraphics - deve ser um gráfico 2D
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();

        //a imagem que passamos nesse método é a imagem antiga, porque é ela que será escrita na nova imagem
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);
        
        //escrever uma frase na nova imagem
        graphics.drawString("NOTA: DÓ", 0, novaAltura-100);

        //escrever a nova imagem em um arquivo
        File figurinha = new File("saida/"+nomeArquivo);
        if(figurinha.mkdirs())
            ImageIO.write(novaImagem, "png", figurinha);

    }
}
