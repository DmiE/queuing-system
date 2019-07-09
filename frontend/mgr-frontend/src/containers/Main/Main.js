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

class Main extends Component {
    render() {
        return (
            <ReactAux>
                <section className={classes.MainSection}>
                    <NavBar></NavBar>
                    <section className={classes.ContentSection}>
                        {/* <Route path="/" exact component={} /> */}
                        <Route path="/signup" exact component={SignUp} />
                        <Route path="/signin" exact component={SignIn} />
                        <Route path="/usercontroller" exact component={UserController} />
                        <Route path="/queuecontroller" exact component={QueueController} />
                        <Route path="/admincontroller" exact component={AdminController} />
                    </section>
                </section>
            </ReactAux>
        )
    }
}

export default Main