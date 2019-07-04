import React, { Component } from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';
import ReactAux from '../../../hoc/ReactAux/ReactAux';
import { thisExpression } from '@babel/types';

class GetQueue extends Component {

    state = {
        queueName: "",
        userInQueue: []
    }

    componentDidUpdate() {
        if (this.state.queueName !== this.props.choosen.queue) {
            let newState = this.state;
            axios.get('http://' + this.props.ipAddress + ':5000/api/queues/' + this.props.choosen.queue, { headers: { Authorization: this.props.token } })
                .then((response) => {
                    newState = {
                        queueName: this.props.choosen.queue,
                        userInQueue: response.data.userInQueue
                    }
                    this.setState(newState)
                })
        }
    }

    render() {
        console.log(this.state.userInQueue)

        let queueInfo = (<h1>choose queue to see details</h1>)

        if (this.state.queueName && this.state.userInQueue.length > 0) {
            let rows = []

            for (let i = 0; i < this.state.userInQueue.length; i++) {
                rows.push(
                    <tr key={this.state.userInQueue[i].id}>
                        <td>{i + 1}</td>
                        <td>{this.state.userInQueue[i].firstName}</td>
                        <td>{this.state.userInQueue[i].lastName}</td>
                    </tr>)
            }


            queueInfo = (
                <ReactAux>
                    <h1>{this.state.queueName}</h1>
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
                    </table>
                </ReactAux>
            )
        }

        return (
            <div>
                <h1>GET QUEUE INFO</h1>
                <div>
                    {queueInfo}
                </div>
            </div>
        );
    }
}

export default GetQueue;