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
    }
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
    getLikeVideo({ commit }) {      
      api({
        url: `/video/like/${this.state.user.id}`,
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
    insertLikeVideo({ commit }, data) {
      commit
      api({
        url: `/user/write/`,
        method: 'POST', 
        params: data
      })
    },
    deleteLikeVideo({ dispatch }, data) {      
      api({
        url: `/user/deletelikevideo/${data.userId}/${data.videoId}`,
        method: 'DELETE', 
        params: data
      })
      dispatch("getPartVideo")     
      dispatch('getLikeVideo')      
    }
  },  
  modules: {
  }
})
