import React, { Component } from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';

class DeleteUserFromQueue extends Component {
    state = {
        allQueues: [],
        choosenQueue: '1',
        allNames: [],
        choosenName: '1',
        value: 1
    }

    componentDidMount() {
        if (this.props.token) {
            axios.get('http://' + this.props.ipAddress + ':5000/api/queues/queueNames', { headers: { Authorization: this.props.token } })
                .then((response) => {
                    this.setState({ allQueues: response.data.queueNames })
                    console.log(this.state.allQueues)
                })
        }
    }

    deleteFromQueueHandler = (event) => {
        event.preventDefault();
        const deleteInfo = {
            email: this.state.choosenName,
            queueName: this.state.choosenQueue
        }
        console.log(deleteInfo)
        axios.delete('http://' + this.props.ipAddress + ':5000/admin/queues/user', {
            data: deleteInfo,
            headers: { Authorization: this.props.token }
        })
            .then(response => {
                console.log(response)
            })
    }

    queueChangeHandler = (event) => {
        let choosenQueue = event.target.value
        axios.get(
            'http://' + this.props.ipAddress + ':5000/api/queues/' + choosenQueue,
            { headers: { Authorization: this.props.token } })
            .then((response) => {
                let newState = { 
                ...this.state,
                choosenQueue: choosenQueue,
                allNames: response.data.userInQueue,
                value: choosenQueue }
                this.setState(newState);
            })
        }

    userChangeHandler = (event) => {
        let choosenName = event.target.value
        this.setState({ choosenName: choosenName })
    }

    render() {

        let defauleValue = <option value="1" disabled hidden>Select Queue</option>
        let defaultValueUser = <option value="1" disabled hidden>Select queue to see users</option>
        if (this.state.choosenQueue !== '1') {defaultValueUser =  <option value="1" disabled hidden>Select User</option>}

        console.log(this.state.allNames)

        return (
            <div>
                <h2>Delete User from Queue</h2>
                {/* <form onSubmit={this.deleteFromQueueHandler}>
                    <input className={mainClasses.AppInput} type="email" id="emailOfUserToDelete" placeholder="Type user email" onChange={this.changeHandler} />
                    <input className={mainClasses.AppInput} type="text" id="deleteFromQueueName" placeholder="Type name of queue" onChange={this.changeHandler} />
                    <button className={mainClasses.AppButton} type="submit">Delete from queue</button>
                </form> */}
                <form onSubmit={this.deleteFromQueueHandler}>
                    <select value={this.state.choosenQueue} className={mainClasses.AppSelect} onChange={(event) => this.queueChangeHandler(event)}>
                        {defauleValue}
                        {this.state.allQueues.map(queue => (<option key={queue} value={queue}>{queue}</option>))}
                    </select>
                    <select value={this.state.choosenName} className={mainClasses.AppSelect} onChange={(event) => this.userChangeHandler(event)}>
                        {defaultValueUser}
                        {this.state.allNames.map(user => (<option key={user.email} value={user.email}>{user.email}</option>))}
                    </select>
                    <button className={mainClasses.AppButton} type="submit">Delete From Queue</button>
                </form>
            </div>
        )
    }
}


export default DeleteUserFromQueue;