<template>
  <header>
    <nav class="header-nav">
      <div>
        <router-link to="/">SSAFIT</router-link>
      </div>
      <div>
        <router-link v-if="!isLogin" :to="{name : 'UserLogin'}">로그인 </router-link>
        <a href="/" v-else @click="doLogout">로그아웃 </a>
        <router-link v-if="!isLogin" :to="{name : 'UserRegist'}">회원가입 </router-link>
        <router-link v-else :to="{name : 'UserInfo'}">마이페이지 </router-link>
      </div>
    </nav>
  </header>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  computed: {
    ...mapState(["isLogin"])
  },
  methods: {
    ...mapMutations(["USER_LOGOUT"]),
    doLogout(){
      this.USER_LOGOUT();
    }
  },
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