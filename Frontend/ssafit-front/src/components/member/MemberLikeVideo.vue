<template>
  <div class="container">
    <h2 class="fw-bold">
      찜 영상 목록
    </h2>
      <!--  -->
      <div v-for="video in member.likeVideos" :key="video.id" class="card" style="width: 18rem;">
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
          <div class="card-text">
            <div class="d-flex justify-content-between">
              <span>{{video.channelName}}</span>
              <span class="viewCnt"><i class="bi bi-eye"></i> {{video.viewCnt}}회</span>          
            </div>                                                
            <div>
              #{{video.part}}
            </div> 
          </div>          
        </div>
      </div>
        <!--  -->
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {  
  methods: {
  },
  computed: {
    ...mapState(['member'])
  },
  created() {
    // console.log(this.$route.params.memberId)
    if (this.$route.params.memberId) {
      this.$store.dispatch('getMemberLikeVideo', this.$route.params.memberId); 
      this.$store.dispatch('getMember', this.$route.params.memberId)  
    }
  },
}
</script>

<style>
a {
  text-decoration: none;
  color: black;
}
</style>