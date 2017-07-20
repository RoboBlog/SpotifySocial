import React from 'react';
import { Field, reduxForm } from 'redux-form'

const required = value => value ? undefined : 'Required';
const renderField = ({ input, type, placeholder, meta: { touched, error, warning } }) => (
  <div >
    <div>
      <input {...input} placeholder={placeholder} type={type}  className="form-control"/>
      {touched && ((error && <span>{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>)


let LoginForm = props => {
  const { handleSubmit } = props;
  return (
      <div id="login-page">
        <div className="container">

          <form className="form-login" action="index.html" onSubmit={handleSubmit}>
            <h2 className="form-login-heading">sign in now</h2>
            <div className="login-wrap">
              <Field component={renderField}  placeholder="Username" name="username" type="text" validate={required}/>
              <br />
                <Field component={renderField} placeholder="Password" name="password"  type="password" validate={required}/>

              <label className="checkbox">
      <span className="pull-right">
      <a data-toggle="modal" href="login.html#myModal"> Forgot Password?</a>

        <br />

        <br />

         </span>
              </label>
              <button className="btn btn-theme btn-block" href="index.html" type="submit"><i className="fa fa-lock" /> SIGN IN</button>
              <hr />

              <div className="login-social-link centered">
                <p>or you can sign in via your social network</p>
                <button className="btn btn-facebook" type="submit"><i className="fa fa-facebook" /> Facebook</button>
                &nbsp;
                <button className="btn btn-twitter" type="submit"><i className="fa fa-twitter" /> Twitter</button>
              </div>
              <div className="registration">
                Don't have an account yet?
                <a className="" href="#">
                  <br />
                  Create an account
                </a>
              </div>


            </div>

          </form>

        </div>
      </div>
      // </div>
    );
};


export default reduxForm({
  form: 'login' // a unique identifier for this form
})(LoginForm);
