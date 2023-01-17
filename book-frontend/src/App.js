import React from 'react';
import { Container } from 'react-bootstrap';
import { Route, Routes } from 'react-router-dom';
import Header from './components/Header';
import Detail from './pages/book/Detail';
import Home from './pages/book/Home';
import SaveForm from './pages/book/SaveForm';
import UpdateForm from './pages/book/UpdateForm';
import JoinForm from './pages/user/JoinForm';
import LoginForm from './pages/user/LoginForm';

function App() {
  return (
    <>
      <Header />
      <Container>
        <Routes>
          <Route path="/" exact element={<Home />} />
          <Route path="/saveForm" exact element={<SaveForm />} />
          <Route path="/book/:id" exact element={<Detail />} />
          <Route path="/loginForm" exact element={<LoginForm />} />
          <Route path="/joinForm" exact element={<JoinForm />} />
          <Route path="/updateForm/:id" exact element={<UpdateForm />} />
        </Routes>
      </Container>
    </>
  );
}

export default App;
