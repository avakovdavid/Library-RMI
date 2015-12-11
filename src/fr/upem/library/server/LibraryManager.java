import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;



public class LibraryManager extends UnicastRemoteObject implements LibraryObserver, LibraryManagerInterface {

	/**
	 * Remote warning value
	 */
	private static final long serialVersionUID = 5600157775059918047L;

	/**
	 * The library that contains books
	 */
	private final Library library;

	/**
	 * The list containing all subscribers
	 */
	private final List<Client> users = new ArrayList<Client>();

	// singleton
	private static LibraryManager libraryManager;

	private LibraryManager() throws RemoteException {
		super();
		this.library = new Library();
	}

	/**
	 * Constructor of LibraryManager
	 * @return a new LibraryManager object
	 * @throws RemoteException 
	 */
	public static LibraryManager getInstance() throws RemoteException {
		if (libraryManager == null) {
			libraryManager = new LibraryManager();
			try {
			BookGenerator.init(libraryManager);
			} catch (ParseException e) {
				System.err.println("Trouble " + e);
			}
		}
		return libraryManager;
	}

	@Override
	public void subscribe(Client user) {
		this.users.add(user);
	}

	@Override
	public void unsubscribe(Client user) {
		this.users.remove(user);
	}

	@Override
	public void notifyUsers() throws RemoteException {
		for (Client user : this.users) {
			user.updateBooksStatus();
		}
	}

	@Override
	public void addElement(Element element) throws RemoteException {
		this.library.addElement(element);
	}

	@Override
	public void addElement(long date, Element element) throws RemoteException {
		this.library.addElement(date, element);
	}

	@Override
	public void removeElement(Element element) throws RemoteException {
		this.library.removeElement(element);
	}

	@Override
	public long getBankAccountId() throws RemoteException {
		return this.library.getBankAccountId();
	}

	@Override
	public void setResume(String resume, ElementReference elementReference) throws RemoteException{
		this.library.setResume(resume, elementReference);
	}

	@Override
	public List<Element> buyableElements() throws RemoteException {
		return this.library.buyableElements();
	}

	@Override
	public List<ElementReference> borrowableElements() throws RemoteException {
		return this.library.borrowableElements();
	}
	
	@Override
	public List<Element> availableElements() throws RemoteException {
		return this.library.availableElements();
	}

	@Override 
	public void removeNotifications(Client user) throws RemoteException {
		user.clearNotifications();
	}
	
	@Override
	public HashMap<Long, String> getNotifications(Client user) throws RemoteException {
		return user.getNotifications();
	}

	// Methods to never call in the main 						//
	// Only used as mediator between the user and the library 	//

	/**
	 * Adds a user to the waiting list of the library
	 * @param reference the reference of the element
	 * @param user the user that will wait
	 * @throws RemoteException 
	 */
	public void addUser(ElementReference elementReference, Client user) throws RemoteException {
		this.library.addUser(elementReference, user);
	}

	/**
	 * Removes a user to the waiting list of the library
	 * @param elementReference the reference of the element
	 * @param user the user that stopped waiting
	 * @throws RemoteException 
	 */
	void removeUser(ElementReference elementReference, Client user) throws RemoteException {
		this.library.removeUser(elementReference, user);
	}

	/**
	 * Allows a user to borrow a book from the library
	 * @param elementReference the reference of the element
	 * @param user the user that borrow the element
	 * @return the element borrowed
	 * @throws RemoteException 
	 * @throws IllegalArgumentException 
	 */
	Optional<Element> borrowElement(ElementReference elementReference, Client user) throws IllegalArgumentException, RemoteException {
		return this.library.borrowElement(elementReference, user);
	}

	/**
	 * Allows a user to borrow a book from the library at the specified date
	 * @param date the date of borrowing
	 * @param elementReference the reference of the element
	 * @param user the user that borrow the element
	 * @return the element borrowed
	 * @throws RemoteException 
	 */
	Optional<Element> borrowElement(long date, ElementReference elementReference, Client user) throws RemoteException {
		return this.library.borrowElement(date, elementReference,  user);
	}

	/**
	 * Allows a user to release an element previously borrowed
	 * @param element the element to release
	 * @throws RemoteException 
	 */
	void releaseElement(Element element) throws RemoteException {
		this.library.releaseElement(element);
	}
	
	/**
	 * Returns the element reference associated to the isbn
	 * @param isbn the id of the element
	 * @return the element reference
	 * @throws RemoteException 
	 */
	public Optional<ElementReference> getElementReferenceWithIsbn(long isbn) throws RemoteException {
		return this.library.getElementReferenceWithIsbn(isbn);
	}

	/**
	 * Allows a user to comment an element
	 * @param element the element to comment
	 * @param comment the comment to add to the element
	 * @throws RemoteException 
	 */
	void commentElement(ElementReference elementReference, Comment comment) throws RemoteException {
		this.library.commentElement(elementReference, comment);
	}
	
	@Override
	public boolean isAvailable(ElementReference elementReference) throws RemoteException {
		return this.library.isAvailable(elementReference);
	}

	@Override
	public String toString() {
		return this.library.toString();
	}



}
