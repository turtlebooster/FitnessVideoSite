import Vue from 'vue'
import VueRouter from 'vue-router'
// 비디오 파트
import VideoView from '@/views/VideoView.vue'
import VideoDetail from '@/components/video/VideoDetail.vue'
import VideoPart from '@/components/video/VideoPart.vue'
import VideoSearch from '@/components/video/VideoSearch.vue'
// 유저 파트
import UserView from '@/views/UserView.vue'
import UserCalander from '@/components/user/UserCalander.vue'
import UserFollowList from '@/components/user/UserFollowList.vue'
import UserInfo from '@/components/user/UserInfo.vue'
import UserLikeVideo from '@/components/user/UserLikeVideo.vue'
import UserLogin from '@/components/user/UserLogin.vue'
import UserRegist from '@/components/user/UserRegist.vue'
import UserReview from '@/components/user/UserReview.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: VideoView,
    children: [
      {
        path: 'detail/:videoId',
        name: 'VideoDetail',
        component: VideoDetail,
      },
      {
        path: '',
        name: 'VideoPart',
        component: VideoPart,
      },
      {
        path: 'search',
        name: 'VideoSearch',
        component: VideoSearch,
      },
    ]
  },
  {
    path: '/user',
    name: 'UserView',
    component: UserView,
    children: [
      {
        path: 'calander',
        name: 'UserCalander',
        component: UserCalander,
      },
      {
        path: 'follow-list',
        name: 'UserFollowList',
        component: UserFollowList,
      },
      {
        path: 'info',
        name: 'UserInfo',
        component: UserInfo,
      },
      {
        path: 'like-video',
        name: 'UserLikeVideo',
        component: UserLikeVideo,
      },
      {
        path: 'login',
        name: 'UserLogin',
        component: UserLogin,
      },
      {
        path: 'regist',
        name: 'UserRegist',
        component: UserRegist,
      },
      {
        path: 'review',
        name: 'UserReview',
        component: UserReview,
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
