import { combineReducers } from 'redux';
import { reducer as reduxForm} from 'redux-form';
import apiReducer from './apiReducer'
import authReducer from './authReducer'
const reducers = combineReducers({
  authReducer, apiReducer, form:reduxForm
});
export default reducers


