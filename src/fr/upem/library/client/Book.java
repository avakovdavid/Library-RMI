

public final class Book extends Element {

	private Book(long purchaseDate, ElementReference elementReference) {
		super(purchaseDate, elementReference);
	}
	
	private Book(ElementReference elementReference) {
		super(elementReference);
	}
	
	/**
	 * Creates a book
	 * @param elementReference the reference of the book
	 * @return a new book
	 */
	public static Book create(ElementReference elementReference) {
		return new Book(elementReference);
	}
	
	/**
	 * Creates a book bought at the specified date
	 * @param elementReference the reference of the book
	 * @return a new book
	 */
	public static Book create(long purchaseDate, ElementReference elementReference) {
		return new Book(purchaseDate, elementReference);
	}
	
}
