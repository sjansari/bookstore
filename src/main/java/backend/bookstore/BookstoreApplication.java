package backend.bookstore;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.domain.*;

@SpringBootApplication
public class BookstoreApplication {

    private final AppUserRepository appUserRepository;

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    BookstoreApplication(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			log.info ("save a couple of book");
			Category category1 = new Category("Romance");
			Category category2= new Category("Fantasy");
			Category category3= new Category("Horror");
			Category category4= new Category("Historical Fiction");

			categoryRepository.save(category1);
			categoryRepository.save(category2);
			categoryRepository.save(category3);
			categoryRepository.save(category4);


			bookRepository.save(new Book ("Aku Ankka", "Carl B", 1990, "5679", 20, category1 ));
			bookRepository.save(new Book ("Aku Ankka2", "Carl B", 1991, "4678", 25, category2));

			AppUser user1= new AppUser("user", "$2a$10$uNCVFtA4KNseiaZ.vdFZB.L9vNKpgkHvD6DOcZOvUAAIyMPZEMV2y", "USER");
			AppUser user2= new AppUser("admin", "$2a$10$R45e3TgpKuQRsPkFTtzDXOpjEL2sSVZbR0lmTnGmRP/mLeArZMIQe", "ADMIN");
			appUserRepository.save(user1);
			appUserRepository.save(user2);


			
			log.info("fetch all books");
			for (Book kirja : bookRepository.findAll()) {
				log.info(kirja.toString());
			}

		};
	}
}
