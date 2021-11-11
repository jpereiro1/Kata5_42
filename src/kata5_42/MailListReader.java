package kata5_42;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MailListReader {
    
    public static ArrayList<String> read(String fileName) throws FileNotFoundException{
        
        ArrayList<String> mailList = new ArrayList<String>();
        
        
        String line;
        try {
            File archivo = new File (fileName);
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            while((line=br.readLine())!=null)
                if(line.contains("@")){
                    mailList.add(line);
                }
       
        } catch (IOException ex) {
            System.out.println("Ha fallado leyendo el archivo");
        }
        
        return mailList;
    }
}
