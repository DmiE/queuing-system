import React, { Component } from 'react';
import './SignUp.css';
import axios from 'axios';
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

    registerHandler = ( event ) => {
        event.preventDefault();
        const formData = {
            email: this.state.inputs.eMail.value,
            firstName: this.state.inputs.firstName.value,
            lastName: this.state.inputs.lastName.value,
            password: this.state.inputs.password.value
        }
        axios.post('http://192.168.0.25:5000/api/auth/signup', formData)
            .then( response => {
                console.log(response + "form was send")
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

export default SignUp;