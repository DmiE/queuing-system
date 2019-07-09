import React, { Component } from 'react';
import whiteLogo from '../../assets/personwhite_logo.png';
import { withRouter } from 'react-router-dom';

import classes from './Logo.css';

class logo extends Component {

    logoClick = () => {
        this.props.history.push("/")
    }

    render() {
        return (
            <div className={classes.logo} onClick={this.logoClick}>
                <img src={whiteLogo} />
            </div>
        )
    }

}

export default withRouter(logo);