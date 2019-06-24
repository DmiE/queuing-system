import React, { Component } from 'react';
import axios from 'axios';


class CreateAdminUser extends Component {
    state = {
        inputs: {
            adminFirstName: "",
            adminLastName: "",
            adminEmail: "",
            adminPassword: ""
        }
    }

    createAdminHandler = (event) => {
        const AdminData = {
            email: this.state.inputs.adminEmail,
            firstName: this.state.inputs.adminFirstName,
            lastName: this.state.inputs.adminLastName,
            password: this.state.inputs.adminPassword
        }
        event.preventDefault();
        console.log(AdminData);
        axios.post('http://' + this.props.ipAddress + ':5000/admin/user', AdminData, { headers: { Authorization: this.props.token }})
            .then(response => {
                console.log(response)
            })
    }

    changeHandler = (event) => {
        let newAdminInputs = { ...this.state.inputs }
        newAdminInputs[event.target.id] = event.target.value
        this.setState({ inputs: newAdminInputs })
    }

    render() {

        return (
            <div>
                <form onSubmit={this.createAdminHandler}>
                    <input type="text" id="adminFirstName" placeholder="Admin Firstname" onChange={this.changeHandler} />
                    <input type="text" id="adminLastName" placeholder="Admin Lastame" onChange={this.changeHandler} />
                    <input type="email" id="adminEmail" placeholder="Admin E-Mail Address" onChange={this.changeHandler} />
                    <input type="password" id="adminPassword" placeholder="Admin Password" onChange={this.changeHandler} />
                    <button type="submit">Create Admin</button>
                </form>
            </div>
        )
    }
}


export default CreateAdminUser;