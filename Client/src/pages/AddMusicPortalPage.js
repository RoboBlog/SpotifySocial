import React, {PropTypes} from 'react'
import { instanceOf } from 'prop-types';

class AddMusicPortalPage extends React.Component {
//cookie LOGIN

  render() {
    return (
      <div id="login-page">
        <div className="container">

          <div className="form-login">
            <h2 className="form-login-heading">ADD MUSIC PORTAL</h2>
              <br />
              <center>
              <p>You can add Spotify or Deezer</p>
                <a className="btn btn-spotify" href = "http://localhost:8080/api/spotify/add"><i className="fa fa-spotify" /> Spotify</a>
                &nbsp;
                &nbsp;
                &nbsp;
                <a className="btn btn-deezer"> Deezer</a>
              </center>
            <br />
        </div>
      </div>
      </div>
    )
  }
}
export default AddMusicPortalPage
