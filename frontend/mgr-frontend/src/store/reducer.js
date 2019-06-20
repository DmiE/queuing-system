const initialState = {
    authToken: ""
}

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case 'SETAUTHTOKEN':
            return {
                authToken: action.token
            }
    }
    return state;
};

export default reducer;