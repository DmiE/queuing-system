import React from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';

const deleteFromQueueHandler = (props) => {

    axios.delete('http://' + props.ipAddress + ':5000/api/queues/user', { headers: { Authorization: props.token } })
        .then(response => {
            console.log(response)
        })
}

const DeleteFromQueue = (props) => {
    return (
        <div>
            <h1>DELETE ME FROM QUEUE</h1>
            <button className={mainClasses.AppButton} onClick={() => deleteFromQueueHandler(props)}>DELETE</button>
        </div>
    )
}

export default DeleteFromQueue;