import React from 'react';
import { BrowserRouter } from 'react-router-dom';

import './App.css';
import Register from './containers/SignUp/SignUp'
import SingIn from './containers/SignIn/SignIn'
import NavBar from './components/navBar/navBar';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <NavBar></NavBar>
        <Register></Register>
        <SingIn></SingIn>

      </div>
    </BrowserRouter>
  );
}

export default App;
