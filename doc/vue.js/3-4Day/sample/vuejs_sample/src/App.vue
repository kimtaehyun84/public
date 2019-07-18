<template>
  <div id="app">
    <div id="nav">
      <!-- <router-link to="/">Home</router-link>| -->
      <router-link to="/sample">Sample</router-link>|
      <router-link to="/sample2">Sample2</router-link>|
      <router-link to="/sample3">Sample3</router-link>|
      <router-link to="/sample4">Sample4</router-link>|
      <router-link to="/sample5">Sample5</router-link>|
      <router-link to="/sample6">Sample6</router-link>|
      <router-link to="/sample7">Sample7</router-link>|
      <router-link to="/sample8">Sample8</router-link>|
      <router-link to="/sample9">Sample9</router-link>|
      <router-link to="/login/1">Login</router-link>|
    </div>
    <!-- <keep-alive> -->
    <router-view :key="$route.fullPath" />
    <!-- </keep-alive> -->
  </div>
</template>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
