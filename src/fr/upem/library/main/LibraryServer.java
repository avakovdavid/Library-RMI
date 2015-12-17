package fr.upem.library.main;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;

import fr.upem.library.element.Book;
import fr.upem.library.library.Library;
import fr.upem.library.library.LibraryManager;


public class LibraryServer {

	public static void main(String[] args) {
		try {
			
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			Library library = Library.getInstance();
			
			library.addElement(Book.create(BookGenerator.auCoeurDeCorba()));
			library.addElement(Book.create(BookGenerator.autantEnEmporteLeVent()));
			library.addElement(Book.create(BookGenerator.histoireDeLaRussieEtDeSonEmpire()));
			library.addElement(Book.create(BookGenerator.laGuerreEtLaPaix()));
			
			LibraryManager libraryStub = (LibraryManager) UnicastRemoteObject.exportObject(library, 0);
			
			Registry registry = LocateRegistry.getRegistry(); // port 1099
			registry.rebind("LibraryManager", libraryStub);
			
		} catch (RemoteException e) {
			System.err.println("Trouble : " + e);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
