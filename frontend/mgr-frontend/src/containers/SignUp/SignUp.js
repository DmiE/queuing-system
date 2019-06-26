import React, { Component } from 'react';
import { connect } from 'react-redux';
import axios from 'axios';

import classes from './SignUp.css';
import mainClasses from '../../App.css';

class SignUp extends Component {
    state = {
        inputs: {
            firstName: '',
            lastName: '',
            eMail: '',
            password: ''
        }
    }

    registerHandler = (event) => {
        event.preventDefault();
        const formData = {
            email: this.state.inputs.eMail,
            firstName: this.state.inputs.firstName,
            lastName: this.state.inputs.lastName,
            password: this.state.inputs.password
        }
        axios.post('http://' + this.props.ipAddr + ':5000/api/auth/signup', formData)
            .then(response => {
                console.log(response)
            })
    }

    changeHandler = (event) => {
        const updatedStateInputs = { ...this.state.inputs };
        const inputId = event.target.id;
        updatedStateInputs[inputId] = event.target.value;
        this.setState({ inputs: updatedStateInputs });
    }

    render() {

        return (
            <div>
                <form className={mainClasses.SignForm} onSubmit={this.registerHandler}>
                    <input className={mainClasses.AppInput} type="text" id="firstName" placeholder="Firstname" value={this.state.inputs.firstName} onChange={this.changeHandler} />
                    <input className={mainClasses.AppInput} type="text" id="lastName" placeholder="Lastname" value={this.state.inputs.lastName} onChange={this.changeHandler} />
                    <input className={mainClasses.AppInput} type="text" id="eMail" placeholder="E-Mail" value={this.state.inputs.eMail} onChange={this.changeHandler} />
                    <input className={mainClasses.AppInput} type="password" id="password" placeholder="Password" value={this.state.inputs.password} onChange={this.changeHandler} />
                    <button className={mainClasses.AppButton} type="submit">SignUp</button>
                    <h1>{this.props.authorizationToken}</h1>
                </form>
            </div>
        )
    }
}

const mapStateToProps = state => {
    return {
        ipAddr: state.ipAddr
    };
};

export default connect(mapStateToProps)(SignUp);