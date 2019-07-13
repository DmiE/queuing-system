const initialState = {
    authToken: "",
    ipAddr: '192.168.55.104',
    eMailAddress: "",
    isAnAdmin: null,
    userName: ""
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
        case 'RESETADMINUSER':
            return {
                ...state,
                isAnAdmin: null
            }
        case 'SETUSERNAME':
            return {
                ...state,
                userName: action.userName
            }
        case 'RESETUSERNAME':
            return {
                ...state,
                userName: ""
            }
    }
    return state;
};

export default reducer;