import { act } from "react-dom/test-utils";
import {GET_PROJECT, GET_PROJECTS} from "../actions/types"

const initialState = {
    projects: [],
    project: {}
}

export default function anyFunctionName(state = initialState, action){
    
    switch(action.type){
        case GET_PROJECTS: 
            return{
                ...state,
                projects: action.payload
            }
        
        case GET_PROJECT:
            return{
                ...state,
                project: action.payload
            }
        default:
            return state;
    }
}