import authService from '../services/authService';

const user = localStorage.getItem('jwtToken');
const initialState = user
    ? { status: { loggedIn: true }, user }
    : { status: { loggedIn: false }, user: null };

export const auth = {
    namespaced: true,
    state: initialState,
    actions: {
        login({ commit }, user: any) {
            return authService.login(user).then(
                user => {
                    window.localStorage.setItem("jwtToken", user.data.accessToken);
                    commit('loginSuccess', user);
                    return Promise.resolve(user);
                },
                error => {
                    commit('loginFailure');
                    return Promise.reject(error);
                }
            );
        },
        logout({ commit }) {
            authService.logout();
            commit('logout');
        }
    },
    mutations: {
        loginSuccess(state: any, user: any) {
            state.status.loggedIn = true;
            state.user = user;
        },
        loginFailure(state: any) {
            state.status.loggedIn = false;
            state.user = null;
        },
        logout(state: any) {
            state.status.loggedIn = false;
            state.user = null;
            window.localStorage.clear();
        },
    }
};