import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import backlogReducer from "./backlogReducer";
import securityReducer from "./securityReducer"

export default combineReducers({
  errors: errorReducer,
  backlog: backlogReducer,
  security: securityReducer
});