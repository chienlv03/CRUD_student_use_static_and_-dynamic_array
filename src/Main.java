import menu.DynamicMenu;
import menu.StaticMenu;
import service.DynamicArray;
import service.StaticArray;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StaticMenu staticMenu = new StaticMenu(new StaticArray());
        DynamicMenu dynamicMenu = new DynamicMenu(new DynamicArray());
        int choose;
        Scanner scanner = new Scanner(System.in);

        do {
            printMainMenu();
            try {
                choose = scanner.nextInt();
                scanner.nextLine();
                handleMainSelection(choose, staticMenu, dynamicMenu);
            } catch (InputMismatchException e) {
                System.out.println(">>Invalid option, please try again. main");
                scanner.nextLine();
                choose = -1;
            }
        } while (choose != 0);
    }

    private static void printMainMenu() {
        System.out.println("\n<================================= Student Management System ====================================>");
        System.out.println("\n--------------------------------------------------------------------------------------------------");
        System.out.println("  <----------------  1.Static Array          2.Dynamic Arrays          0.Exit  ---------------->");
        System.out.println("--------------------------------------------------------------------------------------------------\n");
        System.out.print("Please, select feature: ");
    }

    private static void handleMainSelection(int choose, StaticMenu staticMenu, DynamicMenu dynamicMenu) {
        switch (choose) {
            case 1 -> staticMenu.displayMenu();
            case 2 -> dynamicMenu.displayMenu();
            case 0 -> System.out.println("Exiting program...");
            default -> System.out.println("Invalid option, please try again.");
        }
    }
}
