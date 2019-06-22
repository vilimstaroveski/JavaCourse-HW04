package hr.fer.zemris.java.tecaj.hw4.grafika.demo;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import hr.fer.zemris.java.tecaj.hw4.collections.SimpleHashtable;
import hr.fer.zemris.java.tecaj.hw4.grafika.Elipsa;
import hr.fer.zemris.java.tecaj.hw4.grafika.GeometrijskiLik;
import hr.fer.zemris.java.tecaj.hw4.grafika.Kruznica;
import hr.fer.zemris.java.tecaj.hw4.grafika.Kvadrat;
import hr.fer.zemris.java.tecaj.hw4.grafika.Linija;
import hr.fer.zemris.java.tecaj.hw4.grafika.Pravokutnik;
import hr.fer.zemris.java.tecaj.hw4.grafika.StvarateljLika;
import hr.fer.zemris.java.tecaj_3.prikaz.PrikaznikSlike;
import hr.fer.zemris.java.tecaj_3.prikaz.Slika;

/**
 * Razred koji iscrtava geometrijske likove iz zadane datoteke sa ispravnim parametrima za izradu likova. Razred ima main metodu koja 
 * prima 3 argumenta: put do datoteke sa definiranim likovima, sirina slike i visina slike na koju će se iscrtati likovi.
 *  
 * @author Vilim Staroveški
 *
 */
public class Crtalo {
	
	/**
	 * Poziva se pri pokretanju programa. Koristi argumente zadane u komandnoj liniji.
	 * 
	 * @param args - argumenti iz komandne linije.
	 */
	public static void main(String[] args) {
		
		if(args.length != 3) {
			
			System.err.println("Nije unešen točan broj argumentata za program. \n"
					+ "Program radi sa 3 arrgumenta: \n"
					+ "put do datoteke sa definicijama geometrijskih likova, sirinom i visinom slike na koju će se iscrtati.");
			System.exit(1);
		}
		
		SimpleHashtable stvaratelji = null;
		try {
			
			stvaratelji = podesi(Linija.class, Pravokutnik.class, Kruznica.class, Kvadrat.class, Elipsa.class);
		
		}catch (RuntimeException e) {
			
			System.out.println(e.getMessage()+" Slika neće biti iscrtana.");
			System.exit(1);
		}
		
		String[] definicije = null;
		try {
			
			definicije = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8).toArray(new String[0]);
		
		} catch (IOException e1) {
			
			System.err.println("Greška kod učitavanja datoteke: "+args[0]);
		}
		
		GeometrijskiLik[] likovi = new GeometrijskiLik[definicije.length];
		int index = 0;
		
		for(String def : definicije) {

			def = def.trim();
			
			String lik = def.substring(0, def.indexOf(" ")).trim();
			
			String parametri = def.substring(def.indexOf(" ")).trim();
			
			StvarateljLika stvaratelj = null;
			try {
				
				stvaratelj = (StvarateljLika)stvaratelji.get(lik);
				
			} catch(Exception e) {
				
				System.err.println("Greška u liniji: " +(index+1)+". Lik: "+lik+" nije definiran! Slika neće biti iscrtana.");
				System.exit(1);
			}
			
			try {
				
				likovi[index] = stvaratelj.stvoriIzStringa(parametri);
				
			} catch(Exception e) {

				System.err.println("Greška u liniji: " +(index+1)+". Parametri su krivo zadani! Slika neće biti iscrtana.");
				System.exit(1);
			}
			
			index++;
		}
		
		int sirina = 0;
		int visina = 0;
		try {
			
			sirina = Integer.parseInt(args[1]);
			visina = Integer.parseInt(args[2]);
			
		} catch(NumberFormatException e) {
			
			System.out.println("Krivo zadani argumenti komandne linije! Slika neće biti iscrtana.");
			System.exit(1);
		}
		
		Slika slika = new Slika(sirina, visina);
		
		for (GeometrijskiLik lik : likovi) {

			lik.popuniLik(slika);
		}
		
		PrikaznikSlike.prikaziSliku(slika);
	}
	
	/**
	 * Metoda koja postavlja zadane geometrijske likove u {@link SimpleHashtable}.
	 * 
	 * @param razredi - {@link GeometrijskiLik}-ovi koje ćemo postaviti u tablicu.
	 * @return ispunjena {@link SimpleHashtable}.
	 */
	private static SimpleHashtable podesi(Class<?> ... razredi) {
		
		SimpleHashtable stvaratelji = new SimpleHashtable();
		for(Class<?> razred : razredi) {
			
			try {
			
				Field field = razred.getDeclaredField("STVARATELJ");
				StvarateljLika stvaratelj = (StvarateljLika)field.get(null);
				stvaratelji.put(stvaratelj.nazivLika(), stvaratelj);
				
			} catch(Exception ex) {
				
				throw new RuntimeException("Nije moguće doći do stvaratelja za razred "+razred.getName()+".", ex);
			}
			
		}
		
		return stvaratelji;
	}
}
