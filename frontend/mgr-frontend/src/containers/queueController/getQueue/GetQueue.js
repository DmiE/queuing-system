import React, { Component } from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';
import ReactAux from '../../../hoc/ReactAux/ReactAux';
import classes from './GetQueue.css';


class GetQueue extends Component {

    state = {
        response: {
            queueName: undefined,
            userInQueue: []
        },
        error: null
    }

    componentDidUpdate() {
        if (this.state.response.queueName !== this.props.choosen.queue) {
            let newState = { ...this.state }
            axios.get('http://' + this.props.ipAddress + ':5000/api/queues/' + this.props.choosen.queue, { headers: { Authorization: this.props.token } })
                .then(response => {
                    newState = {
                        response: {
                            queueName: this.props.choosen.queue,
                            userInQueue: response.data.userInQueue
                        },
                        error: null
                    }
                    this.setState(newState);
                })
                .catch(error => {
                    if (error.response.status === 500) {
                        newState = {
                            response: {
                                queueName: this.props.choosen.queue,
                                userInQueue: []
                            },
                            error: 500
                        }
                        this.setState(newState);
                    }
                    else { console.log(error) }
                })
        }
    }

    render() {
        let queueInfo = (<h1>choose queue to see details</h1>)
        let rows = []
        let tableOfUsers

        if (this.state.response.queueName && this.state.response.userInQueue.length > 0) {


            for (let i = 0; i < this.state.response.userInQueue.length; i++) {
                rows.push(
                    <tr key={this.state.response.userInQueue[i].id}>
                        <td>{i + 1}</td>
                        <td>{this.state.response.userInQueue[i].firstName}</td>
                        <td>{this.state.response.userInQueue[i].lastName}</td>
                    </tr>)
            }

            tableOfUsers = (
                <table>
                    <thead>
                        <tr>
                            <th>Nr</th>
                            <th>Firstname</th>
                            <th>Lastname</th>
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                    </tbody>
                </table>)

        } else if (this.state.response.queueName && this.state.response.userInQueue.length === 0) {
            tableOfUsers = <h1>this queue is empty!</h1>
        }


        queueInfo = (
            <ReactAux>
                <h1>{this.state.response.queueName}</h1>
                {tableOfUsers}
            </ReactAux>
        )

        return (
            <div>
                <h1>GET QUEUE INFO</h1>
                <div className={classes.queueInfoTable}>
                    {queueInfo}
                </div>
            </div>
        );
    }
}

export default GetQueue;