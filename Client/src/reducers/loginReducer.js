/**
 * Created by maciek on 8/14/17.
 */
const INITIAL_STATE = {user: null, status:null, error:null, loading: false};

export default function(state = INITIAL_STATE, action) {
  let error;
  switch (action.type) {
    case 'LOGIN_USER':// sign in user,  set loading = true and status = signin
      return {...state, user: null, status: 'signin', error: null, loading: true};
    case 'LOGIN_USER_SUCCESS'://return authenticated user,  make loading = false and status = authenticated
      return {...state, user: action.payload.user, status: 'authenticated', error: null, loading: false}; //<-- authenticated
    case 'LOGIN_USER_FAILURE':// return error and make loading = false
      error = action.payload.data || {message: action.payload.message};//2nd one is network or server down errors
      return {...state, user: null, status: 'signin', error: error, loading: false};

    default:
      return state;
  }
}
