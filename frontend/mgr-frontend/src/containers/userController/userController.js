import React, { Component } from 'react';
import { connect } from 'react-redux';

import GetAllUsers from './getAllUsers/GetAllUsers';
import DeleteUser from './deleteUser/deleteUser';
import classes from './userController.css'

class UserController extends Component {
    render() {
        return (
            <div className={classes.UserControllerMain}>
                <div className={classes.UserControllerChild}>
                    <GetAllUsers ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
                </div>
                <div className={classes.UserControllerChild}>
                    <DeleteUser 
                        ipAddress={this.props.ipAddr} 
                        token={this.props.authorizationToken} 
                        resetToken={this.props.resetAuthToken} 
                        showSuccessBar={(message) => this.props.showSuccessBar(message)}/>
                </div>
            </div>
        )
    }
}

const mapDispatchToProps = dispatch => {
    return {
        setAuthToken: (token) => dispatch({ type: "SETAUTHTOKEN", token: token }),
        resetAuthToken: () => dispatch({type: "RESETAUTHTOKEN"})
    };
};

const mapStateToProps = state => {
    return {
        authorizationToken: state.authToken,
        ipAddr: state.ipAddr
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(UserController);