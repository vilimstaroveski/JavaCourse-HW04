package hr.fer.zemris.java.tecaj.hw4.grafika;

import hr.fer.zemris.java.tecaj_3.prikaz.Slika;

/**
 * Razred predstavlja geometrijski lik.
 * 
 * @author Vilim Staroveški
 *
 */
public abstract class GeometrijskiLik {
	
	/**
	 * Vrača {@code true} ako {@link GeometrijskiLik} sadrzi točku sa koordinatama x i y. 
	 *  
	 * @param x - {@code int} vrijednost koja predstavlja x koordinatu točke koju ispitujemo.
	 * @param y - {@code int} vrijednost koja predstavlja y koordinatu točke koju ispitujemo.
	 * @return {@code true} ako {@link GeometrijskiLik} sadrzi točku sa koordinatama x i y. Inače {@code false}.
	 */
	public abstract boolean sadrziTocku(int x, int y);
	
	/**
	 * Metoda koja crta {@link GeometrijskiLik} na predani argument {@link Slika}. Iscrtava se pomoću paljenja pixela koji se nalaze unutar lika.
	 * Ako je lik prevelik da se iscrta na predanoj slici, iscrtati će se samo dio lika koji je vidljiv na slici.
	 *  
	 * @param slika - {@link Slika} na koju će {@link GeometrijskiLik} biti nacrtan.
	 */
	public abstract void popuniLik(Slika slika);
}
