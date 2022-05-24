<template>
  <div class="container">
    <br>
    <h2 class="fw-bold">리뷰 목록</h2>
    <table v-if="user.reviews.length">
      <thead>
        <tr>
          <th id="thumnail-col">영상 썸네일</th>
          <th id="title-col">제목</th>
          <th id="review-col">리뷰 내용</th>
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
    <div v-else class="center">
      작성한 리뷰가 없습니다.
    </div>
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
h2 {
  text-align: center;
}
.center {
  text-align: center;
}
#thumnail-col {
  width: 15%;
}

#review-col,
#title-col {
  width: 40%;
}
</style>