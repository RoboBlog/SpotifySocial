import React from 'react'
import LoginForm from './LoginForm'
import * as sendFormActions from '../actions/sendFormActions'
import { CookiesProvider, withCookies, Cookies } from 'react-cookie';
import { instanceOf } from 'prop-types';
class Login extends React.Component {

  submit = (values) => {
    let body = {username : values.username,password : values.password};
    sendFormActions.sendData('http://localhost:8080/login', body);
    const { cookies } = this.props;
    cookies.set('username', values.username, { path: '/' });

  };
  render() {
    return (
      <div>
      <LoginForm onSubmit={this.submit} />
      </div>

    )
  }
}



export default withCookies(Login);
