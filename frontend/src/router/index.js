import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/components/page/Login'
import MainPage from '@/components/page/Main'
import LoginSuccess from '@/components/page/LoginSuccess'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: LoginPage
    },
    {
      path: '/view/index',
      name: 'Login',
      component: LoginPage
    },
    {
      path: '/view/main',
      name: 'Main',
      component: MainPage
    },
    {
      path: '/view/loginSuccess',
      name: 'LoginSuccess',
      component: LoginSuccess
    }
  ]
})
