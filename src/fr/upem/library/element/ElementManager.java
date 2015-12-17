package fr.upem.library.element;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import fr.upem.library.client.Client;



public interface ElementManager extends Remote {
	
	/**
	 * @return a queue of all users waiting
	 */
	public Queue<Client> getWaitingList() throws RemoteException;
	
	/**
	 * @return all elements
	 */
	public List<Book> getElements() throws RemoteException;
	
	/**
	 * @return all comments
	 */
	public Queue<Comment> getComments() throws RemoteException;
	
	/**
	 * Adds a user in the list of waiting
	 * @param user the user to add
	 * @return true if the user as been had, false otherwise
	 */
	boolean addUser(Client user) throws RemoteException;
	
	/**
	 * Removes a user from the list of waiting
	 * @param user the user to remove
	 */
	void removeUser(Client user) throws RemoteException;
	
	/**
	 * Adds an element in the library at the specified date
	 * @param date the date of adding
	 * @param element the element to add
	 * @return true if the element has been added, false otherwise
	 */
	boolean addElement(long date, Book element) throws RemoteException;
	
	/**
	 * Removes an element from the library
	 * @param element the element to remove
	 */
	void removeElement(Book element) throws RemoteException;
	
	/**
	 * Indicates if the list of elements is empty
	 * @return true if the list is empty, false otherwise
	 */
	boolean isElementListEmpty() throws RemoteException;
	
	/**
	 * Indicates if the list of waiting users is empty
	 * @return true if the list is empty, false otherwise
	 */	
	boolean isWaitingListEmpty() throws RemoteException; // useless
	
	/**
	 * Returns the element borrowed by the specified user
	 * @param user the user borrowing the element
	 * @return an <b>Optional</b> containing the element
	 */
	Optional<Book> borrowByUser(Client person) throws RemoteException;

	/**
	 * Returns the element borrowed by the specified user at the specified date
	 * @param date the date of borrowing
	 * @param user the user borrowing the element
	 * @return an <b>Optional</b> containing the element
	 */
	Optional<Book> borrowByUser(long date, Client user) throws RemoteException;
	
	/**
	 * Post a comment 
	 * @param comment the comment to add
	 */
	void comment(Comment comment) throws RemoteException;

	/**
	 * Releases an element 
	 * @param element the element to release
	 */
	void release(Book element) throws RemoteException;
	
	/**
	 * Returns the list of all buyable elements
	 * @return a list of buyable elements
	 */
	List<Book> getListOfBuyableElements() throws RemoteException;
	
	/**
	 * Returns the list of all borrowable elements
	 * @return a list of borrowable elements
	 */
	List<Book> getListOfBorrowableElements() throws RemoteException;
	
	/**
	 * Returns the list of all available elements
	 * @return a list of available elements
	 */
	List<Book> getListOfAvailableElements() throws RemoteException;
	
	/**
	 * Indicates if there at less one element available
	 * @return true if one element is available, false otherwise
	 * @throws RemoteException
	 */
	boolean isAvailable() throws RemoteException;
	
}
