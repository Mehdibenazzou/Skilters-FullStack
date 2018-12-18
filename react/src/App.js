import React, { Component } from 'react';
import {BrowserRouter as Router,Route, Switch} from 'react-router-dom';
import Users from './Users.js';
import Accueil from './Accueil.js';
import Login from './Login.js'
import Register from './Register.js';
import {Provider} from 'react-redux'
import store from './store'
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./Actions/types";
import { logout } from "./Actions/securityActions";
import SecureRoute from "./securityUtils/SecureRoute";

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
          <Route exact path="/" component={Login} />
          <Route exact path="/register" component={Register} />
          <Switch>
            <SecureRoute exact path="/accueil" component={Accueil} />
            <SecureRoute exact path="/users" component={Users} />
          </Switch>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
