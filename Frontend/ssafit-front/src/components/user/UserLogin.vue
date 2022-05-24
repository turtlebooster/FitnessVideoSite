<template>
    <div>
        <br><br><br><br>
        <div class="modal-dialog" role="document">
            <div class="modal-content rounded-4 shadow">
                <div class="modal-header p-5 pb-4 border-bottom-0">
                    <!-- <h5 class="modal-title">Modal title</h5> -->
                    <h2 class="fw-bold mb-0">로그인</h2>        
                </div>

                <div class="modal-body p-5 pt-0">
                    <form @submit.prevent="login">
                    <div class="form-floating mb-3">
                        <input type="text" v-model="user.id" class="form-control rounded-3" id="IdInput" placeholder="아이디를 입력하세요." required>
                        <label for="IdInput">ID</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" v-model="user.password" class="form-control rounded-3" id="PasswordInput" placeholder="비밀번호를 입력하세요." required>
                        <label for="PasswordInput">Password</label>
                    </div>
                        <button class="w-100 mb-2 btn btn-lg rounded-3 btn-primary" type="submit">로그인</button>                    
                        <router-link :to="{name : 'UserRegist'}">
                            <button class="w-100 mb-2 btn btn-lg rounded-3 btn-secondary" type="submit">회원가입</button>
                        </router-link>
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
                id : "",
                password : "",
            },
        }
    },
    methods:{
        login(){            
            this.$store.dispatch('userLogin', {                
                user: this.user, 
                // call: document.referrer,
            });       
        }
    },
}
</script>

<style>

</style>