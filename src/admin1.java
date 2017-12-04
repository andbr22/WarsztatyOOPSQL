import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Arrays;

import entities.User;
import utils.Connector;

public class admin1 {

	// Zarządzanie użytkownikami
	public static void main(String[] args) {
		try {
			System.out.println(Arrays.toString(User.loadAllUsers(Connector.getConnection())));
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
					System.out.println("a - dodaj użytkownika");
					System.out.println("e - edytuj dane użytkownika");
					System.out.println("d - usuń użytkownika");
					System.out.println("x - wyjdź z programu");
					action = br.readLine();
					if (action.equals("a")) {
						User user = new User();
						System.out.println("Podaj nazwę");
						user.setUsername(br.readLine());
						System.out.println("Podaj email");
						user.setEmail(br.readLine());
						System.out.println("Podaj hasło");
						user.setPassword(br.readLine());
						user.setUser_group_id(2);
						user.saveToDB(Connector.getConnection());
						System.out.println(Arrays.toString(User.loadAllUsers(Connector.getConnection())));
						System.out.println();
					} else if (action.equals("e")) {
						System.out.println("Podaj id użytkownika którego edytować");
						int id = Integer.parseInt(br.readLine());
						User user = User.loadUserById(Connector.getConnection(), id);
						System.out.println("Podaj nazwę");
						user.setUsername(br.readLine());
						System.out.println("Podaj email");
						user.setEmail(br.readLine());
						System.out.println("Podaj hasło");
						user.setPassword(br.readLine());
						System.out.println("Podaj id grupy użytkownika");
						user.setUser_group_id(Integer.parseInt(br.readLine()));
						user.saveToDB(Connector.getConnection());
						System.out.println(Arrays.toString(User.loadAllUsers(Connector.getConnection())));
						System.out.println();
					} else if (action.equals("d")) {
						System.out.println("Podaj id użytkownika którego usunąć");
						int id = Integer.parseInt(br.readLine());
						User user = User.loadUserById(Connector.getConnection(), id);
						user.delete(Connector.getConnection());
						System.out.println(Arrays.toString(User.loadAllUsers(Connector.getConnection())));
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
					System.out.println("Błąd bazy danych");
				}
			}
		} catch (Exception e) {
			System.out.println("Cośtam");
		}

	}
}
