import React, { Component } from 'react';
import { connect } from 'react-redux';

import GetAllQueues from './getAllQueues/GetAllQueues';
import AddToQueue from './addToQueue/AddToQueue';
import GetQueue from './getQueue/GetQueue';
import DeleteFromQueue from './deleteFromQueue/DeleteFromQueue';
import GetAllQueueNames from './getAllQueueNames/GetAllQueueNames';
import classes from './QueueController.css';


class QueueController extends Component {

    state = {
        queuesNames: []
    }

    loadAllQueues = (allQueues) => {
        this.setState({ queuesNames: allQueues })
    }

    render() {


        return (
            <div className={classes.QueueControllerMain}>
                <div><GetAllQueueNames ipAddress={this.props.ipAddr} token={this.props.authorizationToken} loadQueues={(names) => this.loadAllQueues(names)} /></div>
                <div>
                    <GetQueue ipAddress={this.props.ipAddr} token={this.props.authorizationToken} />
                    <AddToQueue ipAddress={this.props.ipAddr} token={this.props.authorizationToken} allQueuesNames={this.state.queuesNames} />
                    <DeleteFromQueue ipAddress={this.props.ipAddr} token={this.props.authorizationToken} />
                    {/* <GetAllQueues ipAddress={this.props.ipAddr} token={this.props.authorizationToken} /> */}
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

export default connect(mapStateToProps)(QueueController);