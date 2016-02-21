package fr.upem.library.element;



import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import fr.upem.library.client.Client;
import fr.upem.library.reference.ElementReference;


public class BookManager implements ElementManager, Serializable {
	
	private ArrayList<Book> elements = new ArrayList<>();
	private ArrayBlockingQueue<Client> users = new ArrayBlockingQueue<>(10); // limited to 10 users
	private final ArrayBlockingQueue<Comment> comments = new ArrayBlockingQueue<>(100); // limited to 100 comments
	
	
	public BookManager() throws RemoteException {
		super();
	}
	
	@Override
	public List<Book> getElements() throws RemoteException {
		return this.elements;
	}
	
	@Override
	public Queue<Client> getWaitingList() throws RemoteException {
		return this.users;
	}
	
	@Override
	public Queue<Comment> getComments() throws RemoteException {
		return this.comments;
	}

	@Override
	public boolean addUser(Client user) throws RemoteException {
		Objects.requireNonNull(user);
		return this.users.offer(user);
	}
	
	@Override
	public void removeUser(Client user) throws RemoteException {
		Objects.requireNonNull(user);
		this.users.remove(user);
	}
	
	@Override
	public boolean addElement(long date, Book element) throws RemoteException {
		Objects.requireNonNull(element);
		return this.elements.add(element);
	}
	
	@Override
	public void removeElement(Book book) throws RemoteException {
		Objects.requireNonNull(book);
		this.elements.remove(book);
	}
	
	@Override
	public boolean isElementListEmpty() throws RemoteException {
		return this.elements.size() == 0;
	}
	
	@Override
	public boolean isWaitingListEmpty() throws RemoteException {
		return this.users.size() == 0;
	}
	
	@Override
	public Optional<Book> borrowByUser(Client user) throws RemoteException {
		Objects.requireNonNull(user);
		for (Book element : this.elements) {
			if (element.isAvailable()) {
				element.borrowByUser(user);
				return Optional.of(element);
			}
		}
		if (!addUser(user)) { // add to the list of waiting user
			throw new IllegalStateException("The waiting list is already full");
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<Book> borrowByUser(long date, Client user) throws RemoteException {
		Objects.requireNonNull(user);
		for (Book element : this.elements) {
			if (element.isAvailable()) {
				element.borrowByUser(date, user);
				return Optional.of(element);
			}
		}
		if (!addUser(user)) { // add to the list of waiting user
			throw new IllegalStateException("The waiting list is already full");
		}
		return Optional.empty();
	}
	
	@Override
	public void release(Book element) throws RemoteException {
		Objects.requireNonNull(element);
		if (this.elements.contains(element)) {
			Book myElement = this.elements.get(this.elements.indexOf(element));
			ElementReference reference = myElement.getReference();
			Client user = this.users.poll();
			if (user != null) {
				user.addNotification("Book (" + myElement.getReference().getTitle() + ')');
				myElement.releaseByUser();
				user.borrowElement(reference);
			} else {
				myElement.releaseByUser();
			}
		}
	}

	@Override
	public void comment(Comment comment) throws RemoteException {
		Objects.requireNonNull(comment);
		this.comments.add(comment);
	}
	
	@Override
	public List<Book> getListOfBuyableElements() throws RemoteException {
		List<Book> buyableBooks = new ArrayList<Book>();
		for (Book element : this.elements) {
			boolean buyable = element.hasBeenBorrowed();
			boolean available = element.isAvailable();
			boolean old = element.age() >= 2;
			if (buyable && available && old) {
				buyableBooks.add(element);
			}
		}
		return buyableBooks;
	}
	
	@Override
	public List<Book> getListOfAvailableElements() throws RemoteException {
		List<Book> borrowableBooks = new ArrayList<Book>();
		for (Book element : this.elements) {
			if (element.isAvailable()) {
				borrowableBooks.add(element);
			}
		}
		return borrowableBooks;
	}
	
	@Override
	public List<Book> getListOfBorrowableElements() throws RemoteException {
		List<Book> borrowableBooks = new ArrayList<Book>();
		for (Book element : this.elements) {
			borrowableBooks.add(element);
		}
		return borrowableBooks;
	}
	
	@Override
	public boolean isAvailable() throws RemoteException {
		for (Book element : this.elements) {
			if (!element.isAvailable()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.comments.size() > 0) {
			sb.append("\nComments : \n");
			for (Comment comment : comments) {
				sb.append(comment);
			}
		}
		if (this.users.size() > 0) {
			sb.append("\nPeople waiting : ");
			for (Client user : this.users) {
				try {
					sb.append(user.getName()).append(", ");
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			int size = sb.length();
			sb.delete(size - 2, size);
		}
		sb.append('\n');
		for (Book book : elements) {
			sb.append(book).append('\n');
		}
		return sb.toString();
	}
	
}
