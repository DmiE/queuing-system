import React, { Component } from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';

class GetQueue extends Component {

    state = {
        queueName: "",
        userInQueue: []
    }
    //trzeba przerobic zeby do stanu trafialy tylko nazwy 
    
    getQueueHandler = (event) => {
        event.preventDefault();
        axios.get('http://' + this.props.ipAddress + ':5000/api/queues/' + this.state.queueName, { headers: { Authorization: this.props.token } })
            .then((response) => this.setState({userInQueue: response.data.userInQueue}))
    }

    changeHandler = (event) => {
        const searchingQueue = event.target.value
        this.setState({queueName: searchingQueue})
    }

    render() {
        return (
            <div>
                <h1>GET QUEUE INFO</h1>
                <form onSubmit={this.getQueueHandler}>
                    <input className={mainClasses.AppInput} type="text" id="getQueueName" placeholder="Type queue name" onChange={this.changeHandler} />
                    <button className={mainClasses.AppButton} type='submit'>Get Queue Info</button>
                </form>
                {this.state.userInQueue.map(user => (<h6 key={user.id}>Firstname: {user.firstName} Lastname: {user.lastName}</h6>))}
            </div>
        );
    }
}

export default GetQueue;