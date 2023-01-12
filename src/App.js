import styled from 'styled-components';
import './App.css';

// useRef (디자인)
// dom을 변경할 때 사용

const Title = styled.h1`
  font-size: 1.5em;
  text-align: center;
  color: palevioletred;
`;

function App() {
  return (
    <div>
      <Title>헬로</Title>
    </div>
  );
}

export default App;
