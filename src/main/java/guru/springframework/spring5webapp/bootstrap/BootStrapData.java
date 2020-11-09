package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		
		Publisher publisher = new Publisher();
		publisher.setAddressLine1("Village Hidden in the Leaves");
		publisher.setCity("Konoha");
		publisher.setName("Leaf");
		publisher.setState("Fire Village");
		publisher.setZip("4343453");
		publisherRepository.save(publisher);
		
		Author minato = new Author("Minato", "Namikaze");
		Book ninja = new Book("Tale of Gutsy Ninja","99238492");
		ninja.setPublisher(publisher);
		publisher.getBooks().add(ninja);
		minato.getBooks().add(ninja);
		ninja.getAuthors().add(minato);
		authorRepository.save(minato);
		bookRepository.save(ninja);
		publisherRepository.save(publisher);
		Author kushina = new Author("Kushina", "Uzumaki");
		Book redTomato = new Book("The Red Tomato","93829482");
		publisher.getBooks().add(redTomato);
		kushina.getBooks().add(redTomato);
		redTomato.getAuthors().add(kushina);
		redTomato.setPublisher(publisher);
		publisher.getBooks().add(redTomato);
		authorRepository.save(kushina);
		bookRepository.save(redTomato);
		publisherRepository.save(publisher);
		System.out.println("Started in Bootstrap");
		System.out.println("Number of Books :-"+bookRepository.count());
	}

}
