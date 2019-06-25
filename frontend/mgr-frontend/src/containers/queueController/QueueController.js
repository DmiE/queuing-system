import React, { Component } from 'react';
import { connect } from 'react-redux';

import GetAllQueues from './getAllQueues/GetAllQueues';
import AddToQueue from './addToQueue/AddToQueue';
import GetQueue from './getQueue/GetQueue';
import DeleteFromQueue from './deleteFromQueue/DeleteFromQueue';
import GetAllQueueNames from './getAllQueueNames/GetAllQueueNames';


class QueueController extends Component {
    render() {
        return (
            <div>
                <GetAllQueues ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
                <AddToQueue ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
                <GetQueue ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
                <DeleteFromQueue ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
                <GetAllQueueNames ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
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

export default connect(mapStateToProps)(QueueController);