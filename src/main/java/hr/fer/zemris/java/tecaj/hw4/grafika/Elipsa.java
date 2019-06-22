package hr.fer.zemris.java.tecaj.hw4.grafika;

import hr.fer.zemris.java.tecaj_3.prikaz.Slika;

/**
 * Razred koji predstavlja elipsu. Elipsa je određena koordinatama centra, vodoravnom poluosi i okomitom poluosi.
 * 
 * @author Vilim Staroveški
 *
 */
public class Elipsa extends GeometrijskiLik {

	/**
	 * X koordinata točke centra elipse.
	 */
	private final int centarX;
	
	/**
	 * Y koordinata točke centra elipse.
	 */
	private final int centarY;
	
	/**
	 * Vodoravni radijus elipse.
	 */
	private final int vodoravnaPoluos;
	
	/**
	 * Okomiti radijus elipse.
	 */
	private final int okomitaPoluos;
	
	/**
	 * Kvadrirani vodoravni radijus. Potreban za jednadžbu elipse.
	 */
	private final double vodoravnaPoluosNaDrugu;
	
	/**
	 * Kvadrirani okomiti radijus. Potreban za jednadžbu elipse.
	 */
	private final double okomitaPoluosNaDrugu;
	
	/**
	 * Konstruktor sa 4 parametra. Postavlja privatne varijable elipse na zadane vrijednosti u argumentima metode te dodatno izračunava vrijednosti kvadriranih poluosi.
	 * 
	 * @param centarX - {@code int} vrijednost na koju postavljamo x koordinatu centra.
	 * @param centarY - {@code int} vrijednost na koju postavljamo y koordinatu centra.
	 * @param vodoravnaPoluos - {@code int} vrijednost na koju postavljamo vodoravnu poluos. Nesmije biti negativna.
	 * @param okomitaPoluos - {@code int} vrijednost na koju postavljamo okomitu poluos. Nesmije biti negativna.
	 * @throws IllegalArgumentException ako je jedna od poluosi zadana kao negativan broj.
	 */
	public Elipsa(int centarX, int centarY, int vodoravnaPoluos, int okomitaPoluos) {
		
		super();
		
		if (vodoravnaPoluos < 0 || okomitaPoluos < 0) {
			
			throw new IllegalArgumentException("Elipsa nemože imati negativne poluosi!");
		}
		
		this.centarX = centarX;
		this.centarY = centarY;
		this.vodoravnaPoluos = vodoravnaPoluos;
		this.okomitaPoluos = okomitaPoluos;
		
		this.okomitaPoluosNaDrugu = Math.pow(okomitaPoluos, 2);
		this.vodoravnaPoluosNaDrugu = Math.pow(vodoravnaPoluos, 2);
	}

	@Override
	public boolean sadrziTocku(int x, int y) {
		
		double prviClanUJednadžbi = Math.pow(x - centarX, 2);
		double drugiClanUJednadžbi = Math.pow(y - centarY, 2);
		
		prviClanUJednadžbi /= vodoravnaPoluosNaDrugu;
		drugiClanUJednadžbi /= okomitaPoluosNaDrugu;
		
		if ( (prviClanUJednadžbi + drugiClanUJednadžbi) <= 1) {
			
			return true;
		}
		
		return false;
	}

	@Override
	public void popuniLik(Slika slika) {

		int gornjiKutX = centarX - vodoravnaPoluos;
		int gornjiKutY = centarY - okomitaPoluos;
		int donjiKutX = centarX + vodoravnaPoluos;
		int donjiKutY = centarY + okomitaPoluos;
		
		for (int i = gornjiKutX; i <= donjiKutX; i++) {
			
			for (int j = gornjiKutY; j <= donjiKutY; j++) {
				
				if(sadrziTocku(i, j) && Potrepštine.tockaJeNaSlici(slika, i, j)) {
					
					slika.upaliTocku(i, j);
				}
			}
		}
	}
	
	/**
	 * {@link StvarateljLika} koji proizvodi elipse.
	 */
	public static final StvarateljLika STVARATELJ = new ElipsaStvaratelj();
	
	/**
	 * Privatni statički razred koji služi kao tvornica za proizvodnju elipse. Implementacija {@link StvarateljLika}.
	 * 
	 * @author Vilim Staroveški
	 *
	 */
	private static class ElipsaStvaratelj implements StvarateljLika {

		@Override
		public String nazivLika() {

			return "ELIPSA";
		}

		@Override
		public GeometrijskiLik stvoriIzStringa(String parametri) {
			
			int[] argumenti = null;
			
			try {
				
				argumenti = Potrepštine.izdvojiArgumente(parametri, 4);
			
			} catch(NumberFormatException e) {
				
				throw new IllegalArgumentException();
			}
			
			return new Elipsa(argumenti[0], argumenti[1], argumenti[2], argumenti[3]);
		}
		
		
	}
}
