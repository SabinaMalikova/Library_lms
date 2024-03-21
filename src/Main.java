import models.*;
import models.enums.Gender;
import models.enums.Genre;
import service.BookService;
import service.LibraryService;
import service.ReaderService;
import service.serviceImpl.BookServiceImpl;
import service.serviceImpl.LibraryServiceImpl;
import service.serviceImpl.ReaderServiceImpl;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerNum = new Scanner(System.in);

        BookService bookService = new BookServiceImpl();
        ReaderService readerService = new ReaderServiceImpl();
        LibraryService libraryService = new LibraryServiceImpl();

        while (true) {

            System.out.println("""
                           Вход в отдел:  
                    1. Библиотеки  
                    2. Читатель
                    3. Книги
                           Выберите команду: """);
            int choice = scannerNum.nextInt();
            boolean isTrue = false;
            while (!isTrue) {
                switch (choice) {
                    case 1: {
                        System.out.println("       Возможные действия: \n" +
                                "1. Добавить библиотеку \n" +
                                "2. Посмотреть все библиотеки \n" +
                                "3. Посмотреть библиотеку по ID \n" +
                                "4. Обновить библиотеку \n" +
                                "5. Удалить библиотеку \n" +
                                "6. Выйти\n" +
                                "       Выберите команду: ");
                        int choiceLibraryCommand = scannerNum.nextInt();
                        switch (choiceLibraryCommand) {
                            case 1: {
                                Library library = new Library();
                                library.setId(GenerateId.genLibraryId());
                                System.out.println("Введите название библиотеки: ");
                                library.setName(scannerStr.nextLine());
                                System.out.println("Введите адресс: ");
                                library.setAddress(scannerStr.nextLine());
                                System.out.println("ID библиотеки: " + library.getId());
                                libraryService.saveLibrary(library);
                                break;
                            }
                            case 2: {
                                System.out.println(libraryService.getAllLibraries());
                                break;
                            }
                            case 3: {
                                System.out.println("Введите ID библиотеки: ");
                                System.out.println(libraryService.getLibraryById(scannerNum.nextLong()));
                                break;
                            }
                            case 4: {
                                Library library = new Library();
                                System.out.println("Введите ID библиотеки: ");
                                library.setId(scannerNum.nextLong());
                                System.out.println("Введите название библиотеки: ");
                                library.setName(scannerStr.nextLine());
                                System.out.println("Введите адресс: ");
                                library.setAddress(scannerStr.nextLine());
                                System.out.println(libraryService.updateLibrary(library.getId(), library));
                                break;
                            }
                            case 5: {
                                System.out.println("Введите ID библиотеки: ");
                                System.out.println(libraryService.deleteLibrary(scannerNum.nextLong()));
                                break;
                            }
                            case 6: {
                                isTrue = true;
                                break;
                            }
                        }
                    }
                    break;
                    case 2: {
                        System.out.println("       Возможные действия: \n" +
                                "1. Добавить читателя\n" +
                                "2. Посмотреть читателей библиотеки\n" +
                                "3. Посмотреть читателя по ID\n" +
                                "4. Обновить читателя\n" +
                                "5. Назначить читателя \n" +
                                "6. Выйти\n" +
                                "       Выберите команду: ");
                        int choiceReaderCommand = scannerNum.nextInt();
                        switch (choiceReaderCommand) {
                            case 1: {
                                Reader reader = new Reader();
                                reader.setId(GenerateId.genReaderId());
                                System.out.println("Введите ФиО: ");
                                reader.setFullName(scannerStr.nextLine());
                                System.out.println("Введите email: ");
                                reader.setEmail(scannerStr.nextLine());
                                System.out.println("введите номер телефона: ");
                                reader.setPhoneNumber(scannerStr.nextLine());
                                System.out.println("Введите гендер: 1. FEMALE или 2. MALE");
                                switch (scannerStr.nextLine()) {
                                    case "1", "FEMALE", "Female", "female", "F": {
                                        reader.setGender(Gender.FEMALE);
                                        break;
                                    }
                                    case "2", "MALE", "Male", "male", "M": {
                                        reader.setGender(Gender.MALE);
                                        break;
                                    }
                                    default: {
                                        System.out.println("не найдено");
                                    }
                                }
                                System.out.println("Ваш ID: " + reader.getId());
                                readerService.saveReader(reader);
                                break;
                            }
                            case 2: {
                                System.out.println("Введите ID библиотеки: ");
                                System.out.println(readerService.getAllReaders(new Scanner(System.in).nextLong()));
                                break;
                            }
                            case 3: {
                                System.out.println("Введите ID читателя: ");
                                System.out.println(readerService.getReaderById(scannerNum.nextLong()));
                                break;
                            }
                            case 4: {
                                Reader reader = new Reader();
                                System.out.println("Введите ID читателя: ");
                                reader.setId(scannerNum.nextLong());
                                System.out.println("Введите ФиО: ");
                                reader.setFullName(scannerStr.nextLine());
                                System.out.println("Введите email: ");
                                reader.setEmail(scannerStr.nextLine());
                                System.out.println("введите номер телефона: ");
                                reader.setPhoneNumber(scannerStr.nextLine());
                                System.out.println("Введите гендер: 1. FEMALE 2. MALE");
                                switch (scannerStr.nextLine()) {
                                    case "1", "FEMALE", "Female", "female", "F": {
                                        reader.setGender(Gender.FEMALE);
                                        break;
                                    }
                                    case "2", "MALE", "Male", "male", "M": {
                                        reader.setGender(Gender.MALE);
                                        break;
                                    }
                                    default: {
                                        System.out.println("Такое значения не найдено.");
                                    }
                                }
                                System.out.println(readerService.updateReader(reader.getId(), reader));
                                break;
                            }
                            case 5: {
                                System.out.println("Введите ID читателя и ID библиотеи в которую нужно назначить:  ");
                                readerService.assignReaderToLibrary(new Scanner(System.in).nextLong(), new Scanner(System.in).nextLong());
                                break;
                            }
                            case 6: {
                                isTrue = true;
                                break;
                            }
                            default:
                                System.out.println("Такой опции нет!");
                        }
                    }
                    break;
                    case 3: {
                        System.out.println("       Возможные действия: \n" +
                                "1. Добавить книгу \n" +
                                "2. Посмотреть все книги в библиотеке\n" +
                                "3. Посмотреть книгу \n" +
                                "4. Удалить книгу \n" +
                                "5. Очистить книги в библиотеке  \n" +
                                "6. Выйти\n" +
                                "       Выберите команду: ");
                        int choiceBookCommand = scannerNum.nextInt();
                        switch (choiceBookCommand) {
                            case 1: {
                                Book book = new Book();
                                Library library = new Library();
                                System.out.println("Введите ID библиотеки в котрую хотите добавить книгу: ");
                                library.setId((long) scannerNum.nextInt());
                                System.out.println("Введите название книги: ");
                                book.setName(scannerStr.nextLine());
                                System.out.println("Введите ФиО автора книги: ");
                                book.setAuthor(scannerStr.nextLine());
                                System.out.println("Введите жанр книги: 1.FANTASY " + " 2.DRAMA " + " 3.DETECTIVE");
                                switch (scannerStr.nextLine()) {
                                    case "1", "FANTASY", "Fantasy", "fantasy": {
                                        book.setGenre(Genre.FANTASY);
                                        break;
                                    }
                                    case "2", "DRAMA", "Drama", "drama": {
                                        book.setGenre(Genre.DRAMA);
                                        break;
                                    }
                                    case "3", "DETECTIVE", "Detective", "detective": {
                                        book.setGenre(Genre.DETECTIVE);
                                        break;
                                    }
                                    default: {
                                        System.out.println("Такого жанра нет.");
                                    }
                                }
                                book.setId(GenerateId.genBookId());
                                System.out.println("ID книги " + book.getName() + ": " + book.getId());
                                bookService.saveBook(library.getId(), book);
                                break;
                            }
                            case 2: {
                                System.out.println("Введите ID библиотеки в которой находятся книги: ");
                                System.out.println(bookService.getAllBooks(scannerNum.nextLong()));
                                break;
                            }
                            case 3: {
                                System.out.println("Введите ID библиотеки и ID книги : ");
                                System.out.println(bookService.getBookById(scannerNum.nextLong(), scannerNum.nextLong()));
                                break;
                            }
                            case 4: {
                                System.out.println("Введите ID библиотеки и ID книги, где нужно удалить: ");
                                System.out.println(bookService.deleteBook(scannerNum.nextLong(), scannerNum.nextLong()));
                                break;
                            }
                            case 5: {
                                System.out.println("Введите ID библиотеки которую хотите очистить");
                                bookService.clearBooksByLibraryId(scannerNum.nextLong());
                                break;
                            }
                            case 6: {
                                isTrue = true;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
}

