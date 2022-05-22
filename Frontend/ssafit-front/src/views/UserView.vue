<template>
  <b-container>
    <div v-if="user.id.length">
      <h2>{{user.nickname}} 님 페이지</h2>
      <div class="justify-content-md-center">
        <router-link :to="{name: 'UserInfo'}">회원 정보</router-link> |
        <router-link :to="{name: 'UserLikeVideo'}">찜 영상</router-link> |
        <router-link :to="{name: 'UserReview'}">내가 쓴 리뷰</router-link> |
        <router-link :to="{name: 'UserCalander'}">캘린더</router-link> |
        <router-link :to="{name: 'UserFollowList'}">팔로우</router-link>
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

<style>

</style>