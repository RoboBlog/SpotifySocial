import React from 'react';
import { Field, reduxForm } from 'redux-form'



const required = value => value ? undefined : 'Required';
const renderField = ({ input, label, type, meta: { touched, error, warning } }) => (
  <div>
    <label>{label}</label>
    <div>
      <input {...input} placeholder={label} type={type}/>
      {touched && ((error && <span>{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>)

  let SignUpForm = props => {
  const { handleSubmit } = props;
    return(
        <form onSubmit={handleSubmit}>
          <br />  <br />  <br />  <br />  <br />

        <div>
          <label htmlFor="username">Username: </label>
          <Field component={renderField}  name="username" type="text" validate={required} />
        </div>

        <div>
          <label htmlFor="password">Password: </label>
          <Field component={renderField} name="password"  type="password" validate={required}/>
        </div>

        <div>
          <label htmlFor="email">Email: </label>
          <Field component={renderField}  name="email" type="email" />
        </div>
          <button type="submit">Submit</button>
        </form>
    );
};


export default reduxForm({
  form: 'user'
})(SignUpForm);



