import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/components/page/Login'
import MainPage from '@/components/page/Main'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/view/index',
      name: 'Login',
      component: LoginPage
    },
    {
      path: '/view/main',
      name: 'Main',
      component: MainPage
    }
  ]
})
