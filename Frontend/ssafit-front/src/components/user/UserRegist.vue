<template>
  <div>
      <h2>유저 회원가입</h2>
      <form @submit.prevent="regist">
        ID: <input type="text" v-model="user.id" placeholder="아이디를 입력하세요." required><br>
        PW: <input type="password" v-model="user.password" placeholder="비밀번호를 입력하세요." required><br>
        E-mail: <input type="email" v-model="user.email" placeholder="email@email.com" required><br>
        Nickname: <input type="text" v-model="user.nickname" placeholder="닉네임을 입력하세요." required><br>
        <button class="btn btn-outline-dark">가입</button>        
      </form> 
  </div>
</template>

<script>
export default {
   data() {
        return {
            user: {
                id : "",
                password : "",
                email : "",
                nickname : "",
            },
        }
    },
    methods: {
      regist() {
        this.$store.dispatch('userRegist', this.user)
      }
    }
}
</script>

<style>
input {
  width: 25rem;
}
</style>