package com.ej.book.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.ej.book.domain.Book;
import com.ej.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// 단위 테스트(Controller, Filter, ControllerAdvice(Exception 처리..)

@Slf4j
@WebMvcTest
public class BookControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean // IoC 환경에 bean 등록됨(가짜) -> 진짜로 쓰면 모든 Repository를 연결시켜줘야 함
	private BookService bookService;

	// BDDMockito 패턴(given, when, then)
	@Test
	public void save_테스트() throws Exception {
		// given (테스트를 하기 위한 준비)
		Book book = new Book(null, "스프링제목", "작성자");
		String content = new ObjectMapper().writeValueAsString(book);
		
		// stub (가정)
		when(bookService.저장하기(book)).thenReturn(new Book(1L, "스프링제목", "작성자"));
		
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
		books.add(new Book(1L, "스프링제목1", "작성자1"));
		books.add(new Book(2L, "스프링제목2", "작성자2"));
		
		// stub
		when(bookService.모두가져오기()).thenReturn(books);
		
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
		Long id = 1L;
		
		// stub
		when(bookService.한건가져오기(id)).thenReturn(new Book(1L, "제목1", "작성자1"));
		
		// when
		ResultActions resultAction = mockMvc.perform(get("/book/{id}", id)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		// then
		resultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("제목1"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void update_테스트() throws Exception {
		// given
		Long id = 1L;
		Book book = new Book(null, "스프링제목수정", "작성자");
		String content = new ObjectMapper().writeValueAsString(book);
		
		// stub
		when(bookService.수정하기(id, book)).thenReturn(new Book(1L, "스프링제목수정", "작성자"));
		
		// when
		ResultActions resultAction = mockMvc.perform(put("/book/{id}", id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		// then
		resultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("스프링제목수정"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void delete_테스트() throws Exception {
		// given
		Long id = 1L;
		
		// stub
		when(bookService.삭제하기(id)).thenReturn("ok");
		
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
