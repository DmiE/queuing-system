import React, { Component } from 'react';
import { Route } from 'react-router-dom';

import SignUp from '../SignUp/SignUp';
import SignIn from '../SignIn/SignIn';
import UserController from '../userController/userController';
import QueueController from '../queueController/QueueController';
import AdminController from '../adminController/adminController';
import classes from './Main.css'
import NavBar from '../../components/navBar/navBar';
import ReactAux from '../../hoc/ReactAux/ReactAux';
import errorHandler from '../../hoc/ErrorHandler/ErrorHandler';
import SuccessBar from '../../containers/SuccessBar/SuccessBar'

class Main extends Component {
    state = {
        successShow: null
    }
    showSuccess = (message) => {
        this.setState({ successShow: message });
        setTimeout(() => this.setState({ successShow: null}), 3000);
    }

    clicked = () => {
        this.setState({ successShow: null })
    }

    render() {
        let successBar = this.state.successShow ? <SuccessBar successMessage={this.state.successShow} clicked={this.clicked}/> : null

        return (
            <ReactAux>
                <section className={classes.MainSection}>
                    <NavBar></NavBar>
                    {successBar}
                    <section className={classes.ContentSection}>
                        {/* <Route path="/" exact component={} /> */}
                        {/* <Route path="/signup" exact component={SignUp} /> */}
                        {/* <Route path="/signin" exact component={SignIn} /> */}
                        {/* <Route path="/usercontroller" exact component={UserController} /> */}
                        {/* <Route path="/queuecontroller" exact component={QueueController} /> */}
                        {/* <Route path="/admincontroller" exact component={AdminController} /> */}
                        <Route path="/signup" render={(props) => <SignUp {...props} showSuccessBar={(message) => this.showSuccess(message)}/>} />
                        <Route path="/signin" render={(props) => <SignIn {...props} showSuccessBar={(message) => this.showSuccess(message)}/>} />
                        <Route path="/usercontroller" render={(props) => <UserController {...props} showSuccessBar={(message) => this.showSuccess(message)}/>} />
                        <Route path="/queuecontroller" exact render={(props) => <QueueController {...props} showSuccessBar={(message) => this.showSuccess(message)}/>} />
                        <Route path="/admincontroller" exact render={(props) => <AdminController {...props} showSuccessBar={(message) => this.showSuccess(message)}/>} />
                    </section>
                </section>
            </ReactAux>
        )
    }
}

export default errorHandler(Main);