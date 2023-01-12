import React from 'react';
import styled from 'styled-components';

// styled-components => js파일과 css파일 관리!!
const FooterList = styled.div`
  border: 1px solid black;
  height: 300px;
`;

const Footer = () => {
  return (
    <FooterList>
      <ul>
        <li>오시는길: 서울 강남구....</li>
        <li>전화번호 : 02222222</li>
      </ul>
    </FooterList>
  );
};

export default Footer;
