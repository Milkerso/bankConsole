import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class ZapisDanych
{
	public ZapisDanych()
	{
	}
	public static void zapisDoPliku(ArrayList klienci)
	{
		try{
	  PrintWriter zapis = new PrintWriter("baza_kont.txt");
      for(int i=0;i<klienci.size();i++)
	  {
		  zapis.println(klienci.get(i));
	  }
      zapis.close();
		}
	  catch(FileNotFoundException fnfe){
	  System.err.println("Blad");
	  }
	}
	public static void odczytPliku(ArrayList<String> klienci)
	{
		String nazwaPliku = "baza_kont.txt";
		try{
		BufferedReader br = new BufferedReader(new FileReader(nazwaPliku));
			   String linia = null;
            while ((linia = br.readLine()) != null) {
               klienci.add(linia);
            }
		}
			catch (Exception e) {
            System.err.println("Wystapil blad przy wczytywaniu danych");
            e.printStackTrace();
        }
	}
	
	  
}