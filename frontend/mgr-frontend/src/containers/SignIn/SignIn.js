import React, { Component } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';

import mainClasses from '../../App.css'
import ReactAux from '../../hoc/ReactAux/ReactAux';
import classes from './SignIn.css';

class SignIn extends Component {
    state = {
        loginInfo: {
            email: "",
            password: ""
        }
    }

    // componentDidUpdate() {
    //     if (this.state.)
    // }


    changeHandler = (event) => {
        const newStateLoginInfo = { ...this.state.loginInfo }
        const objectId = event.target.id
        newStateLoginInfo[objectId] = event.target.value
        this.setState({ loginInfo: newStateLoginInfo })
    }

    logOut = () => {
        this.setState({ifLogedIn: ""})
        this.props.resetAuthToken()
        this.props.resetEmailAddress()
    }

    getUserData = (email) => {
        axios.get('http://' + this.props.ipAddr + ':5000/api/users/' + email, { headers: { Authorization: this.props.authorizationToken } })
        .then((response) => {
            this.props.setAdminUser(response.data.role.includes("ROLE_ADMIN"))
        })
    }

    submitHandler = (event) => {
        event.preventDefault();
        const formData = {
            password: this.state.loginInfo.password,
            usernameOrEmail: this.state.loginInfo.email
        }
        axios.post('http://' + this.props.ipAddr + ':5000/api/auth/signin', formData)
        .then((response) => {
            const newAccessToken = ("Bearer " + response.data.accessToken)
            this.props.setAuthToken(newAccessToken);
            this.props.setEmailAddress(formData.usernameOrEmail)
            this.getUserData(this.state.loginInfo.email)
            this.props.history.push('/')
        })
    }

    render() {

        let logedIn = ( 
            <form className={mainClasses.SignForm} onSubmit={this.submitHandler}>
            <input className={mainClasses.AppInput} type="text" id="email" placeholder="Your E-Mail" value={this.state.loginInfo.email} onChange={this.changeHandler} />
            <input className={mainClasses.AppInput} type="password" id="password" placeholder="Your Password" value={this.state.loginInfo.password} onChange={this.changeHandler} />
            <button className={mainClasses.AppButton} type="submit">Login</button>
        </form>)
        
        if (this.props.authorizationToken) {
            logedIn = (
                <ReactAux>
                    <h1>You are logged in !</h1>
                    <button className={mainClasses.AppButton} onClick={this.logOut}>Log out</button>
                </ReactAux>
            )
        }
        


        return (
            <div className={classes.SignInSection}>
                {logedIn}
            </div>
        )
    }
}

const mapDispatchToProps = dispatch => {
    return {
        setAuthToken: (token) => dispatch({ type: "SETAUTHTOKEN", token: token }),
        resetAuthToken: () => dispatch({type: "RESETAUTHTOKEN"}),
        setEmailAddress: (eMail) => dispatch({type: "SETEMAIL", eMail: eMail}),
        resetEmailAddress: () => dispatch({type: "RESETEMAIL"}),
        setAdminUser: (isAdmin) => dispatch({type: 'SETADMINUSER', isAdmin: isAdmin})
    };
};

const mapStateToProps = state => {
    return {
        authorizationToken: state.authToken,
        ipAddr: state.ipAddr
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(SignIn);