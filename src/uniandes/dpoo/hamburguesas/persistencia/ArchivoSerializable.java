package uniandes.dpoo.hamburguesas.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArchivoSerializable {

	public static void guardarObjetoSerializable(Object objeto, String nombreArchivo) throws IOException {
		ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
		ois.writeObject(objeto);
		ois.close();
	}

	public static Object leerObjetoSerializable(String nombreArchivo) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo));
		Object objeto = ois.readObject();		
		ois.close();
		return objeto;
	}
}
