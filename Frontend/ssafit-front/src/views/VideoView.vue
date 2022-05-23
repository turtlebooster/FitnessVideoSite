<template>
  <div>
   <div id="search-backgraund">    
    <br>
    <div class="search">   
      <div class="input-group mb-3 d-flex justify-content-center">
        <input type="text" class="form-control" v-model="keyword" @keyup.enter="search" placeholder="제목을 입력하세요" aria-label="검색어" aria-describedby="button-addon2">
        <button class="btn btn-outline-secondary search-btn" type="button" id="button-addon2" @click="search" style="margin: 0;"><i class="bi bi-search"></i></button>
      </div>     
    </div>
    <br>
   </div>
   <div>
      <!-- <h2>운동 영상 목록</h2> -->

      <router-view :key="$route.fullPath"/>
  </div>
  </div>
</template>

<script>
export default {  
   data() {
    return {
      keyword:'',      
    }
  },
  methods: {
    search() {
      this.$store.dispatch("getSearchVideo", this.keyword)
      this.$router.push({name: 'VideoSearch', params: {keyword : this.keyword}})
    },
  }
}
</script>

<style scoped>
.search {
  position: relative;
  width: 600px;
  margin-left: auto;
  margin-right: auto;   
}
#search-backgraund {
  background: linear-gradient(87deg,#2dce89,#2dcecc);
}
#button-addon2 {
  background: #FFF;
}
#button-addon2:hover {
  background: #6c757d;
}
input {
  width: 200%;  
  border: 1px solid #bbb;
  border-radius: 8px;
  text-align: center; 
  padding: 10px 12px;
  font-size: 14px;
}
</style>