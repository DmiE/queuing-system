import React, { Component } from 'react';
import { Route } from 'react-router-dom';

import SignUp from '../SignUp/SignUp';
import SignIn from '../SignIn/SignIn';
import GetAllUsers from '../getAllUsers/GetAllUsers';
import GetUser from '../getUser/GetUser';
import QueueController from '../queueController/QueueController';
import AdminController from '../adminController/adminController';

class Main extends Component {
    render () {
        return (
            <section>
                <Route path="/signup" exact component={SignUp}/>
                <Route path="/signin" exact component={SignIn}/>
                <Route path="/getallusers" exact component={GetAllUsers}/>
                <Route path="/getuserinfo" exact component={GetUser}/>
                <Route path="/queuecontroller" exact component={QueueController}/>
                <Route path="/admincontroller" exact component={AdminController}/>
            </section>
        )
    }
}

export default Main