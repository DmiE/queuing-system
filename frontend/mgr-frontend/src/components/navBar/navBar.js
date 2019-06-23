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
                    <li><Link to="/getallusers">GET ALL USERS</Link></li>
                    <li><Link to="/getuserinfo">GET USER INFO</Link></li>
                </ul>
            </nav>
        </header>
    );
}

export default navBar;