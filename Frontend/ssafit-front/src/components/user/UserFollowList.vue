<template>
  <div>
    <br><br>
    <div>
      <h2 class="fw-bold">팔로우 목록</h2>
      <table v-if="user.followList.length">
        <thead>
          <tr>
            <th>닉네임</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(follow, index) in user.followList" :key="index">
            <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follow.id}}">
              <td>{{ follow.nickname }}</td>    
            </router-link> 
            <td><button @click="deleteFollow(follow.id)" class="btn btn-danger">삭제</button></td>
          </tr>
        </tbody>
      </table>
      <div v-else>
        팔로우한 사용자가 없습니다.
      </div>
    </div>
    <br><br><br>
    <div>
      <h2 class="fw-bold">팔로워 목록</h2>
      <table v-if="user.followerList.length">
        <thead>
          <tr>
            <th>닉네임</th>            
          </tr>
        </thead>
        <tbody>
          <tr v-for="(follower, index) in user.followerList" :key="index">
            <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follower.id}}">
              <td>{{ follower.nickname }}</td>   
            </router-link>              
          </tr>
        </tbody>
      </table>
      <div v-else>
        당신을 팔로우한 사용자가 없습니다.
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  computed: {
    ...mapState(['user'])
  },
  methods: {
    deleteFollow(followId) {
      this.$store.dispatch('deleteFollow', followId)
    },
  },
  created() {
    this.$store.dispatch('getFollowList')
    this.$store.dispatch('getFollowerList')
  }
}
</script>

<style scoped>
* {
  text-align: center;
}

table {
  margin-left: auto;
  margin-right: auto;
}
</style>