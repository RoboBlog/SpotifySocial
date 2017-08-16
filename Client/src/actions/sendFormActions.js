import {LOGIN_USER_REQUEST, LOGIN_USER_SUCCESS, LOGIN_USER_FAILURE, LOGOUT_USER} from '../constants';
import axios from 'axios'
import dispatch from 'redux'
export function loginUserSuccess(token) {
  localStorage.setItem('token', token);
  window.location.replace('/');
    return {
    type: LOGIN_USER_SUCCESS,
    payload: {
      token: token
    }

  }
}

export function loginUserFailure(error) {
  if(error.toString()==='Error: Request failed with status code 401') {
    alert('Incorrect username or password!');

  }
  // else if(error!==null) {
  //   console.log(error)
  //   alert('Undefined error');
  // }
  localStorage.removeItem('token');
  return {
    type: LOGIN_USER_FAILURE,
    payload: {
      status: error.response.status,
      statusText: error.response.statusText
    }
  }
}


export function sendData(link,form) {

    axios.post(link, {
      username: form.username,
      password: form.password
    })
      .then(response => {
        dispatch(loginUserSuccess(response.headers['authorization']));
      })
      .catch(error => {
        console.log(error)
        dispatch(loginUserFailure(error))
      })
}


