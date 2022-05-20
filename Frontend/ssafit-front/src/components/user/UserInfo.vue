<template>
  <div>
    <h2>유저 INFO</h2>
    <div>
      <div class="m-4">
      <b-form-group label="ID" label-for="input-1">
        <b-form-input id="input-1" trim disabled :value="user.id"></b-form-input>
      </b-form-group>
      <b-form-group label="PW" label-for="input-2">
        <b-form-input id="input-2" trim></b-form-input>
      </b-form-group>
      <b-form-group label="E-mail" label-for="input-3">
        <b-form-input id="input-3" trim v-model="user.email"></b-form-input>
      </b-form-group>
      <b-form-group label="Nickname" label-for="input-4">
        <b-form-input id="input-4" trim v-model="user.nickname"></b-form-input>
      </b-form-group>
      <b-button variant="outline-success" >수정</b-button>
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

<style>

</style>