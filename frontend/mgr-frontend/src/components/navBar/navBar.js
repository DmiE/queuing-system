import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';

import classes from './navBar.css'
import ReactAux from '../../hoc/ReactAux/ReactAux';
import Logo from '../Logo/Logo'

class navBar extends Component {

    logout = () => {
        this.props.resetAuthToken()
        this.props.resetEmailAddress()
        this.props.resetAdminUser()
        this.props.resetUserName()
        this.props.history.push('/signin')
    }


    render() {
        let welcome = this.props.userName ? <p className={classes.welcome}>Hello, {this.props.userName}</p> : null;


        let navBar = (
            <ReactAux>
                <nav className={classes.navBar}>
                    <div className={classes.logo}><Logo /></div>
                    <ul className={classes.signInMenu}>
                        <Link to="/signup"><li className={classes.signUp}>SIGNUP</li></Link>
                        <Link to="/signin"><li className={classes.signIn}>SIGNIN</li></Link>
                    </ul>
                </nav>
            </ReactAux>)

        if (this.props.authorizationToken && this.props.eMailAddress && this.props.isAnAdmin) {
            navBar = (
                <ReactAux>
                    <nav className={classes.navBar}>
                        <div className={classes.logo}><Logo /></div>
                        <ul className={classes.mainMenu}>
                            <Link to="/usercontroller"><li>User Panel</li></Link>
                            <Link to="/queuecontroller"><li>Queues Panel</li></Link>
                            <Link to="/admincontroller"><li>Admin Panel</li></Link>
                        </ul>
                        <div className={classes.logoutPanel}>
                            {welcome}
                            <button className={classes.logoutButton} onClick={this.logout}>Logout</button>
                        </div>
                    </nav>
                </ReactAux>)
        } else if (this.props.authorizationToken && this.props.eMailAddress) {
            navBar = (
                <ReactAux>
                    <nav className={classes.navBar}>
                        <div className={classes.logo}><Logo /></div>
                        <ul className={classes.mainMenu}>
                            <Link to="/usercontroller"><li>User Panel</li></Link>
                            <Link to="/queuecontroller"><li>Queues Panel</li></Link>
                        </ul>
                        <div className={classes.logoutPanel}>
                            {welcome}
                            <button className={classes.logoutButton} onClick={this.logout}>Logout</button>
                        </div>
                    </nav>
                </ReactAux>)
        }


        return (
            <header>
                {navBar}
            </header>
        );
    }
}

const mapStateToProps = state => {
    return {
        authorizationToken: state.authToken,
        ipAddr: state.ipAddr,
        eMailAddress: state.eMailAddress,
        isAnAdmin: state.isAnAdmin,
        userName: state.userName
    };
};

const mapDispatchToProps = dispatch => {
    return {
        resetAuthToken: () => dispatch({ type: "RESETAUTHTOKEN" }),
        resetEmailAddress: () => dispatch({ type: "RESETEMAIL" }),
        resetAdminUser: () => dispatch({ type: "RESETADMINUSER" }),
        resetUserName: () => dispatch({ type: "RESETUSERNAME" })
    };
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(navBar));