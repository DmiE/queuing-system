import React, { Component } from 'react';
import axios from 'axios';

class getAllQueues extends Component {
    state = {
        error: false,
        queues: []
    }
    //trzeba przerobic zeby do stanu trafialy tylko nazwy 

    componentDidMount() {
        axios.get('http://' + this.props.ipAddress + ':5000/api/queues', { headers: { Authorization: this.props.token } })
            .then((response) => {
                console.log(response)
                this.setState({queues: response.data.getAllQueueResponse})
            })
            .catch(error => this.setState({error: true}))
    }

    
    render() {

        // let queuesDisplay = {this.state.queues.map(queue => (
        //     <h1 key={queue.id}>queue name: {queue.queueName}</h1>
        // ))}
    
        // if (this.state.error) {
        //     queuesDisplay = (<h1>Please SignIn</h1>)
        // }

        console.log(this.state.queues)

        return (
            <div>
                <h1>List of Queues</h1>
                {this.state.queues.map(queue => (<h6 key={queue.queueID}>queue name: {queue.queueName}</h6>))}
            </div>
        )
    }
}

export default getAllQueues;