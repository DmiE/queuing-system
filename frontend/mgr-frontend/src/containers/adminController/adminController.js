import React, { Component } from 'react';
import { connect } from 'react-redux';

import CreateQueue from './createQueue/CreateQueue';
import CreateAdminUser from './createAdminUser/createAdminUser';
import DeleteUserFromQueue from './deleteUserFromQueue/DeleteUserFromQueue';


class AdminController extends Component {
    render() {
        return (
            <div>
                <CreateQueue ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
                <CreateAdminUser ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
                <DeleteUserFromQueue ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
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

export default connect(mapStateToProps)(AdminController);