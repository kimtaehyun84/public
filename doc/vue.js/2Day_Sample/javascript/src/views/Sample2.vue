<template>
  <div class="sample2">
    <h1>sample2</h1>
    <div>
      <h1>{{message}}</h1>
    </div>

    <div>
      <button @click="destructuringArray">배열 비구조화 할당</button>
      <button @click="destructuringObject">객체 비구조화 할당</button>
    </div>
    <div>
      <button @click="restOperator">나머지 연산자</button>
      <button @click="spreadOperator">전개 연산자</button>
    </div>
    <div>
      <button @click="defaultParamenter(1,2,5)">기본 매개변수</button>
      <button @click="arrowFunction(1,2)">화살표 함수</button>
    </div>
    <div>
      <button @click="test">Test</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: ""
    };
  },
  methods: {
    destructuringArray() {
      const [a, b] = [1, 2];
      this.message = `a = ${a}, b = ${b}`;
      //this.log(this.message);
    },
    destructuringObject() {
      const obj = { a: 1, b: 2 };
      this.message = `obj.a = ${obj.a}, obj.b = ${obj.b}`;
      //this.log(abc);
    },
    restOperator() {
      const [a, ...restArr] = [1, 2, 3, 4];
      this.message = `a = ${a}, restArr = ${restArr}`;
      //this.log(abc);
    },
    spreadOperator() {
      const arr = [1, 2, 3];
      this.testAnyThing(...arr);
    },

    testAnyThing(a, b, c) {
      this.message = `a = ${a}, b = ${b}, c= ${c}`;
    },

    defaultParamenter(a, b, c = 3) {
      this.message = `a = ${a}, b = ${b}, c= ${c}`;
    },

    arrowFunction(a, b, c = 3) {
      let arrow = () => {
        a = a + b;
        b = c + 1;
      };
      arrow();
      console.log(arrow.toString());
      this.message = `a = ${a}, b = ${b}, c= ${c}`;
    },

    // 자 수정해 봅시다~~!!!
    test() {
      this.message = "응! 아니야!";
      const arrNumbers = [1, 2, 3, 4, 5, 6];
      const [a,b, ...c] = arrNumbers;

      this.message = `a = ${a}, b=${b}, c=${c}`;
      // const a = arrNumbers[0];
      // const b = arrNumbers[1];
      // 구조화 할당 과 나머지연산자 를통해 위 배열을 a = 1, b =2, c = [3,4,5,6]이 되도록 할당 하시오

      // a = 1, b = 2, c=[3,4,5,6];
      // 템플릿 리터럴을 통해 위 message 를 출력하시오
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
