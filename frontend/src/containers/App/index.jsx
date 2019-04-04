import React, { Component } from 'react';
import Routes from '../../routes';
import NavBar from '../../components/NavBar';

class App extends Component {
  state = {
    userAuthenticated: false,
  };
  login = () => this.setState({ userAuthenticated: true }); 
  render() {
    return (
      <div>
      <NavBar userAuthenticated={this.state.userAuthenticated} />
      <Routes login={this.login} />
      </div>
    );
  }
}
export default App;
