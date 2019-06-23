import React, { Component } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';

class GetAllUsers extends Component {
    state = {
        users: []
    }

    getUsers = () => {
        // axios.get('http://192.168.0.25:5000/api/user/getAll', { headers: { Authorization: this.props.authorizationToken } })
        axios.get('http://' + this.props.ipAddr + ':5000/api/user/getAll', { headers: { Authorization: this.props.authorizationToken } })
            .then((response) => {
                const newUsers = response.data.users
                console.log(newUsers)
                this.setState({users: newUsers});
            })
    }

    render() {
        return (
            <div>
                <h1>GET USERS</h1>
                <button onClick={this.getUsers}>SHOW ALL USERS</button>
                {this.state.users.map(user => (
                    <h1 key={user.id}>first name: {user.firstName} last name: {user.lastName}</h1>
                ))}
            </div>
        )
    }
}



const mapStateToProps = state => {
    return {
        authorizationToken: state.authToken,
        ipAddr: state.ipAddr
    };
};

export default connect(mapStateToProps)(GetAllUsers);