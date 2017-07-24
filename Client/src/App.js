import React, { Component } from 'react';
import {
  BrowserRouter as Router,
  Route
} from 'react-router-dom';
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
import About from './components/Text';
import Profile from './components/Profile';
import Login from './components/Login';
import AddSpotify from './components/AddSpotify'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './assets/font-awesome/css/font-awesome.css'
import  './assets/css/style.css'
import './assets/css/style-responsive.css'
import { Link } from 'react-router-dom'
import { CookiesProvider, withCookies, Cookies } from 'react-cookie';
const store = createStore(reducers, applyMiddleware(thunk));
import GetSpotifyTopTracks from './components/GetSpotifyTopTracks'
class App extends Component {




  render() {
    return (
  <CookiesProvider>
      <Provider store={store}>
      <Router>
        <div>
          <header className="header black-bg">
            <Link to='/' className="logo"><b>SPOTIFY SOCIAL</b></Link>
            <div className="nav notify-row" id="top_menu">
              <ul className="nav top-menu">
                <li className="dropdown">
                  <Link to='/'>
                        {/*<i className="fa fa-exclamation-triangle" />*/}
                    <i className="fa fa-envelope" />
                    <span className="badge bg-theme">4</span>
                  </Link>
                </li>

                <li className="dropdown ">
                  <Link to='/'>
                        {/*<i className="fa fa-exclamation-triangle" />*/}
                    <i className="fa fa-flag" />
                    <span className="badge bg-theme">4</span>
                  </Link>
                </li>
              </ul>
            </div>

            <div>
              <ul className="nav pull-right top-menu">
                <li><a className="logout" href="login.html">Logout</a></li>
              </ul>
            </div>


            <div className="nav notify-row-nomarginleft pull-right">
              <ul className="nav top-menu">

                <li className="dropdown">
                  <Link to='/'>
                        {/*<i className="fa fa-exclamation-triangle" />*/}
                    <i className="fa fa-cog" />
                    {/*<span className="badge bg-theme">4</span>*/}
                  </Link>
                </li>
              </ul>
            </div>
          </header>


          <Route exact path="/" component={Home} />
          <Route path="/profile" component={Profile} />
          <Route path="/login" component={Login} />
          <Route path="/addspotify" component={AddSpotify} />
          <Route path="/getspotifytoptracks" component={GetSpotifyTopTracks} />
          <Route path="/signup" component={SignUp} />
          <Route path="/test" component={Test} />
          <Route path="/about" component={About} />

        </div>
      </Router>

      </Provider>
  </CookiesProvider>
    );
  }
}

export default App;
