package com.ej.book.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

/*
	단위 테스트(DB 관련된 Bean이 IoC에 등록되면 됨)
	- Replace.ANY : 가짜 DB
	- Replace.NONE : 실제 DB
*/
@AutoConfigureTestDatabase(replace = Replace.ANY)
@DataJpaTest
@Transactional
public class BookRepositoryUnitTest {

	// DataJpaTest 에 DB에 띄워져 있음(Mock / 가짜로 띄울 필요가 없음)
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void save_테스트() {
		// given
		Book book = new Book(null, "책제목1", "책저자1");

		// when
		Book bookEntity = bookRepository.save(book);

		// then
		assertEquals("책제목1", book.getTitle());
		assertEquals(bookEntity, book);
	}

}
