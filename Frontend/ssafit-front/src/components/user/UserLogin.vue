<template>
    <div>
        <h2>유저 Login</h2>
        <form @submit.prevent="login">
            ID : <input type="text" v-model="user.id" placeholder="아이디를 입력하세요." required><br>
            PW : <input type="password" v-model="user.password" placeholder="비밀번호를 입력하세요." required><br>
        <button class="btn btn-outline-dark">로그인</button>
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
            },
        }
    },
    methods:{
        login(){
            console.log(this.user.id)
            console.log(this.user.password)
            this.$store.dispatch('userLogin', {                
                user: this.user, 
                call: this.$route.query.call,
            });       
        }
    }
}
</script>

<style>

</style>