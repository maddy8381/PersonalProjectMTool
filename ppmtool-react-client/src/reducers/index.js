//This is the Root Reducer
import {combineReducers} from "redux"
import errorReducer from "./errorReducer";

export default combineReducers({
    errors: errorReducer
});