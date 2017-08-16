import axios from 'axios';

export function fetchTracks() {
  const request = axios({
    method: 'get',
    url: 'http://localhost:8080/api/gettracks',
    headers: []
  });


  return{
    type : 'FETCH_TRACKS',
    payload: request
  }
}

export function fetchTracksSuccess(response) {
  return {
    type: 'FETCH_TRACKS_SUCCESS',
    payload: response
  };
}

export function fetchTracksFailure(error) {
  return {
    type: 'FETCH_TRACKS_FAILURE',
    payload: error
  };
}
