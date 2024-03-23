package service.serviceImpl;

import exceptions.MyExceptions;
import models.DataBase;

import models.Library;
import models.Reader;
import service.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

public class ReaderServiceImpl implements ReaderService {

    @Override
    public void saveReader(Reader reader) {
        DataBase.readers.add(reader);
        System.out.println("Successfully saved");
    }


    @Override
    public List<Reader> getAllReaders(Long libraryId) {
        try {
            for (Library library : DataBase.libraries) {
                if (library.getId() == libraryId) {
                    return library.getReaders();
                }

            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Reader getReaderById(Long id) {
        for (Library library : DataBase.libraries) {
            for (Reader reader : library.getReaders()) {
                try {
                    if (reader.getId() == id) {
                        return reader;
                    } else {
                        throw new MyExceptions("ID неверный");
                    }
                } catch (MyExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public Reader updateReader(Long id, Reader reader) {
        for (Library library : DataBase.libraries) {
            for (Reader reader1 : library.getReaders()) {
                try {
                    if (reader1.getId() == id) {
                        library.getReaders().set(library.getReaders().indexOf(reader1), reader);
                        System.out.println("успешно обновился");
                    } else {
                        throw new MyExceptions("ID неверный");
                    }
                } catch (MyExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return reader;
    }

    @Override
    public void assignReaderToLibrary(Long readerId, Long libraryId) {
        for (Library library : DataBase.libraries) {
            for (Reader reader : DataBase.readers) {
                try {
                    if (library.getId().equals(libraryId)) {
                        try {
                            if (reader.getId().equals(readerId)) {
                                library.getReaders().add(reader);
                                System.out.println("Читатель " + reader.getFullName() + " успешно назначен ");
                            } else {
                                throw new MyExceptions("ID читателя не найден");
                            }
                        } catch (MyExceptions e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        throw new MyExceptions("ID библиотеки не найден");
                    }
                } catch (MyExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
