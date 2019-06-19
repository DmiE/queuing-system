import React from 'react';
import './App.css';
import Register from './containers/Register/Register'
import SingIn from './containers/SignIn/SignIn'

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Register></Register>
        <SingIn></SingIn>
      </header>
    </div>
  );
}

export default App;
