import React, { Component } from 'react';
import { connect } from 'react-redux';

import GetAllQueues from './getAllQueues/GetAllQueues';

class QueueController extends Component {
    render() {
        return (
            <div>
                <GetAllQueues ipAddress={this.props.ipAddr} token={this.props.authorizationToken}/>
            </div>
        )
    }
}

const mapStateToProps = state => {
    return {
        authorizationToken: state.authToken,
        ipAddr: state.ipAddr
    };
};

export default connect(mapStateToProps)(QueueController);