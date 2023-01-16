import 'bootstrap/dist/css/bootstrap.min.css';
import { Route, Routes } from 'react-router-dom';
import Navigation from './components/Navigation';
import ListPage from './pages/ListPage';
import WritePage from './pages/WritePage';

// 글쓰기, 글삭제, 글목록보기

function App() {
  return (
    <>
      <Navigation />
      <Routes>
        <Route path="/" exact element={<ListPage />} />
        <Route path="/write" exact element={<WritePage />} />
      </Routes>
    </>
  );
}

export default App;
