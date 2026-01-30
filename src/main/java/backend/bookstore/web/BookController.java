package backend.bookstore.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class BookController {

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
    public String addBook(@ModelAttribute Book book, Model model) {
            log.info("Kirja " + book.toString());
            bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect: /books";
    }
    
    
    
}
