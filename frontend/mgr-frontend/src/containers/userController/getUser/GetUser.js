import React, { Component } from 'react';
import axios from 'axios';

class getUser extends Component {

    state = {
        user: {
            id: null,
            firstName: "",
            lastName: "",
            email: ""
        },
        email: ""
    }
    
    
    getUserHandler = (event) => {
        event.preventDefault();
        axios.get('http://' + this.props.ipAddress + ':5000/api/users/' + this.state.email, { headers: { Authorization: this.props.token } })
            .then((response) => {
                const newUser = {...this.state.user}
                newUser.email = response.data.email
                newUser.firstName = response.data.firstName
                newUser.lastName = response.data.lastName
                newUser.id = response.data.id
                this.setState({ user: newUser });
            })
    }

    changeHandler = (event) => {
        const searchingUser = event.target.value
        this.setState({email: searchingUser})
    }

    render() {
        return (
            <div>
                <h1>Get User Info</h1>
                <form onSubmit={this.getUserHandler}>
                    <input type="email" id="getUserEmail" placeholder="Type user E-mail" onChange={this.changeHandler} />
                    <button type='submit'>Get User Info</button>
                </form>
                {this.state.user.firstName}
                {/* TUTAJ MOZNA DODAC OBIEKT KTORY PRZED WYSLANIEM GETA BEDZIE FORMEM A PO BEDZIE WYNIKIEMY MOZNA WROCIC PO KLIKNIECIU PRZYCISKU BACK */}
            </div>
        );
    }
}

export default getUser;