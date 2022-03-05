package ar.edu.ort.tp1.pacial2.clases;

import ar.edu.ort.tp1.tdas.implementaciones.PilaNodos;
import ar.edu.ort.tp1.tdas.interfaces.Pila;

public class Pizzeria {

	private static final String MSG_PIZZA_TOPPINGS_NULO = "No se pudo fabricar Pizza o Topping nulo.";
	private static final String MSG_TOPPINGS = "Error de parámetros incorporando toppings";
	private static final String MSG_TOTALES = "La venta total fue: $%8.2f\n";
	private static final String MSG_CANTIDADES = "Se han fabricado: %d Tradicionales, %d Especiales y %d Rectangulares\n";

	private String nombre;
	private int[][] toppings;
	private Pila<String> errores;
	private float importeTotal;
//	private int especial;
//	private int rectangular;
//	private int tradicional;
	private PizzaOrdenadaPorPrecioDesc pizzaOrdenadaPorPrecioDesc;
	private int[] cantPizzas; 

	public Pizzeria(String nombre) {
		setNombre(nombre);
		this.toppings = new int[TipoPizza.values().length][Topping.values().length];
		this.errores = new PilaNodos<>();
		this.importeTotal = 0;
//		this.especial = 0;
//		this.rectangular = 0;
//		this.tradicional = 0;
		this.pizzaOrdenadaPorPrecioDesc = new PizzaOrdenadaPorPrecioDesc();
		this.cantPizzas = new int[TipoPizza.values().length];
	}

	private void setNombre(String nombre) {
		if (nombre == null || nombre.isBlank()) {
			throw new RuntimeException("Nombre inválido");
		}
		this.nombre = nombre;
	}

	public void incorporarTopping(TipoPizza tipoPizza, Topping topping, int cantidad) {
		if (tipoPizza == null || topping == null || cantidad <= 0) {
			throw new RuntimeException(MSG_TOPPINGS);
		}

		int tipo = tipoPizza.ordinal();
		int topp = topping.ordinal();

		toppings[tipo][topp] += cantidad;

	}

	public void ingresarPedido(Pizza p, Topping topping) {
		if (p == null || topping == null) {
			errores.push(MSG_PIZZA_TOPPINGS_NULO);
			throw new RuntimeException(MSG_PIZZA_TOPPINGS_NULO);
		}
		if (!hayStock(p, topping)) {
			String msg = "No se pudo fabricar " + p.getNombre() + " por falta de topping " + topping.getNombre();
			this.errores.push(msg);
			throw new RuntimeException(msg);
		}
		
		cantPizzas[p.tipo().ordinal()]++;
		
//		if (p instanceof Especial) {
//			especial++;
//		} else if (p instanceof Rectangular) {
//			rectangular++;
//		} else if (p instanceof Tradicional) {
//			tradicional++;
//		}

		toppings[p.tipo().ordinal()][topping.ordinal()]--;
		importeTotal += p.getPrecioDeVenta();
		pizzaOrdenadaPorPrecioDesc.add(p);

	}

	private boolean hayStock(Pizza p, Topping topping) {
		return toppings[p.tipo().ordinal()][topping.ordinal()] > 0;
	}

	public void mostrar() {
		System.out.println("Pizzería: " + nombre);
		System.out.printf(MSG_CANTIDADES, cantPizzas[TipoPizza.TRADICIONAL.ordinal()], 
											cantPizzas[TipoPizza.ESPECIAL.ordinal()], 
											cantPizzas[TipoPizza.RECTANGULAR.ordinal()]);
		System.out.printf(MSG_TOTALES, importeTotal);
		System.out.println("--------------------------------------");
		System.out.println("Pizzas fabricadas por precio descendente: ");
		listarPizzasPorPrecioDescendente();
		System.out.println("--------------------------------------");
		System.out.println("Pedidos con error: ");
		mostrarErrores();
		System.out.println("--------------------------------------");

	}

	private void mostrarErrores() {
		Pila<String> aux = new PilaNodos<>();

		while (!errores.isEmpty()) {
			String s = errores.pop();
			System.out.println(s);
			aux.push(s);
		}

		while (!aux.isEmpty()) {
			errores.push(aux.pop());
		}

	}

	private void listarPizzasPorPrecioDescendente() {
		for (Pizza pizza : pizzaOrdenadaPorPrecioDesc) {
			pizza.mostrar();
		}

	}

	public double importeTotalVentasPorNombrePizza(String nombrePizza) {
		float total = 0f;

		for (Pizza pizza : pizzaOrdenadaPorPrecioDesc) {
			if (pizza.getNombre() == nombrePizza) {
				total += pizza.getPrecioDeVenta();
			}
		}
		return total;
	}

}
