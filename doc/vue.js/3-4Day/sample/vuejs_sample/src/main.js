import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false;

Vue.directive('focus', {
  // 바인딩 된 엘리먼트가 DOM에 삽입되었을 때...
  inserted: function (el) {
    // 엘리먼트에 포커스를 줍니다
    el.focus()
  }
});

Vue.directive('binding-info', {
  bind: function (el, binding, vnode) {
    var s = JSON.stringify
    el.innerHTML =
      'name: ' + s(binding.name) + '<br>' +
      'value: ' + s(binding.value) + '<br>' +
      'expression: ' + s(binding.expression) + '<br>' +
      'argument: ' + s(binding.arg) + '<br>' +
      'modifiers: ' + s(binding.modifiers) + '<br>' +
      'vnode keys: ' + Object.keys(vnode).join(', ');

  }

});

// 전역 guard
// router.beforeEach((to, from, next) => {
//   console.log(`page moved ${from.fullPath} => ${to.fullPath}`);
//   next();
// });

// 전역 guard 인증 using meta data
// router.beforeEach(function (to, from, next) {
//   // to: 이동할 url에 해당하는 라우팅 객체
//   if (to.matched.some(function (routeInfo) {
//     return routeInfo.meta.authRequired;
//   })) {
//     // 이동할 페이지에 인증 정보가 필요하면 경고 창을 띄우고 페이지 전환은 하지 않음
//     alert('Login Please!');
//   } else {
//     console.log("routing success : '" + to.path + "'");
//     next(); // 페이지 전환
//   };
// });

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
