<template>
  <div>
    <!-- 헤더 -->
    <header>
      <nav class="header-nav">
        <div>
          <router-link to="/" class="nav-logo"><img class="nav-logo-image" src="../../assets/headerImage/SSAFIT.png" alt=""></router-link>
        </div>
        <div class="d-flex">
          <div class="nav-menu" >
            <router-link v-if="!isLogin" :to="{name : 'UserLogin'}">로그인 </router-link>
            <a href="/" v-else @click="doLogout">로그아웃 </a>
          </div>
          <div class="nav-menu">
            <router-link v-if="!isLogin" :to="{name : 'UserRegist'}">회원가입 </router-link>
            <router-link v-else :to="{name : 'UserInfo'}">마이페이지 </router-link>
          </div>
        </div>
      </nav>    
    </header>
    <!-- 이미지 -->
    <!-- <div class="d-flex img-box">
      <img src="../../assets/headerImage/man_excercise.jpg">      
      <img src="../../assets/headerImage/woman_excercise.jpg">
    </div> -->
    </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  computed: {
    ...mapState(["isLogin"]),
  },
  methods: {
    ...mapMutations(["USER_LOGOUT"]),
    doLogout(){
      this.USER_LOGOUT();
    }
  },
}
</script>

<style scoped>
header {
  height: 70px;
  background-color: black;
  line-height: 65px;
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

.nav-menu {   
  padding-left: 10px;
  padding-right: 10px;  
}

.nav-logo-image {
  padding: 0;
  margin: 0;
  height: 65px;
}


a:after {/*after 가상요소*/
   display:block;/*a요소를 블록 요소라고 선언*/
   /* width:70px;카테고리 메뉴 밑줄의 크기를 동일하게 주기 위해 width 설정 */
/*혹시 동일하지 않길 바란다면 width 삭제*/
   content: '';
   border-bottom: solid 5px #fff;
   transform: scaleX(0);/*크기를 0으로 줌으로써 평상시엔 밑줄 없음*/
   transition: transform 250ms ease-in-out; /*변형 방식*/
}
a:hover:after {
   transform: scaleX(1);/*a 속성에 hover시 기존 크기로*/
}

.img-box {
  height: 200px;
}
</style>