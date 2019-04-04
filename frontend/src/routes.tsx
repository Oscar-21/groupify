import React from "react";
import { Route, Switch } from "react-router";
import Home from './containers/Home';
import SignUp from './containers/SignUp';
import SignIn from './containers/SignIn';
import MyBands from "./containers/MyBands";
import UpcomingEvents from "./containers/UpcomingEvents";

const Routes = () =>
  <Switch>
    <Route
      exact path="/"
      render={props => <Home {...props} />}
    />
    <Route
      path="/register"
      render={props => <SignUp {...props} />}
    />
    <Route
      path="/login"
      render={props => <SignIn {...props} />}
    />
    <Route
      path="/my-bands"
      render={props => <MyBands {...props} />}
    />
    <Route
      path="/upcoming-events"
      render={props => <UpcomingEvents {...props} />}
    />
  </Switch>

export default Routes;
