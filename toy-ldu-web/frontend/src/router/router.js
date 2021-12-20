import Vue from 'vue'
import Router from 'vue-router'

import Test from '../components/Test.vue'
import HelloWorld from '../components/HelloWorld.vue'

Vue.use(Router)

export default new Router({
    mode: 'history', // history ���� �ڿ������� url ����, �������� ������ �ؽ�(#)��ȣ�� url ���
    routes: [
		{
            path: "/",
            name: "HelloWorld",
            component: HelloWorld
        },
        {
            path: "/test", // ���
            name: "test", // �ش� ����� �̸� 
            component: Test // �̵��� ������Ʈ
        },
        {
            path: "/foo",
            name: "Foo",
            component: HelloWorld
        },
        {
            path: "/bar",
            name: "Bar",
            component: HelloWorld
        },
    ]
})