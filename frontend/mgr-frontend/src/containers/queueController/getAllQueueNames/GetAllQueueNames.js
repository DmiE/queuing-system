import React, { Component } from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';
import classes from './GetAllQueueNames.css'

class GetAllQueueNames extends Component {
    state = {
        queues: []
    }

    componentDidMount() {
        axios.get('http://' + this.props.ipAddress + ':5000/api/queues/queueNames', { headers: { Authorization: this.props.token } })
            .then((response) => {
                const newQueues = response.data.queueNames
                this.setState({ queues: newQueues });
                this.props.loadQueues(this.state.queues);
            })
    }

    render() {

        let QueueNames = <h1>Sign in to see user list</h1>

        if (this.props.token) {
            QueueNames = (
                <ul>
                    {this.state.queues.map(queue => (
                        <li key={queue}>Queue Name: {queue}</li>
                    ))}
                </ul>)
        }

        return (
            <div>
                <h1>GET LIST OF ALL QUEUES</h1>
                <div className={classes.QueuesTable}>
                    {QueueNames}
                </div>
            </div>
        )
    }
}

export default GetAllQueueNames;