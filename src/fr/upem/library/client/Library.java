
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;



// la librairie au sens propre du terme
public class Library {
	
	private final ConcurrentHashMap<ElementReference, ElementManager> elements = new ConcurrentHashMap<>();
	
	private final long bankAccountId = 0;
	private final String currency = "EUR";
	
	/**
	 * @return the bank account id
	 */
	long getBankAccountId() {
		return this.bankAccountId;
	}
	
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return this.currency;
	}

	/**
	 * Adds an element to the library
	 * @param element the element to add
	 * @throws RemoteException 
	 */
	void addElement(Element element) throws RemoteException {
		Objects.requireNonNull(element);
		addElement(System.currentTimeMillis(), element);
	}

	/**
	 * Adds an element to the library
	 * @param date the adding date of the element
	 * @param element the element to add
	 * @throws RemoteException 
	 */
	void addElement(long date, Element element) throws RemoteException {
		if (date < 0) {
			throw new IllegalArgumentException("date : " + date + " is negative");
		}
		Objects.requireNonNull(element);
		ElementReference reference = element.getReference();
		if (!this.elements.containsKey(reference)) {
			this.elements.put(reference, new BookManager());
		}
		ElementManager bookManager = this.elements.getOrDefault(reference, new BookManager()); // on ne sait jamais
		bookManager.addElement(date, element);
	}
	
	/**
	 * Removes an element from the library
	 * @param element the element to remove
	 * @throws RemoteException 
	 */
	void removeElement(Element element) throws RemoteException {
		Objects.requireNonNull(element);
		ElementReference elementReference = element.getReference();
		ElementManager elementManager = this.elements.get(elementReference);
		if (elementManager != null) {
			elementManager.removeElement(element);
			if (elementManager.isElementListEmpty()) {
				this.elements.remove(elementReference);
			}
		}
	}
	
	/**
	 * Adds a user in the waiting list
	 * @param elementReference the reference of the element
	 * @param user the user to add
	 * @throws RemoteException 
	 */
	void addUser(ElementReference elementReference, User user) throws RemoteException {
		Objects.requireNonNull(elementReference);
		Objects.requireNonNull(user);
		ElementManager elementManager = this.elements.get(elementReference);
		if (elementManager != null) { // verify if the element exists
			elementManager.addUser(user);
		}
	}
	
	/**
	 * Removes a user from the waiting list
	 * @param elementReference
	 * @param user
	 * @throws RemoteException 
	*/
	void removeUser(ElementReference elementReference, User user) throws RemoteException {
		Objects.requireNonNull(elementReference);
		Objects.requireNonNull(user);
		ElementManager elementManager = this.elements.get(elementReference);
		if (elementManager != null) {
			elementManager.removeUser(user);
		}
	}
	
	/**
	 * Allows a use to borrow an element
	 * @param elementReference the reference of the element
	 * @param user the that borrow the element
	 * @return an <b>Optional</b> containing the element borrowed, empty otherwise 
	 * @throws IllegalArgumentException
	 * @throws RemoteException 
	 */
	Optional<Element> borrowElement(ElementReference elementReference, User user) throws IllegalArgumentException, RemoteException{
		Objects.requireNonNull(elementReference);
		Objects.requireNonNull(user);
		ElementManager elementManager = this.elements.get(elementReference); // must always be non null 
		if (elementManager == null) {
			throw new IllegalArgumentException("Element manager : " + elementManager + " doesn't exist"); 
		}
		return elementManager.borrowByUser(user);
	}
	
	/**
	 * Allows a use to borrow an element according the date
	 * @param date the borrowing date
	 * @param elementReference the reference of the element
	 * @param user the that borrow the element
	 * @return an <b>Optional</b> containing the element borrowed, empty otherwise 
	 * @throws RemoteException 
	 * @throws IllegalArgumentException
	 */
	Optional<Element> borrowElement(long date, ElementReference elementReference, User user) throws RemoteException {
		Objects.requireNonNull(elementReference);
		Objects.requireNonNull(user);
		ElementManager elementManager = this.elements.get(elementReference); // must always be non null 
		if (elementManager == null) {
			throw new IllegalArgumentException("Element manager : " + elementManager + " doesn't exist"); 
		}
		return elementManager.borrowByUser(date, user);
	}
	
	/**
	 * Releases the element
	 * @param element the element to release
	 * @throws RemoteException 
	 */
	void releaseElement(Element element) throws RemoteException {
		Objects.requireNonNull(element);
		ElementReference elementReference = element.getReference();
		ElementManager elementManager = this.elements.get(elementReference); 
		if (elementManager != null) { 
			elementManager.release(element);
		}
	}
	
	/**
	 * Sets the resume of an element
	 * @param resume the resume to modify
	 * @param elementReference the reference of the element
	 * @throws RemoteException 
	 */
	public void setResume(String resume, ElementReference elementReference) throws RemoteException {
		Objects.requireNonNull(resume);
		Objects.requireNonNull(elementReference);
		ElementManager elementManager = this.elements.remove(elementReference);
		if (elementManager != null) {
			elementReference.setResume(resume);
			this.elements.put(elementReference, elementManager);
		}
	}

	/**
	 * Comments an element
	 * @param isbn the element to be commented
	 * @param comment the comment to add
	 * @throws RemoteException 
	 */
	void commentElement(ElementReference elementReference, Comment comment) throws RemoteException {
		Objects.requireNonNull(elementReference);
		Objects.requireNonNull(comment);
		ElementManager elementManager = this.elements.get(elementReference);
		if (elementManager != null) { // must never return null
			elementManager.comment(comment);
		}
	}
	
	/**
	 * Returns all references of borrowable elements
	 * @return a list of references
	 */
	List<ElementReference> borrowableElements() {
		return this.elements.keySet().stream().collect(Collectors.toList());
	}	
	
	/**
	 * Returns all elements of buyable elements
	 * @return a list of buyable elements
	 * @throws RemoteException 
	 */
	List<Element> buyableElements() throws RemoteException {
		List<Element> buyableElements = new ArrayList<Element>();
		for (ElementReference elementReference : this.elements.keySet()) {
			ElementManager elementManager = this.elements.get(elementReference);
			elementManager.getListOfBuyableElements().forEach(element -> buyableElements.add(element));
		}
		return buyableElements;
	}
	
	/**
	 * Returns all elements of available elements
	 * @return a list of available elements
	 * @throws RemoteException 
	 */
	List<Element> availableElements() throws RemoteException { // might be useless
		List<Element> availableElements = new ArrayList<Element>();
		for (ElementReference elementReference : this.elements.keySet()) {
			ElementManager elementManager = this.elements.get(elementReference);
			elementManager.getListOfAvailableElements().forEach(element -> availableElements.add(element));
		}
		return availableElements;
	}
	
	/**
	 * Returns the reference of an element according to its ISBN
	 * @param isbn the id of the reference
	 * @return an <b>Optional</b> containing the reference
	 * @throws RemoteException 
	 */
	public Optional<ElementReference> getElementReferenceWithIsbn(long isbn) throws RemoteException {
		for (ElementReference elementReference : this.elements.keySet()) {
			if (elementReference.getIsbn() == isbn) {
				return Optional.of(elementReference);
			}
		}
		return Optional.empty();
	}
	
	/**
	 * Indicates if at less one element remains available
	 * @param elementReference the reference of the element
	 * @return true if one element is available, false otherwise
	 * @throws RemoteException
	 */
	public boolean isAvailable(ElementReference elementReference) throws RemoteException {
		Objects.requireNonNull(elementReference);
		ElementManager elementManager = this.elements.get(elementReference);
		if (elementManager != null) {
			return elementManager.isAvailable();
		}
		return false;
	}
	
	@Override
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		if (this.elements.size() > 0) {
			for (ElementReference reference : this.elements.keySet()) {
				ElementManager copy = this.elements.get(reference);
				sb.append("[Reference]\n").append(reference);
				if (copy != null) {
					sb.append("\n\n[Copies]\n").append(copy);
				}
			}
			int size = sb.length();
			sb.delete(size - 1, size);
		}
		return sb.toString();
	}

}