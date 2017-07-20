import React from 'react'
import * as apiActions from '../actions/apiActions';
import {connect} from 'react-redux';
import PropTypes from 'prop-types';


class Test extends React.Component{
  static propTypes = {
    isLoading: PropTypes.bool ,
    isError: PropTypes.bool ,
    data: PropTypes.array
  };
  componentDidMount() {
    const { getData } = this.props;
    getData();
  }
  render(){

    const {data} = this.props;
    // console.log(data);

    return(
      <div>
        <br />
        <br />
        <br />
        <br />
        Username : <b>{data.userName}</b>
        <br />
        Password: <b>{data.password}</b>
        <br />
        Email: <b>{data.email}</b>
        <br />
        <b>{data.enabled ? 'Account is active' : 'Account is Unactive'}</b>
      </div>
    );
  }
}
const mapStateToProps = (state) => {
  return state.apiReducer;
};
const mapDispatchToProps = (dispatch) => {
  return {
    getData: () => dispatch(apiActions.getData('http://localhost:8080/spotify/testlog1'))
  }
};
Test = connect(mapStateToProps, mapDispatchToProps)(Test);
export default Test;
