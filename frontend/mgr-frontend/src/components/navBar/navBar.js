import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

import classes from './navBar.css'
import ReactAux from '../../hoc/ReactAux/ReactAux';

const navBar = (props) => {


    let navBar = (
            <ReactAux>
                <nav className={classes.navBar}>
                    <ul className={classes.signInMenu}>
                        <Link to="/signup"><li className={classes.signUp}>SIGNUP</li></Link>
                        <Link to="/signin"><li className={classes.signIn}>SIGNIN</li></Link>
                    </ul>
                </nav>
            </ReactAux>)

        if (props.authorizationToken && props.eMailAddress) {
            navBar = (
                <ReactAux>
                <nav className={classes.navBar}>
                    <ul className={classes.signInMenu}>
                        <Link to="/usercontroller"><li>User Panel</li></Link>
                        <Link to="/queuecontroller"><li>Queues Panel</li></Link>
                    </ul>
                </nav>
            </ReactAux>)

            if (props.isAnAdmin) {
                navBar = (
                    <ReactAux>
                    <nav className={classes.navBar}>
                        <ul className={classes.signInMenu}>
                            <Link to="/usercontroller"><li>User Panel</li></Link>
                            <Link to="/queuecontroller"><li>Queues Panel</li></Link>
                            <Link to="/admincontroller"><li>Admin Panel</li></Link>
                        </ul>
                    </nav>
                </ReactAux>)
            }
        }

        return (
            <header>
                {navBar}
            </header>
        );
    }


const mapStateToProps = state => {
    return {
        authorizationToken: state.authToken,
        ipAddr: state.ipAddr,
        eMailAddress: state.eMailAddress,
        isAnAdmin: state.isAnAdmin
    };
};

export default connect(mapStateToProps)(navBar);