import React from 'react'
import SignUpForm from './SignUpForm'
import * as SignUpFormActions from '../actions/SignUpActions'
class SignUp extends React.Component {
  submit = (values) => {
    let body = {username : values.username,password : values.password,email : values.email};
    SignUpFormActions.sendData('http://localhost:8080/signup', body);
  };
  render() {
    return (
      <SignUpForm onSubmit={this.submit} />
    )
  }
}

export default SignUp;
