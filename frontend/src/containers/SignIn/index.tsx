import React, { Component } from 'react';
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
import { styles } from './styles';
import { ArtistSearch } from '../../dto/ArtistSearch';
import { Artist } from '../../dto/common/Artist';
import { History } from 'history';

class SignIn extends Component<SignInProps, SignInState> {

  state = {
    email: '',
    password: '',
    error: false,
  };

  handleChange = (e: any) => {
    // @ts-ignore
    this.setState({ [e.target.name]: e.target.value });
  };

  authLogin = (e: any) => {
    e.preventDefault();
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

    const { classes } = this.props;
    const { email, password, error } = this.state;

    return (
      <div className={classes.main}>

        <CssBaseline />

        <Paper className={classes.paper}>

          <Avatar className={classes.avatar}>
            <LockOutlinedIcon />
          </Avatar>

          <Typography component="h1" variant="h5">
            Sign in
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
              onClick={this.authLogin}
            >
              Sign in
            </Button>
            {error ? <div className='alert alert-danger mr-3'>Invalid email or password.</div> : null}

          </form>
        </Paper>
      </div>
    );
  }
}
// @ts-ignore
export default withStyles(styles)(SignIn);

interface Classes {
  main: string
  paper: string
  avatar: string
  form: string
  submit: string
  bottom: string
};

interface SignInState {
  email: string
  password: string
  error: boolean
}

interface SignInProps {
  classes: Classes,
  history: History
}
