import React from 'react';
import { BrowserRouter } from 'react-router-dom';

import './App.css';
import Main from './containers/Main/Main'

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Main></Main>
      </div>
    </BrowserRouter>
  );
}

export default App;
