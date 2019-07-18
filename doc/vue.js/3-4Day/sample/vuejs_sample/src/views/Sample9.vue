<template>
  <div class="sample9">
    <h1>Event 시스템</h1>
    <div>{{message}}</div>
    <eventTest />
  </div>
</template>
<script>
import eventTest from "../components/EventTest";
import eventBus from "../controllers/eventbus";
export default {
  // directives: {
  //   focus: {
  //     inserted(el) {
  //       el.focus();
  //     }
  //   }
  // },
  components: {
    eventTest
  },
  props: {},

  data() {
    return {
      message: "hello"
    };
  },

  methods: {
    onReceived(data) {
      this.message = data;
    }
  },

  computed: {},

  created() {
    console.log("#### parent created ####");
    eventBus.$on("sendMessage", this.onReceived);
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
    eventBus.$off("sendMessage", this.onReceived);
  }
};
</script>
