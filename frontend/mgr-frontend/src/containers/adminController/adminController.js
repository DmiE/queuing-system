import React, { Component } from 'react';
import { connect } from 'react-redux';

import CreateQueue from './createQueue/CreateQueue';
import CreateAdminUser from './createAdminUser/createAdminUser';
import DeleteUserFromQueue from './deleteUserFromQueue/DeleteUserFromQueue';


class AdminController extends Component {
    state = {
        refresh: false
    }

    refreshView = () => {
        this.setState({refresh: !this.state.refresh})
    }

    render() {
        return (
            <div>
                <CreateQueue 
                    ipAddress={this.props.ipAddr} 
                    token={this.props.authorizationToken} 
                    showSuccessBar={(message) => this.props.showSuccessBar(message)}
                    updateView = {this.refreshView}
                    refresh={this.state.refresh}/>
                <CreateAdminUser 
                    ipAddress={this.props.ipAddr} 
                    token={this.props.authorizationToken} 
                    showSuccessBar={(message) => this.props.showSuccessBar(message)}/>
                <DeleteUserFromQueue 
                    ipAddress={this.props.ipAddr} 
                    token={this.props.authorizationToken} 
                    showSuccessBar={(message) => this.props.showSuccessBar(message)}
                    updateView = {this.refreshView}
                    refresh={this.state.refresh}/>
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