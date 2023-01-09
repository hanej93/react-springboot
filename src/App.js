import logo from './logo.svg';
import './App.css';

// 0. React 엔진 - 데이터 변경 감지에서 UI 그려주는!!
// 1. 실행과정(index.html) -> SPA(Single Page Application)
// 2. JSX 문법

// (1) ruturn 시에 하나의 DOM만 리턴할 수 있다.
// (2) 변수선언은 let 혹은 const로만 해야함
// (3) if 사용 불가능 -> 삼항연산자
// (4) 조건부 렌더링 (조건) && 값
// (5) CSS 디자인
//      - 내부에 적는 방법
//      - 외부파일에 넣는 방법
//      - 라이브러리 사용(부트스트랩, component-styled)

let a = 10;
const b = 20;

function App() {
  let c;
  let d = undefined;
  console.log(1, c, d);

  const myStyle = {
    color: 'red',
  }

  return (
    <>
      <div style={myStyle}>안녕 {a === 10 ? '10입니다.' : '10이 아닙니다.'}</div>
      <h1 className='box-style'>헬로{b === 20 && '20입니다.'}</h1>
      <hr />
    </>
  );
}

export default App;
