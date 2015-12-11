
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

//java -Djava.security.policy=securityPolicy.policy LibraryServer

public class LibraryServer {

	public static void main(String[] args) {
		try {
			LibraryManager library = LibraryManager.getInstance();
			
			User yfeugueur = User.create(123, "EUR", "Feugueur", "Yann", "yann.feugueur@mlv.fr", "yann", library);
			User davakov = User.create(333, "USD", "Avakov", "David", "david.avakov@mlv.fr", "david", library);
			User mlabardy = User.create(345, "JPN", "Labardy", "Martine", "martine.labardy@mlv.fr", "martine", library);
			
			String libraryUrl = "rmi://localhost:1099/LibraryService";
			String user1Url = "rmi://localhost:1099/User1Service";
			String user2Url = "rmi://localhost:1099/User2Service";
			String user3Url = "rmi://localhost:1099/User3Service";
			
			Naming.rebind(libraryUrl, library);
			Naming.rebind(user1Url, yfeugueur);
			Naming.rebind(user2Url, davakov);
			Naming.rebind(user3Url, mlabardy);
			
		} catch (RemoteException e) {
			System.err.println("Trouble : " + e);
		} catch (MalformedURLException e) {		
			System.err.println("Trouble with URL " + e);
		}

	}

}
