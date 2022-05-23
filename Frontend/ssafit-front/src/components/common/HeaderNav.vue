<template>
  <header>
    <nav class="header-nav">
      <div>
        <router-link to="/">SSAFIT</router-link>
      </div>
      <div>
        <!-- <button id="myModal" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myInput">
          Launch static backdrop modal
        </button> -->
        <router-link v-if="!isLogin" :to="{name : 'UserLogin'}">로그인 </router-link>
        <a href="/" v-else @click="doLogout">로그아웃 </a>
        <router-link v-if="!isLogin" :to="{name : 'UserRegist'}">회원가입 </router-link>
        <router-link v-else :to="{name : 'UserInfo'}">마이페이지 </router-link>
      </div>
    </nav>
    <!-- 모달 테스트 -->  
    <!-- <div class="modal fade" id="myInput" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            ...
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Understood</button>
          </div>
        </div>
      </div>
    </div> -->
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
  created() {
    const myModal = document.getElementById('myModal')
    const myInput = document.getElementById('myInput')

    myModal.addEventListener('shown.bs.modal', () => {
    myInput.focus()
})
  }
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