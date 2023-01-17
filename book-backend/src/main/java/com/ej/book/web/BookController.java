package com.ej.book.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ej.book.domain.Book;
import com.ej.book.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookController {
	
	private final BookService bookService;
	
	@CrossOrigin
	@PostMapping("/book")
	public ResponseEntity<?> save(@RequestBody Book book) {
		Book newBook = bookService.저장하기(book);
		return new ResponseEntity<>(newBook, HttpStatus.CREATED); // 201
	}

	@CrossOrigin
	@GetMapping("/book")
	public ResponseEntity<?> findAll() {
		List<Book> books = bookService.모두가져오기();
		return new ResponseEntity<>(books, HttpStatus.OK); // 200
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Book book = bookService.한건가져오기(id);
		return new ResponseEntity<>(book, HttpStatus.OK); // 200
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
		Book newBook = bookService.수정하기(id, book);
		return new ResponseEntity<>(newBook, HttpStatus.OK); // 200
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		String status = bookService.삭제하기(id);
		return new ResponseEntity<>(status, HttpStatus.OK); // 200
	}
}
