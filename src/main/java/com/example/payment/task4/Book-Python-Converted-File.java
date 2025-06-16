package com.example.payment.task4;
// import pickle
// import os
// from typing import Dict
// from dataclasses import dataclass, field


// @dataclass
// class Book:
//     title: str
//     author: str
//     isbn: str
//     is_available: bool = field(default=True)

//     def __str__(self) -> str:
//         status = "Available" if self.is_available else "Reserved"
//         return f"{self.title} by {self.author} [{status}]"


// class BookNotAvailableException(Exception):
//     """Custom exception for when a book is already reserved."""
//     pass


// class LibrarySystem:
//     books: Dict[str, Book] = {}

//     @classmethod
//     def load_books(cls) -> None:
//         """Loads books from the 'books.dat' file if it exists."""
//         if os.path.exists("books.dat"):
//             with open("books.dat", "rb") as file:
//                 cls.books = pickle.load(file)

//     @classmethod
//     def save_books(cls) -> None:
//         """Saves current books to the 'books.dat' file."""
//         with open("books.dat", "wb") as file:
//             pickle.dump(cls.books, file)

//     @classmethod
//     def add_book(cls, title: str, author: str, isbn: str) -> None:
//         """Adds a new book to the system."""
//         cls.books[isbn] = Book(title, author, isbn)
//         cls.save_books()

//     @classmethod
//     def reserve_book(cls, isbn: str) -> None:
//         """Reserves a book by ISBN, raises error if unavailable."""
//         book = cls.books.get(isbn)
//         if book is None:
//             print("Book not found.")
//         elif not book.is_available:
//             raise BookNotAvailableException("Already reserved!")
//         else:
//             book.is_available = False
//             print("Book reserved!")
//             cls.save_books()

//     @classmethod
//     def show_available_books(cls) -> None:
//         """Displays all available books, sorted by title."""
//         available_books = sorted(
//             (book for book in cls.books.values() if book.is_available),
//             key=lambda b: b.title
//         )
//         for book in available_books:
//             print(book)

//     @classmethod
//     def main(cls) -> None:
//         """Main interactive loop for the library system."""
//         import sys
//         try:
//             cls.load_books()
//             while True:
//                 print("\n1. Add Book\n2. Reserve Book\n3. Show Available Books\n4. Exit")
//                 choice = input("Enter choice: ").strip()
//                 if choice == "1":
//                     title = input("Title: ").strip()
//                     author = input("Author: ").strip()
//                     isbn = input("ISBN: ").strip()
//                     cls.add_book(title, author, isbn)
//                 elif choice == "2":
//                     isbn = input("Enter ISBN to reserve: ").strip()
//                     try:
//                         cls.reserve_book(isbn)
//                     except BookNotAvailableException as e:
//                         print(e)
//                 elif choice == "3":
//                     cls.show_available_books()
//                 elif choice == "4":
//                     cls.save_books()
//                     print("Exiting.")
//                     break
//                 else:
//                     print("Invalid choice.")
//         except Exception as e:
//             print(f"An error occurred: {e}", file=sys.stderr)
