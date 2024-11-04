package uniandes.dpoo.hamburguesas.main;


import java.util.ArrayList;
import java.util.Scanner;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import uniandes.dpoo.hamburguesas.persistencia.ArchivoCSV;
import uniandes.dpoo.hamburguesas.persistencia.ArchivoSerializable;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class main {
	public main(String[] args) {
		main1(args);
	}
	
	
	public static void main1(String[] args) {
		HashMap<String, ArrayList<String>> combos = new HashMap<>();
		HashMap<String, Integer> ingredientes = new HashMap<>();
		HashMap<String, Integer> menuC = new HashMap<>();
		ArrayList<Pedido> facturasLista = new ArrayList<>(); 
		boolean correrUsuario = true;
		Scanner scanner = new Scanner(System.in);
		try {
		ArrayList<String> combostxt = ArchivoCSV.leerArchivoCSV("data/combos.txt");
		for (String combo : combostxt){
			String[] informacionCombo = combo.split(";");
			String nombre = informacionCombo[0];
			String porcentaje = informacionCombo[1];
			String hamburguesa = informacionCombo[2];
			String papas = informacionCombo[3];
			String bebida = informacionCombo[4];
			ArrayList<String> infoCombo = new ArrayList<>();
			infoCombo.add(porcentaje);
			infoCombo.add(hamburguesa);
			infoCombo.add(papas);
			infoCombo.add(bebida);
			combos.put(nombre, infoCombo);
		}
		try {
	            facturasLista = (ArrayList<Pedido>) ArchivoSerializable.leerObjetoSerializable("data/facturas.ser");
	            System.out.println("Facturas cargados!");
	        } catch (IOException | ClassNotFoundException e) {
	            System.out.println("No existen facutras. Se crea nueva lista");
	        }
		 System.out.println("Se ha cargado el archivo de combos!");
	 } catch (IOException e) {
         System.out.println("Ocurrio un error leyendo el archivo");
         e.printStackTrace();
     } try {
    	 ArrayList<String> ingredientestxt = ArchivoCSV.leerArchivoCSV("data/ingredientes.txt");
    	 for (String ingredienteLinea: ingredientestxt) {
    		 String[] informacionIngrediente = ingredienteLinea.split(";");
    		 String ingrediente = informacionIngrediente[0];
    		 String precioString = informacionIngrediente[1];
    		 int precioNum;
    		 try {
    			   precioNum = Integer.parseInt(precioString);
    			}
    			catch (NumberFormatException e) {
    			   precioNum = 0;
    			}
    		  ingredientes.put(ingrediente, precioNum);
    	 }
    	 System.out.println("Se ha cargado el archivo de ingredientes!");
     } catch (IOException e) {
    	 System.out.println("Ocurrio un error leyendo el archivo");
         e.printStackTrace();
     } try {
    	 ArrayList<String> menutxt = ArchivoCSV.leerArchivoCSV("data/menu.txt");
    	 for (String menuLinea: menutxt) {
    		 String[] informacionMenu = menuLinea.split(";");
    		 String item = informacionMenu[0];
    		 String precioString = informacionMenu[1];
    		 int precioNum;
    		 try {
    			   precioNum = Integer.parseInt(precioString);
    			}
    			catch (NumberFormatException e) {
    			   precioNum = 0;
    			}
    		  menuC.put(item, precioNum);
    		  
    	 } System.out.println("Se ha cargado el archivo de menu!");
    	 
     } catch (IOException e) {
    	 System.out.println("Ocurrio un error leyendo el archivo");
         e.printStackTrace();
     } 
     Pedido pedido = null; 
     while(correrUsuario) {
    	  System.out.println("Bienvenido");
          System.out.println("1. Ver menu");
          System.out.println("2. Iniciar nuevo pedido");
          System.out.println("3. Agregar elemento a pedido");
          System.out.println("4. Crear pedido y guardar factura");
          System.out.println("5. Consultar información de pedido basado en identificador");
          System.out.println("6. Salir");
          System.out.print("Elija una opción: ");
          int option = scanner.nextInt();
          File facturas = new File("data/facturas.txt");
          scanner.nextLine(); 
          
          switch(option) {
          case 1:
        	  imprimirMenu(combos, ingredientes, menuC);
        	  break;
          case 2:
        	  System.out.println("Digite su nombre: ");
        	  String nombre = scanner.next();
              scanner.nextLine(); 
              System.out.println("Digite su direccion: ");
        	  String direccion = scanner.next();
              scanner.nextLine(); 
        	  pedido = new Pedido(nombre, direccion);
        	  break;
        	  //Nuevo pedido
          case 3:
        	  if (pedido != null) {
        	  System.out.println("Digite un elemento que desea añadir a su orden: ");
        	  String producto = scanner.next();
              scanner.nextLine();
              for (Object pos : ingredientes.keySet()) {
            	  String llave = (String) pos;
            	  if (ingredientes.containsKey(producto)) {
            		  System.out.println("!");
            		  Object precio = ingredientes.get(producto);
            		  ProductoMenu product = new ProductoMenu(producto, (int) precio );
            		  pedido.agregarProducto(product);
            		  break;
            	  }
              }
              for (Object pos : menuC.keySet()) {
      	    	String llave = (String) pos;
      	    	if(menuC.containsKey(producto)) {
      	    		System.out.println("?");
      	    		Object precio = menuC.get(producto);
      	    		ProductoMenu product = new ProductoMenu(producto, (int) precio );
      	    		pedido.agregarProducto(product);
      	    		break;
      	    	}
      	    	else {
      	    		System.out.println("No es opción.");
      	    		break;
      	    	}
              }
        	  } else {
        		  System.out.println("No se ha creado el pedido.");
        		  break;
        	  }
        	  break;
        	  //Agregar elemento a pedido
          case 4: 
        	  if(pedido != null) {
        		  try {
					pedido.guardarFactura(facturas);
					
					try {
						ArchivoSerializable.guardarObjetoSerializable(facturasLista, "data/facturas.ser");
					    System.out.println("Se ha guardado la factura.");
					    System.out.println("Id de su factura; ");
					    System.out.println(pedido.getIdPedido());
					} catch (IOException e) {
					    e.printStackTrace();
					};
					System.out.println("Sirvio!");
					
					
					break;
				} catch (FileNotFoundException e) {
					System.out.println("No sirvio.");
					e.printStackTrace();
				}
        	  }else {
        		  System.out.println("No hay pedido");
        		  break;
        	  }
        	  
        	  //Crear pedido/ guardar factura
          case 5: 
        	  System.out.println("Digite el id: ");
        	  int idBuscar = scanner.nextInt();
              scanner.nextLine();
              
              for (Pedido pedidoActual : facturasLista ) {
            	  if(pedidoActual.getIdPedido() == idBuscar ) {
            		  System.out.println("Esta es la factura que buscaba: ");
            		  String factura = pedidoActual.generarTextoFactura();
            		  System.out.println(factura);
            		  break;
            	  }
              }
              System.out.println("No se ha encontrado la factura.");
        	  break;
        	  //Consultar informacion basado en identificador
          case 6: 
        	  System.out.println("Hasta luego");
              return;

          default:
              System.out.println("No es una opción: intente de nuevo");
          }
      }
	} 
	
	public static void imprimirMenu(HashMap combos, HashMap ingredientes, HashMap menuC) {
		System.out.println("==Menu: ");
		for (Object pos : menuC.keySet()) {
	    	String llave = (String) pos;
	    	Object valor = menuC.get(llave);
	    	System.out.println(llave + ": " + valor + "$");
	}
	    System.out.println("==Productos basicos: ");
		for (Object pos : ingredientes.keySet()) {
	    	String llave = (String) pos;
	    	Object valor = ingredientes.get(llave);
	    	System.out.println(llave + ": " + valor + "$");
	    }
		System.out.println("==Combos: ");
		for(Object pos : combos.keySet()) {
			String llave = (String) pos;
			System.out.println("--" + llave + ":");
			ArrayList<String> combo = (ArrayList<String>) combos.get(llave);
			int j = 0;
			for(int i = 0; i < combo.size(); i++) {
				if (j == 0) {
					System.out.print("Porcentaje: ");
				} else if (j==1) {
					System.out.print("Hamburguesa: ");
				} else if (j==2) {
					System.out.print("Acompañamiento: ");
				} else {
					System.out.print("Bebida: ");
				}
				j = j+1;
				System.out.println(combo.get(i));
			}
			
		}
	}
}


