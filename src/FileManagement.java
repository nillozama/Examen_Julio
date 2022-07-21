
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {
	
	private static String fileStock;

    public static void setFileName(String name) {
    	
    	fileStock=name.toLowerCase()+"Stock.CSV";
    }
    
    public static void fileNotFound(String fileName) {
    	
    	try {
    	      File file = new File(fileName);
    	      if (file.createNewFile()) {
    	        System.out.println("Arxiu creat: " + file.getName());
    	      } 
    	    } catch (IOException e) {
    	      System.out.println("Error.");
    	      e.printStackTrace();
    	    }
    }
    
    public static List<PC> readStock() {

        List<PC> stock=new ArrayList<PC>();
        BufferedReader br=null;
        String line=null;

        try {
        	
            br=new BufferedReader(new InputStreamReader(new FileInputStream(fileStock)));
            line=br.readLine();

            while(line!=null){
            	
                String[] dummy=line.split(";");
                stock.add(new PC(dummy[0], dummy[1], Float.parseFloat(dummy[2])));                
                line=br.readLine();
            }
        }
        catch (FileNotFoundException e) {
        	
            System.out.println("No s'ha trobat el fitxer.");   
        }
        catch (IOException e) {
        	
            System.out.println("IOException!!!!");
        }

        return stock;
    }

    static void writeStock(List<PC> stock) {

        try {
        	
            BufferedWriter br=new BufferedWriter(new FileWriter(fileStock, false));

            for (int i=0; i<stock.size(); i++) {
            	
                br.write(stock.get(i).toString() + "\r\n");
            }

            br.close();
        }
        catch (IOException e) {
        	
            System.out.println("IOException!!!!");
        }
    }
}
