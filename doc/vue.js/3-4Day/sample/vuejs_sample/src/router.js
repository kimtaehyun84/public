import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Sample.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },
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
    },
    {
      path: '/sample4',
      name: 'sample4',
      component: require('./views/Sample4.vue').default
    },
    {
      path: '/sample5',
      name: 'sample5',
      component: require('./views/Sample5.vue').default
    }
    ,
    {
      path: '/sample6',
      name: 'sample6',
      component: require('./views/Sample6.vue').default
    },
    {
      path: '/sample7',
      name: 'sample7',
      component: require('./views/Sample7.vue').default
    }
    ,
    {
      path: '/sample8',
      name: 'sample8',
      component: require('./views/Sample8.vue').default
    },
    {
      path: '/sample9',
      name: 'sample9',
      component: require('./views/Sample9.vue').default
    },
    {
      path: '/login/:message',
      name: 'login',
      component: require('./views/Login.vue').default,
      props: true,
      meta: { authRequired: true },
      // beforeEnter: function (to, from, next) {
      //   // 인증 값 검증 로직 추가
      //   if (to.matched.some(function (routeInfo) {
      //     return routeInfo.meta.authRequired;
      //   })) {
      //     // 이동할 페이지에 인증 정보가 필요하면 경고 창을 띄우고 페이지 전환은 하지 않음
      //     alert('Login Please!');
      //   } else {
      //     console.log("routing success : '" + to.path + "'");
      //     next(); // 페이지 전환
      //   };
      // }
    },
  ]
})
