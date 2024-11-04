package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.main.main;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {

	private ProductoMenu producto;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
        
    	producto = new ProductoMenu("corral", 14000);
    }
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	@Test
    void testGetNombre( )
    {
		assertEquals( "corral", producto.getNombre( ), "El nombre del ingrediente no es el esperado." );
    	
    }

	@Test
    void testPrecio( ) {
		assertEquals(14000, producto.getPrecio(), "El precio no es esperado");
	}
	
	@Test
    void generarTextoFactura( ) {
		boolean hola = false;
		if (producto.generarTextoFactura() != null) {
			hola = true;
		}
		assertEquals(true, hola, "No ha funcionado");
	}
	

}
