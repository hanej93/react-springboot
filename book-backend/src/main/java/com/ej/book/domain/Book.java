package com.ej.book.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // 서버 실행시에 Object Relation Mapping이 됨
public class Book {
	@Id // PK를 해당 변수로 하겠음 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호증가 전략을 따라감
	private Long id;
	
	private String title;
	private String author;
	
}
