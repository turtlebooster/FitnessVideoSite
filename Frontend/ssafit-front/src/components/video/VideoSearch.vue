<template>
  <div>
    <!-- 비디오 목록 -->
    <div class="d-flex flex-wrap justify-content-center">  
      <div v-for="video in videos" :key="video.id" class="card" style="width: 18rem;">
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
          <router-link :to="`detail/${video.id}`" >
            <h5 class="card-title">{{video.title}}</h5>
          </router-link>
          <p class="card-text">
            {{video.channelName}}
            {{video.part}}
            {{video.viewCnt}}
          </p>            
        </div>
      </div>  
    </div>


    <!-- <div>
        <b-card
        v-for="video in videos"
        :key="video.id"
        width="320"
        height="180"
        :title="`${video.title}`"
        :img-src="`https://img.youtube.com/vi/${video.id}/mqdefault.jpg`"
        img-alt="Image"
        img-top
        tag="article"
        style="max-width: 20rem;"
        class="mb-2"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen
        >
        <b-card-text>
        {{video.channelName}}
        {{video.part}}
        {{video.viewCnt}}
        </b-card-text>
        </b-card>               
    </div>    -->
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {  
  computed:{
    ...mapState(['videos']),
  },
  created() {
    // let keyword = new URL(document.location).searchParams.get("keyword");
    let keyword = this.$route.paras.keyword;
    this.$store.dispatch("getSearchVideo", keyword)    
  }
}
</script>

<style scoped>
.card {
  margin-right: 10px;
  margin-left: 10px;
  margin-top: 10px;
  margin-bottom: 10px;  
}
</style>