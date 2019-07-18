<template>
  <div class="sample">
    <img alt="Vue logo" src="../assets/logo.png" />
    <div>
      <h1>{{message}}</h1>
    </div>
    <div>
      <button @click="scope_block">scope_block</button>
      <button @click="scope_function">scope_function</button>
      <button @click="hoisting">hoisting</button>
      <button @click="scope_let">let_block</button>
      <button @click="scope_var">var</button>
      <button @click="scopeBlockUsingLet">scopeBlockUsingLet</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: 0
    };
  },
  methods: {
    scope_block() {
      var abc = 123;
      if (true) {
        var abc = 456;
      }
      this.message = abc;
      this.log(abc);
    },

    scope_function() {
      var abc = 123;
      function bar() {
        var abc = 456;
      }
      this.message = abc;
      this.log(abc);
    },

    hoisting() {
      this.log(a); // undefined
      this.message = a;
      var a = 123;
    },
    scope_let() {
      {
        let goOut = true;
        if (true) {
          let goOut = false;
        }
        this.log(goOut); // true
        goOut = false;
        this.log(goOut); // false
        this.message = goOut;
      }
      this.log(goOut);
    },
    scope_var() {
      var a = 1;
      var a = 2;
      this.message = a;
      this.log(a);
    },
    scope_let2() {
      let a = 1;
      //let a = 2;
      //console.log(this.scope_let2.name);
      this.message = a;
      this.log(a);
    },
    constTest() {
      const a = 1;
      //a = 2; <-- error 재할당 불가
      this.message = a;
    },

    // scope_block 을 block_scope 에 맞게 let 으로 구현
    scopeBlockUsingLet() {
      //scope_block 함수를 let 을 이용하여 123 이 나오도록 변환
      var abc = 123;
      if (true) {
        let abc = 456;
      }
      this.message = abc;
      this.log(abc);
    },

    log(data) {
      //let displayMessage = `${this.log.caller.name} : ${data}`;
      console.log(data);
    }
  },

  created() {},

  mounted() {
    //this.scope();
    // this.scope2();
    // this.hoisting();
    //this.scope_let();
    // this.scope_var2();
    // this.scope_let2();
  },

  destroyed() {}
};
</script>
