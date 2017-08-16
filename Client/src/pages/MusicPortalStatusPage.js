import React from 'react'
import { Link } from 'react-router-dom'

class MusicPortalStatusPage extends React.Component {
  redirectToHomepage = () => {
    window.location.replace("/")
  };
  render() {
    return (
      <div id="login-page">
        <div className="container">

          <div className="form-login">
            <h2 className="form-login-heading">MUSIC PORTAL STATUS</h2>
            <br />
            <center>
              <h2>Congratulations, your music portal is added.</h2>
              <br />
              <button className = "btn btn-warning" onClick={this.redirectToHomepage}>GO TO HOME PAGE</button>
            </center>
            <br />
          </div>
        </div>
      </div>
    )
  }
}
export default MusicPortalStatusPage


//GET update user database
