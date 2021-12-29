import Vue from 'vue'
import Router from 'vue-router'

import Login from '../components/login/Login.vue'
import ChatMain from '../components/chat/ChatMain.vue'
import Signup from '../components/signup/Signup.vue'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        // root 경로
		{
            path: "/",
            name: "Root",
            component: Login
        },
        // 로그인 화면
        {
            path: "/login",
            name: "Login",
            component: Login
        },
        // 회원가입 화면
        {
            path: "/signup",
            name: "Signup",
            component: Signup
        },
        {
            path: "/web/chat/main",
            name: "ChatMain",
            component: ChatMain
        },
    ]
})