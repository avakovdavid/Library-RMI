import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.text.ParseException;


public class LibraryClient {

	public static void main(String[] args) {
		
		String codebase = "file:///home/2inl2/mlabardy/workspace-full/MLVSchoolLibrary/src/fr/upem/library/server/";
		System.setProperty("java.rmi.server.codebase", codebase);
		System.setProperty("java.security.policy", codebase + "securityPolicy.policy");
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			System.out.println("Client execution ... ");
			LibraryManagerInterface library = (LibraryManagerInterface)Naming.lookup("rmi://localhost/LibraryService");
		
			Client yfeugueur = (Client)Naming.lookup("rmi://localhost/User1Service");
			Client davakov = (Client)Naming.lookup("rmi://localhost/User2Service");
			Client mlabardy = (Client)Naming.lookup("rmi://localhost/User3Service");
			
			yfeugueur.borrowElement(2535);
			davakov.borrowElement(2535);
			mlabardy.borrowElement(2535);
			
			
			System.out.println(library);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.err.println("Trouble " + e);
		}
	
	}

}
