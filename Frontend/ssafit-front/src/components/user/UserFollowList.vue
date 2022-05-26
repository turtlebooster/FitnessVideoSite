<template>
  <div class="container">
    <br>
    <div class="d-flex justify-content-evenly">
      <div>
        <h2 class="fw-bold">팔로우 목록</h2>
        <table v-if="user.followList.length">
          <thead>
            <tr>
              <th></th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(follow, index) in user.followList" :key="index">
              <td style="width: 100px;">
                <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follow.id}}">
                  <img src="../../assets/profile/basic_profile.jpg" width="50px">
                  </router-link>
              </td>
              <td style="text-align : left; width: 100px;">
                <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follow.id}}" style=" margin-left: 10px;">{{ follow.nickname }}</router-link>         
              </td>               
              <td><button @click="deleteFollow(follow.id)" class="btn btn-danger" style=" margin-left: 10px;">삭제</button></td>
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
              <th></th>            
              <th></th>            
            </tr>
          </thead>
          <tbody>
            <tr v-for="(follower, index) in user.followerList" :key="index">
              <td style="width: 100px;">
                <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follower.id}}">
                  <img src="../../assets/profile/basic_profile.jpg" width="50px">
                  </router-link>
              </td>
              <td style="text-align : left; ">
                <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follower.id}}" style=" margin-left: 10px;">{{ follower.nickname }}</router-link>         
              </td>             
            </tr>
          </tbody>
        </table>
        <div v-else>
          당신을 팔로우한 사용자가 없습니다.
        </div>
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
  width: 300px;
}

a {
  text-decoration: none;
  color: black;
}

tr:hover {
  color: black;
  background: rgba(56, 56, 56, 0.217);
}
</style>