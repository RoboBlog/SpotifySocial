import React from 'react';
import Header from '../components/Header'
import axios from 'axios'
class ProfileComponent extends React.Component {
  constructor() {
    super();
    this.state = {isLoading:false, tracks:[]}
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/spotify/gettoptrackslist',{ headers: {'Authorization': localStorage.getItem('jwtToken')}})
      .then((response) => {
        console.log(response.data);
        this.setState({ isLoading:false, tracks: response.data })

      })
      .catch((error) => {
        console.log(error);
        // window.location.replace('/login')
      });

  }

  render() {



    return (
      //loading page
      <div>
          <Header />
        <table className="mt60 center table table-bordered " >
          <thead >
          <tr>
            <td><h3><b><center>Artist</center></b></h3></td>
            <td><h3><b><center>Name</center></b></h3></td>
            <td><h3><b><center>Track</center></b></h3></td>
          </tr>
          </thead>
          <tbody>
        {this.state.tracks.map((track) => {
          let link = "https://open.spotify.com/embed?uri=spotify:track:"+ track.trackId
          return (

              <tr>
                <td><center>{track.artist}  &nbsp;&nbsp;</center></td>
                <td><center>{track.name}  &nbsp;&nbsp;</center></td>
                <td>
                  <iframe src={link}  height="80px" />
                  </td>
              </tr>

          )
        })
        }
          </tbody>
        </table>

      </div>

    );
  }
}

export default ProfileComponent;
