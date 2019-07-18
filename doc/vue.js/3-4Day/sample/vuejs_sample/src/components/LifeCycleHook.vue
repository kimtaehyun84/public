<template>
  <div class="sample">
    <h1>Life Cycle Hook</h1>
    <h1>{{message}}</h1>
  </div>
</template>
<script>
export default {
  data() {
    return {
      message: ""
    };
  },

  methods: {},

  created() {
    this.message = "#### created ####";
    console.log("created");
  },

  activated() {
    this.message += "#### activated ####";
    console.log("activated");
  },

  mounted() {
    this.message += " " + "#### mounted ####";
    console.log("mounted");
  },

  deactivated() {
    this.message += "#### deactivated ####";
    console.log("deactivated");
  },

  destroyed() {
    this.message += "#### destroyed ####";
    console.log("destroyed");
  }
};
</script>
