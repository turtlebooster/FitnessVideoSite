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
  </div>  
</template>

<script>
// import { BCard } from 'bootstrap-vue'
import { mapState } from "vuex";

export default {
name: "VideoPart",
  components:{
    // BCard
  },
  methods:{
    getPartVideo(part){
      // console.log(part)
      this.$store.dispatch("getPartVideo", part)
    },
  },
  computed:{
    ...mapState(['videos', 'user']),
  },
  created() {
    this.$store.dispatch("getPartVideo")
    if(this.user.id){
      this.$store.dispatch('getLikeVideo', this.user)
    }
  }
}
</script>

<style>

</style>