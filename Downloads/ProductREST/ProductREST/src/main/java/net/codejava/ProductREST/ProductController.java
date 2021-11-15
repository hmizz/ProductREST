package net.codejava.ProductREST;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@Autowired

	private ProductService service;

	@GetMapping("/products")
	public List<Product> list() {

		return service.listAll();
	}
	// you can execute the method using the CMD as admin 
		//curl http://localhost:8080/products
		//curl -X POST -H "Content-Type: application/json"
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> get(@PathVariable Integer id) {
		try {
			
		
		Product product =service.get(id);
		
		return new  ResponseEntity<Product>(product, HttpStatus.OK);
		
	} catch(NoSuchElementException e) {
		
		return new  ResponseEntity<Product>( HttpStatus.NOT_FOUND);	
		
	}
	}

	
	@PostMapping("/products/")
	
	public void add(@RequestBody Product product) {
		
		service.save(product);
	}
	// you can execute the method using the CMD as admin 
	//curl http://localhost:8080/products
	//curl -X PUT -H "Content-Type: application/json"
	@PutMapping("/products/{id}")
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Integer id) {
		try {
		Product existproduct = service.get(id);
		service.save(product);
		return new  ResponseEntity<>(HttpStatus.OK);
	} catch(NoSuchElementException e) {
		return new  ResponseEntity<Product>( HttpStatus.NOT_FOUND);	
	}
	}
	
	// you can execute the method using the CMD as admin 
	//curl http://localhost:8080/products
	//curl -X DELETE http://localhost:8080/products/{id}
		@DeleteMapping("/products/{id}")
		
		public void delete(@PathVariable Integer id) {
			
			service.delete(id);
			
		}
}
	
	

