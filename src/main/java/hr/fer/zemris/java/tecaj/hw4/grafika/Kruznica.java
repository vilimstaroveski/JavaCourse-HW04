package hr.fer.zemris.java.tecaj.hw4.grafika;

/**
 * Razred koji predstavlja kružnicu. Kružnica je određena koordinatama točke centra i radijusom.
 * 
 * @author Vilim Staroveški
 *
 */
public class Kruznica extends Elipsa {

	/**
	 * Konstruktor koji prima 3 argumenta, koordinate točke centra i radijus. 
	 * 
	 * @param centarX - {@code int} vrijednost koja predstavlja x koordinatu centra kružnice.
	 * @param centarY - {@code int} vrijednost koja predstavlja x koordinatu centra kružnice.
	 * @param radijus - {@code int} vrijednost koja predstavlja radijus kružnice. Nesmije biti negativan!
	 */
	public Kruznica(int centarX, int centarY, int radijus) {
		
		super(centarX, centarY, radijus, radijus);
	}

	/**
	 * {@link StvarateljLika} koji proizvodi kružnice.
	 */
	public static final StvarateljLika STVARATELJ = new KruznicaStvaratelj();
	
	/**
	 * Privatni statički razred koji služi kao tvornica za proizvodnju kružnice. Implementacija {@link StvarateljLika}.
	 * 
	 * @author Vilim Staroveški
	 *
	 */
	private static class KruznicaStvaratelj implements StvarateljLika {

		@Override
		public String nazivLika() {

			return "KRUG";
		}

		@Override
		public GeometrijskiLik stvoriIzStringa(String parametri) {
			
			int[] argumenti = null;
			
			try {
				
				argumenti = Potrepštine.izdvojiArgumente(parametri, 3);
			
			} catch(NumberFormatException e) {
				
				throw new IllegalArgumentException();
			}
			
			return new Kruznica(argumenti[0], argumenti[1], argumenti[2]);
		}
		
		
	}
}
