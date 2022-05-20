<template>
  <div class = "">
      <h2>비디오 디테일</h2>  
      <!-- flex 잡을때 참고 왼쪽 -->
      <div>
        <!-- 선택한 비디오 영상 -->
        <div style="position:relative; padding-bottom:56.25%; padding-top:30px; height:0; overflow:hidden;">
          <iframe style="position:absolute; top:0; left:0; width:100%; height:100%;" width="100%" height="315" :src="`https://www.youtube.com/embed/${video.id }`" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
        </div>
        <!-- 댓글  -->
        <div>
          <table>
            <thead>
              <tr>
                <th>닉네임</th>
                <th>내용</th>
                <th>작성시간</th>
                <th>좋아요</th>
                <th>싫어요</th>
                <th>수정/삭제</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="review in reviews" :key="review.no">
                <td>{{review.nickname}}</td>
                <td>{{review.content}}</td>
                <td>{{review.regTime}}</td>
                <td>{{review.likeCnt}}</td>
                <td>{{review.dislikeCnt}}</td>
                <td><button>수정</button><button>삭제</button></td>              
              </tr>
            </tbody>
          </table>
        </div>      
      </div>     
      <!-- flex 잡을때 참고 오른쪽 -->
      <!-- 추천영상 -->
      <div>
        <router-link v-for="v in videos" :key="v.id"
                     :to="`detail/${v.id}`" >
            <b-card            
            width="320"
            height="180"
            :title="`${v.title}`"
            :img-src="`https://img.youtube.com/vi/${v.id}/mqdefault.jpg`"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem;"
            class="mb-2"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen
            >
            <b-card-text>
            {{v.channelName}}
            {{v.part}}
            {{v.viewCnt}}
            </b-card-text>
            </b-card>               
        </router-link>
      </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: "VideoDetail",
  computed: {
    ...mapState(['video', 'reviews', 'videos'])
  },
  created(){
    const pathName = new URL(document.location).pathname.split("/");
    const videoId = pathName[pathName.length-1]
    this.$store.dispatch('getVideoById', videoId)
    this.$store.dispatch('getReviewByVideoId', videoId)    
  },
}
</script>

<style>

</style>