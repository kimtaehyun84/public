<template>
  <div class="sample4">
    <h1>Component Props</h1>
    <div>{{initMessage}}</div>
    <div>{{message}}</div>
  </div>
</template>
<script>
export default {
  props: {
    initMessage: {
      type: String,
      default: "문먹자"
    }
  },

  data() {
    return {
      counter: 0,
      message: ""
    };
  },

  methods: {},

  computed: {},

  watch: {
    initMessage() {
      this.message += "initMessage 변경됨 " + this.initMessage;
    }
  },

  mounted() {
    this.message = "Mounted 됨";
  }
};
</script>
