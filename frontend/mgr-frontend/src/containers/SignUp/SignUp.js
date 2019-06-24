import React, { Component } from 'react';
import { connect } from 'react-redux';
import axios from 'axios';

import './SignUp.css';
import Input from '../../components/input/Input';

class SignUp extends Component {
    state = {
        inputs: {
            firstName: {
                elementConfig: {
                    type: 'text',
                    name: 'firstName',
                    placeholder: 'First Name'
                },
                value: ''
            },
            lastName: {
                elementConfig: {
                    type: 'text',
                    name: 'lastName',
                    placeholder: 'Last Name'
                },
                value: ''
            },
            eMail: {
                elementConfig: {
                    type: 'email',
                    name: 'email',
                    placeholder: 'E-mail Address'
                },
                value: ''
            },
            password: {
                elementConfig: {
                    type: 'text',
                    name: 'password',
                    placeholder: 'Password'
                },
                value: ''
            }
        }
    }

    registerHandler = (event) => {
        event.preventDefault();
        const formData = {
            email: this.state.inputs.eMail.value,
            firstName: this.state.inputs.firstName.value,
            lastName: this.state.inputs.lastName.value,
            password: this.state.inputs.password.value
        }
        axios.post('http://' + this.props.ipAddr + ':5000/api/auth/signup', formData)
            .then(response => {
                console.log(response)
            })
    }

    inputHandler = (event, inputId) => {
        const updatedStateInputs = { ...this.state.inputs };
        const updatedStateInputsElement = { ...updatedStateInputs[inputId] }

        updatedStateInputsElement.value = event.target.value;
        updatedStateInputs[inputId] = updatedStateInputsElement;

        this.setState({ inputs: updatedStateInputs });
    }

    render() {
        const inputArray = [];
        for (let key in this.state.inputs) {
            inputArray.push({
                id: key,
                config: this.state.inputs[key]
            })
        }


        return (
            <div>
                <form onSubmit={this.registerHandler}>
                    {inputArray.map(inputObject => (
                        <Input key={inputObject.id} elementConfig={inputObject.config.elementConfig} value={inputObject.config.value} changed={(event) => this.inputHandler(event, inputObject.id)} />
                    ))}
                    <button type="submit">SignUp</button>
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