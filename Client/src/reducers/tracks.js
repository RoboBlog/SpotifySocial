const INITIAL_STATE = { tracksList: { tracks:[], error:null, loading:true }};

export default function(state = INITIAL_STATE, action){
  let error;
  switch (action.type) {
    case 'FETCH_TRACKS':// start fetching posts and set loading = true
      return { ...state, tracksList: {tracks:[], error: null, loading: true} };
    case 'FETCH_TRACKS_SUCCESS':// return list of posts and make loading = false
      return { ...state, tracksList: {tracks: action.payload, error:null, loading: false} };
    case 'FETCH_TRACKS_FAILURE':// return error and make loading = false
      error = action.payload
      return { ...state, tracksList: {tracks: [], error: error, loading: false} };
    default:
      return state;
  }
}
