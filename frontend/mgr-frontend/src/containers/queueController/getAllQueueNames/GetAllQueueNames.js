import React, { Component } from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';
import classes from './GetAllQueueNames.css'

class GetAllQueueNames extends Component {
    state = {
        queues: [],
        choosenQueue: ""
    }

    componentDidMount() {
        axios.get('http://' + this.props.ipAddress + ':5000/api/queues/queueNames', { headers: { Authorization: this.props.token } })
            .then((response) => {
                const newQueues = response.data.queueNames;
                this.setState({ queues: newQueues });
                this.props.loadQueues(this.state.queues);
            })
    }

    chooseQueueHandler = (target) => {
        this.props.clickedQueue(target);
    }

    render() {

        let QueueNames = <h1>Sign in to see queues list</h1>

        if (this.props.token) {
            QueueNames = (
                <ul>
                    {this.state.queues.map(queue => (
                        <li key={queue} onClick={() => this.chooseQueueHandler({queue})}>Queue Name: {queue}</li>
                    ))}
                </ul>)
        }

        return (
            <div>
                <h1>LIST OF AVAILABLE QUEUES</h1>
                <div className={classes.QueuesTable}>
                    {QueueNames}
                </div>
            </div>
        )
    }
}

export default GetAllQueueNames;