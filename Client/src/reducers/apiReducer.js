import * as actions from '../actions/apiActions';

const initialState = { isLoading: false, isError: false, data: [] };

export const reducer = (state = initialState, action) => {
  switch (action.type) {
    case actions.GET_DATA_REQUESTED:
      return { ...state, isLoading: true };
    case actions.GET_DATA_DONE:
      return { ...state, isLoading: false, data: action.payload };
    case actions.GET_DATA_FAILED:
      return { ...state, isLoading: false, isError: true };
    default:
      return state;
  }
};
export default reducer
