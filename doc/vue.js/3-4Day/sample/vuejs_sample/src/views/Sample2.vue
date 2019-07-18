<template>
  <div class="sample2">
    <h1>조건부 렌더링</h1>
    <h1 v-if="isReRender">v-if : {{message}}</h1>
    <h1 v-show="isShow">v-show : {{message}}</h1>
    <div v-if="loginType === 'username'">
      <label>사용자 이름</label>
      <input placeholder="사용자 이름을 입력하세요" />
      <!-- <input placeholder="사용자 이름을 입력하세요" key="username-input" /> -->
      <!-- <input placeholder="사용자 이름을 입력하세요" v-model="a" /> -->
    </div>
    <div v-else>
      <label>이메일</label>
      <input placeholder="이메일 주소를 입력하세요" />
      <!-- <input placeholder="이메일 주소를 입력하세요" key="email-input" /> -->
      <!-- <input placeholder="이메일 주소를 입력하세요" v-model="b" /> -->
    </div>

    <div>
      <button @click="isReRender = !isReRender">v-if</button> |
      <button @click="isShow = !isShow">v-show</button>
    </div>

    <div>
      <button @click="changeLoginType">로그인 유형 변경</button>
    </div>
  </div>
</template>
<script>
export default {
  props: {},

  data() {
    return {
      message: "",
      isShow: true,
      isReRender: true,
      loginType: "",
      a: "",
      b: ""
    };
  },

  methods: {
    upperData() {
      this.data.name = this.data.name.toUpperCase();
      this.data.id = this.data.id.toUpperCase();
    },

    changeLoginType() {
      if (this.loginType === "username") this.loginType = "";
      else this.loginType = "username";
    }
  },

  computed: {
    reverseData() {
      return this.message
        .split("")
        .reverse()
        .join("");
    }
  },

  watch: {
    message(newVal, oldVal) {
      //console.log("하하");
      this.changeMessage = `changed message ${oldVal} ==>  ${newVal} `;
    }
  },

  created() {
    console.log("#### created ####");
    this.message = "hello world";
  },

  activated() {
    console.log("#### activated ####");
  },

  mounted() {
    console.log("#### mounted ####");
  },

  deactivated() {
    console.log("#### deactivated ####");
  },

  destroyed() {
    console.log("#### destroyed ####");
  }
};
</script>
