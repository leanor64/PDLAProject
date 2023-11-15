package Controller;

import java.sql.SQLIntegrityConstraintViolationException;

public class CheckConnection {
	public CheckConnection (String id, String password) {
		String commande = "SELECT userName FROM Person WHERE userName = '"+id+"' AND passwrd = '"+password+"';";
		try {
			Action check = new Action(commande, "Person");
		} catch (SQLIntegrityConstraintViolationException exc) {}
	}
}
