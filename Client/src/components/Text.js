import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
class TextComponent extends React.Component {

static propTypes = {
  token: PropTypes.string,
  userName: PropTypes.string,
  statusText: PropTypes.string
}


  render() {

    let token2 = localStorage.getItem('token');

    const{token, userName, statusText} = this.props;
      function handleClick(e) {
        e.preventDefault();

        alert(localStorage.getItem('token'));
      }

    return (
      <div>


        {/*<button onClick="console.log('Dsads');">dss</button>*/}
        <br />
        <br />
        <br />

        <br />

       <p className="text"> Is Authenticated: {statusText}</p>
        <p className="text">
        Token: {this.token2} {token}
        </p>
        <a href="#" onClick={handleClick}>
          Click me
        </a>
      </div>
    );
  }
}
// const mapStateToProps = (state) => ({
//   token: state.auth.token,
//   userName: state.auth.userName,
//   isAuthenticated: state.auth.isAuthenticated
// });
const mapStateToProps = (state) => {
    return state.authReducer;
};

TextComponent = connect(mapStateToProps)(TextComponent);
export default TextComponent;
