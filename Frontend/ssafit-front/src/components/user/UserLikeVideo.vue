<template>
  <div>
    <h2 class="fw-bold">
      찜 영상 목록
    </h2>
    <div class="d-flex flex-wrap justify-content-center">
      <div v-for="video in user.likeVideos" :key="video.id" class="card" style="width: 18rem;">
        <router-link :to="`detail/${video.id}`" >
        <img width="320"
          height="180"
          :src="`https://img.youtube.com/vi/${video.id}/mqdefault.jpg`"
          alt="Image"
          img-top
          tag="article"
          style="max-width: 17.5rem;"
          class="mb-2"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen>
        </router-link>
        <div class="card-body">
          <router-link :to="`/detail/${video.id}`" >
            <h5 class="card-title">{{video.title}}</h5>
          </router-link>
          <p class="card-text">
            {{video.channelName}}
            {{video.part}}
            {{video.viewCnt}}
            <b-button @click="deleteLikeVideo(video.id)" variant="danger">삭제</b-button>
          </p>            
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {  
  methods: {
    deleteLikeVideo(videoId){
      let data = {
        userId : this.user.id,
        videoId
      }
      this.$store.dispatch("deleteLikeVideo", data)   
      this.$store.dispatch('getLikeVideo', this.user)        
    },
  },
  computed: {
    ...mapState(['user'])
  },
  created() {
    this.$store.dispatch('getLikeVideo');   
  },
}
</script>

<style scoped>
h2 {
  text-align: center;
}
</style>