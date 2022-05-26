<template>
  <div class="background">
    <div v-if="user.id.length" id="user-backgraund">
      <br>
      <div>
        <h2 class="fw-bold">'{{user.nickname}}' 님 페이지</h2>
        <!-- 메뉴 -->
        <div class="d-flex justify-content-center py-3">
          <ul class="nav nav-pills menu">
            <li class="nav-item" @click="getActive(0)"><router-link :to="{name: 'UserInfo'}" :class="{active: isActive === 0}">회원 정보</router-link></li>
            <li class="nav-item" @click="getActive(1)"><router-link :to="{name: 'UserLikeVideo'}" :class="{active: isActive === 1}">찜 영상</router-link></li>
            <li class="nav-item" @click="getActive(2)"><router-link :to="{name: 'UserReview'}" :class="{active: isActive === 2}">내가 쓴 리뷰</router-link></li>
            <li class="nav-item" @click="getActive(3)"><router-link :to="{name: 'UserCalander'}" :class="{active: isActive === 3}">캘린더</router-link></li>
            <li class="nav-item" @click="getActive(4)"><router-link :to="{name: 'UserFollowList'}" :class="{active: isActive === 4}">팔로우</router-link></li>
          </ul>
        </div>
      </div>
    </div>      
      <router-view/>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      isActive: 0,
    }
  },
  methods: {
    getActive(num) {
      this.isActive = num
    }
  },
  computed:{
    ...mapState(["user"])
  }
}
</script>

<style scoped>
/* #user-backgraund {
  background: linear-gradient(87deg,#2dce89,#2dcecc);
} */

h2 {
  text-align: center;
}

a {
  text-decoration: none;
  color: rgb(106, 106, 106);
  font-weight: bold;
  margin: 5px;
}

a:hover {
  color: rgb(3, 60, 231);  
  font-weight: bolder;
}

/* 메뉴 밑줄 */
.menu{
   /* font-size: 16pt; */
   text-align: center;/*중앙정렬*/
   /* background: #00b3d3; */
   /* padding: 20px 0 20px 0; */   
}
.menu li {
  display: inline-block;/*여백 없이 좌측으로 붙은 li*/
  /* padding: 0 40px 0 0; */
  padding: 0 5px 0 5px;
}
.menu a{
   /* font-family:'Noto Serif KR', serif;  폰트 */
   color: rgb(0, 0, 0);/*폰트컬러*/
   font-weight: 200;/*폰트굵기*/
   text-decoration: none;/*a href 밑줄 등 글자 꾸밈 없음*/
   /* line-height: 40px; */
}
.menu a:after {/*after 가상요소*/
   display:block;/*a요소를 블록 요소라고 선언*/
   /* width:70px;카테고리 메뉴 밑줄의 크기를 동일하게 주기 위해 width 설정 */
/*혹시 동일하지 않길 바란다면 width 삭제*/
   content: '';
   border-bottom: solid 2px rgb(0, 0, 0);
   transform: scaleX(0);/*크기를 0으로 줌으로써 평상시엔 밑줄 없음*/
   transition: transform 250ms ease-in-out; /*변형 방식*/
}
.menu a:hover:after {
   transform: scaleX(1);/*a 속성에 hover시 기존 크기로*/
}

a.router-link-exact-active.router-link-active{
  font-weight: 500;
  color: rgb(0, 0, 0);
}

a.router-link-exact-active.router-link-active::after {  
  content: '';
  border-bottom: solid 2px rgb(0, 0, 0);
  transform: scaleX(1);/*크기를 0으로 줌으로써 평상시엔 밑줄 없음*/   
}

.background {
  z-index: 1;  
  background: no-repeat center center fixed;
    position: absolute;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;  
  content: "";
  position: relative;
  min-height: 100vh;
}
.background::after {
  top: 0;
  left: 0;
  z-index: -1;
  content: "";
  width: 100%;
  height: 100%;  
  background: url(/src/assets/headerImage/woman_excercise.jpg)  no-repeat center center fixed;  
  opacity: 0.3;
  position: absolute;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
</style>