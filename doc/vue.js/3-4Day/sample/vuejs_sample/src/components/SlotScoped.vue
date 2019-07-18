<template>
  <div class="hello">
    <slot name="header">
      <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
    </slot>
    <slot :items="items">
      <div v-for="(item, index) in items" :key="index">{{item.name}} => {{item.nick}}</div>
    </slot>
    <slot name="footer">
      <!-- <div>우주제일 TNS Hyousung</div> -->
    </slot>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        { name: "moon", nick: "useless" },
        { name: "moon2", nick: "badguy" },
        { name: "moon3", nick: "zzirong" },
        { name: "moon4", nick: "monster" },
        { name: "moon5", nick: "WonBin of bigGuys" }
      ]
    };
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
