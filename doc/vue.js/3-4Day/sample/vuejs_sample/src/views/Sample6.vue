<template>
  <div class="sample6">
    <h1>Slots</h1>
    <slot-normal />
    <slot-named>
      <template v-slot:header>세계제일 TNS Hyosung</template>
      <template v-slot:footer>
        <h1>우주제일 TNS Hyosung</h1>
      </template>
    </slot-named>
    <slot-scoped></slot-scoped>
    <slot-scoped>
      <!-- <template v-slot:default="slotProps">
        <div v-for="(item,index) in slotProps.items" :key="index">
          <h1>{{item.name}} : {{item.nick}}</h1>
        </div>
      </template>-->
      <!-- <template v-slot="slotProps">
        <div v-for="(item,index) in slotProps.items" :key="index">
          <h1>{{item.name}} : {{item.nick}}</h1>
        </div>
      </template>-->
      <template v-slot="{items}">
        <div v-for="(item,index) in items" :key="index">
          <h1>{{item.name}} : {{item.nick}}</h1>
        </div>
      </template>
    </slot-scoped>
  </div>
</template>
<script>
import SlotNormal from "../components/SlotNormal.vue";
import SlotNamed from "../components/SlotNamed.vue";
import SlotScoped from "../components/SlotScoped.vue";
export default {
  components: {
    SlotNormal,
    SlotNamed,
    SlotScoped
  },
  props: {},

  data() {
    return {
      items: []
    };
  },

  methods: {
    add() {
      this.items.push({ name: "newData", nick: "neo" });
    }
  },

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
