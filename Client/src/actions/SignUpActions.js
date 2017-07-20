import {LOGIN_USER_REQUEST, LOGIN_USER_SUCCESS, LOGIN_USER_FAILURE, LOGOUT_USER} from '../constants';
import axios from 'axios'

export function loginUserSuccess(token) {
  localStorage.setItem('token', token);
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
  else{
    alert('Undefined error');
  }
}

export function loginUserRequest() {
  return {
    type: LOGIN_USER_REQUEST
  }
}

export function sendData(link,form) {
  axios.post(link, {
    username : form.username,
    password : form.password,
    email: form.email
  })
    .catch(function (error) {
      alert(error.toString());

    });

}
