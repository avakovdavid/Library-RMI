package fr.upem.library.main;


import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.upem.library.element.Book;
import fr.upem.library.library.Library;
import fr.upem.library.reference.BookReference;
import fr.upem.library.reference.ElementReference;

public final class BookGenerator {

	public static void init(Library libraryManager) throws RemoteException, ParseException {

		//Autant en emporte le vent
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("17/04/2013").getTime(), Book.create(autantEnEmporteLeVent()));
		libraryManager.addElement(Book.create(autantEnEmporteLeVent()));
		
		//Madame Bovary
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("08/12/2010").getTime(), Book.create(madameBovary()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("17/04/2013").getTime(), Book.create(madameBovary()));
		libraryManager.addElement(Book.create(madameBovary()));
		
		//Windows 10 pour les nuls
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("05/09/2015").getTime(), Book.create(windows10pourLesNuls()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("05/09/2015").getTime(), Book.create(windows10pourLesNuls()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("05/09/2015").getTime(), Book.create(windows10pourLesNuls()));
		
		//Au coeur de CORBA
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("15/10/2002").getTime(), Book.create(auCoeurDeCorba()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("23/10/2005").getTime(), Book.create(auCoeurDeCorba()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("13/07/2007").getTime(), Book.create(auCoeurDeCorba()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2014").getTime(), Book.create(auCoeurDeCorba()));
		
		//La Guerre et la Paix
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2005").getTime(), Book.create(laGuerreEtLaPaix()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2014").getTime(), Book.create(laGuerreEtLaPaix()));
		
		//Pokémon l'encyclo 
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2009").getTime(), Book.create(pokemonLEncyclo()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2013").getTime(), Book.create(pokemonLEncyclo()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2015").getTime(), Book.create(pokemonLEncyclo()));
		
		//Histoire de la Russie et de son Empire
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2000").getTime(), Book.create(histoireDeLaRussieEtDeSonEmpire()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2005").getTime(), Book.create(histoireDeLaRussieEtDeSonEmpire()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2007").getTime(), Book.create(histoireDeLaRussieEtDeSonEmpire()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2011").getTime(), Book.create(histoireDeLaRussieEtDeSonEmpire()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2014").getTime(), Book.create(histoireDeLaRussieEtDeSonEmpire()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2015").getTime(), Book.create(histoireDeLaRussieEtDeSonEmpire()));
		
		//Poutine
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("18/11/2014").getTime(), Book.create(poutine()));
		
		//Programmation d'applications internet avec java
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("18/11/2000").getTime(), Book.create(programmationDApplicationsInternetAvecJava()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("18/11/2003").getTime(), Book.create(programmationDApplicationsInternetAvecJava()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("18/11/2006").getTime(), Book.create(programmationDApplicationsInternetAvecJava()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("18/11/2007").getTime(), Book.create(programmationDApplicationsInternetAvecJava()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("18/11/2010").getTime(), Book.create(programmationDApplicationsInternetAvecJava()));
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("18/11/2014").getTime(), Book.create(programmationDApplicationsInternetAvecJava()));
		
		//Une autre idée du bonheur
		libraryManager.addElement(new SimpleDateFormat("dd/MM/yyyy").parse("12/10/2015").getTime(), Book.create(programmationDApplicationsInternetAvecJava()));
		libraryManager.addElement(Book.create(programmationDApplicationsInternetAvecJava()));
	}

	public static ElementReference autantEnEmporteLeVent() throws ParseException, RemoteException {
		String title = "Autant en emporte le vent";
		String editor = "Macmillan Publishers";
		String authors = "Margaret Mitchell";
		long ISBN = 2534;
		String resume = "Courtisée par tous les bons partis du pays, Scarlett O'Hara n'a d'yeux que pour Ashley Wilkes.\n"
				+ "Mais celui-ci est amoureux de la douce et timide  Melanie Hamilton. \n"
				+ "Scarlett est pourtant bien décidée à le faire changer d'avis, mais à la réception des Douze Chênes c'est du cynique Rhett Butler qu'elle retient l'attention. \n"
				+ "En pleine guerre  de Sécession,  Scarlett O'Hara voit le bel avenir qui lui était réservé à jamais ravagé. \n"
				+ "Douée d'une énergie hors du commun, la jeune Scarlett devient  une femme cynique et orgueilleuse. \n"
				+ "Elle va se battre sur tous les fronts.  Revenue à Tara, le domaine paternel  dévasté, elle le reconstruit puis épouse par nécessité Rhett Butler\n";
		double price = 25.99;
		Date publicationDate = new SimpleDateFormat("dd/MM/yyyy").parse("22/10/1970");
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, authors);
	}

	public static ElementReference madameBovary() throws ParseException, RemoteException {
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
		Date publicationDate = new SimpleDateFormat("dd/MM/yyyy").parse("22/10/1980");
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, authors);
	}

	public static ElementReference windows10pourLesNuls() throws ParseException, RemoteException {
		String title = "Windows 10 Tout-en-un pour les Nuls";
		String editor = "First Interactive";
		String authors = "Woody LEONHARD";
		long ISBN = 645631268;
		String resume = "Cette nouvelle version de Windows gomme tous les défauts des anciennes versions de Windows et apporte encore plus d'ergonomie dans son utilisation, ainsi qu'une vitesse d'exécution accrue. "
				+ "Fort de son expérience et compte tenu de l'effort fait par Microsoft pour sortir Windows 10 au plus vite, nous pouvons nous attendre avec ce nouveau système d'exploitation à une version majeure de Windows qui sera à n'en pas douter une réussite totale, coome l'on été Windows XP et Windows 7. "
				+ "Ce livre s'adresse à la fois aux possesseurs de PC sous Vista, XP, ou Windows 8 et 8.1 désireux de ne pas perdre trop de temps dans leur passage à Windows 10, et également aux nouveaux venus qui débutent sur PC. "
				+ "Afin de faciliter l'accès aux informations essentielles pour tous ceux qui migrent depuis Windows Vista, une icône spécifique leur indique toutes les différences et nouveautés chaque fois que c'est nécessaire.";

		double price = 29.99;
		Date publicationDate = new SimpleDateFormat("dd/MM/yyyy").parse("26/08/2015");
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, authors);
	}
	
	public static ElementReference auCoeurDeCorba() throws ParseException, RemoteException {
		String title = "Au coeur de corba avec Java";
		String editor = "Vuibert";
		String authors = "Jérôme Daniel";
		long ISBN = 78696324;
		String resume = "Les environnements à objets répartis suscitent un intérêt croissant. "
				+ "Aujourd'hui, l'industrie et la recherche s'intéresse tout particulièrement à l'un de ces environnements, CORBA, spécifié par l'OMG (Object Management Group). "
				+ "Cet ouvrage a pour ambition de favoriser la compréhension et la maîtrise des techniques relatives à CORBA, en expliquant ses spécifications et en illustrant développement et fonctionnement des applications CORBA par des exemples concrets. "
				+ "L'ouvrage s'articule autour de quatre parties : une introduction à CORBA exposant pédagogiquement son architecture globale et ses constituants : langage de description d'interfaces (IDL), héritage et délégation, négociateur de requêtes (ORB), adaptateur d'objets (POA), types Any, TypeCod, TCKind, classes helper et holder, service d'annuaire interopérable ; "
				+ "les mécanismes avancés, avec la découverte des nouveautés de la révision CORBA 2*3 : servants, gestionnaire de POA, objets par valeur, encodage personnalisé ; "
				+ "les mécanismes dynamiques : référentiels d'interfaces, TypeCodes, interfaces dynamiques d'invocation et d'interception de requêtes ; "
				+ "enfin, la dernière partie propose des thèmes complémentaires (mécanisme d'encodage, extensions RMI, protocole de communication de CORBA, etc.), l'exposé de la prochaine version 3 de CORBA (modèle de composants CCM et extensions du langage de description d'interfaces), ainsi qu'un comparatif entre DCOM et CORBA. "
				+ "Le CD-ROM accompagnant cet ouvrage propose des versions d'évaluation de VisiBroker et OrbixWeb des sociétés Inprise et Iona, acteurs principaux de la sphère des environnement répartis. "
				+ "Il inclut également JavaORB, un ORB libre de droits, ainsi que les spécifications de CORBA. "
				+ "La préface est de J Siegel, directeur technique à l'OMG.";
		
		double price = 50.0;
		Date publicationDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/03/2000");
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, authors);
	}
	
	public static ElementReference laGuerreEtLaPaix() throws ParseException, RemoteException {
		String title = "La Guerre et la Paix";
		String editor = "Gallimard";
		String authors = "Léon Tolstoï";
		long ISBN = 35745678;
		String resume = "\"Eh bien, mon prince, Gênes et Lucques ne sont plus que des apanages, des domaines de la famille Buonaparte\". "
				+ "Prononcés en français, ces mots par lesquels une grande dame de Saint-Pétersbourg accueille un ami au mois de juin 1805 nous plongent d'emblée dans l'atmosphère des salons aristocratiques. "
				+ "Mais ils nous disent aussi que, passé les scènes de la vie domestique et mondaine, le véritable sujet du roman sera l'Histoire et les hostilités entre la France de Napoléon et la Russie d'Alexandre I er. "
				+ "En écrivant La Guerre et la Paix qui paraît de 1865 à 1869, Tolstoï fait concurrence à l'historien : il puise ses informations aux sources les plus sûres sans renoncer aux pouvoirs de l'imagination qui ouvre à une vérité supérieure. "
				+ "Et cette vérité-là désacralise les grands hommes : les événements qu'ils croient susciter, ils ne font que leur obéir. "
				+ "Au-delà de l'Histoire, enfin, reste la vie elle-même : inconnaissable, et l'évocation de sa complexité donne aussi tout son prix au roman.";

		double price = 27.40;
		Date publicationDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1865");
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, authors);
	}
	
	public static ElementReference pokemonLEncyclo() throws ParseException, RemoteException {
		String title = "Pokémon / L'Encyclo";
		String editor = "Gallimard";
		String authors = "Collectif";
		long ISBN = 867631275;
		String resume = "Le livre de référence pour tous les fans de Pokémon avec tout ce qu’il y a à savoir sur cet univers foisonnant depuis l’orgine : les aventures de Sacha détaillée, ses amis, ses rivaux, les combats célèbres et tous les pokémon classés par saison.";

		double price = 12.99;
		Date publicationDate = new SimpleDateFormat("dd/MM/yyyy").parse("17/11/2007");
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, authors);
	}
	
	public static ElementReference histoireDeLaRussieEtDeSonEmpire() throws ParseException, RemoteException {
		String title = "Histoire de la Russie et de son Empire";
		String editor = "Plon";
		String authors = "Michel HELLER";
		long ISBN = 3542137;
		String resume = "Fruit de plus de dix ans de travail, ce maître-livre raconte la riche et grande histoire de la Russie, des origines à la fin de l'URSS, en passant par l'établissement d'une autocratie impérialiste assise sur la force de l'orthodoxie et d'un nationalisme conquérant. "
				+ "Un classique dont l'ampleur et l'intelligence se conjuguent avec un rare bonheur d'écriture, ici présenté dans une édition entièrement revue et augmentée d'une préface inédite de Marie-Pierre Rey. "
				+ "\" C'est un travail sans équivalent, une lecture indispensable pour qui s'intéresse au pouvoir de la Russie et s'interroge sur son avenir. \" Jean-François Revel";

		double price = 17.00;
		Date publicationDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/09/1999");
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, authors);
	}
	
	public static ElementReference poutine() throws ParseException, RemoteException {
		String title = "Poutine";
		String editor = "Calmann-Lévy";
		String authors = "Frédéric Pons";
		long ISBN = 237865;
		String resume = "Vladimir Poutine reste une énigme. Que veut-il ? "
				+ "Affirmer son pouvoir personnel et celui de son clan dans la lignée des autocrates qui se sont succédé au Kremlin depuis des siècles ? "
				+ "Restaurer la grandeur de son pays, en faisant la synthèse de l’histoire russe, des tsars aux soviétiques ? "
				+ "Comment le petit lieutenant-colonel du KGB qu’il fut à la fin de la guerre froide a-t-il pu s’imposer au sommet de l’une des premières puissances du monde ? "
				+ "Qui est cet homme qui semble porter un masque ? Un politicien cynique et brutal, assoiffé de pouvoir et d’argent, ou un patriote sincère ?"
				+ "Au fil d’une enquête rigoureuse, nourrie de témoignages inédits recueillis en Russie, Frédéric Pons répond à ces questions. Sans rien occulter, il brosse un portrait saisissant du maître du Kremlin : son enfance dans un milieu modeste de Leningrad, ses rêves de jeune soviétique, les étapes décisives de sa formidable ascension, ses réseaux de pouvoir. "
				+ "Il démontre à quel point cet homme déterminé incarne les aspirations et les craintes de la Russie depuis la chute du communisme."
				+ "Dans le contexte actuel de crise, ce document brûlant, informé aux meilleures sources, est une lecture indispensable pour décrypter la personnalité, les objectifs et la stratégie de cet interlocuteur incontournable de l’Occident.";

		double price = 18.20;
		Date publicationDate = new SimpleDateFormat("dd/MM/yyyy").parse("08/10/2014");
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, authors);
	}
	
	public static ElementReference programmationDApplicationsInternetAvecJava() throws ParseException, RemoteException {
		String title = "Programmation d'applications internet avec java";
		String editor = "Vuibert";
		String author1 = "Gaby Roussel";
		String author2 = "Etienne Duris";
		long ISBN = 9764567;
		String resume = "Programmation d'applications internet avec java";

		double price = 14.30;
		Date publicationDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/12/1999");
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, author1, author2);
	}
	
	public static ElementReference uneAutreIdéeDuBonheur() throws ParseException, RemoteException {
		String title = "Une autre idée du bonheur";
		String editor = "Pocket";
		String authors = "Marc LEVY";
		long ISBN = 6875454;
		String resume = "Il y a des rêves et des amours qui ne s'éteignent jamais. "
				+ "Philadelphie, printemps 2010 : Agatha s'évade de prison. "
				+ "Elle était pourtant tout près du terme de sa peine. "
				+ "Alors pourquoi ? Dans une station-service proche du campus, elle s'invite à bord de la voiture de Milly. "
				+ "C'est le début d'une cavale de cinq jours à travers l'Amérique avec le FBI à leurs trousses. "
				+ "Une course contre la montre pour découvrir un secret qui va changer leurs vies. "
				+ "\" Une formidable histoire d'amour et d'amitié. "
				+ "\" Paris Match \" Du suspense, de l'émotion. Du pur Marc Levy. "
				+ "\" Voici \" Dès les premières lignes, les lecteurs sont du voyage, et Marc Levy nous attend au tournant pour prendre résolument le chemin du bonheur. \" Notre Temps";

		double price = 7.90;
		Date publicationDate = new SimpleDateFormat("dd/MM/yyyy").parse("23/04/2015");
		return BookReference.create(price, ISBN, title, editor, resume, publicationDate, authors);
	}
}
