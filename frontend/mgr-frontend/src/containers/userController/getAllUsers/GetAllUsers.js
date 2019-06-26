import React, { Component } from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';
import classes from './GetAllUsers.css'

class GetAllUsers extends Component {
    state = {
        users: []
    }

    componentDidMount() {
        if (this.props.token) {
            axios.get('http://' + this.props.ipAddress + ':5000/api/users', { headers: { Authorization: this.props.token } })
                .then((response) => {
                    const newUsers = response.data.users
                    console.log(newUsers)
                    this.setState({ users: newUsers });
                })
        }
    }


    render() {

        let UserList = <h1>Sign in to see user list</h1>

        if (this.props.token) {
            UserList = (this.state.users.map(user => (
                <li key={user.id}><p>Firstname: {user.firstName}</p><p>Lastname: {user.lastName}</p></li>
            )))
        }


        return (
            <div>
                <h1>OUR USERS</h1>
                <div className={classes.UserTable}>
                    {UserList}
                </div>
            </div>
        )
    }
}

export default GetAllUsers;