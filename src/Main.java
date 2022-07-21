import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	
	static Scanner sc=new Scanner(System.in);
	static List<PC> storeStock=new ArrayList<PC>();
	static List<String> storeList=new ArrayList<String>();
	static String storeName;
	
	public static void main(String[] args) {

		boolean exitMenu=false;
		
		do{
			exitMenu=showMenu(exitMenu);
			
		}while(!exitMenu);
	}
	
	public static boolean showMenu(boolean exitMenu) {
		
		String brand;
		float price;
		
		System.out.println("Programa gestión tiendas orddenadores.\n \n"
				+ "1.- Mostrar por pantalla lista tiendas.\r\n"
				+ "\r\n"
				+ "2.- Mostrar lista ordenadores de una determinada tienda.\r\n"
				+ "\r\n"
				+ "3.- Mostrar primer ordenador de una tienda de una determinada marca.\r\n"
				+ "\r\n"
				+ "4.- Mostrar número de ordenadores de una tienda.\r\n"
				+ "\r\n"
				+ "5.- Mostrar número de ordenadores de una tienda de una determinada marca.\r\n"
				+ "\r\n"
				+ "6.- Modificar precio del primer ordenador de una tienda de una determinada marca.\r\n"
				+ "\r\n"
				+ "7.- Guardar lista en CSV.\r\n"
				+ "\r\n"
				+ "0.- Salir.");
		
		String indexSwitch=requireString("\r\nQue opción del menú quieres escoger?");
		
		switch(indexSwitch) {
		
		case "0":

			System.out.println("Gracias por utilitzar la aplicación. Adios!!");
			exitMenu=true;

			break;
			
		case "1":
			
			getStoreList();

			//storeList.forEach(System.out::println);
			
			break;
			
		case "2":
			
			getStoreStock();
			System.out.println(storeStock);

			break;
			
		case "3":
			
			getStoreStock();
			brand=requireString("\r\nQue marca quieres ver?");
			
			Optional<PC> pc =storeStock.stream().filter(p->p.getBrand().equals(brand)).findFirst();

			System.out.println(pc);
			
			break;
			
		case "4":

			getStoreStock();
			System.out.println(storeStock.stream().count());

			break;
			
		case "5":
			
			getStoreStock();
			brand=requireString("\r\nQue marca quieres ver?");
			List<PC> pcListBrand=storeStock.stream().filter(p->p.getBrand().equals(brand))
					.collect(Collectors.toList());
			
			System.out.println(pcListBrand);

			break;
				
		case "6":
			
			getStoreStock();
			brand=requireString("\r\nQue marca quieres ver?");
			price=requireFloatNumber("Nuevo precio a introducir.");
			
		    storeStock.stream()
		    		.mapToDouble(t->t.getPrice()).findFirst();

			break;
			
		case "7":
			
			String storeName=requireString("Amb quina tenda vols treballar?");
			updateCSV(storeName+"Stock.csv");
			break;

			
		default:
			
			System.out.println("Tienes que escoger un número de la lista.");
		}
		
		return exitMenu;
	}
	
	public static void getStoreList() {
	
		try {
			loadCSVFileStore("storeList.csv");
		} catch (IOException e) {

		e.printStackTrace();
		System.out.println("No se ha encontrado el archivo con nombre "+storeName);
		}
	}
	
	public static void loadCSVFileStore(String fileName) throws IOException{
		
		FileReader fr = new FileReader(new File(fileName));
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        String [] parts;
        String store;

        while (linea != null) {
            
            parts = linea.split(", ");
            
            store = parts[0].trim();
            
            storeList.add(store);

            linea = br.readLine();
 
        }
        System.out.println(storeList);
        
        br.close();
        fr.close();
	}
	
	public static void getStoreStock() {
		
		String storeName=requireString("Amb quina tenda vols treballar?");
		
		try {
			loadCSVFile(storeName+"Stock.csv");
		} catch (IOException e) {

			System.out.println("No se ha encontrado el archivo con nombre "+storeName);
			getStoreStock();
		}
	}
	
	public static void loadCSVFile (String fileName) throws IOException{
		
		FileReader fr = new FileReader(new File(fileName));
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        String [] parts;
        PC pc;
		String brand;
		String model;
		float price;

        while (line != null) {
            
          parts=line.split(";");
       	   
          brand= parts[0].trim();
          model= parts[1].trim();
          price= Float.valueOf(parts[2].trim());
              
          pc=new PC(brand, model, price);

          storeStock.add(pc);
          
          line = br.readLine();
        }
        
        br.close();
        fr.close();
	}   
	
	public static void updateCSV(String fileStock) {
		
	   try {
	        	
	     BufferedWriter br=new BufferedWriter(new FileWriter(fileStock, false));

	        for (int i=0; i<storeStock.size(); i++) {
	            	
	                br.write(storeStock.get(i).toString() + "\r\n");
	            }

	            br.close();
	        }
	        catch (IOException e) {
	        	
	            System.out.println("IOException!!!!");
	        }	
	}
	
	public static String requireString(String message) {
		
		String string="";
		boolean loopOut=false;
		
		do {
			
			System.out.println(message);
			try{
				string=sc.nextLine();
				loopOut=true;
			}catch (Exception e){
				
				System.out.println("No es correcte.");
			}
			
		}while(!loopOut);
		
		return string;
	}
	
	public static float requireFloatNumber(String message) {
		
		Scanner sc=new Scanner(System.in);
		float num;
		
		System.out.println(message);
		num=sc.nextFloat();
		
		return num;
	}
}
