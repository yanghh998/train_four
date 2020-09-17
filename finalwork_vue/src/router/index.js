import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from "../views/Index";
import FindAll from "../views/FindAll";
import AddUser from "../views/AddUser";
import Login from "../views/Login";
import Register from "../views/Register";

Vue.use(VueRouter);

const routes = [
    {
        path: '/Index',
        name: '用户管理',
        component: Index,
        children: [
            {
                path: '/FindAll',
                name: '所有用户',
                component: FindAll
            },
            {
                path: '/AddUser',
                name: '添加用户',
                component: AddUser
            }
        ]
    },
    {
        path: '/',
        name: '登录',
        component: Login
    },
    {
        path: '/Register',
        name: '注册',
        component: Register
    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router
