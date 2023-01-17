package com.ej.book.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.ej.book.domain.Book;
import com.ej.book.domain.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

/*
 	통합 테스트(모든 Bean들을 똑같이 IoC 올리고 테스트 하는 것)
	 - WebEnvironment.MOCK : 실제 톰캣을 돌리는 것이 아닌 다른 톰캣으로 테스트
	 - WebEnvironment.RANDOM_PORT : 실제 톰캣으로 테스트
	@AutoConfigureMockMvc : MockMvc를 IoC에 등록
	@Transactional 각각의 테스트함수가 종료될 때마다 트랜잭션을 rollback 해주는 어노테이션
*/

@SpringBootTest(webEnvironment = WebEnvironment.MOCK) // 실제 톰캣을 돌리는 것이 아닌 다른 톰캣으로 테스트
@AutoConfigureMockMvc
@Slf4j
@Transactional
public class BookControllerIntegreTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@BeforeEach
	public void init() {
		entityManager.createNativeQuery("ALTER TABLE book ALTER COLUMN id RESTART WITH 1")
			.executeUpdate();
	}
	
	// BDDMockito 패턴(given, when, then)
	@Test
	public void save_테스트() throws Exception {
		// given (테스트를 하기 위한 준비)
		Book book = new Book(null, "스프링제목", "작성자");
		String content = new ObjectMapper().writeValueAsString(book);
		
		// when (테스트 실행)
		ResultActions resultAction = mockMvc.perform(post("/book")
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(content)
			.accept(MediaType.APPLICATION_JSON_UTF8));
		
		// then (검증)
		resultAction
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.title").value("스프링제목"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void findAll_테스트() throws Exception {
		// given
		List<Book> books = new ArrayList<>();
		books.add(new Book(null, "스프링제목1", "작성자1"));
		books.add(new Book(null, "스프링제목2", "작성자2"));
		books.add(new Book(null, "스프링제목3", "작성자3"));
		bookRepository.saveAll(books);
		
		// when
		ResultActions resultAction = mockMvc.perform(get("/book")
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		// then
		resultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(2)))
			.andExpect(jsonPath("$.[0].title").value("스프링제목1"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void findById_테스트() throws Exception {
		// given
		List<Book> books = new ArrayList<>();
		books.add(new Book(null, "스프링제목1", "작성자1"));
		books.add(new Book(null, "스프링제목2", "작성자2"));
		books.add(new Book(null, "스프링제목3", "작성자3"));
		bookRepository.saveAll(books);
		
		Long id = 2L;
		
		// when
		ResultActions resultAction = mockMvc.perform(get("/book/{id}", id)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		// then
		resultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("스프링제목2"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void update_테스트() throws Exception {
		// given
		List<Book> books = new ArrayList<>();
		books.add(new Book(null, "스프링제목1", "작성자1"));
		books.add(new Book(null, "스프링제목2", "작성자2"));
		books.add(new Book(null, "스프링제목3", "작성자3"));
		bookRepository.saveAll(books);
		
		Long id = 2L;
		Book book = new Book(null, "스프링제목수정", "작성자");
		String content = new ObjectMapper().writeValueAsString(book);
		
		// when
		ResultActions resultAction = mockMvc.perform(put("/book/{id}", id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		// then
		resultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.title").value("스프링제목수정"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void delete_테스트() throws Exception {
		// given
		List<Book> books = new ArrayList<>();
		books.add(new Book(null, "스프링제목1", "작성자1"));
		books.add(new Book(null, "스프링제목2", "작성자2"));
		books.add(new Book(null, "스프링제목3", "작성자3"));
		bookRepository.saveAll(books);
		
		Long id = 2L;
		
		// when
		ResultActions resultAction = mockMvc.perform(delete("/book/{id}", id)
				.accept(MediaType.TEXT_PLAIN));
		
		// then
		resultAction
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print());
		
		// 문자응답(TEXT_PLAIN)일 경우 테스트
		MvcResult requestResult = resultAction.andReturn();
		String result = requestResult.getResponse().getContentAsString();
		
		assertEquals("ok", result);
	}

}
