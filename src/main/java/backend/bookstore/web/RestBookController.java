package backend.bookstore.web;

import org.springframework.web.bind.annotation.RestController;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
public class RestBookController {
    private final BookRepository bookRepository;

    public RestBookController(BookRepository bookRepository){
        this.bookRepository= bookRepository;
    }
@GetMapping("/books")
public Iterable<Book> findAlllBooks() {
    return bookRepository.findAll();
}

@GetMapping("/books/{id}")
public Optional<Book> findById(@PathVariable("id") Long bookid) {
    return bookRepository.findById(bookid);
}

@PostMapping("/books")
public Book saveBook(@RequestBody Book book) {
    return bookRepository.save(book) ;
}
@PutMapping("/books/{id}")
public Book saveEditedBook(@RequestBody Book editedBook, @PathVariable Long id) {
    editedBook.setId(id);
    return bookRepository.save(editedBook);
}
@DeleteMapping ("/books/{id}") 
    public Iterable<Book> deleteBook (@PathVariable Long id){
        bookRepository.deleteById(id);
        return bookRepository.findAll();

    }


}
