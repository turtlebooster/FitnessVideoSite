<template>
  <header>
    <nav class="header-nav">
      <div>
        <router-link :to="{ name: 'home' }">SSAFIT</router-link>
      </div>
      <div>
        <router-link to="#1">로그인 </router-link>
        <router-link to="#2">회원가입 </router-link>
        <router-link to="#3">마이페이지 </router-link>
      </div>
    </nav>
  </header>
</template>

<script>
export default {

}
</script>

<style>
header {
  height: 70px;
  background-color: black;
  line-height: 70px;
  padding: 0px 30px;
}

.header-nav {
  display: flex;
  justify-content: space-between;
}

nav a {
  font-weight: bold;
  color: white;
  text-decoration: none;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>