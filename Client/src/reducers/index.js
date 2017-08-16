import { combineReducers} from 'redux';
import { reducer as reduxForm} from 'redux-form';
import apiReducer from './apiReducer';
import authReducer from './authReducer'
import tracks from './tracks'
import loginReducer from './loginReducer'


const reducers = combineReducers({
  authReducer, apiReducer, tracks, loginReducer, form:reduxForm,
  }
);
export default reducers


