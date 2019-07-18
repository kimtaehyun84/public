<template>
  <div class="sample4">
    <h1>sample4</h1>
    <div>
      <h1>{{message}}</h1>
    </div>
    <div>
      <button @click="callbackTest">callbackTest</button>
      <button @click="promiseTest">promiseTest</button>
      <button @click="asyncAwaitTest">asyncAwaitTest</button>
    </div>
  </div>
</template>

<script>
//import { setTimeout } from "timers";
const delay = t => new Promise(resolve => setTimeout(resolve, t));
export default {
  data() {
    return {
      message: "",
      langs: ["TypeScript", "JavaScript", "Python"]
    };
  },
  methods: {
    callbackTest() {
      setTimeout(() => {
        this.message = 1;
        console.log(this.message);
        setTimeout(() => {
          this.message = 2;
          console.log(this.message);
          setTimeout(() => {
            this.message = 3;
            console.log(this.message);
            setTimeout(() => {
              this.message = 4;
              console.log(this.message);
              setTimeout(() => {
                this.message = 5;
                console.log(this.message);
              }, 1000);
            }, 1000);
          }, 1000);
        }, 1000);
      }, 1000);
    },
    promiseTest() {
      //const delay = t => new Promise(resolve => setTimeout(resolve, t));

      delay(1000)
        .then(() => {
          this.message = 1;
          console.log(this.message);
          return delay(1000);
        })
        .then(() => {
          this.message = 2;
          console.log(this.message);
          return delay(1000);
        })
        .then(() => {
          this.message = 3;
          console.log(this.message);
          return delay(1000);
        })
        .then(() => {
          this.message = 4;
          console.log(this.message);
          return delay(1000);
        })
        .then(() => {
          this.message = 5;
          console.log(this.message);
        });
    },
    async asyncAwaitTest() {
      await this.delayAndMessage(1);
      await this.delayAndMessage(2);
      await this.delayAndMessage(3);
      await this.delayAndMessage(4);
      await this.delayAndMessage(5);
    },

    delay(t) {
      return new Promise(resolve => setTimeout(resolve, t));
    },

    async delayAndMessage(message, delay = 1000) {
      await this.delay(delay);
      this.message = message;
      console.log(this.message);
    },

    log(data) {
      //let displayMessage = `${this.log.caller.name} : ${data}`;
      console.log(data);
    }
  },

  created() {},

  mounted() {},

  destroyed() {}
};
</script>
