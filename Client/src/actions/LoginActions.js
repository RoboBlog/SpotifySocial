import axios from 'axios';

export function loginUser(formValues) {
  const request = axios.post('http://localhost:8080/login', {
    username: formValues.username,
    password: formValues.password
  });

  return {
    type: 'LOGIN_USER',
    payload: request
  };
}

export function loginUserSuccess(user) {
  window.location.replace("/addmusicportal");
  return {
    type: 'LOGIN_USER_SUCCESS',
    payload: user
  };
}

export function loginUserFailure(error) {
  return {
    type: 'LOGIN_USER_FAILURE',
    payload: error
  };
}


