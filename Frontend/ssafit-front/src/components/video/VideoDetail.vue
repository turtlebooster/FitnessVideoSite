<template>
  <div class = "container d-flex justify-content-around">
      <!-- 비디오 디테일  -->
      <!-- flex 잡을때 참고 왼쪽 -->
      <div class="left-box">
        <!-- 선택한 비디오 영상 -->
        <br>
        <div class="d-flex justify-content-center">
          <div id="video-container" >          
            <div id="area">
              <iframe id="video" :src="`https://www.youtube.com/embed/${video.id }`" />
            </div>
          </div>
        </div>
        <!-- 비디오 아랫쪽 내용들 -->
        <div id="video-bottom">
          <div >
            <!-- 제목 -->
            <h2 class="fw-bold" id="video-title">{{video.title}}</h2>
            <div id="viewCntandLike">
              <!-- 조회수 -->
              <span id="view-cnt"><i class="bi bi-eye"></i>{{video.viewCnt}} </span>
              <!-- 찜 버튼 -->
              <span v-if="user.nickname.length">
                <button class="btn btn-outline-danger" v-if="!isLikeVideo" @click="insertLikeVideo"> <i class="bi bi-heart"></i></button>
                <button class="btn btn-outline-danger" v-else @click="deleteLikeVideo"> <i class="bi bi-heart-fill"></i></button>
              </span>
            </div>
          </div>
          <!-- 댓글  -->
          <div id="comment">
            <!-- 댓글 작성 창 -->
            <div v-if="user.nickname.length">
              <span class="fw-bold"> {{ user.nickname }} </span>
              <input type="text" v-model="content" @keyup.enter="insertReview" placeholder="댓글 추가...">
              <button class="btn btn-outline-dark" @click="insertReview"><i class="bi bi-chat-dots"></i></button>
            </div>
            <div v-else class="fw-bold">
              댓글을 추가하려면 로그인하세요.
            </div >
            <div v-if="reviews.length" class="d-flex">
              <table class="flex-fill">
                <thead>
                  <tr>
                    <th id="nickname-col">닉네임</th>
                    <th id="content-col">내용</th>
                    <th id="regtime-col">작성시간</th>
                    <th id="like-col">좋아요</th>
                    <th id="dislike-col">싫어요</th>
                    <th id="btn-col">수정/삭제</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="review in reviews" :key="review.no">
                    <td><router-link :to="{name : 'MemberLikeVideo', params: {memberId : review.userId}}" class="nicname-col">{{review.nickname}}</router-link></td>
                    <td v-if="writeNo == review.no"><input type="text" v-model="updateContent" @keyup.enter="updateReview(review)"></td>
                    <td v-else class="content-col">{{review.content}}</td>
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
                    <td v-else> </td>           
                  </tr>
                </tbody>
              </table>
            </div>        
            <div v-else >
              작성된 댓글이 없습니다.              
            </div>
            <br>
          </div>      
        </div>
      </div>     
      <!-- flex 잡을때 참고 오른쪽 -->
      <!-- 추천영상 -->
      <div class="right-box">
        <br>
        <h2 class="fw-bold">추천영상</h2>
        <br>
        <div v-for="v in videos" :key="v.id" class="card" style="width: 288px;"> 
          <router-link :to="{name: 'VideoDetail', params: {videoId: v.id}}">       
            <img 
              width="288"
              height="162"
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
            <div class="card-text">
              <div class="d-flex justify-content-between">
                <span>{{v.channelName}}</span>
                <span class="viewCnt"><i class="bi bi-eye"></i> {{v.viewCnt}}회</span>          
              </div>                                                
              <div>
                #{{v.part}}
              </div> 
            </div>            
          </div>
        </div>
      </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'

function rightBoxResize() {
    let videoContainer = document.getElementsById('video-container');
    let videoBottom = document.getElementsByClassName('video-bottom');
    let rightBox = document.getElementsByClassName('right-box');
    rightBox.style.height = videoContainer.style.height + videoBottom;
}

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
    },    
  },
  created(){
    // const pathName = new URL(document.location).pathname.split("/");
    // const videoId = pathName[pathName.length-1]    
    const videoId = this.$route.params.videoId
    this.$store.dispatch('getVideoById', videoId)
    this.$store.dispatch('getReviewByVideoId', videoId)    
    rightBoxResize()
  },
}
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}

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
  /* width: 100%; */
  width: 970px;
  height: 545px;  
}

#video-title {
  padding-top: 10px;
  /* width: 40em; */
  text-align: start;
  word-break: break-all;
  
}

#view-cnt {
  font-size: 1.3rem;
  padding-right: 10px;
}

#viewCntandLike {
  text-align: end;
  /* margin-right: 3%; */
}

#content-col {
  /* width: 40%; */
  text-align: center;
}

#like-col,
#dislike-col {
  width: 8%;
  text-align: center;
}

#nickname-col {
  width: 10%;
  text-align: center;
}

#btn-col,
#regtime-col {
  width: 19%;
  text-align: center;
}

.nicname-col {
  color: black;
  text-decoration: none; 
  font-weight: 700;
}

input {
  width: 85%;  
  border: 1px solid #bbb;
  border-radius: 8px;
  text-align: center; 
  padding: 10px 12px;
  font-size: 14px;
}

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

table {
  /* margin-left: auto;
  margin-right: auto; */
  text-align: center;
}

tr:hover {
  color: black;
  background: rgba(56, 56, 56, 0.150);
}

.left-box {
  margin-top: 10px;  
  padding: 0 10px 0 10px;
  border-right: 2px solid rgb(0, 0, 0);
}

.right-box {    
  padding: 0 10px 0 10px;
}
</style>