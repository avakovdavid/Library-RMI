package fr.upem.library.element;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

import fr.upem.library.client.Client;
import fr.upem.library.reference.ElementReference;


public class Book implements Serializable {
	
	private static final double VALUE = 5.0;
	private static final double PERCENT = 0.2;
	private static long ID = 0;
	private final long id;
	private static final long BORROW_PERIOD = 5259487666L; // 2 mois
	private static final long PRICE_UPDATE_PERIOD = 2;
	private static final long YEAR = 31556926000L;
	
	private final ElementReference reference;
	private final TreeMap<Long, Client> borrowers;
	private final long purchaseDate; // date d'achat
	private AtomicBoolean available = new AtomicBoolean(true);
	private AtomicBoolean late = new AtomicBoolean(false);
	
	private Book(ElementReference reference) {
		this(System.currentTimeMillis(), reference);
	}
	
	private Book(long purchaseDate, ElementReference reference) {
		this.reference = Objects.requireNonNull(reference);
		this.purchaseDate = purchaseDate;
		this.borrowers = new TreeMap<Long, Client>();
		this.id = ID++;
	}
	
	/**
	 * 
	 * @param purchaseDate
	 * @param reference
	 * @return
	 */
	public static Book create(long purchaseDate, ElementReference reference) {
		return new Book(purchaseDate, reference);
	}
	
	/**
	 * 
	 * @param reference
	 * @return
	 */
	public static Book create(ElementReference reference) {
		return new Book(reference);
	}
	
	/**
	 * Borrows an element by the specified user
	 * @param user the user that borrows
	 * @throws RemoteException 
	 */
	public void borrowByUser(Client user) throws RemoteException {
		if (!this.available.get()) {
			throw new IllegalStateException("The element : " + this.getInfo() + " is already borrowed");
		}
		Objects.requireNonNull(user);
		this.borrowers.put(System.currentTimeMillis(), user);
		this.available.set(false);
	}
	
	/**
	 * Borrows an element by the specified user according to the date
	 * @param date the borrowing date
	 * @param user the user that borrows
	 * @throws RemoteException 
	 */
	public void borrowByUser(long date, Client user) throws RemoteException {
		if (!this.available.get()) {
			throw new IllegalStateException("The element : " + this.getInfo()  + " is already borrowed");
		}
		Objects.requireNonNull(user);
		this.borrowers.put(date, user);
		this.available.set(false);
	}

	/**
	 * Releases the element
	 */
	public void releaseByUser() {
		if (this.available.get()) {
			throw new IllegalStateException("The element : " + this + " is not borrowed");
		}
		this.available.set(true);
		this.late.set(false);
	}
	
	/**
	 * Indicates if the element has already been borrowed once
	 * @return true if the element has been borrowed, false otherwise
	 */
	public boolean hasBeenBorrowed() {
		return this.borrowers.size() > 0;
	}
	
	/**
	 * Returns the state of the element 
	 * @return true if the element is late, false otherwise
	 */
	public boolean isReleasedDateExpire() {
		long tmp = System.currentTimeMillis();
		long period = tmp - this.borrowers.lastKey();
		if ((period > BORROW_PERIOD)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Computes the age of an element
	 * @return the age of the element
	 */
	public double age() {
		double bookAge = (System.currentTimeMillis() - this.purchaseDate) / YEAR;
		if (bookAge >= PRICE_UPDATE_PERIOD / 12) {
			return bookAge;
		}
		return 0;
	}

	/**
	 * Puts an element to the late state
	 */
	public void setToLate() {
		this.late.set(true);
	}
	
	/**
	 * Tests the availability of an element
	 * @return true if the element is available, false otherwise
	 */
	public boolean isAvailable() {
		return this.available.get();
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(AtomicBoolean available) {
		this.available = available;
	}

	/**
	 * @return the borrowers
	 */
	public TreeMap<Long, Client> getBorrowers() {
		return borrowers;
	}

	/**
	 * @return the purchaseDate
	 */
	public long getPurchaseDate() {
		return purchaseDate;
	}
	
	/**
	 * @return the reference
	 */
	public ElementReference getReference() {
		return reference;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * Get the current price of an element 
	 * @param reference the reference of the element
	 * @param element the element concerns
	 * @return the current price of the element
	 * @throws RemoteException 
	 */
	public double getCurrentPrice() throws RemoteException {
		ElementReference reference = getReference();
		double bookAge = age();
		return reference.updatePrice(bookAge, VALUE, PERCENT);
	}
	
	@Override
	public int hashCode() {
		Long tmp = this.id;
		return Integer.rotateLeft(tmp.intValue(), 12) & Integer.rotateRight(tmp.intValue(), 12);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Book)) {
			return false;
		}
		Book bookIndex = (Book)obj;
		return /*bookIndex.borrowers.equals(this.borrowers) &&
				bookIndex.purchaseDate == this.purchaseDate &&
				bookIndex.available.equals(this.available) &&*/
				bookIndex.id == this.id;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("------------------------------------- BOOK -------------------------------------\n");
		//sb.append("ID ").append(this.id).append('\n');
		sb.append("Date of purchase : ").append(new Date(purchaseDate))
										.append("\n");
		if (this.borrowers.size() > 0) {
			sb.append("Borrowers : ");
			for (Client user : this.borrowers.values()) {
				try {
					sb.append(user.getName()).append(", ");
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			int size = sb.length();
			sb.delete(size - 2, size);	
			sb.append('\n');
		}
		sb.append((this.available.get()) ? "AVAILABLE" : "NOT AVAILABLE");
		sb.append("\n--------------------------------------------------------------------------------\n");
		return sb.toString();
	}

	public String getInfo() throws RemoteException {
		StringBuilder sb = new StringBuilder();
		sb.append("Date of purchase : ").append(new Date(purchaseDate))
										.append("\n");
		if (this.borrowers.size() > 0) {
			sb.append("Borrowers : ");
			for (Client user : this.borrowers.values()) {
				sb.append(user.getName()).append(", ");
			}
			int size = sb.length();
			sb.delete(size - 2, size);	
			sb.append('\n');
		}
		if (this.late.get()) {
			sb.append("SHOULD BE RELEASED\n");
		}
		return sb.toString();
	}

}
