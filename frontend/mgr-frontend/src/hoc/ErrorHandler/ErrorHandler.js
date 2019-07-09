import React, {Component} from 'react'

class ErrorHandler extends Component {
    constructor (props) {
        super(props)
        this.state = { error:null }
    }

    componentDidCatch (error, errorInfo) {
        console.log(error);
        console.log('dupaBobra');
        console.log(errorInfo);
        this.setState({error: error})
    }

    render () {
        if (this.state.error !== null) {
            return (
                <h1>upsss something sie zjebalo</h1>
            )
        } else {
            return this.props.children
        }
        
    }
}

export default ErrorHandler