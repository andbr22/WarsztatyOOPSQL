import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Arrays;

import entities.Excercise;
import entities.Group;
import utils.Connector;

public class admin3 {

	// Zarządzanie Grupami
	public static void main(String[] args) {
		try {
			System.out.println(Arrays.toString(Group.loadAllGroups(Connector.getConnection())));
			System.out.println();
			action();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void action() {
		String action = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				try {
					System.out.println("Wybierz opcję");
					System.out.println("a - dodaj grupę");
					System.out.println("e - edytuj grupę");
					System.out.println("d - usuń grupę");
					System.out.println("x - wyjdź z programu");
					action = br.readLine();
					if (action.equals("a")) {
						Group gp = new Group();
						System.out.println("Podaj nazwę");
						gp.setName(br.readLine());
						gp.saveToDB(Connector.getConnection());
						System.out.println(Arrays.toString(Group.loadAllGroups(Connector.getConnection())));
						System.out.println();
					} else if (action.equals("e")) {
						System.out.println("Podaj id grupy którą edytować");
						int id = Integer.parseInt(br.readLine());
						Group gp = Group.loadGroupById(Connector.getConnection(), id);
						System.out.println("Podaj nazwę");
						gp.setName(br.readLine());
						gp.saveToDB(Connector.getConnection());
						System.out.println(Arrays.toString(Group.loadAllGroups(Connector.getConnection())));
						System.out.println();
					} else if (action.equals("d")) {
						System.out.println("Podaj id grupy którą usunąć");
						int id = Integer.parseInt(br.readLine());
						Group gp = Group.loadGroupById(Connector.getConnection(), id);
						gp.delete(Connector.getConnection());
						System.out.println(Arrays.toString(Group.loadAllGroups(Connector.getConnection())));
						System.out.println();
					} else if (action.equals("x"))
						break;
					else
						System.out.println("Niepoprawna opcja");

				} catch (IOException e) {
					System.out.println("Niepoprawna opcja");
				} catch (NumberFormatException e) {
					System.out.println("To nie jest liczba");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Błąd bazy danych");
				}
			}
		} catch (Exception e) {
			System.out.println("Cośtam");
		}

	}
}
