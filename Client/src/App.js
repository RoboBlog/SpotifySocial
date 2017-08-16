import React, { Component } from 'react';
import {
  BrowserRouter as Router,
  Route
} from 'react-router-dom';
import TopTracks from './pages/TopTracks'
//
// import { Router, Route, browserHistory } from 'react-router'
// import { syncHistoryWithStore, routerReducer } from 'react-router-redux'

import { Provider } from 'react-redux';
import reducers from './reducers'
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import Home from './components/Home';
import SignUp from './components/SignUp';
import Test from './components/Test'
import Chat from './components/Chat'
import About from './components/Text';
import Profile from './components/Profile';
import Login from './components/Login';
import AddSpotify from './components/AddSpotify'
import promise from 'redux-promise';
import LoginPage from './pages/LoginPage'
import AddMusicPortal from './pages/AddMusicPortalPage'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './assets/font-awesome/css/font-awesome.css'
import  './assets/css/style.css'
import './assets/css/style-responsive.css'
import Tracks from './pages/TracksList'
import { Link } from 'react-router-dom'
import MusicPortalStatus from './pages/MusicPortalStatusPage'
import { CookiesProvider, withCookies, Cookies } from 'react-cookie';
const store = createStore(reducers, applyMiddleware(promise));
import GetSpotifyTopTracks from './components/GetSpotifyTopTracks'
import Header from "./components/Header";
class App extends Component {



  render() {
    return (
  <CookiesProvider>
      <Provider store={store}>
      <Router>

        <div>

          <Route exact path="/" component={Home} />
          <Route path="/profile" component={Profile} />
          <Route path="/tracks" component={Tracks} />
          <Route path="/topTracks" component={TopTracks} />
          <Route path="/login" component={LoginPage} />
          <Route path="/chat" component={Chat} />
          <Route path="/addspotify" component={AddSpotify} />
          <Route path="/getspotifytoptracks" component={GetSpotifyTopTracks} />
          <Route path="/signup" component={SignUp} />
          <Route path="/test" component={Test} />
          <Route path="/about" component={About} />
          <Route path="/addmusicportal" component={AddMusicPortal} />
          <Route path="/musicportalstatus" component={MusicPortalStatus} />

        </div>
      </Router>

      </Provider>
  </CookiesProvider>
    );
  }
}

export default App;
