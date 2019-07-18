<template>
  <div class="sample">
    <h1>Computed And Watch</h1>
    <h1>{{message}}</h1>
    <h1>{{reverseMessage}}</h1>
    <input type="text" v-model="message" />
    <h1>{{changeMessage}}</h1>
    <!-- <button @click="upperMessage">Upper Message</button>
    <button @click="reverseMessage">Reverse Message</button>-->
  </div>
</template>
<script>
export default {
  data() {
    return {
      message: "",
      changeMessage: ""
    };
  },

  methods: {},

  computed: {
    reverseMessage() {
      return this.message
        .split("")
        .reverse()
        .join("");
    }
  },

  watch: {
    message(newVal, oldVal) {
      //console.log("하하");
      this.changeMessage = `changed message ${oldVal} ==>  ${newVal} `;
    }
  }
};
</script>
