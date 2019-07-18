<template>
  <div class="about">
    <h1>This is an Sample2 page</h1>
    <button @click="onClick(1)">KuKu</button>
    <button @click="onClick(2)">SeniorCitizens</button>
    <button @click="onClick(3)">Master</button>
    <button @click="onClick(4)">Moon</button>
    <button @click="onClick(5)">ChaCha</button>
    <h3>이름 : {{unitData.name}}</h3>
    <h3>별명 : {{unitData.nick}}</h3>
    <h3>근력 : {{unitData.power}}</h3>
    <h3>지능 : {{unitData.int}}</h3>
    <h3>민첩 : {{unitData.agility}}</h3>
    <h3>스킬 : {{skill}}</h3>
    <h3>{{description}}</h3>
    <p />
  </div>
</template>
<script lang="ts">
import Vue from "vue";
import * as Test from "../typescript/ClassTest3";
export default Vue.extend({
  data() {
    return {
      skill: "",
      description: "",
      unitData: {}
    };
  },

  methods: {
    onClick(type: number) {
      let unit!: Test.IUnit;

      switch (type) {
        case 1: {
          unit = new Test.KuKu();
          break;
        }
        case 2: {
          unit = new Test.SeniorCitizens();
          break;
        }
        case 3: {
          unit = new Test.JWMaster();
          break;
        }
        case 4: {
          unit = new Test.Moon();
          break;
        }
        case 5: {
          unit = new Test.ChaCha();
          break;
        }
      }
      this.unitData = unit;
      this.skill = (this.unitData as Test.IUnit).skill();
      this.description = (this.unitData as Test.IUnit).description();
    }
  }
});
</script>


