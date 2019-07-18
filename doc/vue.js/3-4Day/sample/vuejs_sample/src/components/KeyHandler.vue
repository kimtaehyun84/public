<template>
  <div class="sample4">
    <h1>Event Test</h1>
    <div id="example-1">
      <button v-on:click="counter += 1">Add 1</button>
      <p>위 버튼을 클릭한 횟수는 {{ counter }} 번 입니다.</p>
    </div>
    <div @click.ctrl="clear">clear</div>
    <!-- Ctrl 키만 눌려있을 때만 실행됩니다. -->
    <button @click.ctrl.exact="clear">only ctrl</button>
    <!-- Ctrl 키만 눌려있을 때만 실행됩니다. -->
    <button @click.left="add" @click.right="subtract">{{message}}</button>
  </div>
</template>
<script>
export default {
  props: {},

  data() {
    return {
      counter: 0,
      message: ""
    };
  },

  methods: {
    clear() {
      this.counter = 0;
      this.message = "clear";
    },
    add() {
      ++this.counter;
      this.message = "add";
    },
    subtract() {
      --this.counter;
      this.message = "subtract";
    }
  },

  created() {
    console.log("#### created ####");
  },

  activated() {
    console.log("#### activated ####");
  },

  mounted() {
    console.log("#### mounted ####");
    this.message = `Mounted`;
  },

  deactivated() {
    console.log("#### deactivated ####");
  },

  destroyed() {
    console.log("#### destroyed ####");
  }
};
</script>
