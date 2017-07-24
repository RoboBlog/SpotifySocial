import {LOGIN_USER_REQUEST, LOGIN_USER_SUCCESS, LOGIN_USER_FAILURE, LOGOUT_USER} from '../constants';
import axios from 'axios'
import dispatch from "redux";
import {Link} from "react-router-dom";
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

  localStorage.removeItem('token');
  return {
    type: LOGIN_USER_FAILURE,
    payload: {
      status: error.response.status,
      statusText: error.response.statusText
    }
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
    password : form.password
  })
    .then((response) => {
      // alert(response.headers['authorization']);
      dispatch(loginUserSuccess(response.headers['authorization']));
  })
    .catch(function (error) {
      // if(error.toString()==='Error: Request failed with status code 401') {
      //   alert('Incorrect username or password!');
      // }
      // else{
      //   alert('Undefined error');
      // }
    });


  // throw fetch(link, {
  //   method: 'POST',
  //   headers: {'Content-Type': 'application/json'},
  //   body: form
  // }).then(response => console.log(response))
}
