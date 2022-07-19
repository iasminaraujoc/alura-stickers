import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
*
* @author Robson Fernando Gomes
* http://maguscode.blogspot.com
*
*/
public class ArquivoConfiguracao {

  
    public static Properties getProp() throws IOException
    {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./propriedades/arquivo.properties");
        props.load(file);
        return props;
      
    }
}

