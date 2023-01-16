import React, { useState } from 'react';
import styled from 'styled-components';

const StyledItemBoxDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px solid black;
  padding: 10px;
  height: 100px;
  margin: 20px;
`;

const ListPage = () => {
  const [no, setNo] = useState(6);

  const [post, setPost] = useState({
    id: no,
    title: '',
    content: '',
  });

  const [posts, setPosts] = useState([
    { id: 1, title: '제목1', content: '내용1' },
    { id: 2, title: '제목2', content: '내용2' },
    { id: 3, title: '제목3', content: '내용3' },
    { id: 4, title: '제목4', content: '내용4' },
    { id: 5, title: '제목5', content: '내용5' },
  ]);

  const handleWrite = (e) => {
    e.preventDefault(); // form태그가 하려는 액션을 중지
    setPosts([...posts, post]);
    setNo(no + 1);
    setPost({ ...post, id: no });
  };

  const handleForm = (e) => {
    // compute property names (key 값을 동적할당)
    setPost({ ...post, [e.target.name]: e.target.value, id: no });
    console.log(`제목: ${post.title} // 내용: ${post.content}`);
  };

  return (
    <div>
      <h1>리스트페이지</h1>
      <form onSubmit={handleWrite}>
        <input type="text" placeholder="제목을 입력하세요.." value={post.title} name="title" onChange={handleForm} />
        <input type="text" placeholder="내용을 입력하세요.." value={post.content} name="content" onChange={handleForm} />
        <button type="submit">글쓰기</button>
      </form>
      <hr />
      {posts.map((post) => (
        <StyledItemBoxDiv key={post.id}>
          <div>
            NO: {post.id} TITLE: {post.title} CONTENT: {post.content}
          </div>
          <button>삭제</button>
        </StyledItemBoxDiv>
      ))}
    </div>
  );
};

export default ListPage;
