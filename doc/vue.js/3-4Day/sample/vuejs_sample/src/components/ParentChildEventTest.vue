<template>
  <div class="sample4">
    <h1>Parent Child Event</h1>
    <div id="example-1">
      <button v-on:click="add">Add</button> |
      <button v-on:click="subtract">Subtract</button>
      <p>위 버튼을 클릭한 횟수는 {{ counter }} 번 입니다.</p>
    </div>
    <!-- <button @click.left="add" @click.right="subtract">{{message}}</button> -->
  </div>
</template>
<script>
export default {
  data() {
    return {
      counter: 0,
      totalValue: 0,
      message: ":)"
    };
  },

  methods: {
    clear() {
      this.counter = 0;
      this.totalValue = 0;
      this.message = "clear";
    },
    add() {
      ++this.counter;
      ++this.totalValue;
      this.message = "add";
      this.$emit("added", this.totalValue.toString());
    },
    subtract() {
      ++this.counter;
      --this.totalValue;
      this.message = "subtract";
      this.$emit("substracted", this.totalValue.toString());
    }
  },

  computed: {}
};
</script>
