import Vue from 'vue'
import App from './App.vue'
import store from './store/store.js'
import router from './router/router.js'
import axios from 'axios';

Vue.prototype.$store = store;	// 전역에서 this.$store 사용 가능

// axios request에 jwt 토큰 값 넣기 : for all requests
axios.defaults.headers.post['Content-Type'] = 'application.json';

// 캐싱 방지
axios.defaults.headers.get['Cache-Control'] = 'no-cache';
axios.defaults.headers.get['Pragma'] = 'no-cache';

Vue.prototype.$http = axios;	// 전역에서 this.$axios 사용 가능


Vue.config.productionTip = false

new Vue({
	axios,
	store,
	router,
  render: h => h(App),
}).$mount('#app')
