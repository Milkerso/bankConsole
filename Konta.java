import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class Konta
{
	ArrayList<String> klienci = new ArrayList<String>();
	int indexyKlientow;
	int liczbaDanychKlienta=6;
	int ktoraZmienna;
	String pomocniczyNumerKlienta;	
	public Konta()
	{
	}
	public void startProgramu()
	{
		ZapisDanych.odczytPliku(klienci);
		dokonajWyboru();
	}
	
	
	public void dokonajWyboru()
	{
		try{
		Scanner input=new Scanner(System.in);
		int dokonanyWybor;
		do
	{	
		ZapisDanych.zapisDoPliku(klienci);
		System.out.println("1.Zalozenie konta uzytkownika");
		System.out.println("2.Usuniecie konta uzytkownika");
		System.out.println("3.Dokonanie wplaty na konto");
		System.out.println("4.Dokonanie wyplaty z konta");
		System.out.println("5.Transfer pomiedzy kontami");
		System.out.println("6.Wyswietl informacje o wszystkich kontach");
		System.out.println("7.Wyswietl informacje o pojedynczym koncie");
		System.out.println("0. zakoncz");
		System.out.println("Wcisnij liczby od 0-7");
		dokonanyWybor=input.nextInt();
		if(dokonanyWybor==1)
		{
			setNowyKlient();
		}
		if(dokonanyWybor==2)
		{
			deleteKonto();
		}
		if(dokonanyWybor==3)
		{
			wplacNaKontoKlienta();
		}
		if(dokonanyWybor==4)
		{
			wyplacOdKontoKlienta();
		}
		if(dokonanyWybor==5)
		{
			przelejZKontaNaKontoKlienta();
		}
		if(dokonanyWybor==6)
		{
			getKlienci();
		}
		if(dokonanyWybor==7)
		{
			getKlient();
		}
	}while(dokonanyWybor!=0);
	System.exit(0);
		}catch(InputMismatchException ime)
		{
			System.err.println("Wprowadziles zle dane wprowadz dane zgodne z instrukcja");
			dokonajWyboru();
		}
	}
	public void potwierdz()
	{
		Scanner input=new Scanner(System.in);
		System.out.println("Potwierdz polecenia wcisnij t/n");
		String takNie=input.nextLine();
		if(takNie.equals("t"))
		{
			return;
		}
		else if(takNie.equals("n"))
		{
			dokonajWyboru();
		}
		else
		{	
			System.out.println("Podales zla opcje podaj jeszcze raz");
		}
	
	}
	public void setNowyKlient()
	{
		Scanner input=new Scanner(System.in);
		Scanner wprowadz=new Scanner(System.in);
		System.out.println("Prosze podac numer klienta (liczba typu calkowitego)");
		
		int numerKlienta=input.nextInt();
		pomocniczyNumerKlienta=Integer.toString(numerKlienta);
		if(znajdzNumerKlienta()!=-1)
		{
			System.out.println("Uzytkownik o tym numerze juz istnieje");
			dokonajWyboru();
		}
		System.out.println("Prosze podac imie Klienta");
		String imieKlienta=wprowadz.nextLine();
		System.out.println("Prosze podac nazwisko Klienta");
		String nazwiskoKlienta=wprowadz.nextLine();	
		System.out.println("Prosze podac Pesel Klienta(liczbe typu calkowitego skladajaca sie z 11 cyfr");
		long peselKlienta=input.nextLong();
		System.out.println("Prosze podac Adres Klienta");
		String adresKlienta=wprowadz.nextLine();
		potwierdz();
		klienci.add(Integer.toString(numerKlienta));
		klienci.add(imieKlienta);		
		klienci.add(nazwiskoKlienta);
		klienci.add(Long.toString(peselKlienta));
		klienci.add(adresKlienta);
		klienci.add(Double.toString(0));
	}
	public void deleteKonto()
	{	
		sprawdzCzyListaJestPusta();
		ktoraZmienna=0;
		System.out.println("Podaj nr klienta ktorego chcesz usunac");
		wprowadzKlienta();
		int indexKlienta=znajdzNumerKlienta();
		if (indexKlienta==-1)
		{
			zleDaneKlienta();
			deleteKonto();
		}
		else{
			potwierdz();
			for(int j=0;j<liczbaDanychKlienta;j++)
			{
				klienci.remove(indexKlienta);
			}
		}
	}
	public void wprowadzKlienta()
	{
		Scanner input=new Scanner(System.in);
		pomocniczyNumerKlienta=input.nextLine();
	}
	public int znajdzNumerKlienta()
	{	
		for(int i=ktoraZmienna;i<klienci.size();i=i+liczbaDanychKlienta)
		{
			if(klienci.get(i).equals(pomocniczyNumerKlienta)){
			return i;
			}
		}
	return -1;
	}
	public void sprawdzCzyListaJestPusta()
	{
		if(klienci.size()==0)
		{
			System.out.println("Lista klientow jest pusta- Nie ma co usuwac");
			dokonajWyboru();
		}
	}
	public void wplacNaKontoKlienta()
	{
		sprawdzCzyListaJestPusta();
		ktoraZmienna=0;
		System.out.println("Podaj numer klienta na ktory chcesz wplacic dana sume");
		wprowadzKlienta();
		int indexKlienta=znajdzNumerKlienta();
		if (indexKlienta==-1)
		{
			zleDaneKlienta();
			wplacNaKontoKlienta();
		}
		else{
			double kwotaDoWplacenia=sumaDoPrzelania();
			potwierdz();
			kwotaDoWplacenia=Double.parseDouble(klienci.get(indexKlienta+5))+kwotaDoWplacenia;
			klienci.set(indexKlienta+5,Double.toString(kwotaDoWplacenia));
		}
	}
	public double sumaDoPrzelania()
	{
		System.out.println("Podaj kwote: ");
		Scanner input=new Scanner(System.in);
		double kwota=input.nextDouble();
		return kwota;
		
	}
		public void wyplacOdKontoKlienta()
	{
		sprawdzCzyListaJestPusta();
		ktoraZmienna=0;
		System.out.println("Podaj numer klienta z ktorego chcesz wyplacic sume");
		wprowadzKlienta();
		int indexKlienta=znajdzNumerKlienta();
		if (indexKlienta==-1)
		{
			zleDaneKlienta();
			wyplacOdKontoKlienta();
		}
		else{
			double kwotaDoWyplacenia=sumaDoPrzelania();
			potwierdz();
			kwotaDoWyplacenia=Double.parseDouble(klienci.get(indexKlienta+5))-kwotaDoWyplacenia;
			klienci.set(indexKlienta+5,Double.toString(kwotaDoWyplacenia));
		}
	}
	public void zleDaneKlienta()
	{
		System.out.println("Podales zly numer Klienta");
		System.out.println("Prosze podac jeszcze raz");	
	}
		public void przelejZKontaNaKontoKlienta()
	{
		sprawdzCzyListaJestPusta();
		ktoraZmienna=0;
		System.out.println("Podaj numer klienta z ktorego chcesz wyplacic sume");
		wprowadzKlienta();
		int indexWyplacKlienta=znajdzNumerKlienta();
		System.out.println("Podaj numer klienta do ktorego chcesz wplacic sume");
		wprowadzKlienta();
		int indexWplacKlienta=znajdzNumerKlienta();
		if (indexWyplacKlienta==-1||indexWplacKlienta==-1)
		{
			zleDaneKlienta();
			przelejZKontaNaKontoKlienta();
		}
		else
		{
			double kwotaDoPrzelania=sumaDoPrzelania();
			potwierdz();
			double kwotaDoWyplacenia=Double.parseDouble(klienci.get(indexWyplacKlienta+5))-kwotaDoPrzelania;
			klienci.set(indexWyplacKlienta+5,Double.toString(kwotaDoWyplacenia));
			double kwotaDoWplacenia=Double.parseDouble(klienci.get(indexWplacKlienta+5))+kwotaDoPrzelania;
			klienci.set(indexWplacKlienta+5,Double.toString(kwotaDoWplacenia));
		}
	}
	public void getKlienci()
	{
		indexyKlientow=0;
		for(int i=0;i<klienci.size()/liczbaDanychKlienta;i++)
		{
			wywolanieGetterow();
		}
	}
	public void getKlient()
	{
		Scanner input=new Scanner(System.in);
		System.out.println("Podaj jak chcesz znalezc klienta po przez");
		System.out.println("Numer Klienta wcisnij    1");
		System.out.println("Imie Klienta wcisnij     2");
		System.out.println("Nazwisko Klienta wcisnij 3");
		System.out.println("Pesel Klienta wcisnij    4");
		System.out.println("Adres Klienta wcisnij    5");
		System.out.println("*******************************");
		int twojWybor=input.nextInt();
		System.out.println("Wpisz dane :");
		ktoraZmienna=twojWybor-1;
		wprowadzKlienta();
		if(znajdzNumerKlienta()==-1)
		{
			System.out.println("Nie ma takiego uzytkownika");
		}
		while(znajdzNumerKlienta()!=-1){
			indexyKlientow=znajdzNumerKlienta()-twojWybor+1;
			wywolanieGetterow();
			ktoraZmienna=((twojWybor-1)+indexyKlientow);
		}
		
	}
	public void wywolanieGetterow()
	{
			System.out.println("");
			System.out.println("*******************************");
			getNumerKlienta();
			getImieKlienta();
			getNazwiskoKlienta();
			getPeselKlienta();
			getAdresKlienta();
			getStanKontaKlienta();
			System.out.println("*******************************");
	}
	public void getNumerKlienta()
	{
		System.out.println("Numer Klienta: "+klienci.get(indexyKlientow));
		indexyKlientow=indexyKlientow+1;
	}
	public void getImieKlienta()
	{
		System.out.println("Imie Klienta: "+klienci.get(indexyKlientow));
		indexyKlientow=indexyKlientow+1;
	}
	public void getNazwiskoKlienta()
	{
		System.out.println("Nazwisko Klienta: "+klienci.get(indexyKlientow));
		indexyKlientow=indexyKlientow+1;
	}
	public void getPeselKlienta()
	{
		System.out.println("Pesel Klienta: "+klienci.get(indexyKlientow));
		indexyKlientow=indexyKlientow+1;
	}
	public void getAdresKlienta()
	{
		System.out.println("Adres Klienta: "+klienci.get(indexyKlientow));
		indexyKlientow=indexyKlientow+1;	
	}
	public void getStanKontaKlienta()
	{
		System.out.println("Stan konta Klienta: "+klienci.get(indexyKlientow));
		indexyKlientow=indexyKlientow+1;
	}
}