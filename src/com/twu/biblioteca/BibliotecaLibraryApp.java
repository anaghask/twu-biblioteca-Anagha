package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaLibraryApp {
    private static PrintStream printStream;
    private final LibraryManager libraryManager;

    public BibliotecaLibraryApp(LibraryManager libraryManager, PrintStream printStream) {
        this.libraryManager = libraryManager;
        this.printStream = printStream;
        welcomeMessage();
    }

    public static void main(String[] args) {
        Library libraryBook = getBookCollection();
        Library libraryMovie = getMovieCollection();
        Scanner scanner = new Scanner(System.in);

        LibraryManager libraryManager = new LibraryManager(libraryBook, libraryMovie, scanner, System.out);
        new BibliotecaLibraryApp(libraryManager, System.out).selectOption(scanner);
    }

    private static Library getMovieCollection() {
        ArrayList<Item> items;
        Library library;
        Movie movie1 = new Movie("Krish3", 2012, "Rakesh Roshan", 3.2, false);
        Movie movie2 = new Movie("Kaho Na Pyar Hai", 1999, "Rakesh R", 6.1, false);

        items = new ArrayList<Item>();

        items.add(movie1);
        items.add(movie2);

        library = new Library(items, getCustomerList());
        return library;
    }

    private static Library getBookCollection() {
        Book book1 = new Book("J K Rowling", "Harry Potter", 1992, false);
        Book book2 = new Book("Dan Brown", "Angels and Demons", 1245, false);
        ArrayList<Item> items = new ArrayList<Item>();

        items.add(book1);
        items.add(book2);

        return new Library(items, getCustomerList());
    }

    private static ArrayList<Customer> getCustomerList() {
        Customer customer = new Customer("123-1234", "password", "Random", "xyz@gmail.com", "1234567891");
        Customer customer1 = new Customer("123-1235", "password1", "Random", "xyz@gmail.com", "1234567891");
        Customer customer2 = new Customer("123-1236", "password2", "Random", "xyz@gmail.com", "1234567891");
        ArrayList<Customer> customers = new ArrayList<Customer>();

        customers.add(customer);
        customers.add(customer1);
        customers.add(customer2);
        return customers;
    }

    private void welcomeMessage() {
        printStream.println("Welcome");
    }

    private void menuList() {
        for (Menu menu : Menu.values()) {
            if (!(menu == null)) {
                if (!libraryManager.isLoggedIn() && menu == Menu.DISPLAYCUSTOMERINFO)
                    break;
                printStream.println(menu.toString());
            }
        }
    }


    public void selectOption(Scanner scanner) {
        int option;
        do {
            menuList();
            option = Integer.parseInt(scanner.nextLine());
            Menu menu = Menu.getMenu(option);
            if(menu == null || (!libraryManager.isLoggedIn() && menu == Menu.DISPLAYCUSTOMERINFO))
                printStream.println("Invalid Option");
            else
                menu.executeMenu(libraryManager);


        } while (option != Menu.QUIT.index);
    }


}

