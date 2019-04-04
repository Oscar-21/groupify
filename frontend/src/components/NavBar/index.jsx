import React, { Component } from 'react';
import AppBar from '@material-ui/core/AppBar';
import Button from '@material-ui/core/Button';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import styles from './styles';
import { Link } from 'react-router-dom';

class NavBar extends Component {
  render() {

    // @ts-ignore d
    const { classes, userAuthenticated } = this.props;


    return (
      <nav>
        <AppBar position="static" color="default" className={classes.appBar}>
          <Toolbar>
            <Typography variant="h6" color="inherit" noWrap className={classes.toolbarTitle}>
              Band Alert
            </Typography>
            <Link to="/my-bands"><Button>My Bands</Button></Link>
            <Link to="/upcoming-events"><Button>Upcoming Events</Button></Link>

            <Link to="/register" style={{ marginLeft: 12 }}>
              <Button color="primary" variant="outlined">
                Sign up
              </Button>
            </Link>

            <Link to="/login" style={{ marginLeft: 12 }}>
              <Button color="primary" variant="outlined">
                {userAuthenticated ? 'Log out' : 'Log in'}
              </Button>
            </Link>

          </Toolbar>
        </AppBar>
      </nav>
    );
  }
}

// @ts-ignore d
export default withStyles(styles)(NavBar);
