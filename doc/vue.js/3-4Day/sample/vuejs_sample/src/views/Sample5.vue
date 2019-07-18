<template>
  <div class="sample">
    <component :is="currentView" />
    <p />
    <p />
    <button @click="changeView('ComProps')">child 로 data 전달</button> |
    <button @click="changeView('ComParentChild')">Parent 에서 event 받기</button> |
  </div>
</template>
<script>
import ComProps from "../components/ComProps.vue";
import ComParentChild from "../components/ComParentChild.vue";

export default {
  components: {
    ComProps,
    ComParentChild
  },

  data() {
    return {
      currentView: "ComProps"
    };
  },

  methods: {
    changeView(name) {
      this.currentView = name;
    }
  }
};
</script>
