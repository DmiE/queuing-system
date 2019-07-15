import React, { Component } from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';

class DeleteUserFromQueue extends Component {
    state = {
        allQueues: [],
        choosenQueue: '1',
        allNames: [],
        choosenName: '1',
        refresh: false
    }

    componentWillReceiveProps (props) {
        if (this.state.refresh !== props.refresh) {
            this.getAllQueues()
            this.setState({refresh: props.refresh})
        }
    }

    componentDidMount() {
        this.getAllQueues()
    }

    getAllQueues = () => {
        if (this.props.token) {
            axios.get('http://' + this.props.ipAddress + ':5000/api/queues/queueNames', { headers: { Authorization: this.props.token } })
                .then((response) => {
                    this.setState({ allQueues: response.data.queueNames })
                })
        }
    }

    getAllUsersFromQueue = (choosenQueue) => {
        axios.get('http://' + this.props.ipAddress + ':5000/api/queues/' + choosenQueue, { headers: { Authorization: this.props.token } })
            .then((response) => {
                this.setState({ allNames: response.data.userInQueue, choosenQueue: choosenQueue })
            })
            .catch((error) => {
                this.setState({ allNames:[], choosenQueue: choosenQueue })
            })
    }

    deleteFromQueueHandler = (event) => {
        event.preventDefault();
        const deleteInfo = {
            email: this.state.choosenName,
            queueName: this.state.choosenQueue
        }
        axios.delete('http://' + this.props.ipAddress + ':5000/admin/queues/user', {
            data: deleteInfo,
            headers: { Authorization: this.props.token }
        })
            .then(response => {
                this.setState({ state: this.state })
                this.props.showSuccessBar("You deleted " + this.state.choosenName + " user from " + this.state.choosenQueue + " queue")
                this.getAllUsersFromQueue(this.state.choosenQueue)
            })
    }

    queueChangeHandler = (event) => {
        this.getAllUsersFromQueue(event.target.value)
    }

    userChangeHandler = (event) => {
        let choosenName = event.target.value
        let oldState = { ...this.state }
        oldState.choosenName = choosenName
        this.setState(oldState)
    }

    render() {

        let defauleValue = <option value="1" disabled>Select Queue</option>
        let defaultValueUser = <option value="1" disabled>Select queue to see users</option>
        if (this.state.choosenQueue !== '1') { defaultValueUser = <option value="1">Select User</option> }

        return (
            <div>
                <h2>Delete User from Queue</h2>
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