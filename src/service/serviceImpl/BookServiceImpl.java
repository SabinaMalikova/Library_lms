package service.serviceImpl;

import models.Book;
import models.DataBase;
import models.Library;
import service.BookService;

import java.util.List;
import java.util.NoSuchElementException;

public class BookServiceImpl implements BookService {

    @Override
    public Book saveBook(Long libraryId, Book book) {
        for (Library library : DataBase.libraries) {
            if (library.getId().equals(libraryId)) {
                library.getBooks().add(book);
                return book;
            }
        }
        System.out.println("не найдено");
        return null;
    }


    @Override
    public List<Book> getAllBooks(Long libraryId) {
        try {
            for (Library library : DataBase.libraries) {
                if (library.getId().equals(libraryId)) {
                    return library.getBooks();
                } else {
                    System.out.println("не найдено");
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());

        }
        return null;
    }

    @Override
    public Book getBookById(Long libraryId, Long bookId) {
        try {
            for (Library library : DataBase.libraries) {
                if (library.getId().equals(libraryId)) {
                    for (Book book : library.getBooks()) {
                        if (book.getId().equals(bookId)) {
                            return book;
                        }
                    }
                } else {
                    System.out.println("не найдено");
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteBook(Long libraryId, Long bookId) {
        try {
            for (Library library : DataBase.libraries) {
                if (library.getId().equals(libraryId)) {
                    for (Book book : library.getBooks()) {
                        if (book.getId().equals(bookId)) {
                            library.getBooks().remove(book);
                            return "успешно удалено";
                        }
                    }
                } else {
                    System.out.println("не найдено");
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void clearBooksByLibraryId(Long libraryId) {
        try {
            for (Library library : DataBase.libraries) {
                if (library.getId().equals(libraryId)) {
                    library.getBooks().clear();
                    System.out.println("успешно очищено");
                } else {
                    System.out.println("не найдено");
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
}
