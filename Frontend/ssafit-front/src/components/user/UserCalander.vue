<template>
  <div class="container">
    <br>
    <h2 class="fw-bold">유저 캘린더</h2>
    
      <!-- inspired by http://colorhunt.co/c/8184 and 
    https://dribbble.com/shots/2407357-Calendar%60 -->
    <!-- https://intrepidgeeks.com/tutorial/vuecalendarproject-create-a-calendar-using-javascript-3 -->

    <div class="d-flex justify-content-between">   
      <div >
        <vc-date-picker v-model="date" />    
      </div>
      <div>
        선택한 날짜: {{pickDate}}
        <div>
          칼로리 기록 <br>
          <div v-if="isUpdateForm">
            <input type="text" v-model="calendar.eatCalories" :placeholder="user.calendar.eatCalories">
            <input type="text" v-model="calendar.useCalories" :placeholder="user.calendar.useCalories">
            <div>
              <button class="btn-primary" @click="updateCalories">추가</button>
              <button class="btn-secondary" @click="changeToUpdateForm">취소</button>
            </div>
          </div>
          <div v-else>
            {{user.calendar.eatCalories}} <br>
            {{user.calendar.useCalories}} <br>
            <button class="btn-primary" @click="changeToUpdateForm">추가</button>
          </div>
        </div>
        <div>
          오늘 할 일 목록          
          <table>
            <tr v-for="todoList in user.todoLists" :key="todoList.no">
              <td>{{todoList.todo}} </td>
              <td v-if="todoList.check"><i class="btn bi bi-check-square" @click="todoCheck(todoList)"></i></td>              
              <td v-else><i class="btn bi bi-square" @click="todoCheck(todoList)"></i></td>              
            </tr>
          </table>

        </div>
        <div>
          <input type="text" v-model="todoList.todo">
          <button @click="insertTodo">작성</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

function getFormatDate(date){
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '-' + month + '-' + day;     //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}

export default {
  data() {
    return {
      value: '',
      context: null,
      date: new Date(),
      isUpdateForm : false,
      Calendar: {
        useCalories: 0,
        eatCalories: 0,        
      },
      todoList: {
        no: 0,
        id: null,
        todo: "",
        check: false,
      }
    }
  },  
  computed: {
    ...mapState(['user']),
    pickDate() {       
      // 선택한 날짜의 Calendar 불러오기
      let data = {
        userId: this.user.id,
        date: getFormatDate(this.date)
      }
      this.$store.dispatch('getCalendar', data)  
      return getFormatDate(this.date)
    },
    pickDayOfWeek() {
      return this.date.getDay()
    },
    useCalories() {      
      return this.user.calendar.useCalories
    },
    eatCalories() {
      return this.user.calendar.eatCalories
    }
  },
  methods: {
    insertTodo() {
      // 선택한 날짜의 todoList id 갱신      
      this.todoList.id = this.user.calendar.todoId
      const data = {
        todoList: this.todoList,
        userId: this.user.id,
        date: this.pickDate,
        dayOfWeek: this.pickDayOfWeek,
      }
      this.$store.dispatch('insertTodo', data)
    },
    todoCheck(todoList) {
      todoList.check = !todoList.check
      this.$store.dispatch('updateTodo', todoList)
    },
    changeToUpdateForm() {
      this.isUpdateForm = !this.isUpdateForm
    },
    updateCalories() {
      this.$store.dispatch('updateCalories')
    },
  },
}
</script>

<style scoped>
</style>