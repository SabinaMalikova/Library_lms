package service.serviceImpl;
import models.DataBase;
import models.Library;
import service.LibraryService;
import java.util.List;
import java.util.NoSuchElementException;

public class LibraryServiceImpl implements LibraryService {

    @Override
    public List<Library> saveLibrary(Library library) {
        DataBase.libraries.add(library);
        return DataBase.libraries;
    }

    @Override
    public List<Library> getAllLibraries() {
        return DataBase.libraries;
    }

    @Override
    public Library getLibraryById(Long id) {
        try {
            for (Library library : DataBase.libraries){
                if (library.getId().equals(id)) {
                    return library;
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public Library updateLibrary(Long id, Library library) {
        try {
            for (int i = 0; i < DataBase.libraries.size(); i++) {
                Library library1 = DataBase.libraries.get(i);
                if (library1.getId().equals(id)){
                    DataBase.libraries.set(i,library);
                    return library;
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public String deleteLibrary(Long id) {
        try {
            for (Library library : DataBase.libraries){
                if (library.getId().equals(id)){
                    DataBase.libraries.remove(library);
                    return "успешно удалено";
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        return "библиотека не найдена";
    }
}
