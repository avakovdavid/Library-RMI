package fr.upem.library.library;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import fr.upem.library.client.Client;
import fr.upem.library.element.Book;
import fr.upem.library.element.Comment;
import fr.upem.library.reference.ElementReference;


public interface LibraryManager extends Remote {
	
	/**
	 * @return the instance of LibraryManagerInterface
	 */
	public LibraryManager get() throws RemoteException;
	
	/**
	 * Adds an element in the library
	 * @param element an element to add
	 */
	public void addElement(Book element) throws RemoteException;
	
	/**
	 * Adds an element in the library
	 * @param date the adding date
	 * @param element an element to add
	 */
	public void addElement(long date, Book element) throws RemoteException;
	
	/**
	 * Removes an element from the library
	 * @param element an element to remove
	 */
	public void removeElement(Book element) throws RemoteException;
	
	/**
	 * Returns the list of buyable elements whose all elements have been borrowed once, 
	 * that are also available and which has spent two years since the date of purchase
	 * @return a list of all buyable elements;
	 */
	public List<Book> buyableElements() throws RemoteException;
	
	/**
	 * Returns the list of borrowable elements available
	 * @return a list of all borrowable elements;
	 * @throws RemoteException
	 */
	public List<ElementReference> borrowableElements() throws RemoteException;
	
	/**
	 * Returns the list of available elements
	 * @return a list of all available elements
	 * @throws RemoteException
	 */
	public List<Book> availableElements() throws RemoteException;

	/**
	 * Gets the bank account id
	 * @return the bank account id
	 */
	public long getBankAccountId() throws RemoteException;
	
	/**
	 * Returns a list of comments
	 * @param reference
	 * @return list of comments
	 * @throws RemoteException
	 */
	public Queue<Comment> getComments(ElementReference reference) throws RemoteException;
	
	/**
	 * Sets the resume of an element 
	 * @param resume the resume to modify
	 */
	public void setResume(String resume, ElementReference elementReference) throws RemoteException;
	
	/**
	 * Removes all the notifications of the specified user
	 * @param user the owner of the notifications
	 */
	public void removeNotifications(Client user) throws RemoteException;
	
	/**
	 * Get all notifications associated to the specified user
	 * @param user the owner of the notifications
	 * @return a map of all notification whose keys are the date 
	 */
	public HashMap<Long, String> getNotifications(Client user) throws RemoteException;
	
	/**
	 * Indicates if at less one element remains available
	 * @param elementReference the reference of the element
	 * @return true if one element is available, false otherwise
	 * @throws RemoteException
	 */
	public boolean isAvailable(ElementReference elementReference) throws RemoteException;
	
	/**
	 * Displays the library;
	 * @return the library
	 * @throws RemoteException
	 */
	public String display() throws RemoteException;
	
	/**
	 * Clears the library
	 * @throws RemoteException
	 */
	public void clear() throws RemoteException;
	
	/**
	 * Subscribe a user to the observer
	 * @param user subscribing
	 */
	public void subscribe(Client user) throws RemoteException;
	
	/**
	 * Subscribe a user from the observer
	 * @param user that stop subscribing
	 */
	public void unsubscribe(Client user) throws RemoteException;
	
	/**
	 * Notifies all subscribing users of the state of their books 
	 * @throws RemoteException 
	 */
	public void notifyUsers() throws RemoteException;

	/**
	 * Returns the library
	 * @return the library
	 */
	public BookCase getLibrary() throws RemoteException;
	
}
