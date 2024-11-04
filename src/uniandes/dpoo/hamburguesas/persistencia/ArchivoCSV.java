package uniandes.dpoo.hamburguesas.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivoCSV {
	public static void guardarTextoCSV(ArrayList<String> textos, String nombreArchivo) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));
		for(String texto : textos) {
			bw.write(texto);
			bw.newLine();
		}
		bw.close();
	}
	
	public static ArrayList<String> leerArchivoCSV(String nombreArchivo) throws IOException{
		ArrayList<String> textos = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		String linea;
		while((linea=br.readLine()) != null) {
			textos.add(linea);
		}
		br.close();
		return textos;
	}
}
