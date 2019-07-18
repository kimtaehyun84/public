<template>
  <div class="sample2">
    <h1>리스트 렌더링</h1>
    <div>
      <h3>배열 렌더링</h3>
      <div v-for="(item, index) in items" :key="index">{{ item.message }}</div>
      <!-- <div v-for="(item, index) of items" :key="index">{{ item.message }}</div> -->
      <!-- <div v-for="(item, index) in object" :key="index">{{ item }}</div> -->
      <!-- <div v-for="(item, index) of object" :key="index">{{ item }}</div> -->
    </div>

    <div>
      <h3>object 렌더링</h3>
      <div v-for="(item, index) of object" :key="index">{{ item }}</div>
    </div>

    <div>
      <h3>object 렌더링, index 표시</h3>
      <div v-for="(value, key, index) in object" :key="key">{{index}}. {{ key }}: {{ value }}</div>
    </div>
    <div>
      <button @click="add">add</button> |
      <button @click="remove">remove</button> |
      <button @click="reverse">reverse</button>
    </div>
  </div>
</template>
<script>
export default {
  props: {},

  data() {
    return {
      items: [{ message: "Foo" }, { message: "Bar" }],
      object: {
        firstName: "John",
        lastName: "Doe",
        age: 30
      },
      i: 1
    };
  },

  methods: {
    add() {
      this.items.push({ message: `add${this.i++}` });
    },

    remove() {
      this.items.pop();
    },

    reverse() {
      this.items.reverse();
    }
  },

  computed: {},

  watch: {},

  created() {
    console.log("#### created ####");
    this.message = "hello world";
  },

  activated() {
    console.log("#### activated ####");
  },

  mounted() {
    console.log("#### mounted ####");
  },

  deactivated() {
    console.log("#### deactivated ####");
  },

  destroyed() {
    console.log("#### destroyed ####");
  }
};
</script>
