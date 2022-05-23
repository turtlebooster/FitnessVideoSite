<template>
  <b-container>
    <div v-if="user.id.length">
      <h2 class="fw-bold">'{{user.nickname}}' 님 페이지</h2>
      <!-- 메뉴 -->
      <div class="d-flex justify-content-center py-3">
        <ul class="nav nav-pills">
          <li class="nav-item"><router-link :to="{name: 'UserInfo'}">회원 정보</router-link>| </li>
          <li class="nav-item"><router-link :to="{name: 'UserLikeVideo'}">찜 영상</router-link>| </li>
          <li class="nav-item"><router-link :to="{name: 'UserReview'}">내가 쓴 리뷰</router-link>| </li>
          <li class="nav-item"><router-link :to="{name: 'UserCalander'}">캘린더</router-link>| </li>
          <li class="nav-item"><router-link :to="{name: 'UserFollowList'}">팔로우</router-link></li>
        </ul>
      </div>
    </div>
      <router-view/>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed:{
    ...mapState(["user"])
  }
}
</script>

<style scoped>
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
</style>