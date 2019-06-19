import React, { Component } from 'react';
import axios from 'axios';

class SignIn extends Component {
    state = {
        loginInfo: {
            email: "",
            password: ""
        },
        accessToken: ""
    }


    changeHandler = (event) => {
        const newStateLoginInfo = { ...this.state.loginInfo }
        const objectId = event.target.id
        newStateLoginInfo[objectId] = event.target.value
        this.setState({ loginInfo: newStateLoginInfo })
    }

    submitHandler = (event) => {
        event.preventDefault();
        const formData = {
            password: this.state.loginInfo.password,
            usernameOrEmail: this.state.loginInfo.email
        }

        axios.post('http://192.168.0.25:5000/api/auth/signin', formData)
            .then((response) => {
                const newAccessToken = ("Bearer " + response.data.accessToken)
                this.setState({ accessToken: newAccessToken })
            })
    }

    getAllUsers = () => {
        axios.get('http://192.168.0.25:5000/api/user/getAll', { headers: { Authorization: this.state.accessToken } })
            .then((response) => {
                const users = response.data.users
                console.log(users)
            }
            )
    }

    render() {
        return (
            <div>
                <form onSubmit={this.submitHandler}>
                    <input type="email" id="email" placeholder="Your E-Mail" onChange={this.changeHandler} />
                    <input type="password" id="password" placeholder="Your Password" onChange={this.changeHandler} />
                    <button type="submit">Login</button>
                </form>
                <button onClick={this.getAllUsers}></button>
            </div>
        )
    }
}

export default SignIn;