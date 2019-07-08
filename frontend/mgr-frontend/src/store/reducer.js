const initialState = {
    authToken: "",
    ipAddr: '192.168.0.94',
    eMailAddress: "",
    isAnAdmin: false
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
        case 'SETEMAIL':
            return {
                ...state,
                eMailAddress: action.eMail
            }
        case 'RESETEMAIL':
            return {
                ...state,
                eMailAddress: ""
            }
        case 'SETADMINUSER':
            return {
                ...state,
                isAnAdmin: action.isAdmin
            }
    }
    return state;
};

export default reducer;