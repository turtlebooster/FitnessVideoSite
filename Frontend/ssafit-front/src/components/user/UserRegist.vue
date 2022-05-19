<template>
  <div>
      <h2>유저 회원가입</h2>
      <form action="">
        ID: <input type="text" v-model="id" placeholder="아이디를 입력하세요." required><br>
        PW: <input type="password" v-model="password" placeholder="비밀번호를 입력하세요." required><br>
        E-mail: <input type="email" v-model="email" placeholder="email@email.com" required><br>
        Nickname: <input type="text" v-model="nickName" placeholder="닉네임을 입력하세요." required><br>
        <input type="submit" value="가입">
      </form>      
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>