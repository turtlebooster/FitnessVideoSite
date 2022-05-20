<template>
  <div>
   <div class="search">
    <input type="text" v-model="keyword" @keyup.enter="search" placeholder="제목을 입력하세요">
    <!-- <router-link :to="{name: 'VideoSearch', params : {keyword}}"><button>검색</button></router-link> -->
    <!-- <router-link :to="`search?keyword=${keyword}`" ><button @click="search">검색</button></router-link> -->
    <button @click="search">검색</button>
   </div>
   <div>
      <h2>운동 영상 목록</h2>

      <router-view />
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
      this.$router.push(`search?keyword=${this.keyword}`)
    },
  }
}
</script>

<style>
.search {
  position: relative;
  width: 600px;
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