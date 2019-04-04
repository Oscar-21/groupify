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

const initialArtistsState: Artist[] = [];
class Home extends Component<HomeProps, HomeState> {

  state = {
    error: false,
    artistSearchQuery: 'dylan',
    artists: initialArtistsState,
    page: 0,
    maxPage: 0,
    displayResults: false,
    openModal: false,
    artistId: 0,
    artistName: '',
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


  // @ts-ignore d
  componentDidUpdate(prevProps, prevState) {
    window.scrollTo(0, 150);
  }

  handleChange = (e: any) => {
    // @ts-ignore
    this.setState({ [e.target.name]: e.target.value });
  };

  handleOpen = (artistId: number, artistName: string) => {
    this.setState({ openModal: true, artistId, artistName });
  };

  handleClose = () => {
    this.setState({ openModal: false });
  };

  artistSearch = () => {
    const artistSearch = apiGet<ArtistSearch>(`/songkick/artist-search?artist=${this.state.artistSearchQuery}`);
    artistSearch.then((artistSearch: ArtistSearch) => {
      const perPage = artistSearch.resultsPage.perPage;
      const page = artistSearch.resultsPage.page;
      const totalEntries = artistSearch.resultsPage.totalEntries;
      const maxPage = Math.ceil(Math.abs(totalEntries / perPage));
      this.setState(() => ({ page, maxPage }))
      if (artistSearch.resultsPage.totalEntries > 0) {
        const artists: Artist[] = [];
        artistSearch.resultsPage.results.artist.forEach((artist: Artist) => {
          artists.push(artist);
        });
        this.setState(() => ({ artists, displayResults: true }))
      }

    });
  };

  artistSearchNextPage = () => {
    let { artistSearchQuery, maxPage, page } = { ...this.state };
    page++;
    if (page > maxPage) {
      return;
    }
    const artistSearch = apiGet<ArtistSearch>(`/songkick/artist-search-next-page?page=${page}`);
    artistSearch.then((artistSearch: ArtistSearch) => {

      const page = artistSearch.resultsPage.page;

      this.setState(() => ({ page }))
      if (artistSearch.resultsPage.totalEntries > 0) {
        const artists: Artist[] = [];
        artistSearch.resultsPage.results.artist.forEach((artist: Artist) => {
          artists.push(artist);
        });
        this.setState(() => ({ artists }))
      }

    });
  };


  addArtist = (artistId: number, artistName: string) => {
    apiGet<void>(`/songkick/add-artist?artistId=${artistId}&artistName=${artistName}`)
  };

  render() {
    const { classes } = this.props;
    const {
      page,
      artists,
      maxPage,
      displayResults,
      openModal,
      artistId,
      artistName
    } = this.state;
    return (
      <div>
        <img src={myImage} />
        <div className={classes.search}>
            <div className={classes.searchIcon}>
              <SearchIcon />
            </div>
            <input
              name="artistSearchQuery"
              className={classes.inputInput}
              type="text"
              placeholder="Search…"
              onChange={this.handleChange}
            />
        </div>
        <Button
          style={{ marginLeft: '40%' }}
          variant="contained"
          color="primary"
          className={classes.submit}
          onClick={this.artistSearch}
        >
          Search For Bands
        </Button>


        <div className={classes.bottom}
          style={{
            display: displayResults ? 'flex' : 'none',
            flexDirection: 'row',
            justifyContent: 'center',
            color: 'black'
          }}
        >
          {!!artists.length && artists.map(artist =>
            <div
              className={classes.thumbnail}
              key={artist.id}
              style={{
                display: 'flex',
                flexDirection: 'column',
                margin: 30,
              }}
            >
              <h3 style={{ textAlign: 'center' }}>{artist.displayName}</h3>
              <img
                src={`https://images.sk-static.com/images/media/profile_images/artists/${artist.id}/huge_avatar`}
                style={{
                  background: 'black',
                }}
              />
              <Button onClick={() => this.handleOpen(artist.id, artist.displayName)}>
                Follow Artist
              </Button>
            </div>
          )}
        </div>
        {page !== maxPage &&
          <Button
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={this.artistSearchNextPage}
          >
            Next Page
          </Button>
        }
        <SimpleModalWrapped
          open={openModal}
          handleClose={this.handleClose}
          addArtist={this.addArtist}
          artistId={artistId}
          artistName={artistName}
        />
      </div>
    );
  }
}
// @ts-ignore d
export default withStyles(styles)(Home);

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

interface HomeState {
  error: boolean
  artistSearchQuery: string
  artists: Artist[]
  page: number
  maxPage: number
  displayResults: boolean
  openModal: boolean
  artistId: number
  artistName: string
}

interface HomeProps {
  history: History,
  classes: Classes
}
