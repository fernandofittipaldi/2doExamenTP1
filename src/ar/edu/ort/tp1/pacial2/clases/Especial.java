package ar.edu.ort.tp1.pacial2.clases;

public class Especial extends Pizza {

	private static final int VALOR_FAINA = 38;
	private int faina;
	private TamanioDePizza tamanio;

	public Especial(String nombre, float costoDeProduccion, float porcentajeGanancia, int faina,
			TamanioDePizza tamanio) {
		super(nombre, costoDeProduccion, porcentajeGanancia);
		setFaina(faina);
		this.tamanio = tamanio;

	}

	private void setFaina(int faina) {
		if (faina < 0) {
			throw new RuntimeException("cantidad no disponible");
		}
		this.faina = faina;
	}

	@Override
	public void mostrar() {
		System.out.println(tipo() + " - " + getNombre() + " - " + "Precio venta: " + getPrecioDeVenta() + " - "
				+ this.faina + " fainas" + " - " + tamanio.getDescripcion());
	}

	@Override
	public float getPrecioDeCosto() {
		return (faina * VALOR_FAINA) + (getCostoDeProduccion() * tamanio.getPorcentaje());
	}

	@Override
	public TipoPizza tipo() {
		return TipoPizza.ESPECIAL;
	}

}
