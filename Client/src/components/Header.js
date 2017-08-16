import React from 'react'
import {Link} from 'react-router-dom'

class Header extends React.Component {
  logout = () => {
    localStorage.removeItem("jwtToken");
    window.location.replace("/login");
    document.cookie = "login" + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';

  }
  render(){
    return(
      <div >
      <div className="header black-bg">
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
            {/**/}
            <li className="dropdown ">
              <Link to='/'>
                {/*<i className="fa fa-exclamation-triangle" />*/}
                <i className="fa fa-flag" />
                <span className="badge bg-theme">4</span>
              </Link>
            </li>
          </ul>
        </div>
        {/**/}
        <div>
          <ul className="nav pull-right top-menu">
            <li>
              <button className="logout" onClick={this.logout}>Logout</button></li>
          </ul>
        </div>
        {/**/}
        {/**/}
        <div className="nav notify-row-nomarginleft pull-right">
          <ul className="nav top-menu">
            {/**/}
            <li className="dropdown">
              <Link to='/'>
                {/*<i className="fa dasfa-exclamation-triangle" />*/}
                <i className="fa fa-cog" />
                {/*<span className="badge bg-theme">4</span>*/}
              </Link>
            </li>
          </ul>
        </div>
      </div>
      </div>
    );
  }
}

export default Header
