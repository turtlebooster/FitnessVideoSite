<template>
  <div>
      <h2>{{user.nickName}} 님 페이지</h2>
      <div>
        <router-link :to="{name: 'UserInfo'}">회원 정보</router-link> |
        <router-link :to="{name: 'UserLikeVideo'}">찜 영상</router-link> |
        <router-link :to="{name: 'UserReview'}">내가 쓴 리뷰</router-link> |
        <router-link :to="{name: 'UserCalander'}">캘린더</router-link> |
        <router-link :to="{name: 'UserFollowList'}">팔로우</router-link>
      </div>
      <router-view/>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user : {
        nickName : "ssafy",
      }
    }
  }
}
</script>

<style>

</style>