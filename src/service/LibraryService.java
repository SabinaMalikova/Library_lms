package service;

import models.Library;

import java.util.List;

public interface LibraryService {
    List<Library> saveLibrary(Library library);

    List<Library>getAllLibraries();

    Library getLibraryById(Long id);

    Library updateLibrary(Long id, Library library);

    String deleteLibrary(Long id);
}
