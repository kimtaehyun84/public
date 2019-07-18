<template>
  <div class="sample3">
    <h1>sample3</h1>
    <div>
      <h1>{{message}}</h1>
    </div>
    <div>
      <button @click="forTest">for</button>
      <button @click="forinTest">for-in</button>
    </div>
    <div>
      <button @click="forEachTest">forEach</button>
      <button @click="forofTest">for-of</button>
    </div>
    <div>
      <button @click="test">Test</button>
    </div>
    <div>{{total}}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: "",
      langs: ["TypeScript", "JavaScript", "Python"],
      total: undefined
    };
  },
  methods: {
    forTest() {
      this.message = "for=>";
      for (let i = 0; i < this.langs.length; i++) {
        this.message += " " + this.langs[i];
      }

      //this.log(this.message);
    },
    forinTest() {
      this.message = "forin=>";
      for (const index in this.langs) {
        this.message += " " + this.langs[index];
      }
    },
    forEachTest() {
      this.message = "forEach=>";
      this.langs.forEach(lang => {
        this.message += " " + lang;
      });
      //this.log(this.message);
    },
    forofTest() {
      this.message = "forof=>";
      for (const lang of this.langs) {
        if (!lang.includes("Script")) {
          break;
        }
        this.message += " " + lang;
      }
      //this.log(this.message);
    },

    // 2, 4, 5, 6, 7, 8, 10 이 표시되도록 message 에 표시하시오
    // 표시한 숫자들의 합을 total 에 표시 하시오
    // log 에 true 가 나오도록 설정 하시오
    test() {
      const numbers = [2, 4, 5, 6, 7, 8, 10, 13, 24];
      this.message = "forof=>";

      let sum = 0;
      let expectedSum = 42;
      for(const num of numbers){
        this.message += ", " + num;
        sum += num;
        if(sum == 42)
          break;


      }

      this.total = sum;
      this.log(expectedSum == sum);
      //this.log(this.message);
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
