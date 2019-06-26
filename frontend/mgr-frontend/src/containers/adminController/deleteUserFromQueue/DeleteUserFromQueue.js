import React, { Component } from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';

class DeleteUserFromQueue extends Component {
    state = {
        inputs: {
            emailOfUserToDelete: "",        
            deleteFromQueueName: ""
        }
    }

    addToQueueHandler = (event) => {
        event.preventDefault();
        console.log(this.state.inputs)
        const deleteInfo = {
            email: this.state.inputs.emailOfUserToDelete,
            queueName: this.state.inputs.deleteFromQueueName
        }
        axios.delete('http://' + this.props.ipAddress + ':5000/admin/queues/user', { 
                data: deleteInfo,
                headers: { Authorization: this.props.token }})
            .then(response => {
                console.log(response)
            })
    }

    changeHandler = (event) => {
        const deleteFromQueue = { ...this.state.inputs }
        const objectId = event.target.id
        deleteFromQueue[objectId] = event.target.value
        this.setState({ inputs: deleteFromQueue })
    }

    render() {

        return (
            <div>
                <h2>Delete User from Queue</h2>
                <form onSubmit={this.addToQueueHandler}>
                    <input className={mainClasses.AppInput} type="email" id="emailOfUserToDelete" placeholder="Type user email" onChange={this.changeHandler} />
                    <input className={mainClasses.AppInput} type="text" id="deleteFromQueueName" placeholder="Type name of queue" onChange={this.changeHandler} />
                    <button className={mainClasses.AppButton} type="submit">Delete from queue</button>
                </form>
            </div>
        )
    }
}


export default DeleteUserFromQueue;