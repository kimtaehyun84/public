<template>
  <div class="hello">
    <slot name="header">
      <img alt="Vue logo" src="../assets/logo.png" />
    </slot>
    <slot name="body">
      <div>hello named slot</div>
    </slot>
    <slot name="footer">
      <div>우주제일 TNS Hyousung</div>
    </slot>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  props: {
    msg: String
  },
  data() {
    return {};
  },

  created() {
    console.log("#### created ####");
  },

  activated() {
    console.log("#### activated ####");
  },

  mounted() {
    console.log("#### mounted ####");
    this.message = this.initMessage;
  },

  deactivated() {
    console.log("#### deactivated ####");
  },

  destroyed() {
    console.log("#### destroyed ####");
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
