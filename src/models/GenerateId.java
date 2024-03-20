package models;

public class GenerateId {
    public static Long bookId = 0L;
    public static Long libraryId = 0L;
    public static Long readerId = 0L;

    public static Long genBookId(){
        return ++bookId;
    }
    public static Long genLibraryId(){
        return ++libraryId;
    }
    public static Long genReaderId(){
        return ++readerId;
    }
}
