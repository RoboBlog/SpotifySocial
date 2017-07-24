import React from 'react'
import * as apiActions from '../actions/apiActions';
import {connect} from 'react-redux';
import PropTypes from 'prop-types';


class GetSpotifyTopTracks extends React.Component{
  static propTypes = {
    isLoading: PropTypes.bool ,
    isError: PropTypes.bool ,
    data: PropTypes.array
  };
  componentDidMount() {
    const { getData } = this.props;
    getData();
  }
  render(){

    const {data} = this.props;
    // console.log(data);

    return(
      <div>
        <br />
        <br />
        <br />
        <br />
        Data : <b>{data}</b>
      </div>
    );
  }
}
const mapStateToProps = (state) => {
  return state.apiReducer;
};
const mapDispatchToProps = (dispatch) => {
  return {
    getData: () => dispatch(apiActions.getData('http://localhost:8080/api/spotify/gettoptracks'))
  }
};

GetSpotifyTopTracks = connect(mapStateToProps, mapDispatchToProps)(GetSpotifyTopTracks);
export default GetSpotifyTopTracks;
