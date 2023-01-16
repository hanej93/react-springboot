import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import ListPage from './pages/ListPage';
import Top from './components/Top';
import Bottom from './components/Bottom';

// 글쓰기, 글삭제, 글목록보기

function App() {
  return (
    <div className="container">
      {/* <ListPage /> */}
      <h1>최상단 화면</h1>
      <Top />
      <Bottom />
    </div>
  );
}

export default App;
