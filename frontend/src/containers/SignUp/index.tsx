import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import FormControl from '@material-ui/core/FormControl';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import withStyles from '@material-ui/core/styles/withStyles';
import styles from './styles';

class SignUp extends Component {

state = {
    email: '',
    password: '',
  };

  // @ts-ignore
  handleChange = evt => {
    this.setState({ [evt.target.name]: evt.target.value });
  };

  // @ts-ignore
  authRegister = e => {
    e.preventDefault();
    const { email , password } = this.state;
    const data = JSON.stringify({ email , password });
    fetch('/api/v1/users/sign-up', {
      headers: {
        "Content-Type": "application/json"
      },
      body: data,
      method: 'POST'
    })
      .then(response => response.json())
      .then(({ user }) => {
        if (user) {
          this.authLogin();          
        } else {
          this.setState({ error: true })
          alert("Email already taken");
        }
      })
      .catch(error => {
        console.error(error);
      })
  };

  authLogin = () => {
    const { email, password } = this.state;
    const data = JSON.stringify({ email, password });
    fetch('/api/v1/auth/login', {
      headers: {
        "Content-Type": "application/json"
      },
      body: data,
      method: 'POST'
    })
      .then(response => response.json())
      .then(({ token }) => {
        if (token) {
          localStorage.setItem('token', token);
          // @ts-ignore
          this.props.history.push('/')
        } else {
          this.setState({ error: true })
          alert("Invalid Credentials");
        }
      })
      .catch(error => {
        console.error(error);
      })
  };

  render() {

    // @ts-ignore
    const { classes } = this.props;
    // @ts-ignore
    const { email , password, error } = this.state;

    return (
      <main className={classes.main}>

        <CssBaseline />

        <Paper className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>

          <Typography component="h1" variant="h5">
            Sign up
          </Typography>

          <form className={classes.form}>

            <FormControl margin="normal" required fullWidth>
              <InputLabel htmlFor="email">Email Address</InputLabel>
              <Input
                id="email"
                name="email"
                autoComplete="email"
                autoFocus
                value={email}
                onChange={this.handleChange}
              />
            </FormControl>

            <FormControl margin="normal" required fullWidth>
              <InputLabel htmlFor="password">Password</InputLabel>
              <Input
                name="password"
                type="password"
                id="password"
                autoComplete="current-password"
                value={password}
                onChange={this.handleChange}
              />
            </FormControl>

            <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Remember me"
            />

            <Button
              fullWidth
              variant="contained"
              color="primary"
              className={classes.submit}
              onClick={this.authRegister}
            >
              Sign up
          </Button>

          {error ? <div className='alert alert-danger mr-3'>Invalid email or password.</div> : null}

          </form>

        </Paper>

      </main>
    );
  }
}
// @ts-ignore
export default withStyles(styles)(SignUp);



