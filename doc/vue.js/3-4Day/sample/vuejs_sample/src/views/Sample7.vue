<template>
  <div class="sample7">
    <h1>dynamic components</h1>
    <component :is="currentView" />
    <div>
      <button @click="change('SlotNormal')">SlotNormal</button>
      <button @click="change('SlotNamed')">SlotNamed</button>
      <button @click="change('SlotScoped')">SlotScoped</button>
    </div>
  </div>
</template>
<script>
import SlotNormal from "../components/SlotNormal.vue";
import SlotNamed from "../components/SlotNamed.vue";
import SlotScoped from "../components/SlotScoped.vue";
export default {
  components: {
    SlotNormal,
    SlotNamed,
    SlotScoped
  },
  props: {},

  data() {
    return {
      currentView: "SlotNormal"
    };
  },

  methods: {
    change(component) {
      this.currentView = component;
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
