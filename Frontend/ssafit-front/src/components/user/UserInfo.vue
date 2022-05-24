<template>
  <div>
    <br>
    <h2 class="fw-bold">유저 INFO</h2>
      <div class="modal-dialog" role="document">
        <div class="modal-content rounded-4 shadow">
            <div class="modal-header p-5 pb-4 border-bottom-0">
                <!-- <h5 class="modal-title">Modal title</h5> -->
                <h2 class="fw-bold mb-0">회원정보</h2>        
            </div>

            <div class="modal-body p-5 pt-0">
                <form @submit.prevent="regist">
                  <div class="form-floating mb-3">
                      <input type="text" v-model="user.id" class="form-control rounded-3" id="IdInput" placeholder="아이디를 입력하세요." disabled>
                      <label for="IdInput">ID</label>
                  </div>
                  <div class="form-floating mb-3">
                      <input type="password" v-model="user.password" class="form-control rounded-3" id="PasswordInput" placeholder="비밀번호를 입력하세요." required>
                      <label for="PasswordInput">Password</label>
                  </div>
                  <div class="form-floating mb-3">
                      <input type="email" v-model="user.email" class="form-control rounded-3" id="EmailInput" placeholder="이메일을 입력하세요." required>
                      <label for="EmailInput">E-mail</label>
                  </div>
                  <div class="form-floating mb-3">
                      <input type="text" v-model="user.nickname" class="form-control rounded-3" id="NicknameInput" placeholder="닉네임을 입력하세요." required>
                      <label for="NicknameInput">Nickname</label>
                  </div>                   
                    <button class="w-100 mb-2 btn btn-lg rounded-3 btn-primary" type="submit">회원정보 수정</button>                    
                </form>
            </div>
        </div>
    </div>
  </div>
  
</template>

<script>
export default {
  data() {
    return {
      user: {
        id: "",
        password: "",
        email: "",
        nickname: "",
      }
    }
  },  
  created() {
    this.user = this.$store.state.user
  }
}
</script>

<style scoped>
h2 {
  text-align: center;
}

</style>