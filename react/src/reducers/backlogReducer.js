import {
    GET_BACKLOG
  } from "../Actions/types";
  
  const initialState = {
    project_tasks: [],
    project_task: {}
  };
  
  export default function(state = initialState, action) {
    switch (action.type) {
      case GET_BACKLOG:
        return {
          ...state,
          project_tasks: action.payload
        };
  
  
      default:
        return state;
    }
  }