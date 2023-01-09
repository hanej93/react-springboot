import './App.css';
import { useState } from 'react';
import Sub from './Sub';

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

function App() {
  console.log('앱 실행됨');

  const [num, setNum] = useState(5);

  let sample = [
    { id: 1, name: '홍길동' },
    { id: 2, name: '임꺽정' },
    { id: 3, name: '장보고' },
    { id: 4, name: '코스' },
  ];

  const [users, setUsers] = useState(sample); // 레퍼런스 변경되야 동작!!

  const download = () => {
    //fetch().then().then()
    setUsers([...sample, { id: num, name: '조자룡' }]);
    setNum(num + 1);
  };

  // 랜더링 시점 = 상태값 변경
  return (
    <>
      <div>
        <button onClick={download}>다운로드</button>
        {users.map((u) => {
          return (
            <h1>
              {u.id} : {u.name}
            </h1>
          );
        })}
      </div>
    </>
  );
}

export default App;
