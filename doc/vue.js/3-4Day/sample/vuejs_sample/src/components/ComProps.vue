<template>
  <div class="sample5">
    <h1>Components</h1>
    <props-test :initMessage="message" />
    <div>{{mountedMessage}}</div>
    <button @click="send">바꾸자</button>
  </div>
</template>
<script>
import PropsTest from "./PropsTest.vue";
export default {
  components: {
    PropsTest
  },
  props: {},

  data() {
    return {
      message: undefined,
      isShow: false,
      mountedMessage: ""
    };
  },

  methods: {
    send() {
      // watch 는 이럴때 쓴다
      this.message = "parent send";
    }
  },

  computed: {},

  created() {
    console.log("#### parent created ####");
  },

  activated() {
    console.log("#### activated ####");
  },

  mounted() {
    console.log("#### parent mounted ####");
    //this.send();
  },

  deactivated() {
    console.log("#### deactivated ####");
  },

  destroyed() {
    console.log("#### parent destroyed ####");
  }
};
</script>
