import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import GetUser from './components/getUser/GetUser';

import './App.css';
import NavBar from './components/navBar/navBar';
import Main from './containers/Main/Main'

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <NavBar></NavBar>
        <Main></Main>
        <GetUser/>
      </div>
    </BrowserRouter>
  );
}

export default App;
