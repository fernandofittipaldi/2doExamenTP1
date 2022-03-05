package ar.edu.ort.tp1.pacial2.clases;

public class Rectangular extends Pizza {

	private static final int VALOR_PORCION = 52;
	private long largo;
	private long ancho;
	private AdicionalQueso adic;

	public Rectangular(String nombre, float costoDeProduccion, float porcentajeGanancia, long largo, long ancho,
			AdicionalQueso adic) {
		super(nombre, costoDeProduccion, porcentajeGanancia);
		this.largo = largo;
		this.ancho = ancho;
		this.adic = adic;
	}

	@Override
	public void mostrar() {
		System.out.println(tipo() + " - " + getNombre() + " - " + "Precio venta: " + getPrecioDeVenta() + " - "
				+ (this.ancho * this.largo) + " porciones" + " - " + adic.getDescripcion());
	}

	@Override
	public float getPrecioDeCosto() {
		return (getCostoDeProduccion() + (largo * ancho) * VALOR_PORCION) * adic.getMultiplicadorQueso();
	}

	@Override
	public TipoPizza tipo() {
		return TipoPizza.RECTANGULAR;
	}

}
