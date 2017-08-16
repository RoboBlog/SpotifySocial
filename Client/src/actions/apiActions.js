import { Redirect } from 'react-router-dom';
import axios from 'axios'
export const GET_DATA_REQUESTED = 'GET_DATA_REQUESTED';
export const GET_DATA_DONE = 'GET_DATA_DONE';
export const GET_DATA_FAILED = 'GET_DATA_FAILED';

export function getDataRequested() {
  return {
    type: 'GET_DATA_REQUESTED'
  };
}

export function getDataDone(data) {
  return {
    type: 'GET_DATA_DONE',
    payload: data
  };
}

export function getDataFailed(error) {
  return {
    type: 'GET_DATA_FAILED',
    payload: error
  };
}
let token = localStorage.getItem('token');

export function getData(link) {
  return dispatch => {
    console.log(token)
    axios.get(link, {headers: {'Authorization': token}})
      .then(response => {
        dispatch(getDataDone(response.data))
      })
      // .then(data => {
      //   console.log(data)
      //   dispatch(getDataDone(data));
      // })
      .catch(error => {
        console.log(error)
        dispatch(getDataFailed(error));
      })
  }
}

//   return dispatch => {
//     // set state to "loading"
//
//     dispatch(getDataRequested());
//
//     fetch(link)
//       .then(response => response.json())
//       .then(data => {
//         dispatch(getDataDone(data));
//       })
//       .catch(error => {
//         dispatch(getDataFailed(error));
//       })
//   }
// }
