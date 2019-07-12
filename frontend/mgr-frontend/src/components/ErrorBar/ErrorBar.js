import React from 'react';

import classes from './ErrorBar.css'

const errorBar = (props) => {

    let errorMessage = null

    if (props.error === 400) {errorMessage = (<h1>Fields are empty!</h1>)}
    else if (props.error === 500) {errorMessage = (<h1>Queue is empty!</h1>)}
    else {errorMessage = (<h1>ERROR: {props.errorInfo.data.message}</h1>)}



    console.log(props.errorInfo.data.message)

    return (
        <div 
        onClick={props.clicked}
        className={classes.errorBar} 
        style={{
            transform: props.error ? 'translateY(0)' : 'translateY(-100wh)',
            opacity: props.error ? '1' : '0'
        }}>
            {errorMessage}
        </div>
        
    )
}

export default errorBar;