import React from 'react';
import { connect } from 'redux';


const logoutButton = (props) => {
    props.resetAuthToken()
    props.resetEmailAddress()
    props.resetAdminUser()
    props.history.push('/signin')
}


const mapDispatchToProps = dispatch => {
    return {
        resetAuthToken: () => dispatch({ type: "RESETAUTHTOKEN" }),
        resetEmailAddress: () => dispatch({ type: "RESETEMAIL" }),
        resetAdminUser: () => dispatch({ type: "RESETADMINUSER" })
    };
};

export default connect(null, mapDispatchToProps)(logoutButton);