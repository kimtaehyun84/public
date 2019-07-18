<template>
  <div class="sample4">
    <h1>이벤트 핸들러</h1>
    <div>{{message}}</div>
    <div id="example-1">
      <button v-on:click="counter += 1">Add 1</button>
      <p>위 버튼을 클릭한 횟수는 {{ counter }} 번 입니다.</p>
    </div>

    <div @click="addBubble">
      <div @click="addBubble">버블링 {{bubbleCounter}}</div>
    </div>
    <div @click="addBubble">
      <div @click.stop="addBubble">버블링안됨 {{bubbleCounter}}</div>
    </div>

    <div>
      not prevent
      <a href="sample3" @click="addPrevent">{{preventCounter}}</a>
    </div>
    <div>
      prevent
      <a href="sample3" @click.prevent="addPrevent">{{preventCounter}}</a>
    </div>

    <div></div>
    <div></div>
  </div>
</template>
<script>
export default {
  props: {},

  data() {
    return {
      counter: 0,
      message: "",
      bubbleCounter: 0,
      preventCounter: 0
    };
  },

  methods: {
    clear() {
      this.counter = 0;
      this.bubbleCounter = 0;
      this.preventCounter = 0;
    },
    add() {
      ++this.counter;
    },
    subtract() {
      --this.counter;
    },
    onBubbleMessage() {
      this.message = "click 메소드 bubble";
    },

    addBubble() {
      ++this.bubbleCounter;
    },

    addPrevent() {
      ++this.preventCounter;
    }
  }
};
</script>

