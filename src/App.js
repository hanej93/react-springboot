import { useEffect, useMemo, useState } from 'react';
import './App.css';

function App() {
  const [list, setList] = useState([1, 2, 3, 4]);
  const [str, setStr] = useState('합계');

  const getAddResult = () => {
    console.log('SUM 함수실행됨');
    return list.reduce((a, b) => a + b);
  };

  const addResult = useMemo(() => getAddResult(), [list]);

  return (
    <>
      <button
        onClick={() => {
          setStr('안녕');
        }}
      >
        문자변경
      </button>
      <button
        onClick={() => {
          setList([...list, 10]);
        }}
      >
        리스트값 추가
      </button>
      <div>
        {list.map((v) => (
          <h1>{v}</h1>
        ))}
      </div>
      <div>
        {str} : {addResult}
      </div>
    </>
  );
}

export default App;
