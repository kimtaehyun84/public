<template>
  <div class="login">
    <h1>This is an login page</h1>
    <h1>{{message}}</h1>
    <p>
      <button name="button" @click="onClick">beforeRouteUpdate</button>
    </p>
  </div>
</template>
<script>
export default {
  data() {
    return {
      message: ""
    };
  },

  methods: {
    onClick(event) {
      this.$router.push({
        name: "login",
        params: { message: "2" }
      });
    }
  },

  beforeRouteEnter(to, from, next) {
    // Login 컴포넌트가 화면에 표시되기 전에 수행될 로직
    // Login 컴포넌트는 아직 생성되지 않은 시점
    console.log("beforeRouteEnter");
    next();
  },
  beforeRouteUpdate(to, from, next) {
    // 화면에 표시된 컴포넌트가 변경될 때 수행될 로직
    // `this`로 Login 컴포넌트를 접근할 수 있음
    console.log("beforeRouteUpdate");
    next();
  },
  beforeRouteLeave(to, from, next) {
    // Login 컴포넌트를 화면에 표시한 url 값이 변경되기 직전의 로직
    // `this`로 Login 컴포넌트를 접근할 수 있음
    console.log("beforeRouteLeave");
    next();
  }
};
</script>

