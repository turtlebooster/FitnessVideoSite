<template>
  <div class="container">
    <br>
    <div class="d-flex justify-content-evenly">      
      <!-- 팔로우 목록 -->
      <div>
        <h2 class="fw-bold">팔로우 목록</h2>
        <table v-if="member.followList.length">
          <thead>
            <tr>
              <th></th>            
            </tr>
          </thead>
          <tbody>
            <tr v-for="(follow, index) in member.followList" :key="index">
              <td>
                <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follow.id}}">
                  <img src="../../assets/profile/basic_profile.jpg" width="50px">
                  </router-link>
              </td>
              <td style="text-align : left;">
                <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follow.id}}" style=" margin-left: 10px;">{{ follow.nickname }}</router-link>         
              </td>    
            </tr>
          </tbody>
        </table>
        <div v-else>
          팔로우한 사용자가 없습니다.
        </div>
      </div>
      <br><br><br>
      <!-- 팔로워 목록 -->
      <div>
        <h2 class="fw-bold">팔로워 목록</h2>
        <table v-if="member.followerList.length">
          <thead>
            <tr>
              <th></th>            
              <th></th>            
            </tr>
          </thead>
          <tbody>
            <tr v-for="(follower, index) in member.followerList" :key="index">              
              <td>
                <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follower.id}}">
                  <img src="../../assets/profile/basic_profile.jpg" width="50px">
                  </router-link>
              </td>
              <td style="text-align : left;">
                <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follower.id}}" style=" margin-left: 10px;">{{ follower.nickname }}</router-link>         
              </td>              
            </tr>
          </tbody>
        </table>
        <div v-else>
          '{{member.nickname}}' 님을 팔로우한 사용자가 없습니다.
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  computed: {
    ...mapState(['member'])
  },
  created() {
    this.$store.dispatch('getMemberFollowList', this.member.id)
    this.$store.dispatch('getMemberFollowerList', this.member.id)
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