<template>
  <div>
    <div>
      <button @click = "getPartVideo()" id = "전체">전체</button>
      <button @click = "getPartVideo('0001')" id = "전신">전신</button>
      <button @click = "getPartVideo('0002')" id = "상체">상체</button>
      <button @click = "getPartVideo('0003')" id = "하체">하체</button>
      <button @click = "getPartVideo('0004')" id = "복부">복부</button>
    </div>
      <div>
        <router-link v-for="video in videos" :key="video.id"
                     :to="`detail/${video.id}`" >
            <b-card            
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
        </router-link>
    </div>
  </div>  
</template>

<script>
import { BCard } from 'bootstrap-vue'
import { mapState } from "vuex";

export default {
name: "VideoPart",
  components:{
    BCard
  },
  methods:{
    getPartVideo(part){
      console.log(part)
      this.$store.dispatch("getPartVideo", part)
    },
  },
  computed:{
    ...mapState(['videos'])
  },
  created() {
    this.$store.dispatch("getPartVideo")
  }
}
</script>

<style>

</style>