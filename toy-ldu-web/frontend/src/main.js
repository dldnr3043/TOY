import Vue from 'vue'
import App from './App.vue'
import store from './store/store.js'
import router from './router/router.js'
import axios from 'axios';
import Nav from './components/common/Navigation.vue'

require("./assets/css/bootstrap.css");

Vue.prototype.$store = store;	// 전역에서 this.$store 사용 가능

axios.defaults.headers.post['Content-Type'] = 'application.json';

// 캐싱 방지
axios.defaults.headers.get['Cache-Control'] = 'no-cache';
axios.defaults.headers.get['Pragma'] = 'no-cache';

Vue.prototype.$http = axios;	// 전역에서 this.$axios 사용 가능

Vue.component('Nav', Nav);	// nav 전역 컴포넌트 설정

// event-bus 설정, 전역에서 this.eventBus로 eventBus 사용 가능
const EventBus = new Vue();
Vue.prototype.$eventBus = EventBus


Vue.config.productionTip = false

new Vue({
	axios,
	store,
	router,
  render: h => h(App),
}).$mount('#app')
