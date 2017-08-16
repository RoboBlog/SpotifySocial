import LoginForm from '../components/Login';
import { connect } from 'react-redux';

const mapStateToProps = (state)=> {
  return {
    user: state.user
  };
}
const mapDispatchToProps = (dispatch) => {
  return {
  }
}



export default connect(mapStateToProps, mapDispatchToProps)(LoginForm);
