import React from 'react';
import { Link } from 'react-router-dom';

const navBar = () => {
    return (
        <header>
            <nav>
                <ul>
                    <li><Link to="/">HOME</Link></li>
                    <li><Link to="/signup">SIGNUP</Link></li>
                    <li><Link to="/signin">SIGNIN</Link></li>
                    <li><Link to="/usercontroller">USER CONTROLLER</Link></li>
                    <li><Link to="/queuecontroller">QUEUE CONTROLLER</Link></li>
                    <li><Link to="/admincontroller">ADMIN CONTROLLER</Link></li>
                </ul>
            </nav>
        </header>
    );
}

export default navBar;