<template>
  <div>
    <h2>유저 followList</h2>
    <div>
      <h2>팔로우 목록</h2>
      <table>
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
    </div>
    <div>
      <h2>팔로워 목록</h2>
      <table>
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

<style>

</style>