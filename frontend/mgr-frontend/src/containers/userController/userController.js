import React, { Component } from 'react';
import { connect } from 'react-redux';

import GetAllUsers from './getAllUsers/GetAllUsers';
import DeleteUser from './deleteUser/deleteUser';
import GetUser from './getUser/GetUser';
import classes from './userController.css'

class UserController extends Component {
    render() {
        return (
            <div className={classes.UserControllerMain}>
                <div className={classes.UserControllerChild}>
                    <GetAllUsers ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
                </div>
                <div className={classes.UserControllerChild}>
                    {/* <GetUser ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/> */}
                    <DeleteUser ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
                </div>
            </div>
        )
    }
}

const mapStateToProps = state => {
    return {
        authorizationToken: state.authToken,
        ipAddr: state.ipAddr
    };
};

export default connect(mapStateToProps)(UserController);