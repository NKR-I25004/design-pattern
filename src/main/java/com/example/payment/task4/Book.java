package com.example.payment.task4;
import java.io.*;
import java.util.*;

class Book implements Serializable {
    String title, author, isbn;
    boolean isAvailable = true;

    Book(String title, String author, String isbn) {
        this.title = title; this.author = author; this.isbn = isbn;
    }

    public String toString() {
        return title + " by " + author + " [" + (isAvailable ? "Available" : "Reserved") + "]";
    }
}

class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String msg) { super(msg); }
}

class LibrarySystem {
    static HashMap<String, Book> books = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        loadBooks();

        while (true) {
            System.out.println("\n1. Add Book\n2. Reserve Book\n3. Show Available Books\n4. Exit");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1: {
                    System.out.print("Title: "); String t = sc.nextLine();
                    System.out.print("Author: "); String a = sc.nextLine();
                    System.out.print("ISBN: "); String i = sc.nextLine();
                    books.put(i, new Book(t, a, i));
                    saveBooks();
                    break;
                }
                case 2: {
                    System.out.print("Enter ISBN to reserve: ");
                    String isbn = sc.nextLine();
                    Book b = books.get(isbn);
                    if (b == null) System.out.println("Book not found.");
                    else if (!b.isAvailable) throw new BookNotAvailableException("Already reserved!");
                    else {
                        b.isAvailable = false;
                        System.out.println("Book reserved!");
                        saveBooks();
                    }
                    break;
                }
                case 3: {
                    ArrayList<Book> list = new ArrayList<>(books.values());
                    list.removeIf(b -> !b.isAvailable);
                    list.sort(Comparator.comparing(b -> b.title));
                    list.forEach(System.out::println);
                    break;
                }
                case 4: { 
                    saveBooks(); 
                    return; 
                }
            }
        }
    }

    static void saveBooks() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("books.dat"));
        out.writeObject(books); out.close();
    }

    static void loadBooks() throws IOException, ClassNotFoundException {
        File f = new File("books.dat");
        if (!f.exists()) return;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        books = (HashMap<String, Book>) in.readObject(); in.close();
    }
}
