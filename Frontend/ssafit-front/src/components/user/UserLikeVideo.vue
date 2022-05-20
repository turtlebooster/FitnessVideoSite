<template>
  <div>
    <h2>
      찜 영상 목록
    </h2>
    <table>
      <thead>
        <tr>
          <th>영상</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="video in user.likeVideos" :key="video.id">
          <td>
            <img
                width="320"
                height="180"
                :src="`https://img.youtube.com/vi/${video.id}/mqdefault.jpg`"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                allowfullscreen
            >
          </td>
          <td><b-button variant="danger">삭제</b-button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {  
  computed: {
    ...mapState(['user'])
  },
  created() {
    this.$store.dispatch('getLikeVideo');   
  },
}
</script>

<style>

</style>