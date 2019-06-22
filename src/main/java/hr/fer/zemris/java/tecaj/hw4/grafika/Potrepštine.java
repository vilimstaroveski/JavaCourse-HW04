package hr.fer.zemris.java.tecaj.hw4.grafika;

import hr.fer.zemris.java.tecaj_3.prikaz.Slika;


/**
 * Razred koji služi za poslove odvajanja argumenata u nekom stringu i metodu za provjeru je li točka dio slike na koju se crta. 
 * 
 * @author Vilim Staroveški
 * @version 1.0
 */
public class Potrepštine {
	
	/**
	 * Metoda koja analizira predani {@code String} i iz njega izvlači podatke koji predstavljaju brojeve.
	 * 
	 * @param parametriZaAnalizu - {@code String} iz kojeg se izvlače brojevi.
	 * @param brojArgumenata - broj argumenata koji se očekuje da dobijemo iz predanog {@code String}-a.
	 * @return {@code int[]} kao polje brojeva koji su bili zapisani u predanom {@code String}-u.
	 */
	public static int[] izdvojiArgumente(String parametriZaAnalizu, int brojArgumenata) {
		
		int[] argumenti = new int[brojArgumenata];
		
		String parametri = parametriZaAnalizu.trim();
		
		for(int i = 0; i < brojArgumenata; i++) {
			
			String subString = null;
			
			if (parametri.contains(" ")) {
				
				subString = parametri.substring(0, parametri.indexOf(' ')).trim();
				parametri = parametri.substring(parametri.indexOf(" ")).trim();
			}
			else {
				
				subString = parametri;
			}
			try {
				
				argumenti[i] = Integer.parseInt(subString);
			
			} catch(NumberFormatException e) {
				
				System.err.println("Zapis za stvaranje lika nije valjan! Na mjestu gdje je trebao biti broj zatečen je znak '"+subString+"'");
				System.exit(1);
			}
		}
		return argumenti;
	}
	
	/**
	 * Metoda koja provjerava je li tocka sa koordinatama x i y unutar slike predane kao argument.
	 * 
	 * @param slika {@code Slika} za koju provjeravamo istinitost.
	 * @param x x koordinata točke koju provjeravamo.
	 * @param y y koordinata točke koju provjeravamo.
	 * @return true ako je točka zbilja dio slike, false inače.
	 */
	public static boolean tockaJeNaSlici(Slika slika, int x, int y) {
		
		return (x < slika.getSirina()) && (y < slika.getVisina()) && (x >= 0) && (y >= 0);
	}
	
}
