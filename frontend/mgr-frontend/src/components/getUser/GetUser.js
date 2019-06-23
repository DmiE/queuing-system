import React from 'react';
import { connect } from 'react-redux';

const getUser = (props) => {
    return (
        <div>
            {props.authorizationToken}
            {props.ipAddr}
        </div>
    );
}

const mapStateToProps = state => {
    return {
        authorizationToken: state.authToken,
        ipAddr: state.ipAddr
    };
};

export default connect(mapStateToProps)(getUser);