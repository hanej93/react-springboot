import React from 'react';

// Function 방식
const Home = (props) => {
  //   const boards = props.boards;

  // 구조분할 할당
  const { boards, setBoards } = props;
  const { number, setNumber } = props;

  return (
    <div>
      <h1>홈{number}</h1>
      <button onClick={() => setNumber(number + 1)}>번호증가</button>
      <button onClick={() => setBoards([])}>전체삭제</button>
      {boards.map((board) => {
        return (
          <h3>
            제목: {board.title} 내용: {board.content}
          </h3>
        );
      })}
    </div>
  );
};

export default Home;
