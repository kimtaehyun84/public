import Vue from 'vue'
import Router from 'vue-router'
import Sample from './views/Sample.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Sample
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    }
    ,
    {
      path: '/sample',
      name: 'sample',
      component: require('./views/Sample.vue').default
    }
    ,
    {
      path: '/sample2',
      name: 'sample2',
      component: require('./views/Sample2.vue').default
    }
    ,
    {
      path: '/sample3',
      name: 'sample3',
      component: require('./views/Sample3.vue').default
    }
    ,
    {
      path: '/sample4',
      name: 'sample4',
      component: require('./views/Sample4.vue').default
    }
  ]
})
