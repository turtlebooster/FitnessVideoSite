<template>
  <div class = "d-flex">
      <!-- 비디오 디테일  -->
      <!-- flex 잡을때 참고 왼쪽 -->
      <div>
        <!-- 선택한 비디오 영상 -->
        <div id="video-container">          
          <div id="area">
            <iframe id="video" :src="`https://www.youtube.com/embed/${video.id }`" />
          </div>
        </div>
        <div>
          <!-- 제목 -->
          <h2 id="video-title">{{video.title}}</h2>
          <!-- 조회수 -->
          <span id="view-cnt"><i class="bi bi-eye"></i>{{video.viewCnt}}</span>
          <!-- 찜 버튼 -->
          <span>
            <button class="btn btn-outline-danger" v-if="!isLikeVideo" @click="insertLikeVideo"><i class="bi bi-heart"></i></button>
            <button class="btn btn-outline-danger" v-else @click="deleteLikeVideo"><i class="bi bi-heart-fill"></i></button>
          </span>
        </div>
        <!-- 댓글  -->
        <div>
          <!-- 댓글 작성 창 -->
          <div v-if="user.nickname.length">
            {{ user.nickname }} <input type="text" v-model="content" @keyup.enter="insertReview" placeholder="댓글 추가...">
            <button class="btn btn-outline-dark" @click="insertReview"><i class="bi bi-chat-dots"></i></button>
          </div>
          <div v-else>
            댓글을 추가하려면 로그인하세요
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
                <router-link :to="{name : 'MemberLikeVideo', params: {memberId : review.userId}}"><td>{{review.nickname}}</td></router-link>
                <td v-if="writeNo == review.no"><input type="text" v-model="updateContent"></td>
                <td v-else>{{review.content}}</td>
                <td>{{review.regTime}}</td>
                <td><button class="btn btn-light" @click="upLike(review)"><i class="bi bi-hand-thumbs-up"></i></button>{{review.likeCnt}}</td>
                <td><button class="btn btn-light" @click="upDislike(review)"><i class="bi bi-hand-thumbs-down"></i></button>{{review.dislikeCnt}}</td>
                <td v-if="user.id == review.userId">
                  <div v-if="writeNo == review.no">
                    <button class="btn btn-primary" @click="updateReview(review)">수정</button>
                    <button class="btn btn-secondary" @click="cancleUpdate">취소</button>
                  </div>
                  <div v-else>
                    <button class="btn btn-primary" @click="changeToUpdateForm(review.no, review.content)">수정</button>
                    <button class="btn btn-danger" @click="deleteReview(review)">삭제</button>
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
        <div v-for="v in videos" :key="v.id" class="card" style="width: 18rem;"> 
          <router-link :to="{name: 'VideoDetail', params: {videoId: v.id}}">       
            <img 
              width="320"
              height="180"
              :src="`https://img.youtube.com/vi/${v.id}/mqdefault.jpg`"
              alt="Image"
              img-top
              tag="article"
              style="max-width: 17.5rem;"
              class="mb-2"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
              allowfullscreen>   
          </router-link>       
          <div class="card-body">  
            <router-link :to="{name: 'VideoDetail', params: {videoId: v.id}}">           
              <h5 class="card-title">{{v.title}}</h5> 
            </router-link>           
            <p class="card-text">
              {{v.channelName}}
              {{v.part}}
              {{v.viewCnt}}
            </p>            
          </div>
        </div>
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
      // console.log(review.userId)
      this.$store.dispatch('insertReview', review);  
      this.content = ""
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
    },
    insertLikeVideo() {
      let data = {
        userId : this.user.id,
        videoId : this.video.id
      }      
      this.$store.dispatch("insertLikeVideo", data)
    },
    deleteLikeVideo(){
      let data = {
        userId : this.user.id,
        videoId : this.video.id
      }
      this.$store.dispatch("deleteLikeVideo", data)      
    },
  },
  computed: {
    ...mapState(['video', 'reviews', 'videos', 'user']),
    isLikeVideo() {
      const likeVideos = this.user.likeVideos
      for (let i = 0; i < likeVideos.length; i++) {
        if (likeVideos[i].id == this.video.id) {
          return true
        }
      }
      return false
    }
  },
  created(){
    // const pathName = new URL(document.location).pathname.split("/");
    // const videoId = pathName[pathName.length-1]    
    const videoId = this.$route.params.videoId
    this.$store.dispatch('getVideoById', videoId)
    this.$store.dispatch('getReviewByVideoId', videoId)    
  },
}
</script>

<style>
#area {
  position: relative; /* absolute는 부모가 relative일 때 부모를 따라간다. */
  width: 100%;  
  padding-bottom: 56.25%; /* 16:9 비율 */
}

#video {
  position: absolute;
  width: 100%; /* 부모에 맞게 꽉 채운다. */
  height: 100%;
}

#video-container {
  width: 80%;
}

#video-title {
  width: 40em;
  word-break: break-all;
}

#view-cnt {
  font-size: 1.3rem;
}
</style>