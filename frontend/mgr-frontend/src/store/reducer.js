const initialState = {
    authToken: "",
    ipAddr: '192.168.55.101'
}

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case 'SETAUTHTOKEN':
            return {
                ...state,
                authToken: action.token
            }
    }
    return state;
};

export default reducer;