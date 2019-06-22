package hr.fer.zemris.java.tecaj.hw4.grafika;

/**
 * Sučelje za razred koji će stvarati {@link GeometrijskiLik}.
 */
public interface StvarateljLika {

	/**
	 * Vrača {@code String} naziv lika.
	 * 
	 * @return {@code String} vrijednost koja predstavlja naziv lika.
	 */
	String nazivLika();
	
	/**
	 * Metoda koja stvara {@link GeometrijskiLik} iz zadane {@code String} vrijednost parametri.
	 * 
	 * @param parametri - {@code String} vrijednost sa podacima za izradu {@link GeometrijskiLik}
	 * @return {@link GeometrijskiLik} 
	 */
	GeometrijskiLik stvoriIzStringa(String parametri);
}
