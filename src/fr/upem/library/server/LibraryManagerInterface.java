
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;


public interface LibraryManagerInterface extends Remote {

	/**
	 * Notifies all subscribing users of the state of their books 
	 */
	public void notifyUsers() throws RemoteException;
	
	/**
	 * Adds an element in the library
	 * @param element an element to add
	 */
	public void addElement(Element element) throws RemoteException;
	
	/**
	 * Adds an element in the library
	 * @param date the adding date
	 * @param element an element to add
	 */
	public void addElement(long date, Element element) throws RemoteException;
	
	/**
	 * Removes an element from the library
	 * @param element an element to remove
	 */
	public void removeElement(Element element) throws RemoteException;
	
	/**
	 * Returns the list of buyable elements whose all elements have been borrowed once, 
	 * that are also available and which has spent two years since the date of purchase
	 * @return a list of all buyable elements;
	 */
	public List<Element> buyableElements() throws RemoteException;
	
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
	public List<Element> availableElements() throws RemoteException;

	/**
	 * Gets the bank account id
	 * @return the bank account id
	 */
	public long getBankAccountId() throws RemoteException;
	
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
	
}
