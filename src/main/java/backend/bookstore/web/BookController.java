package backend.bookstore.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class BookController {
    
    private static final Logger log =
        LoggerFactory.getLogger(BookController.class);
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository= bookRepository;
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "/books";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "/addBook";
    }
    
    @PostMapping("/saveBook")
    public String saveBook(Book book) {
            log.info("Book " + book.toString());
            bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book= bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id:" + id));
        model.addAttribute( "book", book);
        return "/addbook";
    }
    
    
    
}
