package seminars.fourth.hw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import seminars.fourth.book.Book;
import seminars.fourth.book.BookRepository;
import seminars.fourth.book.BookService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void testFindBookById() {
        // Arrange
        String bookId = "1";
        Book expectedBook = new Book(bookId, "Book1", "Author1");
        when(bookRepository.findById(bookId)).thenReturn(expectedBook);

        // Act
        Book actualBook = bookService.findBookById(bookId);

        // Assert
        assertEquals(expectedBook, actualBook);
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    public void testFindAllBooks() {
        // Arrange
        List<Book> expectedBooks = Arrays.asList(
                new Book("1", "Book1", "Author1"),
                new Book("2", "Book2", "Author2")
        );
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        // Act
        List<Book> actualBooks = bookService.findAllBooks();

        // Assert
        assertEquals(expectedBooks, actualBooks);
        verify(bookRepository, times(1)).findAll();
    }
}
