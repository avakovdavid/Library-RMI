package fr.upem.library.client;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import fr.upem.library.element.Book;
import fr.upem.library.reference.ElementReference;


public interface Client extends Remote {
	/**
	 * Returns the firstname concatenates to the surname 
	 * @return the firstname and the surname
	 */
	public String getName() throws RemoteException;

	/**
	 * @return the borrowing books 
	 */
	public ArrayList<Book> getBooks() throws RemoteException;
	
	/**
	 * @return the id
	 */
	public long getId() throws RemoteException;

	/**
	 * @return the surname
	 */
	public String getSurname() throws RemoteException;

	/**
	 * @return the firstname
	 */
	public String getFirstname() throws RemoteException;

	/**
	 * @return the email
	 */
	public String getEmail() throws RemoteException;

	/**
	 * @return the password
	 */
	public String getPassword() throws RemoteException;

	/**
	 * @return the bank account id
	 */
	public long getBankAccountId() throws RemoteException;

	/**
	 * @return the currency of the account
	 */
	public String getCurrency() throws RemoteException;
	
	/**
	 * Sets the currency
	 * @param currency the currency to modify
	 */
	public void setCurrency(String currency) throws RemoteException;
	
	/**
	 * Updates the status of the books currently borrowed 
	 */
	public void updateBooksStatus() throws RemoteException;
	
	/**
	 * Borrows an element
	 * @param elementReference the reference of the element
	 * @return true if element is borrowed, false otherwise
	 */
	public boolean borrowElement(ElementReference elementReference) throws RemoteException;
	
	/**
	 * Borrows an element at the date
	 * @param date the date of borrowing
	 * @param elementReference the reference of the element
	 * @return true if element is borrowed, false otherwise
	 */
	public boolean borrowElement(long date, ElementReference elementReference) throws RemoteException;

	/**
	 * Borrows an element according to the ISBN
	 * @param elementReference the reference of the element
	 * @return true if element is borrowed, false otherwise
	 */
	public boolean borrowElement(long isbn) throws RemoteException;
	
	/**
	 * Borrows an element at the date according to the ISBN
	 * @param date the date of borrowing
	 * @param elementReference the reference of the element
	 * @return true if element is borrowed, false otherwise
	 */
	public boolean borrowElement(long date, long isbn) throws RemoteException;

	
	/**
	 * Releases an element
	 * @param element the element to release
	 * @return true if the element is released, false otherwise
	 */
	public boolean releaseElement(Book element) throws RemoteException;

	/**
	 * Comments an element according it reference
	 * @param mark a mark to attribute to the element
	 * @param elementReference the reference of the element
	 * @param content the message to post
	 */
	public void commentElement(int mark, String content, ElementReference elementReference) throws RemoteException;

	/**
	 * Comments an element according it reference
	 * @param elementReference the reference of the element
	 * @param content the message to post
	 */
	public void commentElement(String content, ElementReference elementReference) throws RemoteException;
	
	/**
	 * Comments an element according it ISBN
	 * @param mark a mark to attribute to the element
	 * @param isbn the id of the element
	 * @param content the message to post
	 */
	public void commentElement(long isbn, int mark, String content) throws RemoteException;

	/**
	 * Comments an element according it ISBN
	 * @param elementReference the id of the element
	 * @param content the message to post
	 */
	public void commentElement(long isbn, String content) throws RemoteException;

	/**
	 * Adds an element name in the notifications list
	 * @param elementTitle the title of the element
	 */
	public void addNotification(String elementTitle) throws RemoteException;
	
	/**
	 * Clears all notifications
	 */
	public void clearNotifications() throws RemoteException;
	
	/**
	 * Returns the list of notifications
	 * @return all the notifications
	 */
	public HashMap<Long, String> getNotifications() throws RemoteException;
	
	/**
	 * Returns the reference associated to the element according its ISBN
	 * @param isbn the id of the element
	 * @return an <b>Optional</b> containing the reference 
	 */
	Optional<ElementReference> getElementReferenceWithIsbn(long isbn) throws RemoteException;
}
