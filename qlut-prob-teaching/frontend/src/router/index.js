import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/law-of-large-numbers',
    name: 'law-of-large-numbers',
    component: () => import('../views/law-of-large-numbers.vue')
  },
  {
    path: '/lessons',
    name: 'lesson-list',
    component: () => import('../views/lesson/lesson-list.vue')
  },
  {
    path: '/lessons/lesson-1',
    name: 'lesson-1',
    component: () => import('../views/lesson/lesson-1.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
