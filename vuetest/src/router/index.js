import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index.vue'
import BookManage from '../views/BookManage'
import AddBook from '../views/AddBook'
import BookUpdate from '../views/BookUpdate'
import UserManage from '../views/UserManage'
import UserUpdate from '../views/UserUpdate'
import Role from '../views/Role'

Vue.use(VueRouter)

const routes = [
    {
        path:'/',
        name:'书单管理',
        component:Index,
        show:true,
        //打开8080默认的pageOne路径
        redirect:'BookManage',
        children:[
            {
                path:'/BookManage',
                name:'查询书籍',
                component:BookManage
            },
            {
                path:'/AddBook',
                name:'添加书籍',
                component:AddBook
            }

        ]
    },
    {
        path:'/BookUpdate',
        component:BookUpdate,
    },
    {
        path:'/',
        name:'超级管理员',
        component:Index,
        show:true,
        // 一打开就重定向到当前页面
        redirect:'BookManage',
        children:[
            {
                path:'/UserManage',
                name:"查询用户",
                component:UserManage
            },
            {
                path:'/Role',
                name:"系统权限",
                component:Role
            }
        ]

    },
    {
        path:'/UserUpdate',
        component:UserUpdate
    },
    {
        path: '/login',
        name: '退出',
        component:  () =>
            import ('../views/Login'),
    }



]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
