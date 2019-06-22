package hr.fer.zemris.java.tecaj.hw4.grafika;

/**
 * Razred koji predstavlja kvadrat. Kvadrat je geometrijski lik zadan sa koordinatama gornjeg lijevog vrha i duljinom stranice.
 * 
 * @author Vilim Staroveški
 *
 */
public class Kvadrat extends Pravokutnik {

	/**
	 * Konstruktor koji prima 3 argumenta: koordinate gornjeg lijevog vrha i duljinu stranice. Duljina stranice mora biti pozitivan broj.
	 * @param pocetakX - {@code int} vrijednost koja predstavlja x koordinatu gornjeg lijevog vrha.
	 * @param pocetakY - {@code int} vrijednost koja predstavlja y koordinatu gornjeg lijevog vrha.
	 * @param velicinaStr - {@code int} vrijednost koja predstavlja duljinu stranice kvadrata. Mora biti pozitivan broj!
	 */
	public Kvadrat(int pocetakX, int pocetakY, int velicinaStr) {
		
		super(pocetakX, pocetakY, velicinaStr, velicinaStr);
		
	}
	
	/**
	 * {@link StvarateljLika} koji proizvodi kvadrat.
	 */
	public static final StvarateljLika STVARATELJ = new KvadratStvaratelj();
	
	/**
	 * Privatni statički razred koji služi kao tvornica za proizvodnju kvadrata. Implementacija {@link StvarateljLika}.
	 * 
	 * @author Vilim Staroveški
	 *
	 */
	private static class KvadratStvaratelj implements StvarateljLika {

		@Override
		public String nazivLika() {

			return "KVADRAT";
		}

		@Override
		public GeometrijskiLik stvoriIzStringa(String parametri) {
			
			int[] argumenti = null;
			
			try {
				
				argumenti = Potrepštine.izdvojiArgumente(parametri, 3);
			
			} catch(NumberFormatException e) {
				System.out.println(argumenti.toString());
				
				throw new IllegalArgumentException();
			}
			
			return new Kvadrat(argumenti[0], argumenti[1], argumenti[2]);
		}
		
		
	}
}
