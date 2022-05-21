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
          <!-- 댓글 작성 창 -->
          <div>
            댓글: <input type="text" v-model="content" @keyup.enter="insertReview" placeholder="댓글 추가...">
            <button @click="insertReview">댓글</button>
          </div>
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
                <td v-if="writeNo == review.no"><input type="text" v-model="updateContent"></td>
                <td v-else>{{review.content}}</td>
                <td>{{review.regTime}}</td>
                <td><button @click="upLike(review)">좋아요</button>{{review.likeCnt}}</td>
                <td><button @click="upDislike(review)">싫어요</button>{{review.dislikeCnt}}</td>
                <td v-if="user.id == review.userId">
                  <div v-if="writeNo == review.no">
                    <button @click="updateReview(review)">수정</button>
                    <button  @click="cancleUpdate">취소</button>
                  </div>
                  <div v-else>
                    <button @click="changeToUpdateForm(review.no, review.content)">수정</button>
                    <button @click="deleteReview(review)">삭제</button>
                  </div>
                </td>              
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
  data() {
    return {
      content : "",   
      updateContent : "",
      writeNo : null,   
    }
  },
  methods: {
    insertReview() {      
      const pathName = new URL(document.location).pathname.split("/");
      const videoId = pathName[pathName.length-1]      
      let review = {
        content : this.content,
        userId : this.user.id,
        videoId
      }
      console.log(review.userId)
      this.$store.dispatch('insertReview', review);  
    },
    upLike(review) {
      review.likeCnt++
      this.$store.dispatch('updateReview', review);
    },
    upDislike(review) {
      review.dislikeCnt++
      this.$store.dispatch('updateReview', review);
    },
    deleteReview(review) {
      this.$store.dispatch('deleteReview', review)
    },
    changeToUpdateForm(no, content) {
      this.writeNo = no
      this.updateContent = content
    },
    cancleUpdate() {
      this.writeNo = null
      this.updateContent = ""
    },
    updateReview(review) {
      review.content = this.updateContent
      this.cancleUpdate()
      this.$store.dispatch('updateReview', review)
    }
  },
  computed: {
    ...mapState(['video', 'reviews', 'videos', 'user'])
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