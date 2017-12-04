import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Arrays;

import entities.Excercise;
import entities.Solution;
import entities.User;
import utils.Connector;

public class user1 {

	//program do dodawania rozwiązań
	public static void main(String[] args) {
		int userId = Integer.parseInt(args[0]);
		//int userId = 10;
		//System.out.println(userId);
		action(userId);
	}

	public static void action(int userId) {
		String action = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				try {
					System.out.println("Wybierz opcję");
					System.out.println("a - dodaj rozwiązanie zadania");
					System.out.println("v - przeglądaj zadania");
					System.out.println("x - wyjdź z programu");
					action = br.readLine();
					if (action.equals("a")) {
						
						
						System.out.println("Podaj id Zadania");
						int eId = Integer.parseInt(br.readLine());
						Solution sol = Solution.loadSolutionById(Connector.getConnection(), eId);
							if(sol.getUser_id() == userId){
								System.out.println("Dodaj opis zadania");
								sol.setDescription(br.readLine());
								sol.setUpdated(new java.util.Date());
								sol.saveToDB(Connector.getConnection());
							}else {
								System.out.println("Niepoprawne zadanie");
							}
					} else if (action.equals("v")) {
						Solution[] sol = Solution.loadAllByUserId(Connector.getConnection(), userId);
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
