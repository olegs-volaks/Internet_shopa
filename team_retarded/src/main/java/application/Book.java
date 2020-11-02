package application;

import java.util.Objects;

public class Book {

    private String bookTitle;
    private String authorName;
    private int id;

    public Book (String bookTitle, String authorName) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                Objects.equals(bookTitle, book.bookTitle) &&
                Objects.equals(authorName, book.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTitle, authorName, id);
    }

    @Override
    public String toString() {
        return "[ " + id + " ][ " + bookTitle + " ][ " + authorName + " ]";
    }
}
