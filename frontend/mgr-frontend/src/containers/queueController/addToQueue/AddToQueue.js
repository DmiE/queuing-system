import React, { Component } from 'react';
import axios from 'axios';


class AddToQueue extends Component {
    state = {
        queueName: ""
    }

    addToQueueHandler = (event) => {
        event.preventDefault();
        axios.put('http://' + this.props.ipAddress + ':5000/api/queues', {queueName: this.state.queueName}, { headers: { Authorization: this.props.token }})
            .then(response => {
                console.log(response)
            })
    }

    changeHandler = (event) => {
        let joinQueueName = { ...this.state.queueName }
        joinQueueName = event.target.value
        this.setState({ queueName: joinQueueName })
    }

    render() {

        return (
            <div>
                <form onSubmit={this.addToQueueHandler}>
                    <input type="text" id="addToQueueName" placeholder="Type name of queue you want to join" onChange={this.changeHandler} />
                    <button type="submit">Join Queue</button>
                </form>
            </div>
        )
    }
}


export default AddToQueue;