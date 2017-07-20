import React from 'react'
import LoginForm from './LoginForm'
import * as sendFormActions from '../actions/sendFormActions'
class Login extends React.Component {
  submit = (values) => {
    let body = {username : values.username,password : values.password};
    sendFormActions.sendData('http://localhost:8080/login', body);
  };
  render() {
    return (
      <LoginForm onSubmit={this.submit} />
    )
  }
}



export default Login;
