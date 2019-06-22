package hr.fer.zemris.java.tecaj.hw4.grafika;


import hr.fer.zemris.java.tecaj_3.prikaz.Slika;

/**
 * Razred koji predstavlja liniju određenu sa dvije točke.
 * 
 * @author Vilim Staroveški
 *
 */
public class Linija extends GeometrijskiLik {
	
	/**
	 * X koordinata od početne točke.
	 */
	private final int startX;
	
	/**
	 * Y koordinata od početne točke.
	 */
	private final int startY;
	
	/**
	 * X koordinata od krajnje točke.
	 */
	private final int endX;
	
	/**
	 * Y koordinata od krajnje točke.
	 */
	private final int endY;
	
	/**
	 * Koeficijent A pravca koji prolazi ovom linijom, iz implicitne formule za pravac: Ax + By + C = 0;
	 */
	private final int implicitniKoefA;
	
	/**
	 * Koeficijent B pravca koji prolazi ovom linijom, iz implicitne formule za pravac: Ax + By + C = 0;
	 */
	private final int implicitniKoefB;
	
	/**
	 * Koeficijent C pravca koji prolazi ovom linijom, iz implicitne formule za pravac: Ax + By + C = 0;
	 */
	private final int implicitniKoefC;
	
	/**
	 * Konstruktor koji prima koordinate početne i krajnje točke.
	 * 
	 * @param startX - X koordinata od početne točke.
	 * @param startY - Y koordinata od početne točke.
	 * @param endX - X koordinata od krajnje točke.
	 * @param endY - X koordinata od krajnje točke.
	 */
	public Linija(int startX, int startY, int endX, int endY) {
		
		super();
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		
		this.implicitniKoefA = startY - endY;
		this.implicitniKoefB = endX - startX;
		this.implicitniKoefC = (startX - endX)*startY + (endY - startY)*startX;
	}

	/**
	 * Metoda koja vrača boolean vrijednost s obzirom je li točka sa koordinatama x i y element ove linije. Koordinate su 
	 * zadane kao argumenti ove metode. Argumenti moraju biti prirodni brojevi.
	 * @param x - x koordinata točke koju provjeravamo.
	 * @param y - y koordinata točke koju provjeravamo.
	 * @return true ako je točka element linije, false ako nije.
	 */
	@Override
	public boolean sadrziTocku(int x, int y) {
		
		if (x > Math.max(startX, endX) || y > Math.max(startY, endY)) {
			
			return false;
		}
		
		double udaljenostTockeOdPravcaLinije = Math.abs(implicitniKoefA*x + implicitniKoefB*y + implicitniKoefC);
		udaljenostTockeOdPravcaLinije /= Math.sqrt(Math.pow(implicitniKoefA, 2) + Math.pow(implicitniKoefB, 2));
		
		if (udaljenostTockeOdPravcaLinije <= 0.5) {
			
			return true;
		}
		
		return false;
	}

	/**
	 * Metoda koja crta ovu liniju na rasterskom prikazu.
	 * @param slika - rasterski prikaz.
	 */
	@Override
	public void popuniLik(Slika slika) {
		
		
		for (int i = Math.min(startX, endX); i <= Math.max(startX, endX); i++) {
		
			for (int j = Math.min(startY, endY); j <= Math.max(startY, endY); j++) {
				
				if(sadrziTocku(i, j) && Potrepštine.tockaJeNaSlici(slika, i, j)) {
					
					slika.upaliTocku(i, j);
				}
			}
		}
		
	}
	
	/**
	 * {@link StvarateljLika} koji služi za stvaranje linija.
	 */
	public static final StvarateljLika STVARATELJ = new LinijaStvaratelj();
	
	/**
	 * Privatni statički razred koji služi kao tvornica za proizvodnju linije. Implementacija {@link StvarateljLika}.
	 * 
	 * @author Vilim Staroveški
	 *
	 */
	private static class LinijaStvaratelj implements StvarateljLika {

		@Override
		public String nazivLika() {

			return "LINIJA";
		}

		@Override
		public GeometrijskiLik stvoriIzStringa(String parametri) {

			int[] argumenti = null;
			
			try {
				
				argumenti = Potrepštine.izdvojiArgumente(parametri, 4);
			
			} catch(NumberFormatException e) {
				
				throw new IllegalArgumentException();
			}
			
			return new Linija(argumenti[0], argumenti[1], argumenti[2], argumenti[3]);
		}
		
	}
}
