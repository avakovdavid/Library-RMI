
import java.rmi.RemoteException;
import java.util.Date;


public final class BookGenerator {
	
	public static void init(LibraryManager libraryManager) throws RemoteException {
		long date = 1366149600000L; // 8 decembre 2010
		long date1 = 1291762800000L; // 17 avril 2013
		
		
		libraryManager.addElement(date1, Book.create(autantEnEmporteLeVent()));
		libraryManager.addElement(Book.create(autantEnEmporteLeVent()));
		libraryManager.addElement(date, Book.create(madameBovary()));
		libraryManager.addElement(date1, Book.create(madameBovary()));
		libraryManager.addElement(Book.create(madameBovary()));
	}
	
	public static ElementReference autantEnEmporteLeVent() throws RemoteException {
		String title = "Autant en emporte le vent";
		String editor = "Macmillan Publishers";
		String authors = "Margaret Mitchell";
		long ISBN = 2534;
		String resume = "Courtisée par tous les bons partis du pays, Scarlett O'Hara n'a d'yeux que pour Ashley Wilkes.\n"
						+ "Mais celui-ci est amoureux de la douce et timide  Melanie Hamilton. \n"
						+ "Scarlett est pourtant bien décidée à le faire changer d'avis, mais à la réception des Douze Chênes c'est du cynique Rhett Butler qu'elle retient l'attention. \n"
						+ "En pleine guerre  de Sécession,  Scarlett O'Hara voit le bel avenir qui lui était réservé à jamais ravagé. \n"
						+ "Douée d'une énergie hors du commun, la jeune Scarlett devient  une femme cynique et orgueilleuse. \n"
						+ "Elle va se battre sur tous les fronts. Revenue à Tara, le domaine paternel  dévasté, elle le reconstruit puis épouse par nécessité Rhett Butler\n";
		double price = 25.99;
		Date publicationDate = new Date(System.currentTimeMillis());
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, authors);
	}
	
	public static ElementReference madameBovary() throws RemoteException {
		String title = "Madame Bovary";
		String editor = "Michel Lévy frères";
		String authors = "Gustave Flaubert";
		long ISBN = 2535;
		String resume = "Emma Rouault, fille d'un riche fermier, a été élevée dans un couvent.\n"
				+ "Elle rêve d'une vie mondaine comme les princesses des romans à l'eau de rose dans lesquels elle se réfugie pour rompre l'ennui.\n"
				+ "Elle devient l'épouse de Charles Bovary, qui malgré de laborieuses études de médecine, n'est qu'un simple officier de santé.\n"
				+ "Emma est déçue de cette vie monotone.\n"
				+ "Une invitation au bal du marquis d'Andervilliers lui redonne la joie de vivre.\n"
				+ "Lorsque Emma attend un enfant, son mari décide de quitter la ville de Tostes et de s'installer à Yonville.\n"
				+ "Emma fait la connaissance des personnalités locales : le pharmacien progressiste et athée M. Homais ; le curé Bournisien ; Léon Dupuis, clerc du notaire Guillaumin ; le libertin Rodolphe Boulanger, propriétaire du château de la Huchette.\n"
				+ "Emma est déçue par la naissance de la petite Berthe, puisqu’elle aurait préféré mettre au monde un garçon. Elle s'enlise dans l'ennui, et perd tout espoir d'une vie meilleure. Elle n'éprouve plus aucun amour pour Charles, qui pourtant ne lui veut que du bien.\n"
				+ "Elle ne parvient pas non plus à apprécier sa fille, qu'elle trouve laide et qu'elle confie à madame Rollet. Elle laisse libre cours à ses dépenses luxueuses chez son marchand d'étoffes, M. Lheureux.\n"
				+ "Elle repousse les avances de Rodolphe, et de Léon, puis elle finit par céder. Ses amants sont vite lassés du sentimentalisme exacerbé de la jeune femme qui rêve de voyages et de vie trépidante.\n"
				+ "Emma a accumulé une dette envers M. Lheureux, qui exige d'être remboursé. Les amants d'Emma ont refusé de lui prêter de l'argent. Emma se suicide par désespoir. Charles meurt de chagrin. À la mort de ses parents, Berthe est confiée à une tante, pauvre, et qui l'envoie travailler dans une filature de coton pour subsister financièrement.\n";
		
		double price = 12.50;
		Date publicationDate = new Date(System.currentTimeMillis());
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, authors);
	}
	
}
