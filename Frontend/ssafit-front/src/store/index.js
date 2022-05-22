// import axios from 'axios'
import { createApi } from '@/api';

import Vue from 'vue'
import Vuex from 'vuex'
import router from '@/router';

Vue.use(Vuex)

const api = createApi();

export default new Vuex.Store({
  state: {
    user : {
      id: "",
      nickname: "",
      email: "",
      likeVideos: [],
      reviews: [],
      followList: [],
      followerList: [],
    },
    member: {
      id: "",
      nickname: "",
      email: "",
      likeVideos: [],
      reviews: [],
      followList: [],
      followerList: [],
    },
    video : null,
    videos : [],
    reviews : [],
    keyword: "",
    isLogin: false,
  },
  getters: { 
  },
  mutations: {
    USER_LOGIN(state, data) {
      state.user.id = data.id
      state.user.nickname = data.nickname
      state.user.email = data.email
      state.isLogin = true
      sessionStorage.setItem("access-token", data["access-token"]) 
      api.defaults.headers["access-token"] = data["access-token"]
    },
    USER_LOGOUT(state) {
      // 로그아웃시 유저 정보 및 토큰 제거
      state.user.id = ""
      state.user.nickname = ""
      state.user.email = ""
      state.isLogin = false;
      sessionStorage.removeItem("access-token")
      api.defaults.headers["access-token"] = ""
    },
    GET_LIKE_VIDEO(state, videos) {
      state.user.likeVideos = videos
    },
    GET_USER_REVIEW(state, reviews) {
      state.user.reviews = reviews      
    },
    GET_PART_VIDEO(state, partVideos) {      
      state.videos = partVideos      
    },
    GET_SEARCH_VIDEO(state, searchVideos) {      
      state.videos = searchVideos
    },
    GET_VIDEO_BY_ID(state, video) {
      state.video = video
    },
    GET_REVIEW_BY_VIDEO_ID(state, reviews) {
      state.reviews = reviews
    },
    GET_FOLLOW_LIST(state, followList) {
      state.user.followList = followList
    },
    GET_FOLLOWER_LIST(state, followerList) {
      state.user.followerList = followerList
    },
    GET_MEMBER(state, member){
      state.member.id = member.id
      state.member.nickname = member.nickname
      state.member.email = member.email
    },
    GET_MEMBER_LIKE_VIDEO(state, likeVideos) {
      state.member.likeVideos = likeVideos
    },
    GET_MEMBER_REVIEW(state, reviews) {
      state.member.reviews = reviews
    },
    GET_MEMBER_FOLLOW_LIST(state, followList) {
      state.member.followList = followList
    },
    GET_MEMBER_FOLLOWER_LIST(state, followerList) {
      state.member.followerList = followerList
    },
  },
  actions: {
    userLogin({ commit }, {user, call}) {
      api({
        url: `/user/login`,
        method: 'POST',
        params: user
      }).then(({data}) => {
        commit('USER_LOGIN', data)
        if (call) {
          router.push(call)
        } else {
          router.push('/')
        }
      })
    },
    userRegist({ commit }, user) {
      commit
      api({
        url: `/user/join`,
        method: 'POST',
        params: user
      }).then(() => {
        router.push('/')
      })
    },
    getLikeVideo({ commit }, user) {      
      api({
        url: `/video/like/${user.id}`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_LIKE_VIDEO', data)
      })
    },
    getUserReview({ commit }) {      
      api({
        url: `/review/listByUserId/${this.state.user.id}`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_USER_REVIEW', data)
      })
    },   
    getPartVideo({ commit }, part) {
      let url = "";
      if (part){
        url = `/video/list?part=${part}`
      } else {
        url = `/video/list`
      }

      api({        
        url,
        method: 'GET',        
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_PART_VIDEO', data)
      })
    },
    getSearchVideo({ commit }, keyword) {
      api({
        url: `/video/search?keyword=${keyword}`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_SEARCH_VIDEO', data)
      })
    },
    getVideoById({ commit, dispatch }, videoId) {
      api({
        url: `/video/detail/${videoId}`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_VIDEO_BY_ID', data)

        // data.partId
        // 랜덤하게 다른 파트 추천
        let list = ['0001', '0002', '0003', '0004']
        for (let i = 0; i < list.length; i++){
          if (list[i] == data.partId){
            list.splice(i, 1);
            break;
          }
        }
        let idx = Math.floor(Math.random() * list.length)
        dispatch('getPartVideo', list[idx])
      })
    },
    getReviewByVideoId({ commit }, videoId) {
      api({
        url: `/review/listByVideoId/${videoId}/0`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_REVIEW_BY_VIDEO_ID', data)
      })
    },
    insertReview({ dispatch }, review) {            
      api({
        url: `/review/write`,
        method: 'POST',
        params: review,
      })
      .then(()=>{
        // console.log(data)
        dispatch('getReviewByVideoId', review.videoId)
      })
    },
    updateReview({ dispatch }, review) {
      api({
        url: `/review/update`,
        method: 'PUT',
        data: JSON.stringify(review)
      })
      .then(()=>{
        // console.log(data)
        dispatch('getReviewByVideoId', review.videoId)
      })
    },
    deleteReview({ dispatch }, review) {
      api({
        url: `/review/delete/${review.no}`,
        method: 'DELETE',        
      })
      .then(()=>{
        // console.log(data)
        dispatch('getReviewByVideoId', review.videoId)
      })
    },
    insertLikeVideo({ dispatch }, data) {      
      api({
        url: `/user/write/`,
        method: 'POST', 
        params: data
      })
      .then(()=>{
        dispatch('getLikeVideo', this.state.user)
      })
    },
    deleteLikeVideo({ dispatch }, data) {      
      api({
        url: `/user/deletelikevideo/${data.userId}/${data.videoId}`,
        method: 'DELETE', 
        params: data
      })
      .then(()=>{
        dispatch('getLikeVideo', this.state.user)
      })     
    },
    getFollowList({ commit }) {
      api({
        url: `/user/follow/${this.state.user.id}`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_FOLLOW_LIST', data)
      })
    },
    getFollowerList({ commit }) {
      api({
        url: `/user/follower/${this.state.user.id}`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_FOLLOWER_LIST', data)
      })
    },
    insertFollow({ dispatch }, followId) {      
      let params = {
        userId: this.state.user.id,
        followId
      }
      api({
        url: `/user/writefollow`,
        method: 'POST',
        params,
      })
      .then(()=>{
        // console.log(data)
        dispatch('getFollowList')
      })
    },
    deleteFollow({ dispatch }, followId) {
      api({
        url: `/user/deletefollow/${this.state.user.id}/${followId}`,
        method: 'DELETE',
      })
      .then(()=>{
        // console.log(data)
        dispatch('getFollowList')
      })
    },
    getMember({ commit }, memberId) {
      api({
        url: `/user/getmember/${memberId}`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_MEMBER', data)
      })
      .catch(()=>{
        console.log("오류")
      })
    },
    getMemberLikeVideo({ commit }, memberId) {      
      api({
        url: `/video/like/${memberId}`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_MEMBER_LIKE_VIDEO', data)
      })
      .catch(()=>{
        console.log("오류")
      })
    },
    getMemberReview({ commit }, memberId){
      api({
        url: `review/listByUserId/${memberId}`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_MEMBER_REVIEW', data)
      })
      .catch(()=>{
        console.log("오류")
      })
    },
    getMemberFollowList({ commit }, memberId) {
      api({
        url: `/user/follow/${memberId}`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_MEMBER_FOLLOW_LIST', data)
      })
    },
    getMemberFollowerList({ commit }, memberId) {
      api({
        url: `/user/follower/${memberId}`,
        method: 'GET',
      })
      .then(({data})=>{
        // console.log(data)
        commit('GET_MEMBER_FOLLOWER_LIST', data)
      })
    },
  },  
  modules: {
  }
})
