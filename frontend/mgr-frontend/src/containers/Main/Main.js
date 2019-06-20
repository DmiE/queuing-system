import React, { Component } from 'react';
import { Route } from 'react-router-dom';

import SignUp from '../SignUp/SignUp'
import SignIn from '../SignIn/SignIn'
import GetAllUsers from '../../components/getAllUsers/GetAllUsers'

class Main extends Component {
    render () {
        return (
            <section>
                <Route path="/signup" exact component={SignUp}/>
                <Route path="/signin" exact component={SignIn}/>
                <Route path="/getallusers" exact component={GetAllUsers}/>
            </section>
        )
    }
}

export default Main