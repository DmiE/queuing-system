import React, { Component } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';

class SignIn extends Component {
    state = {
        loginInfo: {
            email: "",
            password: ""
        }
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

        // axios.post('http://192.168.0.25:5000/api/auth/signin', formData)
        axios.post('http://' + this.props.ipAddr + ':5000/api/auth/signin', formData)
            .then((response) => {
                const newAccessToken = ("Bearer " + response.data.accessToken)
                this.props.setAuthToken(newAccessToken);
            })
    }

    render() {
        return (
            <div>
                <form onSubmit={this.submitHandler}>
                    <input type="email" id="email" placeholder="Your E-Mail" onChange={this.changeHandler} />
                    <input type="password" id="password" placeholder="Your Password" onChange={this.changeHandler} />
                    <button type="submit">Login</button>
                    <h1>{this.props.authorizationToken}</h1>
                </form>
            </div>
        )
    }
}

const mapDispatchToProps = dispatch => {
    return {
        setAuthToken: (token) => dispatch({ type: "SETAUTHTOKEN", token: token })
    };
};

const mapStateToProps = state => {
    return {
        authorizationToken: state.authToken,
        ipAddr: state.ipAddr
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(SignIn);