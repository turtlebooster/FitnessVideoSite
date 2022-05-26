<template>
  <div class="container">
    <br>
    <h2 class="fw-bold">리뷰 목록</h2>
    <div class="d-flex justify-content-center">
      <div>
        <div v-if="user.reviews.length" class="flex-fill ">
          <div v-for="review in user.reviews" :key="review.no" class="d-flex flex-column comment">
            <div>
              <td>
                <router-link :to="`/detail/${review.videoId}`">
                  <img
                        width="80"
                        height="45"
                        :src="`https://img.youtube.com/vi/${review.videoId}/mqdefault.jpg`"
                        title="YouTube video player"
                        frameborder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                        allowfullscreen
                    >
                </router-link>
              </td>
              <td>            
                <router-link :to="`/detail/${review.videoId}`" class="fw-bold">
                  {{ review.videoTitle }}              
                </router-link>  
              </td>
            </div>          
            <td>
              <router-link :to="`/detail/${review.videoId}`" class="comment-content">
                {{ review.content }}
              </router-link>   
            </td>
          </div>   
        </div>
        <div v-else class="center">
          작성한 리뷰가 없습니다.
        </div>
      </div>
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

<style scoped>
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

a {
  margin-left: 5px;
  margin-right: 5px;
  text-decoration: none;
  color: black;
}

.comment {  
  width: 700px;
  border-radius: 5px;
  border-style: solid;
  border-width: 1px;
  border-color: rgba(58, 58, 58, 0.495);
  margin: 5px;
}

.comment:hover {
  background: rgba(56, 56, 56, 0.150);
}

.comment-content {    
  margin: 10px;
  font-size: 17px;  
}
</style>