import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

import entities.Excercise;
import entities.Group;
import entities.Solution;
import entities.User;
import utils.Connector;

public class admin4 {

	// Przydzielanie zadań użytkownikom
	public static void main(String[] args) {
		action();
	}

	public static void action() {
		String action = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				try {
					System.out.println("Wybierz opcję");
					System.out.println("a - dodaj zadanie do użytkownika");
					System.out.println("v - przeglądaj rozwiązania użytkownika");
					System.out.println("x - wyjdź z programu");
					action = br.readLine();
					if (action.equals("a")) {
						Solution sol = new Solution();
						
						for(User user : User.loadAllUsers(Connector.getConnection()))System.out.println(user);
						System.out.println("Podaj id użytkownika");
						sol.setUser_id(Integer.parseInt(br.readLine()));
						
						for(Excercise exe : Excercise.loadAllExcercises(Connector.getConnection()))System.out.println(exe);
						System.out.println("Podaj id Zadania");
						sol.setExcercise_id(Integer.parseInt(br.readLine()));
						
						sol.setCreated(new java.util.Date());
						sol.setUpdated(new java.util.Date());
						sol.setDescription("");
						
						sol.saveToDB(Connector.getConnection());
					} else if (action.equals("v")) {
						System.out.println("Podaj id użytkownia");
						int id = Integer.parseInt(br.readLine());
						Solution[] sol = Solution.loadAllByUserId(Connector.getConnection(), id);
						for(Solution s: sol){
							System.out.println(s);
						}
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
