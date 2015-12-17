package fr.upem.library.reference;



import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;



public class BookReference implements ElementReference, Serializable {
	
	private final String title;
	private final HashSet<String> authors = new HashSet<String>();
	private final String editor;
	
	private String resume;
	private final double price;
	private final Date publicationDate;
	
	private final long isbn;
	

	private BookReference(double price, long isbn, String title, String editor, String resume, Date publicationDate) throws RemoteException {
		super();
		if(isbn < 0) {
			throw new IllegalArgumentException("isbn is negative");
		}
		this.isbn = isbn;
		this.resume = Objects.requireNonNull(resume);
		this.price = Objects.requireNonNull(price);
		this.publicationDate = Objects.requireNonNull(publicationDate);
		this.title = Objects.requireNonNull(title);
		this.editor = Objects.requireNonNull(editor);
	}
	
	/**
	 * Creates a new book reference
	 * @param price the price of the book
	 * @param isbn the ISBN of the book
	 * @param title the tite of the book
	 * @param editor the editor of the book
	 * @param resume the resume of the book
	 * @param publicationDate the publication date of the book
	 * @param authors the authors of the book
	 * @return a new BookRefence object
	 * @throws RemoteException 
	 */
	public static BookReference create(double price, long isbn, String title, String editor, String resume, Date publicationDate, String...authors) throws RemoteException {
		BookReference book = new BookReference(price, isbn, title, editor, resume, publicationDate);
		for(String author : authors) {
			book.authors.add(author);
		}
		return book;
	}
	
	/**
	 * Sets the resume
	 * @param resume the new resume 
	 */
	@Override
	public void setResume(String resume) {
		this.resume = Objects.requireNonNull(resume);
	}
	
	@Override
	public double updatePrice(double age, double value, double percentage) {
		double result = value * percentage;
		if (result >= this.price - value) {
			return this.price - value;
		}
		return this.price;
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public HashSet<String> getAuthors() {
		return authors;
	}

	@Override
	public String getEditor() {
		return editor;
	}

	@Override
	public String getResume() {
		return resume;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public Date getPublicationDate() {
		return publicationDate;
	}

	@Override
	public long getIsbn() {
		return isbn;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BookReference)) {
			return false;
		}
		BookReference book = (BookReference)obj;
		return book.isbn == this.isbn;
	}
	 
	@Override
	public int hashCode() {
		int value = (int)this.isbn;
		return Integer.rotateLeft(value, 2) & Integer.rotateRight(value, 2);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Title : ").append(this.title)
							.append("\n")
							.append("Authors : ");
		for (String string : authors) {
			sb.append(string).append(", ");
		}
		int size = sb.length();
		sb.delete(size - 3, size);
		sb.append("\n");
		sb.append("Resume : ").append(this.resume)
							.append("\n")
							.append("Editor : ")
							.append(this.editor)
							.append("\n")					
							.append("Date of publication : ")
							.append(this.publicationDate)
							.append("\n")
							.append("ISBN : ")
							.append(this.isbn)
							.append("\n")
							.append("Price : ")
							.append(this.price).append(" €")
							.append("\n");
		return sb.toString();
	}
	
}
