import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

const UpdateForm = () => {
  const navigate = useNavigate();

  const { id } = useParams();
  const [book, setBook] = useState({
    title: '',
    author: '',
  });

  useEffect(() => {
    fetch('http://localhost:8080/book/' + id)
      .then((res) => res.json())
      .then((res) => {
        setBook(res);
      });
  }, []);

  const chageValue = (e) => {
    setBook({
      ...book,
      [e.target.name]: e.target.value,
    });
  };

  const submitBook = (e) => {
    e.preventDefault();

    fetch('http://localhost:8080/book/' + id, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json; charset=utf-8',
      },
      body: JSON.stringify(book),
    })
      .then((res) => {
        if (res.status !== 200) {
          return null;
        }
        return res.json();
      })
      .then((res) => {
        if (res === null) {
          alert('책 수정에 실패하였습니다.');
          return;
        }
        navigate('/book/' + id);
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
          수정
        </Button>
      </Form>
    </>
  );
};

export default UpdateForm;
