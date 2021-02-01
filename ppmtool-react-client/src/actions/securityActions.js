import axios from "axios";
import setJWTToken from "../securityUtils/setJWTToken";
import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import jwt_decode from "jwt-decode";

export const createNewUser = (newUser, history) => async dispatch => {

    try {
        await axios.post("/api/users/register", newUser);
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const login = LoginRequest => async dispatch => {
    try {
        // post => Login Request
        const res = await axios.post("/api/users/login", LoginRequest);
        // Extract token from res.data
        const { token } = res.data;
        // Store the token in localstorage
        localStorage.setItem("jwtToken", token);
        // Set our token in header *** to pass as a authorization token for subsequent requests.
        setJWTToken(token);
        // decode the token on react
        const decoded = jwt_decode(token);
        // dispatch to our securityReducer
        dispatch({
            type: SET_CURRENT_USER,
            payload: decoded
        });

    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};