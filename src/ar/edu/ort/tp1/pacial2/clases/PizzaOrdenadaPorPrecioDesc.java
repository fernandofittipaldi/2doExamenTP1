package ar.edu.ort.tp1.pacial2.clases;

import ar.edu.ort.tp1.tdas.implementaciones.ListaOrdenadaNodos;

public class PizzaOrdenadaPorPrecioDesc extends ListaOrdenadaNodos<Float, Pizza>{

	@Override
	public int compare(Pizza dato1, Pizza dato2) {
		
		return - Float.valueOf(dato1.getPrecioDeVenta()).compareTo(dato2.getPrecioDeVenta());
	}

	@Override
	public int compareByKey(Float clave, Pizza elemento) {
		return - clave.compareTo(elemento.getPrecioDeVenta());
	}

}
