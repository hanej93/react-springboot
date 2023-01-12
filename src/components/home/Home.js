import React from 'react';
import styled from 'styled-components';

const StyledDeleteButton = styled.button`
  color: ${(props) => (props.user.username === 'ssar' ? 'blue' : 'red')};
`;

const StyledAddButton = styled(StyledDeleteButton)`
  background-color: green;
`;

// Function 방식
const Home = (props) => {
  //   const boards = props.boards;

  // 구조분할 할당
  const { boards, setBoards, user } = props;

  return (
    <div>
      <h1>홈페이지</h1>
      <StyledAddButton user={user}>더하기</StyledAddButton>
      <StyledDeleteButton user={user} onClick={() => setBoards([])}>
        전체삭제
      </StyledDeleteButton>
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
