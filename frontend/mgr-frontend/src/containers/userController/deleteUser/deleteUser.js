import React from 'react';
import axios from 'axios';
import { withRouter } from 'react-router-dom';

import mainClasses from '../../../App.css';

const deleteUserHandler = (props) => {

    axios.delete('http://' + props.ipAddress + ':5000/api/users', { headers: { Authorization: props.token } })
        .then(response => {
            props.showSuccessBar("Your account was succesfully deleted!")
            props.history.push('/signup')
        })
    props.resetToken()
}

const deleteUser = (props) => {
    return (
        <div>
            <h1>DELETE MY ACCOUNT</h1>
            <button className={mainClasses.AppButton} onClick={() => deleteUserHandler(props)}>DELETE</button>
        </div>
    )
}

export default withRouter(deleteUser);