import { fade } from '@material-ui/core/styles/colorManipulator';
import { relative } from 'path';

const styles = (theme: any) => ({

  main: {
    width: 'auto',
    display: 'block', // Fix IE 11 issue.
    marginLeft: theme.spacing.unit * 3,
    marginRight: theme.spacing.unit * 3,
    [theme.breakpoints.up(400 + theme.spacing.unit * 3 * 2)]: {
      width: 400,
      marginLeft: 'auto',
      marginRight: 'auto',
      color: 'red'
    },
  },
  paper: {
    marginTop: theme.spacing.unit * 8,
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    padding: `${theme.spacing.unit * 2}px ${theme.spacing.unit * 3}px ${theme.spacing.unit * 3}px`,
  },
  avatar: {
    margin: theme.spacing.unit,
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing.unit,
  },
  submit: {
    marginTop: theme.spacing.unit * 3,
  },
  bottom: {
    flexWrap: 'wrap',
    background: 'white'
  },
  thumbnail: {
    '&:hover': {
      cursor: 'pointer'
    }
  },
  search: {
    position: 'relative',
    borderRadius: theme.shape.borderRadius,
    marginTop: 60,
    marginLeft: 'auto',
    marginRight: 'auto',
    width: 80,
    [theme.breakpoints.up('sm')]: {
      marginLeft: '30%',
      marginRight: 'auto',
      width: '50%',
    },
  },
  searchIcon: {
    width: theme.spacing.unit * 9,
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  inputInput: {
    paddingTop: theme.spacing.unit,
    paddingRight: theme.spacing.unit,
    paddingBottom: theme.spacing.unit,
    paddingLeft: theme.spacing.unit * 10,
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('md')]: {
      width: 'inherit',
    },
  },
});


export { styles };