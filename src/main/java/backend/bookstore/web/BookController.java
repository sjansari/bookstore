package backend.bookstore.web;
import backend.bookstore.domain.CategoryRepository;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BookController {

    private final CategoryRepository categoryRepository;
    
    private static final Logger log =
        LoggerFactory.getLogger(BookController.class);
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository= bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/booklist")
    public String getBooks(Model model) {
        model.addAttribute("booklist", bookRepository.findAll());
        return "/booklist";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "/addBook";
    }
    
    @PostMapping("/saveBook")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        log.info("Kirja " + book.toString());
        if (bindingResult.hasErrors()) {
            log.info("validation error tapahtui: " + book.toString());
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryRepository.findAll());
            return "/addBook";
        }
        bookRepository.save(book);
        return "redirect:/booklist";
    }
      
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book= bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id:" + id));
        model.addAttribute( "book", book);
        model.addAttribute("categories", categoryRepository.findAll());

        return "/addBook";
    }
    
    
    
}
