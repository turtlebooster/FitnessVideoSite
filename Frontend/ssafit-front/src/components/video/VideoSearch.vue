<template>
  <div>
    <!-- <h2 class="fw-bold">'{{keyword}}' 검색결과</h2> -->
    <!-- 비디오 목록 -->
    <div class="container d-flex flex-wrap">  
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
import { mapState } from "vuex";

export default {  
  computed:{
    ...mapState(['videos']),
  },
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

a {
  text-decoration: none;
  color: black;
}
</style>