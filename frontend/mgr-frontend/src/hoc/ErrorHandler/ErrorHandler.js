import React, { Component } from 'react'
import axios from 'axios'

import ReactAux from '../ReactAux/ReactAux';
import ErrorBar from '../../components/ErrorBar/ErrorBar';

const ErrorHandler = ( WrapperdComponent ) => {
    return class extends Component {
        state = {
            error: null,
            errorInfo: null
        }

        showError = (errorStatus, errorInfo) => {
            this.setState({error: errorStatus, errorInfo: errorInfo});
            console.log(errorStatus)
            setTimeout(() => this.setState({error: null, errorInfo: null}), 7000);
            }

        errorConfirmedHandler = () => {
            this.setState({error: null, errorInfo: null})
        }

        componentDidMount() {

            // axios.interceptors.request.use(request => {
            //     this.setState({error: null, errorInfo: null});
            //     return request;
            // });
            axios.interceptors.response.use(response => response, error => {
                this.showError(error.response.status, error.response)
                return Promise.reject(error);
            });
        }

        render() {
            let errorBar = null
            if (this.state.error) {errorBar = (<ErrorBar clicked={this.errorConfirmedHandler} error={this.state.error} errorInfo={this.state.errorInfo}/>)}

            return(
                <ReactAux>
                    {errorBar}
                    <WrapperdComponent/>
                </ReactAux>
            )
        }
    }
}

export default ErrorHandler;