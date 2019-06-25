import React, { Component } from 'react';
import axios from 'axios';

class GetAllQueueNames extends Component {
    state = {
        queues: []
    }

    getQueues = () => {
        axios.get('http://' + this.props.ipAddress + ':5000/api/queues/queueNames', { headers: { Authorization: this.props.token } })
            .then((response) => {
                // const new = response.data.users
                // console.log(newUsers)
                // this.setState({users: newUsers});
                console.log(response);
            })
    }

    render() {
        return (
            <div>
                <h1>GET LIST OF ALL QUEUES</h1>
                <button onClick={this.getQueues}>SHOW ALL QUEUES</button>
                {this.state.queues.map(queue => (
                    <h1>dupa</h1>
                    // <h1 key={user.id}>first name: {user.firstName} last name: {user.lastName}</h1>
                ))}
            </div>
        )
    }
}

export default GetAllQueueNames;