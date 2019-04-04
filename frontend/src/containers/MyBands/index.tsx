import React, { Component } from 'react';
import SearchIcon from '@material-ui/icons/Search';
import authenticate from '../../util/authenticate';
import { Artist } from '../../dto/common/Artist';
import { History } from 'history';
import Button from '@material-ui/core/Button';
import { apiGet } from '../../util/api';
import { ArtistSearch } from '../../dto/ArtistSearch';
import { styles } from './styles';
import { withStyles } from '@material-ui/core';
import SimpleModalWrapped from '../../components/SimpleModal';
import myImage from '../../images/azsgc_text_white_lg.jpg';
import { ArtistEntity } from '../../dto/ArtistEntity';

const initialArtistsState: ArtistEntity[] = [];
class MyBands extends Component<MyBandsProps, MyBandsState> {

  state = {
    error: false,
    artists: initialArtistsState,
  };
  // async componentDidMount() {
  //   let authorized;
  //   try {
  //     authorized = await authenticate(localStorage['token']);
  //   } finally {
  //     if (authorized !== undefined) {
  //       const { isLoggedIn, error } = authorized;
  //       if (isLoggedIn) {
  //         this.setState({ isLoading: false });
  //       } else if (error) {
  //         this.props.history.push('/login');
  //         localStorage.removeItem('token');
  //       }
  //     } else {
  //       this.props.history.push('/login');
  //       localStorage.removeItem('token');
  //     }
  //   }
  // };

  componentDidMount() {
    apiGet<ArtistEntity[]>('/songkick/my-bands').then(artists => {
      this.setState(({ artists }))
    })
  }


  render() {
    const { classes } = this.props;
    const { artists } = this.state;
    return (
      <div className={classes.bottom} style={{ display: 'flex', flexDirection: 'row', justifyContent: 'center', color: 'black' }}>
        {!!artists.length && artists.map(artist =>
          <div className={classes.thumbnail} key={artist.id} style={{ display: 'flex', flexDirection: 'column', margin: 30, }}>
            <h3 style={{ textAlign: 'center' }}>{artist.name}</h3>
            <img src={artist.imageUrl} style={{ background: 'black' }} />
            <Button> Follow Artist </Button>
          </div>
        )}
      </div>
    );
  }
}
// @ts-ignore d
export default withStyles(styles)(MyBands);

interface Classes {
  main: string
  paper: string
  avatar: string
  form: string
  submit: string
  bottom: string
  thumbnail: string
  searchIcon: string
  inputInput: string
  search: string
};

interface MyBandsState {
  artists: ArtistEntity[]
}

interface MyBandsProps {
  classes: Classes
}
