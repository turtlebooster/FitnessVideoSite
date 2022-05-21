<template>
  <div>
    <h2>유저 followList</h2>
    <div>
      <h2>팔로우 목록</h2>
      <table>
        <thead>
          <tr>
            <th>닉네임</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(follow, index) in follows" :key="index">
            <td></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div>
      <h2>팔로워 목록</h2>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>