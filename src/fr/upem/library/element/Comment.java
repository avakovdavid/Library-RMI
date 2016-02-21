package fr.upem.library.element;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import fr.upem.library.client.Client;


public class Comment implements Serializable {
	
	private final Date date; 
	private final String content;
	private Optional<Integer> mark = Optional.empty();
	private final Client person;
	
	/**
	 * Instantiates a new Comment
	 * @param content the message to post
	 * @param user the owner of the message
	 */
	public Comment(String content, Client person) {
		this.date = new Date(System.currentTimeMillis());
		this.content = Objects.requireNonNull(content);
		this.person = Objects.requireNonNull(person);
	}
	
	/**
	 * Instantiates a new Comment
	 * @param mark the mark with the comment
	 * @param content the message to post
	 * @param user the owner of the message
	 */
	public Comment(int mark, String content, Client person) {
		if (mark < 0 || mark > 5) {
			throw new IllegalStateException("Mark must be between 0 and 5 whereas mark = " + mark);
		}
		this.mark = Optional.of(mark);
		this.date = new Date(System.currentTimeMillis());
		this.content = Objects.requireNonNull(content);
		this.person = Objects.requireNonNull(person);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Comment)) {
			return false;
		}
		Comment comment = (Comment)obj;
		return comment.date.equals(this.date) &&
				comment.content.equals(this.content) &&
				comment.mark.equals(this.mark) &&
				comment.person.equals(this.person);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("Author : ").append(this.person.getName())
								.append("\n")
								.append("Date : ")
								.append(this.date)
								.append("\n");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (this.mark.isPresent()) {
			sb.append("Mark : ")
			.append(this.mark.get())
			.append("/5\n");
		}
		sb.append("Message : ").append(this.content);
		return sb.toString();
	}
	
}
