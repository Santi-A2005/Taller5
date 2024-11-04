package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.main.main;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Combo;

public class ComboTest {

	private ProductoAjustado producto;
	private ProductoMenu producto2;
	private ProductoMenu  producto1;
	private Combo combo;
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
    }
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	@Test
	void testGetNombre( )
    {
		assertEquals( "Combo", combo.getNombre( ), "El nombre del ingrediente no es el esperado." );
    	
    }
	
	@Test
    void testPrecio( ) {
		assertEquals(200000, combo.getPrecio(), "El precio no es esperado");
	}
	
	@Test
    void generarTextoFactura( ) {
		boolean hola = false;
		if (combo.generarTextoFactura() != null) {
			hola = true;
		}
		assertEquals(true, hola, "No ha funcionado");
}
}
