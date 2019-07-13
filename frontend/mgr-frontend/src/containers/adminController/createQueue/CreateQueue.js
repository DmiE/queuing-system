import React, { Component } from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';

class CreateQueue extends Component {
    state = {
        queueName: ""
    }

    createQueueHandler = (event) => {
        event.preventDefault();
        axios.post('http://' + this.props.ipAddress + ':5000/admin/queues', {queueName: this.state.queueName}, { headers: { Authorization: this.props.token }})
            .then(response => {
                console.log(response)
                this.props.showSuccessBar("New Queue created sucesfully")
            })
    }

    changeHandler = (event) => {
        let newQueueName = { ...this.state.queueName }
        newQueueName = event.target.value
        this.setState({ queueName: newQueueName })
    }

    render() {

        return (
            <div>
                <form onSubmit={this.createQueueHandler}>
                    <input className={mainClasses.AppInput} type="text" id="queueName" placeholder="Type Queue Name" onChange={this.changeHandler} />
                    <button className={mainClasses.AppButton} type="submit">Create Queue</button>
                </form>
            </div>
        )
    }
}


export default CreateQueue;