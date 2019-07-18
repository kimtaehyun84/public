<template>
  <div class="sample5">
    <h1>Components</h1>
    <parent-child-event-test @added="onAdded" @substracted="onSubstracted" />
    <div>{{mountedMessage}}</div>
  </div>
</template>
<script>
import ParentChildEventTest from "./ParentChildEventTest.vue";
export default {
  components: {
    ParentChildEventTest
  },
  props: {},

  data() {
    return {
      isShow: false,
      mountedMessage: ""
    };
  },

  methods: {
    onAdded(val) {
      this.mountedMessage = `add after totalValue => ${val}`;
    },

    onSubstracted(val) {
      this.mountedMessage = `substract after totalValue => ${val}`;
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
