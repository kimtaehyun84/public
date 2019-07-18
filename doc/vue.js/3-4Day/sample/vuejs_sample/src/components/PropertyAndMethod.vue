<template>
  <div class="sample">
    <h1>Property and Method</h1>
    <h1>{{data}}</h1>
    <h1>{{message}}</h1>
    <h1>{{reverseMessage}}</h1>
    <input type="text" v-model="message" />
    <h1>{{changeMessage}}</h1>
    <button @click="upperMessage">Upper Message</button>
    <button @click="reverseMsg">Reverse Message</button>
  </div>
</template>
<script>
export default {
  data() {
    return {
      data: { name: "moon", id: "useless" },
      message: "",
      changeMessage: "",
      reverseMessage: ""
    };
  },

  methods: {
    upperMessage() {
      this.changeMessage = this.message.toUpperCase();
    },

    reverseMsg() {
      this.reverseMessage = this.message
        .split("")
        .reverse()
        .join("");
    }
  }
};
</script>
