import { createRef, useEffect, useMemo, useRef, useState } from 'react';
import './App.css';

// useRef (디자인)
// dom을 변경할 때 사용

function App() {
  const myRef = useRef(null);

  const [list, listSet] = useState([
    { id: 1, name: '길동' },
    { id: 2, name: '꺽정' },
  ]);

  const myRefs = Array.from({ length: list.length }).map(() => createRef());

  return (
    <div>
      <button
        onClick={() => {
          console.log(myRef);
          // myRef.current.style.backgroundColor = 'red';

          myRefs[0].current.style.backgroundColor = 'red';
        }}
      >
        색 변경
      </button>
      <div ref={myRef}>박스</div>
      {list.map((user, idx) => (
        <h1 ref={myRefs[idx]}>{user.name}</h1>
      ))}
    </div>
  );
}

export default App;
