package fr.upem.library.reference;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;



public interface ElementReference extends Remote {
	
	/**
	 * Set the resume
	 * @param resume
	 */
	public void setResume(String resume) throws RemoteException;
	
	/**
	 * @return the title
	 */
	public String getTitle() throws RemoteException;

	/**
	 * @return the authors
	 */
	public HashSet<String> getAuthors() throws RemoteException;

	/**
	 * @return the editor
	 */
	public String getEditor() throws RemoteException;

	/**
	 * @return the resume
	 */
	public String getResume() throws RemoteException;

	/**
	 * @return the price
	 */
	public double getPrice() throws RemoteException;

	/**
	 * @return the publicationDate
	 */
	public Date getPublicationDate() throws RemoteException;
	
	/**
	 * @return the isbn
	 */
	public long getIsbn() throws RemoteException;
	
	/**
	 * Update the price of a element
	 * @param age the age of the book
	 * @param value the discount 
	 * @param percentage the percentage to not cross
	 * @return the new price
	 */
	double updatePrice(double age, double value, double percentage) throws RemoteException;
	
}
