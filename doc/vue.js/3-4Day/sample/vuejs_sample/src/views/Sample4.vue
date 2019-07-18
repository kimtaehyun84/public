<template>
  <div class="sample">
    <component :is="currentView" />
    <p />
    <p />
    <button @click="changeView('EventHandler')">이헨트 핸들러</button> |
    <button @click="changeView('KeyHandler')">키 수식어</button> |
  </div>
</template>
<script>
import EventHandler from "../components/EventHandler.vue";
import KeyHandler from "../components/KeyHandler.vue";

export default {
  components: {
    EventHandler,
    KeyHandler
  },

  data() {
    return {
      currentView: "EventHandler"
    };
  },

  methods: {
    changeView(name) {
      this.currentView = name;
    }
  }

  // created() {
  //   console.log("#### parent created ####");
  // },

  // activated() {
  //   console.log("#### parent activated ####");
  // },

  // mounted() {
  //   console.log("#### parent mounted ####");
  // },

  // deactivated() {
  //   console.log("#### parent deactivated ####");
  // },

  // destroyed() {
  //   console.log("#### parent destroyed ####");
  // }
};
</script>
