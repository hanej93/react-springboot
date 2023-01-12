import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import Header from './components/Header';
import Footer from './components/Footer';
import { Route, Routes } from 'react-router-dom';

function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" exact element={<HomePage />} />
        <Route path="/login/:id" exact element={<LoginPage />} />
      </Routes>
      <Footer />
    </>
  );
}

export default App;
