const initialState = {
    authToken: "",
    ipAddr: '192.168.0.94'
}

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case 'SETAUTHTOKEN':
            return {
                ...state,
                authToken: action.token
            }
        case 'RESETAUTHTOKEN':
            return {
                ...state,
                authToken: ""
            }
    }
    return state;
};

export default reducer;