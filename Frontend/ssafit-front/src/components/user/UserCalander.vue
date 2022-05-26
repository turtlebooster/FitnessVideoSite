<template>
  <div class="container">
    <br>
    <h2 class="fw-bold">유저 캘린더</h2>
    <br>
    <div class="d-flex justify-content-center">   
      <div >
        <vc-date-picker v-model="date" />    
      </div>
      <div class="pickedDate">
        <div class="center fw-bold date">
          {{pickDate}}
        </div> 
        <div class="calories-box">
          <div class="fw-bold calories center">칼로리 기록</div>
          <div v-if="isUpdateForm">
            <div>먹은 칼로리 : <input class="calories-input" type="text" v-model="user.calendar.eatCalories" :placeholder="0"></div>
            <div>소비 칼로리 : <input class="calories-input" type="text" v-model="user.calendar.useCalories" :placeholder="0"></div>
            <div class="d-flex justify-content-end change-btn">
              <button class="btn-primary" @click="updateCalories()">변경</button>
              <button class="btn-secondary" @click="changeToUpdateForm">취소</button>
            </div>
          </div>
          <div v-else >            
            <div>먹은 칼로리 : {{user.calendar.eatCalories}}</div>
            <div>소비 칼로리 : {{user.calendar.useCalories}}</div>
            <div class="d-flex justify-content-end">
              <button class="btn-primary change-btn" @click="changeToUpdateForm">변경</button>
            </div>
          </div>
        </div>
        <!-- 오늘 할 일 목록 -->
        <div class="d-flex flex-column todo-box">
          <span class="todo-title flex-fill"> 오늘 할 일 목록</span>
          <table class="flex-fill">
            <tr v-for="todoList in user.todoLists" :key="todoList.no" class>
              <td class="todo-content" v-if="todoUpdateForm == todoList.no"><input type="text" v-model="todoList.todo" @keyup.enter="updateTodo(todoList)"></td>
              <td class="todo-content" v-else>{{todoList.todo}}</td>
              <td class="todo-check" v-if="todoList.check"><i class="btn bi bi-check-square" @click="todoCheck(todoList)"></i></td>              
              <td class="todo-check" v-else><i class="btn bi bi-square" @click="todoCheck(todoList)"></i></td>
              <div class="todo-btn" v-if="todoUpdateForm == todoList.no">
                <button class="btn btn-primary" @click="updateTodo(todoList)">수정</button>
                <button class="btn btn-secondary" @click="cancleUpdate">취소</button>
              </div>
              <div class="todo-btn" v-else>
                <button class="btn btn-primary" @click="changeToUpdateFormTodo(todoList.no, todoList)">수정</button>
                <button class="btn btn-danger" @click="deleteTodo(todoList)">삭제</button>
              </div>              
            </tr>
          </table>

        </div>
        <div>
          <input type="text" v-model="todoList.todo" @keyup.enter="insertTodo" style="height: 38px;">
          <button id="write-btn" @click="insertTodo" class="btn btn-outline-dark" style="margin: 0px">작성</button>
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
      todoUpdateForm: null,
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
      this.todoList.todo = ""
    },
    todoCheck(todoList) {
      todoList.check = !todoList.check
      this.$store.dispatch('updateTodo', todoList)
    },
    updateCalories() {
        const Calendar = {
        userId: this.user.id,
        date: this.pickDate,
        useCalories: this.useCalories,
        eatCalories: this.eatCalories
        }
        this.$store.dispatch('updateCalories',Calendar)
        this.isUpdateForm = false
    },
    changeToUpdateForm() {
     this.isUpdateForm = !this.isUpdateForm
    },
    deleteTodo(todoList) {
        const data = {
        todoList: todoList,
        userId: this.user.id,
        date: this.pickDate,
        dayOfWeek: this.pickDayOfWeek,
      }
      this.$store.dispatch('deleteTodo', data)
    },
    updateTodo(todoList){
      this.$store.dispatch('updateTodo', todoList)
      this.todoUpdateForm = true
       this.todoList.todo = ""
    },
    changeToUpdateFormTodo(no, todoList){
      this.todoUpdateForm = no
      this.todoList.todo = todoList.todo
    },
    cancleUpdate() {
      this.todoUpdateForm = null
      this.todoList.todo = ""
    },
  },
}
</script>

<style scoped>
h2 {
  text-align: center;
}

.center {
  text-align: center;
}

.date {
  font-size: 20px;
}

.calories {  
  margin: 5px 0 5px 0;
  font-size: 20px;  
}

.calories-box {  
  border-radius: 10px;
  border: 1px solid rgba(0, 0, 0, 0.274);
  padding-bottom: 5px;
}

.todo-box {
  margin-top: 10px;
  margin-bottom: 10px;
  border-radius: 10px;
  border: 1px solid rgba(0, 0, 0, 0.274);  
}

.change-btn {
  margin-right: 5px;
}

.pickedDate {
  margin-left: 35px;
  border-radius: 15px;
  border: 1px solid rgba(0, 0, 0, 0.274);
  padding: 10px;

}

.todo-title {
  font-size: 20px;
  font-weight: 700;
  text-align: center;
}

tr:hover {
   background: rgba(56, 56, 56, 0.150);
}

.todo-content,
.todo-content input {
  width: 285px;
}

input {
  border-radius: 10px;
}

.todo-btn {
  width: 120px;
  /* text-align: right; */
}

button {
  border-radius: 10px;
}

/* #write-btn:hover {
  color: black !important;
  background: white !important;
} */

.calories-input {
  width: 100px;
}
</style>