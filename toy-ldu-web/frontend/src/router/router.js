import Vue from 'vue'
import Router from 'vue-router'

import Login from '../components/Login.vue'
import HelloWorld from '../components/HelloWorld.vue'
import Signup from '../components/Signup.vue'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        // root 경로
		{
            path: "/",
            name: "HelloWorld",
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
            path: "/web/main",
            name: "Main",
            component: HelloWorld
        },
    ]
})