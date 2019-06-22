package hr.fer.zemris.java.tecaj.hw4.grafika;

import hr.fer.zemris.java.tecaj_3.prikaz.Slika;

/**
 * Razred koji predstavlja pravokutnik. Pravokutnik je geometrijski lik definiran sa koordinatama gornjeg lijevog vrha, sirinom i visinom.
 * 
 * @author Vilim Staroveški
 *
 */
public class Pravokutnik extends GeometrijskiLik {

	/**
	 * X koordinata gornjeg lijevog vrha.
	 */
	private final int pocetakX;
	
	/**
	 * Y koordinata gornjeg lijevog vrha.
	 */
	private final int pocetakY;
	
	/**
	 * Visina pravokutnika.
	 */
	private final int visina;
	
	/**
	 * Sirina pravokutnika.
	 */
	private final int sirina;
	
	/**
	 * Konstruktor koji prima 4 parametra: koordinate gornjeg lijevog vrha, sirinu i visinu. Sirina i visina moraju biti pozitivni brojevi.
	 * 
	 * @param pocetakX - {@code int} vrijednost koja predstavlja x koordinatu gornjeg lijevog vrha.
	 * @param pocetakY - {@code int} vrijednost koja predstavlja y koordinatu gornjeg lijevog vrha.
	 * @param visina - {@code int} vrijednost koja predstavlja visinu pravokutnika. Mora biti pozitivan broj.
	 * @param sirina - {@code int} vrijednost koja predstavlja sirinu pravokutnika. Mora biti pozitivan broj.
	 * @throws IllegalArgumentException ako je sirina ili visina zadana kao negativan broj.
	 */
	public Pravokutnik(int pocetakX, int pocetakY, int visina, int sirina) {
		
		super();
		
		if (sirina < 0 || visina < 0) {
			
			throw new IllegalArgumentException("Stranice pravokutnika moraju biti pozitivni brojevi!");
		}
		
		this.pocetakX = pocetakX;
		this.pocetakY = pocetakY;
		this.visina = visina;
		this.sirina = sirina;
	}

	@Override
	public boolean sadrziTocku(int x, int y) {
		
		boolean pozicijaX = (x >= pocetakX) && (x <= pocetakX+sirina);
		boolean pozicijaY = (y >= pocetakY) && (y <= pocetakY+visina);
		
		if (pozicijaX && pozicijaY) {
			
			return true;
		}
		
		return false;
	}

	@Override
	public void popuniLik(Slika slika) {

		int krajnjiX = pocetakX + sirina;
		int krajnjiY = pocetakY + visina;
		
		for(int i = pocetakX; i <= krajnjiX; i++) {
			
			for(int j = pocetakY; j <= krajnjiY; j++) {
				
				if(sadrziTocku(i, j) && Potrepštine.tockaJeNaSlici(slika, i, j)) {
					
						slika.upaliTocku(i, j);
				}
			}
		}
	}
	
	/**
	 * {@link StvarateljLika} koji proizvodi pravokutnik.
	 */
	public static final StvarateljLika STVARATELJ = new PravokutnikStvaratelj();
	
	/**
	 * Privatni statički razred koji služi kao tvornica za proizvodnju pravokutnika. Implementacija {@link StvarateljLika}.
	 * 
	 * @author Vilim Staroveški
	 *
	 */
	private static class PravokutnikStvaratelj implements StvarateljLika {

		@Override
		public String nazivLika() {

			return "PRAVOKUTNIK";
		}

		@Override
		public GeometrijskiLik stvoriIzStringa(String parametri) {
			
			int[] argumenti = null;
			
			try {
				
				argumenti = Potrepštine.izdvojiArgumente(parametri, 4);
			
			} catch(NumberFormatException e) {
				
				throw new IllegalArgumentException();
			}
			
			return new Pravokutnik(argumenti[0], argumenti[1], argumenti[2], argumenti[3]);
		}
	}

}
