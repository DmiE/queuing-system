import React from 'react';
import axios from 'axios';


const deleteUserHandler = (props) => {

    axios.delete('http://' + props.ipAddress + ':5000/api/users', { headers: { Authorization: props.token } })
        .then(response => {
            console.log(response)
        })
}

const deleteUser = (props) => {
    return (
        <div>
            <h2>Delete User</h2>
            <button onClick={() => deleteUserHandler(props)}>DELETE</button>
        </div>
    )
}

export default deleteUser;