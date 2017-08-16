import React from 'react';

class TracksList extends React.Component{
  componentWillMount(){
    console.log("LOAD")
    this.props.fetchTracks();
  }

  renderTracks(tracks){
    return tracks.map((track) => {
      return(
        <li className="list-group-item" >
            <h3 className="list-group-item-heading">{track.artist} - {track.name} | {track.trackId}</h3>
        </li>
      )
    })
  }

  render(){

    const {tracks, loading, error} = this.props.tracksList;

    if(loading) {
      return <div className="container"><h1>Posts</h1><h3>Loading...</h3></div>
    } else if(error) {
      return <div className="alert alert-danger">Error: {error.message}</div>
    }

    return(
      <div className="container">
        <h1>Tracks</h1>
        <ul className="list-group">
          {this.renderTracks(tracks)}
        </ul>
      </div>
    );
  }
}

export default TracksList;
