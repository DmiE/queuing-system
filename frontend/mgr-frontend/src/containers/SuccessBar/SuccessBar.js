import React, { Component } from 'react';

import classes from './SuccessBar.css'
import ReactAux from '../../hoc/ReactAux/ReactAux'


class SuccessBar extends Component {
    state = {
    }

    // componentDidUpdate() {
    //     console.log(this.state.message)
    //     if (this.state.message !== this.state.success) {
    //         this.showSuccess(this.state.message)
    //         console.log('update')
    //     }
    // }

    render() {
        let successMessage = <h1>{this.props.successMessage}</h1>;

        return (
            <ReactAux>
                <div
                    onClick={this.props.clicked}
                    className={classes.successBar}
                    style={{
                        transform: this.props.successMessage ? 'translateY(0)' : 'translateY(-100wh)',
                        opacity: this.props.successMessage ? '1' : '1'
                    }}>
                    {successMessage}
                </div>
            </ReactAux>

        )
    }
}

export default SuccessBar;