<template>
  <div class="about">
    <h1>This is an about page</h1>
    <button @click="onClick(1)">arrayTest</button>
    <button @click="onClick(2)">tupleTest</button>
    <button @click="onClick(3)">objectTest</button>
    <button @click="onClick(4)">functionTest</button>
    <button @click="onClick(5)">genericTest</button>
    <h3>{{message}}</h3>
  </div>
</template>
<script lang="ts">
import Vue from "vue";
import { BaseTest } from "../typescript/BaseType";
export default Vue.extend({
  data() {
    return {
      message: {}
    };
  },

  methods: {
    onClick(type: number) {
      let baseTest = new BaseTest();
      switch (type) {
        case 1: {
          this.message = baseTest.arrayTest();
          break;
        }
        case 2: {
          this.message = baseTest.tupleTest();
          break;
        }
        case 3: {
          this.message = baseTest.objectTest();
          break;
        }
        case 4: {
          this.message = baseTest.functionTest();
          break;
        }
        case 5: {
          this.message = "genericTest";
          baseTest.genericTest();
          break;
        }
      }
    }
  },
  mounted() {
    this.message = "";
  }
});
</script>


