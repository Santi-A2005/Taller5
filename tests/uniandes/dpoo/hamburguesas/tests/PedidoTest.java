package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.main.main;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.persistencia.ArchivoSerializable;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PedidoTest {
	
	private ProductoAjustado producto;
	private ProductoMenu producto2;
	private ProductoMenu  producto1;
	private Combo combo;
	private Pedido pedido;
	private ArrayList<ProductoMenu> lista = new ArrayList<ProductoMenu>();
	@BeforeEach
    void setUp( ) throws Exception
    {
        
    	producto1 = new ProductoMenu("corral", 14000);
    	producto2 = new ProductoMenu("gaseosa", 6000);
    	producto = new ProductoAjustado(producto1);
    	lista.add(producto1);
    	lista.add(producto2);
    	combo = new Combo("Combo", 10.0, lista );
    	pedido = new Pedido("Nombre", "Direccion");
    }
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	

	@Test
	void testGetNombre( )
    {
		assertEquals( "Nombre", pedido.getNombreCliente(), "El nombre del ingrediente no es el esperado." );
    	
    }
	
	@Test
	void testID( )
    {
		
		assertEquals( 0, pedido.getIdPedido(), "El nombre del ingrediente no es el esperado." );
    	
    }
	
	@Test
	void testImprimir( )
    {
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		boolean hola = false;
		if (pedido.generarTextoFactura() != null) {
			hola = true;
		}

		File facturas = new File("data/facturas.txt");
		try {
			pedido.guardarFactura(facturas);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, hola, "No ha funcionado");
    	
    }
}
