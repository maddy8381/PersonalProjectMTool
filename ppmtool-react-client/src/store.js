import { createStore, applyMiddleware, compose } from "redux"
import thunk from "redux-thunk"
import rootReducer from "./reducers"

const initialState = {}
const middleware = [thunk]

let store;

//Removing -bug : if the client dont have react and redux ext, oR if opened in Incognito mode our app was not working - removed by this
const ReactReduxDevTools = window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()

if (window.navigator.userAgent.includes("Chrome") && ReactReduxDevTools) {
    store = createStore(rootReducer, initialState, compose(applyMiddleware(...middleware),
        ReactReduxDevTools));
} else {
    store = createStore(rootReducer, initialState, compose(applyMiddleware(...middleware)));
}

export default store;