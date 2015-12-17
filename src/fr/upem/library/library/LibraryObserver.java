package fr.upem.library.library;


import java.rmi.RemoteException;

import fr.upem.library.client.Client;




public interface LibraryObserver {
	
	/**
	 * Subscribe a user to the observer
	 * @param user subscribing
	 */
	public void subscribe(Client user);
	
	/**
	 * Subscribe a user from the observer
	 * @param user that stop subscribing
	 */
	public void unsubscribe(Client user);
	
	/**
	 * Notifies all subscribing users of the state of their books 
	 * @throws RemoteException 
	 */
	public void notifyUsers() throws RemoteException;

}
