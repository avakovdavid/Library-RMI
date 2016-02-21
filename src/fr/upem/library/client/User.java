package fr.upem.library.client;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import fr.upem.library.element.Comment;
import fr.upem.library.element.Book;
import fr.upem.library.library.BookCase;
import fr.upem.library.library.Library;
import fr.upem.library.library.LibraryManager;
import fr.upem.library.reference.ElementReference;



public class User implements Client, Serializable {
	
	private static long CURRENT_ID = 0;
	private final long id = CURRENT_ID++;
	
	private final String surname;
	private final String firstname;
	private final String email;
	private final String password;
	private final HashMap<Long, String> notifications = new HashMap<>();
	
	private final long bankAccountId;
	private String currency;
	
	private final ArrayList<Book> elements = new ArrayList<>();
	private final BookCase observer;
	
	
	private User(long bankAccountId, String currency, String surname, String firstname, String email, String password, BookCase observer) throws RemoteException {
		super();
		Objects.requireNonNull(observer);
		this.bankAccountId = bankAccountId;
		this.surname = Objects.requireNonNull(surname);
		this.firstname = Objects.requireNonNull(firstname);
		this.email = Objects.requireNonNull(email);
		this.password = Objects.requireNonNull(password);
		this.observer = Objects.requireNonNull(observer);
		this.currency = currency;
	}
	
	/**
	 * Creates a new user
	 * @param bankAccountId the id of the account
	 * @param currency the currency of the account
	 * @param surname the surname of the user
	 * @param firstname the firstname of the user
	 * @param email the email of the user
	 * @param password the password the user
	 * @param observer the service to subscribe to
	 * @return a new user
	 */
	public static User create(long bankAccountId, String currency, String surname, String firstname, String email, String password, LibraryManager observer) throws RemoteException {
		if (bankAccountId <= 0) {
			throw new IllegalArgumentException("The account number is negative");
		}
		Objects.requireNonNull(observer);
		User user = new User(bankAccountId, currency, surname, firstname, email, password, observer.getLibrary());
		observer.subscribe(user);
		return user;
	}
	
	/**
	 * Returns the firstname concatenates to the surname 
	 * @return the firstname and the surname
	 */
	public String getName() throws RemoteException {
		return this.firstname + ' ' + this.surname;
	}

	/**
	 * @return the borrowing books 
	 */
	public ArrayList<Book> getBooks() throws RemoteException {
		return this.elements;
	}
	
	/**
	 * @return the id
	 */
	public long getId() throws RemoteException {
		return this.id;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() throws RemoteException {
		return this.surname;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() throws RemoteException {
		return this.firstname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() throws RemoteException {
		return this.email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() throws RemoteException {
		return this.password;
	}

	/**
	 * @return the bank account id
	 */
	public long getBankAccountId() throws RemoteException {
		return this.bankAccountId;
	}

	/**
	 * @return the currency of the account
	 */
	public String getCurrency() throws RemoteException {
		return this.currency;
	}
	
	/**
	 * Sets the currency
	 * @param currency the currency to modify
	 */
	public void setCurrency(String currency) throws RemoteException {
		Objects.requireNonNull(currency);
		this.currency = currency;
	}
	
	/**
	 * Updates the status of the books currently borrowed 
	 */
	public void updateBooksStatus() throws RemoteException {
		for (Book element : this.elements) {
			if (element.isReleasedDateExpire()) {
				element.setToLate();
				addNotification(element.getReference().getTitle() + " doit être rendu dans les plus brefs délais");
			}
		}
	}
	
	/**
	 * Borrows an element
	 * @param elementReference the reference of the element
	 * @return true if element is borrowed, false otherwise
	 */
	public boolean borrowElement(ElementReference elementReference) throws RemoteException {
		return borrowElement(System.currentTimeMillis(), elementReference);
	}
	
	/**
	 * Borrows an element at the date
	 * @param date the date of borrowing
	 * @param elementReference the reference of the element
	 * @return true if element is borrowed, false otherwise
	 */
	public boolean borrowElement(long date, ElementReference elementReference) throws RemoteException {
		Objects.requireNonNull(elementReference);
		Optional<Book> element = this.observer.borrowElement(date, elementReference, this);
		if (element.isPresent()) {
			this.elements.add(element.get());
			return true;
		}
		return false;
	}

	/**
	 * Borrows an element according to the ISBN
	 * @param elementReference the reference of the element
	 * @return true if element is borrowed, false otherwise
	 */
	public boolean borrowElement(long isbn) throws RemoteException {
		Optional<ElementReference> elementReference = getElementReferenceWithIsbn(isbn);
		if (elementReference.isPresent()) {
			return borrowElement(elementReference.get());
		}
		return false;
	}
	
	/**
	 * Borrows an element at the date according to the ISBN
	 * @param date the date of borrowing
	 * @param elementReference the reference of the element
	 * @return true if element is borrowed, false otherwise
	 */
	public boolean borrowElement(long date, long isbn) throws RemoteException {
		Optional<ElementReference> elementReference = getElementReferenceWithIsbn(isbn);
		if (elementReference.isPresent()) {
			return borrowElement(date, elementReference.get());
		}
		return false;
	}

	
	/**
	 * Releases an element
	 * @param element the element to release
	 * @return true if the element is released, false otherwise
	 */
	public boolean releaseElement(Book element) throws RemoteException {
		Objects.requireNonNull(element);
		if (this.elements.remove(element)) {
			this.observer.releaseElement(element);
			return true;
		}
		return false;
	}

	/**
	 * Comments an element according it reference
	 * @param mark a mark to attribute to the element
	 * @param elementReference the reference of the element
	 * @param content the message to post
	 */
	public void commentElement(int mark, String content, ElementReference elementReference) throws RemoteException {
		if (mark < 0 || mark > 5) {
			throw new IllegalArgumentException("Mark : " + mark + " is invalid, must be between 0 and 5");
		}
		Objects.requireNonNull(content);
		Objects.requireNonNull(elementReference);
		this.observer.commentElement(elementReference, new Comment(mark, content, this));
	}

	/**
	 * Comments an element according it reference
	 * @param elementReference the reference of the element
	 * @param content the message to post
	 */
	public void commentElement(String content, ElementReference elementReference) throws RemoteException {
		Objects.requireNonNull(elementReference);
		Objects.requireNonNull(content);
		this.observer.commentElement(elementReference, new Comment(content, this));
	}
	
	/**
	 * Comments an element according it ISBN
	 * @param mark a mark to attribute to the element
	 * @param isbn the id of the element
	 * @param content the message to post
	 */
	public void commentElement(long isbn, int mark, String content) throws RemoteException {
		if (mark < 0 || mark > 5) {
			throw new IllegalArgumentException("Mark : " + mark + " is invalid, must be between 0 and 5");
		}
		if (isbn < 0) {
			throw new IllegalArgumentException("ISBN : " + isbn + " must not be negative");
		}
		Objects.requireNonNull(content);
		Optional<ElementReference> elementReference = getElementReferenceWithIsbn(isbn);
		if (elementReference.isPresent()) {
			commentElement(mark, content, elementReference.get());
		}
	}

	/**
	 * Comments an element according it ISBN
	 * @param elementReference the id of the element
	 * @param content the message to post
	 */
	public void commentElement(long isbn, String content) throws RemoteException {
		if (isbn < 0) {
			throw new IllegalArgumentException("ISBN : " + isbn + " must not be negative");
		}
		Objects.requireNonNull(content);
		Optional<ElementReference> elementReference = getElementReferenceWithIsbn(isbn);
		if (elementReference.isPresent()) {
			commentElement(content, elementReference.get());
		}
	}

	/**
	 * Adds an element name in the notifications list
	 * @param elementTitle the title of the element
	 */
	public void addNotification(String elementTitle) throws RemoteException {
		this.notifications.put(System.currentTimeMillis(), elementTitle);
	}
	
	/**
	 * Clears all notifications
	 */
	public void clearNotifications() throws RemoteException {
		this.notifications.clear();
	}
	
	/**
	 * Returns the list of notifications
	 * @return all the notifications
	 */
	public HashMap<Long, String> getNotifications() throws RemoteException {
		return this.notifications;
	}
	
	/**
	 * Returns the reference associated to the element according its ISBN
	 * @param isbn the id of the element
	 * @return an <b>Optional</b> containing the reference 
	 */
	public Optional<ElementReference> getElementReferenceWithIsbn(long isbn) throws RemoteException {
		return this.observer.getElementReferenceWithIsbn(isbn);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		User user = (User)obj;
		return 	user.surname.equals(this.surname) &&
				user.firstname.equals(this.firstname);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.firstname).append(' ').append(this.surname);
		sb.append("\nBooks :\n"); 
		
		for (Book element : elements) {
			try {
				sb.append(element.getInfo()).append('\n');
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
}
