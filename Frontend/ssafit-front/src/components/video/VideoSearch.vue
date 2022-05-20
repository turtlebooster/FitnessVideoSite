<template>
  <div>
    <h2>검색 결과 창</h2> 
    <div>
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
    </div>   
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {  
  computed:{
    ...mapState(['videos']),
  },
  created() {
    let keyword = new URL(document.location).searchParams.get("keyword");
    this.$store.dispatch("getSearchVideo", keyword)    
  }
}
</script>

<style>

</style>