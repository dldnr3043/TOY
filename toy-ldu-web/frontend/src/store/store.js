import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const messages = {
	state: {
		test: 'test'
	}
}





export default new Vuex.Store({
    modules: {
        messages,
    }
})