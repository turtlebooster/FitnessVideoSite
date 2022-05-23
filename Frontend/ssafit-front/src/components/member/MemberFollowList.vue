<template>
  <div>
    <div>
      <h2 class="fw-bold">팔로우 목록</h2>
      <table v-if="member.followList.length">
        <thead>
          <tr>
            <th>닉네임</th>            
          </tr>
        </thead>
        <tbody>
          <tr v-for="(follow, index) in member.followList" :key="index">
            <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follow.id}}">
              <td>{{ follow.nickname }}</td>    
            </router-link>        
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
      <table v-if="member.followerList.length">
        <thead>
          <tr>
            <th>닉네임</th>            
          </tr>
        </thead>
        <tbody>
          <tr v-for="(follower, index) in member.followerList" :key="index">
            <router-link :to="{name : 'MemberLikeVideo', params: {memberId : follower.id}}">
              <td>{{ follower.nickname }}</td>   
            </router-link>         
          </tr>
        </tbody>
      </table>
      <div v-else>
        '{{member.nickname}}' 님을 팔로우한 사용자가 없습니다.
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