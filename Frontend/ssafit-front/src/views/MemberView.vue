<template>
  <b-container>
      <div>
        <h2>{{member.nickname}} 님 페이지</h2>
        <div v-if="!isMe">
          <button v-if="!followed" @click="follow">팔로우</button>
          <button v-else @click="unfollow">언팔로우</button>
        </div>
      </div>
      <div class="justify-content-md-center">
        <router-link :to="{name: 'MemberLikeVideo'}">찜 영상</router-link> |
        <router-link :to="{name: 'MemberReview'}">내가 쓴 리뷰</router-link> |        
        <router-link :to="{name: 'MemberFollowList'}">팔로우</router-link>
      </div>
      <router-view/>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed:{
    ...mapState(["member", "user"]),
    followed() {      
      const followList = this.user.followList
      for (let i = 0; i < followList.length; i++) {
        if (followList[i].id == this.member.id) {
          return true
        }
      }
      return false
    },
    isMe() {
      return (!this.user.id || this.user.id == this.member.id)
    }
  },
  methods: {
    follow() {
      this.$store.dispatch('insertFollow', this.member.id)
    },
    unfollow() {
      this.$store.dispatch('deleteFollow', this.member.id)
    }
  }
}
</script>

<style>

</style>