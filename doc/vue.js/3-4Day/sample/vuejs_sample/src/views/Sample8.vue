<template>
  <div class="sample8">
    <h1>directive</h1>
    <div>
      <!-- <label>no focus</label> -->
      <input placeholder="no focus" />
    </div>
    <div>
      <!-- <label>focus</label> -->
      <input v-focus placeholder="focus" />
    </div>
    <div v-binding-info:moon.useless.guy="message"></div>
    <p></p>
    <div v-binding-info="{color:'white', text:'hello!'}"></div>
  </div>
</template>
<script>
export default {
  // directives: {
  //   focus: {
  //     inserted(el) {
  //       el.focus();
  //     }
  //   }
  // },
  props: {},

  data() {
    return {
      message: "hello"
    };
  },

  methods: {},

  computed: {},

  created() {
    console.log("#### parent created ####");
  },

  activated() {
    console.log("#### activated ####");
  },

  mounted() {
    console.log("#### parent mounted ####");
    //this.send();
  },

  deactivated() {
    console.log("#### deactivated ####");
  },

  destroyed() {
    console.log("#### parent destroyed ####");
  }
};
</script>
