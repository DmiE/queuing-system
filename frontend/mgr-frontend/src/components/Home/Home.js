import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';

import ReactAux from '../../hoc/ReactAux/ReactAux';
import classes from './Home.css';

const home = (props) => {

    let mainPage = (
        <ReactAux>
            <h1>Welcome in our queue app</h1>
            <h3>Please <Link  to="/signup" className={classes.link}>SingUp</Link>, or <Link  to="/signin" className={classes.link}>SingIn</Link> if you have an account</h3>
        </ReactAux>
    )
    
    if (props.authorizationToken) {

    }



    return (
        <div className={classes.HomePage}>
            {mainPage}
        </div>
)

}

const mapStateToProps = state => {
    return {
        authorizationToken: state.authToken,
        ipAddr: state.ipAddr
    };
};

export default connect(mapStateToProps)(home)