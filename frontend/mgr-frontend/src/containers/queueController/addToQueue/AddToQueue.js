import React, { Component } from 'react';
import axios from 'axios';

import mainClasses from '../../../App.css';
import ReactAux from '../../../hoc/ReactAux/ReactAux';

class AddToQueue extends Component {
    state = {
        queueName: "",
        value: "1"
    }

    addToQueueHandler = (event) => {
        event.preventDefault();
        axios.put('http://' + this.props.ipAddress + ':5000/api/queues', { queueName: this.state.queueName }, { headers: { Authorization: this.props.token } })
            .then(response => {
                this.props.showSuccessBar("you succesfully joined to " + this.state.queueName)
            })
    }

    changeHandler = (event) => {
        console.log(event.target.value)
        let joinQueueName = event.target.value
        this.setState({ queueName: joinQueueName, value: joinQueueName })
    }

    render() {

        let defauleValue = <option value="1" disabled hidden>Sign In to see queues</option>

        if (this.props.token) { defauleValue = <option value="1" disabled hidden>Select Queue</option> }

        return (
            <ReactAux>
                <div>
                    <form onSubmit={this.addToQueueHandler}>
                        <select value={this.state.value} className={mainClasses.AppSelect} onChange={this.changeHandler}>
                            {defauleValue}
                            {this.props.allQueuesNames.map(queue => (<option key={queue} value={queue}>{queue}</option>))}
                        </select>
                        <button className={mainClasses.AppButton} type="submit">Join Queue</button>
                    </form>
                </div>
            </ReactAux>
        )
    }
}


export default AddToQueue;