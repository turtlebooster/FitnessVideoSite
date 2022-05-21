<template>
  <div>
    <h2>리뷰 목록</h2>
    <table>
      <thead>
        <tr>
          <th>영상 썸네일</th>
          <th>제목</th>
          <th>리뷰 내용</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="review in user.reviews" :key="review.no">
          <td>
            <router-link :to="`/detail/${review.videoId}`">
              <img
                    width="160"
                    height="90"
                    :src="`https://img.youtube.com/vi/${review.videoId}/mqdefault.jpg`"
                    title="YouTube video player"
                    frameborder="0"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen
                >
            </router-link>
          </td>
          <td>            
            <router-link :to="`/detail/${review.videoId}`">
              {{ review.videoTitle }}              
            </router-link>  
          </td>
          <td>
            <router-link :to="`/detail/${review.videoId}`">
              {{ review.content }}
            </router-link>   
          </td>
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
    this.$store.dispatch('getUserReview');   
  },
}
</script>

<style>

</style>