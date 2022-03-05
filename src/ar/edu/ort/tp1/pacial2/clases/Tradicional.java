package ar.edu.ort.tp1.pacial2.clases;

public class Tradicional extends Pizza {

	private TipoDeMasa tipo;

	public Tradicional(String nombre, float costoDeProduccion, float porcentajeGanancia, TipoDeMasa tipo) {
		super(nombre, costoDeProduccion, porcentajeGanancia);
		this.tipo = tipo;

	}

	@Override
	public void mostrar() {
		System.out.println(tipo() + " - " + getNombre() + " - " + "Precio venta: " + getPrecioDeVenta() + " - "
				+ tipo.getDescripcion());
	}

	@Override
	public float getPrecioDeCosto() {
		return tipo.getPorcentaje() * getCostoDeProduccion();
	}

	@Override
	public TipoPizza tipo() {
		return TipoPizza.TRADICIONAL;
	}

}
