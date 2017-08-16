import { connect } from 'react-redux'
import TracksList from '../components/TracksList'
import {fetchTracks, fetchTracksSuccess, fetchTracksFailure} from '../actions/tracks'

const mapStateToProps = (state) => {
  return{
    tracksList: state.tracks.tracksList
  };
}

const mapDispatchToProps = (dispatch) => {
  return {
    fetchTracks: () => {
      dispatch(fetchTracks()).then((response) => {
        !response.error ? dispatch(fetchTracksSuccess(response.payload.data) )  : dispatch(fetchTracksFailure(response.payload.data));
      });
      // console.log("success")
    }
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(TracksList);
