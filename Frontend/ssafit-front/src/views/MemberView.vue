<template>
  <div >
    <div id="member-backgraund">
      <br>
      <div class="d-flex justify-content-center">        
        <h2 class="fw-bold">'{{member.nickname}}' 님 페이지</h2>
        <span v-if="!isMe" class="d-flex justify-content-end">
          <button v-if="!followed" @click="follow" class="btn btn-outline-primary"><i class="bi bi-person-plus-fill"></i></button>
          <button v-else @click="unfollow" class="btn btn-outline-danger"><i class="bi bi-person-x-fill"></i></button>
        </span>
      </div>
       <!-- 메뉴 -->
      <div class="d-flex justify-content-center py-3">
        <ul class="nav nav-pills">
          <li class="nav-item"><router-link :to="{name: 'MemberLikeVideo'}">찜 영상</router-link> |</li>
          <li class="nav-item"><router-link :to="{name: 'MemberReview'}">'{{member.nickname}}' 님이 작성한 리뷰</router-link> |</li>
          <li class="nav-item"><router-link :to="{name: 'MemberFollowList'}">팔로우</router-link></li>
        </ul>
      </div>
    </div>
      <router-view/>
  </div>
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

<style scoped>
#member-backgraund {
  background: linear-gradient(87deg,#2dce89,#2dcecc);
}

#container {
  margin-left: 25%;
  margin-right: 25%;
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

i {
  font-size: 20px;
}
</style>