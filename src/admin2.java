import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Arrays;

import entities.Excercise;
import utils.Connector;

public class admin2 {

	// Zarządzanie Zadaniami
	public static void main(String[] args) {
		try {
			System.out.println(Arrays.toString(Excercise.loadAllExcercises(Connector.getConnection())));
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
					System.out.println("a - dodaj zadanie");
					System.out.println("e - edytuj dane zadania");
					System.out.println("d - usuń zadanie");
					System.out.println("x - wyjdź z programu");
					action = br.readLine();
					if (action.equals("a")) {
						Excercise exe = new Excercise();
						System.out.println("Podaj nazwę");
						exe.setTitle(br.readLine());
						System.out.println("Podaj opis zadania");
						exe.setDescription(br.readLine());
						exe.saveToDB(Connector.getConnection());
						System.out.println(Arrays.toString(Excercise.loadAllExcercises(Connector.getConnection())));
						System.out.println();
					} else if (action.equals("e")) {
						System.out.println("Podaj id zadania które edytować");
						int id = Integer.parseInt(br.readLine());
						Excercise exe = Excercise.loadExcerciseById(Connector.getConnection(), id);
						System.out.println("Podaj nazwę");
						exe.setTitle(br.readLine());
						System.out.println("Podaj opis zadania");
						exe.setDescription(br.readLine());
						exe.saveToDB(Connector.getConnection());
						System.out.println(Arrays.toString(Excercise.loadAllExcercises(Connector.getConnection())));
						System.out.println();
					} else if (action.equals("d")) {
						System.out.println("Podaj id zadania które usunąć");
						int id = Integer.parseInt(br.readLine());
						Excercise exe = Excercise.loadExcerciseById(Connector.getConnection(), id);
						exe.delete(Connector.getConnection());
						System.out.println(Arrays.toString(Excercise.loadAllExcercises(Connector.getConnection())));
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
