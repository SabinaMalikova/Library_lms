package service.serviceImpl;

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
                if (library.getId()==libraryId){
                    return library.getReaders();
                }

            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Reader getReaderById(Long id) {
        try {
            for (Library library : DataBase.libraries) {
                for (Reader reader : library.getReaders()) {
                    try {
                        if (reader.getId() == id) {
                            return reader;
                        }
                    } catch (NullPointerException e) {
                        System.out.println("не найдено");
                    }
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Reader updateReader(Long id, Reader reader) {
        try {
            for (Library library : DataBase.libraries){
                for (Reader reader1 : library.getReaders()){
                    if (reader1.getId()==id){
                        library.getReaders().set(library.getReaders().indexOf(reader1), reader);
                        System.out.println("успешно обновился");
                    }
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        return reader;
    }

    @Override
    public void assignReaderToLibrary(Long readerId, Long libraryId) {
        try {
            for (Library library: DataBase.libraries){
                for (Reader reader: DataBase.readers){
                    if (library.getId().equals(libraryId)){
                        if (reader.getId().equals(readerId)) {
                            library.getReaders().add(reader);
                            System.out.println("Читатель "+reader.getFullName() + " успешно назначен ");
                        }
                    }
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
}
