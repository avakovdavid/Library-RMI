


public interface LibraryObserver {
	
	/**
	 * Subscribe a user to the observer
	 * @param user subscribing
	 */
	public void subscribe(User user);
	
	/**
	 * Subscribe a user from the observer
	 * @param user that stop subscribing
	 */
	public void unsubscribe(User user);

}
