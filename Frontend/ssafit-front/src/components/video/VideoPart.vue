<template>
  <div>
    <!-- 부위 버튼 -->
    <div id="part-buttons">
      <button class="btn btn-outline-dark" :class="{active: isActive == '0000'}" @click="getPartVideo()" >전체</button>
      <button class="btn btn-outline-dark" :class="{active: isActive == '0001'}" @click="getPartVideo('0001')" >전신</button>
      <button class="btn btn-outline-dark" :class="{active: isActive == '0002'}" @click="getPartVideo('0002')" >상체</button>
      <button class="btn btn-outline-dark" :class="{active: isActive == '0003'}" @click="getPartVideo('0003')" >하체</button>
      <button class="btn btn-outline-dark" :class="{active: isActive == '0004'}" @click="getPartVideo('0004')" >복부</button>
    </div>
    <!-- 비디오 목록 -->
    <div class="container d-flex flex-wrap">  
      <div v-for="video in videos" :key="video.id" class="card">
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
        <div class="card-body d-flex flex-column">
          <router-link :to="`detail/${video.id}`" >
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
    </div>
  </div>  
</template>

<script>
// import { BCard } from 'bootstrap-vue'
import { mapState } from "vuex";

export default {
  name: "VideoPart",
  data() {
    return {
      isActive : '0000',
    }
  },
  components:{
    // BCard
  },
  methods:{
    getPartVideo(part){
      // console.log(part)
      if(!part) {
        this.isActive = '0000'
      } else {
        this.isActive = part
      }
      this.$store.dispatch("getPartVideo", part)
    },
  },
  computed:{
    ...mapState(['videos', 'user']),
  },
  created() {
    // console.log(1111)
    this.$store.dispatch("getPartVideo")
    if(this.user.id){
      this.$store.dispatch('getLikeVideo', this.user)
    }
  }
}
</script>

<style scoped>
.card {
  margin-right: 10px;
  margin-left: 10px;
  margin-top: 10px;
  margin-bottom: 10px;  
  max-width: 17.5rem;  
}

.card,
.card img {
  border-radius: 15px;
}

.card:hover {
  background: rgba(56, 56, 56, 0.150);
}


.btn {
  margin: 5px;
}

.btn:active, 
.btn:focus { 
  border: none !important; 
  box-shadow: none !important; 
}


#part-buttons {
  text-align: center;
}

a {
  text-decoration: none;
  color: black;
}

</style>