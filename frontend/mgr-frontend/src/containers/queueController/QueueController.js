import React, { Component } from 'react';
import { connect } from 'react-redux';

import AddToQueue from './addToQueue/AddToQueue';
import GetQueue from './getQueue/GetQueue';
import DeleteFromQueue from './deleteFromQueue/DeleteFromQueue';
import GetAllQueueNames from './getAllQueueNames/GetAllQueueNames';
import classes from './QueueController.css';
import ReactAux from '../../hoc/ReactAux/ReactAux';

class QueueController extends Component {
    state = {
        queuesNames: [],
        choosenQueue: "",
        refresh: true
    }

    refreshView = () => {
        this.setState({refresh: !this.state.refresh})
    }

    loadAllQueues = (allQueues) => {
        this.setState({ queuesNames: allQueues })
    }

    clickedQueueHandler = (targetQueue) => {
        this.setState({ choosenQueue: targetQueue })
        console.log(targetQueue)
    }

    render() {

        return (
            <ReactAux>
                <div className={classes.QueueControllerMain}>
                    <div>
                        <GetAllQueueNames
                            ipAddress={this.props.ipAddr}
                            token={this.props.authorizationToken}
                            loadQueues={(names) => this.loadAllQueues(names)}
                            clickedQueue={(choosen) => this.clickedQueueHandler(choosen)} /></div>
                    <div>
                        <GetQueue
                            ipAddress={this.props.ipAddr}
                            token={this.props.authorizationToken}
                            choosen={this.state.choosenQueue} 
                            refresh={this.state.refresh}/>
                    </div>
                </div>
                <div>
                    <AddToQueue
                        ipAddress={this.props.ipAddr}
                        token={this.props.authorizationToken}
                        allQueuesNames={this.state.queuesNames}
                        showSuccessBar={(message) => this.props.showSuccessBar(message)}
                        updateView = {this.refreshView} />
                    <DeleteFromQueue
                        ipAddress={this.props.ipAddr}
                        token={this.props.authorizationToken}
                        showSuccessBar={(message) => this.props.showSuccessBar(message)}
                        updateView = {this.refreshView} />
                </div>
            </ReactAux>
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