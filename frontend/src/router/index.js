import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/components/page/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: LoginPage
    }
  ]
})
