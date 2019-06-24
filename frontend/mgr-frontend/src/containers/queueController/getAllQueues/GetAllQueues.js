import React, { Component } from 'react';
import axios from 'axios';

class getAllQueues extends Component {
    state = {
        error: false,
        queues: []
    }

    componentDidMount() {
        axios.get('http://' + this.props.ipAddress + ':5000/api/queues', { headers: { Authorization: this.props.token } })
            .then((response) => {
                console.log(response)
                this.setState({queues: response.getAllQueueResponse})
            })
            .catch(error => this.setState({error: true}))
    }

    
    render() {

        let queuesDisplay = (<div>{this.state.queues}</div>)
    
        if (this.state.error) {
            queuesDisplay = (<h1>Please SignIn</h1>)
        }

        return (
            <div>
                {queuesDisplay}
            </div>
        )
    }
}

export default getAllQueues;