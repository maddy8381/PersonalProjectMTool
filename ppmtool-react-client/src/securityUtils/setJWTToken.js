import axios from "axios";

const setJWTToken = token => {
    if (token) {
        axios.defaults.headers.common["Authorization"] = token; //Setting Header for Authorization 
    } else {
        delete axios.defaults.headers.common["Authorization"];
    }
};

export default setJWTToken;