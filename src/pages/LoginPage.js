import React from 'react';
import { useNavigate } from 'react-router-dom';
import Login from '../components/login/Login';

const LoginPage = () => {
  const navigate = useNavigate();
  return (
    <>
      <button onClick={() => navigate('/')}>뒤로가기</button>
      <Login />
    </>
  );
};

export default LoginPage;
