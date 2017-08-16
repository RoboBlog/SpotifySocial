import React, {PropTypes} from 'react'
import { instanceOf } from 'prop-types';
import { reduxForm, Field, SubmissionError } from 'redux-form';
import { loginUser, loginUserSuccess, loginUserFailure} from '../actions/LoginActions'
import { CookiesProvider, withCookies, Cookies } from 'react-cookie';


// export default withCookies(Login);







const required = value => value ? undefined : 'Required';
const renderField = ({ input, type, placeholder, meta: { touched, error, warning } }) => (
  <div >
    <div>
      <input {...input} placeholder={placeholder} type={type}  className="form-control"/>
      {touched && ((error && <span>{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>);


class Login extends React.Component {

  submitForm = (values, dispatch) => {
    return dispatch(loginUser(values))
      .then((result) => {

        if(result.payload.response && result.payload.response.status !== 200){
          dispatch(loginUserFailure(result.payload.response.data));
          alert(result.payload.response.data.message);
          throw new SubmissionError(result.payload.response.data);
        }
        localStorage.setItem('jwtToken', result.payload.headers["authorization"]);

        const { cookies } = this.props;
        cookies.set('login', values.username, { path: '/' });

        dispatch(loginUserSuccess(values.username));

      });

  };

  render() {
    const { handleSubmit} = this.props;

    return (
      <div id="login-page">
        <div className="container">

          <form className="form-login" action="index.html" onSubmit={handleSubmit(this.submitForm)}>
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
                <button className="btn btn-facebook"><i className="fa fa-facebook" /> Facebook</button>
                &nbsp;
                <button className="btn btn-twitter"><i className="fa fa-twitter" /> Twitter</button>
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
    )
  }
}
export default withCookies(reduxForm({
  form: 'Login',
})(Login));
