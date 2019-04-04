import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Modal from '@material-ui/core/Modal';
import styles from './styles';
import { Button } from '@material-ui/core';

class SimpleModal extends Component {


  render() {
    const { 
      classes, 
      open, 
      handleClose, 
      addArtist,
      artistId,
      artistName
    } = this.props;

    return (
        <Modal
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description"
          open={open}
          onClose={handleClose}
        >
          <div style={{ 
            top: '50%',
            left: '50%',
          }} className={classes.paper}>
            <Button onClick={() => addArtist(artistId, artistName)}>
              <Typography variant="h6" id="modal-title">
                Add Artist to you list.
              </Typography>
            </Button>
            <button onClick={handleClose}>X</button>
          </div>
        </Modal>
    );
  }
}

// We need an intermediary variable for handling the recursive nesting.
const SimpleModalWrapped = withStyles(styles)(SimpleModal);

export default SimpleModalWrapped;