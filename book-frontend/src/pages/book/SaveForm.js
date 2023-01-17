import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

const SaveForm = () => {
  const navigate = useNavigate();

  const [book, setBook] = useState({
    title: '',
    author: '',
  });

  const chageValue = (e) => {
    setBook({
      ...book,
      [e.target.name]: e.target.value,
    });
  };

  const submitBook = (e) => {
    e.preventDefault();

    fetch('http://localhost:8080/book', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json; charset=utf-8',
      },
      body: JSON.stringify(book),
    })
      .then((res) => {
        if (res.status !== 201) {
          return null;
        }
        return res.json();
      })
      .then((res) => {
        if (res === null) {
          alert('책 등록에 실패하였습니다.');
          return;
        }
        navigate('/');
      });
  };

  return (
    <>
      <Form onSubmit={submitBook}>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>제목</Form.Label>
          <Form.Control type="text" placeholder="제목을 입력해주세요." name="title" value={book.title} onChange={chageValue} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>저자</Form.Label>
          <Form.Control type="text" placeholder="저자를 입력해주세요." name="author" value={book.author} onChange={chageValue} />
        </Form.Group>
        <Button variant="primary" type="submit">
          등록
        </Button>
      </Form>
    </>
  );
};

export default SaveForm;
