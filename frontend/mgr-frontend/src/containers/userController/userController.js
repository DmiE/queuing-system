import React, { Component } from 'react';
import { connect } from 'react-redux';

import GetAllUsers from './getAllUsers/GetAllUsers';
import DeleteUser from './deleteUser/deleteUser';
import GetUser from './getUser/GetUser';

class UserController extends Component {
    render() {
        return (
            <div>
                <GetAllUsers ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
                <GetUser ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
                <DeleteUser ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
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