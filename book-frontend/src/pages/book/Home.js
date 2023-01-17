import React, { useEffect, useState } from 'react';
import BookItem from '../../components/BookItem';

const Home = () => {
  const [books, setBooks] = useState([]);

  // 함수 실행시 최초 한번 실행되는 것 useEffect
  //  + 상태값이 변경될 때마다 실행
  // ※ 의존성이 없을 경우 무한 실행
  useEffect(() => {
    // javascript 비동기 함수
    fetch('http://localhost:8080/book', {
      method: 'GET',
    })
      .then((res) => res.json())
      .then((res) => {
        setBooks(res);
      });
  }, []);

  return (
    <div>
      {books.map((book, idx) => (
        <BookItem key={book.id} book={book} />
      ))}
    </div>
  );
};

export default Home;
