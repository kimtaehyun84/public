<template>
  <div class="sample">
    <component :is="currentView" />
    <p />
    <p />
    <button @click="changeView('PropertyAndMethod')">Property And Method</button> |
    <button @click="changeView('ComputedAndWatch')">Computed and Watch</button> |
    <button @click="changeView('LifeCycleHook')">lifeCycleHook</button> |
  </div>
</template>
<script>
import PropertyAndMethod from "../components/PropertyAndMethod.vue";
import ComputedAndWatch from "../components/ComputedAndWatch.vue";
import LifeCycleHook from "../components/LifeCycleHook.vue";

export default {
  components: {
    PropertyAndMethod,
    ComputedAndWatch,
    LifeCycleHook
  },

  data() {
    return {
      currentView: "PropertyAndMethod"
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
