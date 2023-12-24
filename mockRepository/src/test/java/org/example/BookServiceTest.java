package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {
    private BookService bookService;
    private BookRepository bookRepository;

    private List<Book> testData() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("1", "Title1", "Author1"));
        books.add(new Book("2", "Title2", "Author2"));
        books.add(new Book("3", "Title3", "Author3"));
        return books;
    }

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);

        testData().forEach(x -> {
            when(bookRepository.findById(x.getId())).thenReturn(x);
        });
        when(bookRepository.findAll()).thenReturn(testData());

        bookService = new BookService(bookRepository);
    }

    @Test
    void findBookById() {
        testData().forEach(x -> {
            Book book = bookService.findBookById(x.getId());
            assertEquals(book.getAuthor(), x.getAuthor());
            assertEquals(book.getTitle(), x.getTitle());
            assertEquals(book.getId(), x.getId());
        });
    }

    @Test
    void findAllBooks() {
        List<Book> books = bookService.findAllBooks();
        books.forEach(bookGetted -> {
            Book testDataBook = testData().stream().filter(x ->
                    x.getId().equals(bookGetted.getId())).collect(Collectors.toList()).get(0);
            assertEquals(testDataBook.getAuthor(), bookGetted.getAuthor());
            assertEquals(testDataBook.getTitle(), bookGetted.getTitle());
            assertEquals(testDataBook.getId(), bookGetted.getId());
        });
    }
}