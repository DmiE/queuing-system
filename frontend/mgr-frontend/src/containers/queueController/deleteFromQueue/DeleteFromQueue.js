import React from 'react';
import axios from 'axios';


const deleteFromQueueHandler = (props) => {

    axios.delete('http://' + props.ipAddress + ':5000/api/queues/user', { headers: { Authorization: props.token } })
        .then(response => {
            console.log(response)
        })
}

const DeleteFromQueue = (props) => {
    return (
        <div>
            <h2>Delete Me from Queue</h2>
            <button onClick={() => deleteFromQueueHandler(props)}>DELETE</button>
        </div>
    )
}

export default DeleteFromQueue;