<template>
  <div class="test">
    <Alert />

    <h2>Log In</h2>
    <div class="form-group">
      <input type="text" class="form-control" placeholder="Enter your Email" v-model="email">
      <div class="text-danger" v-if="errorEmailMsg">
        <label for="exampleInputPassword1">It's not in email format.</label>
      </div>

      <input type="password" class="form-control" placeholder="Enter your password" v-model="password">
      <div class="text-danger" v-if="errorPwMsg">
        <label for="exampleInputPassword1">It's not in Password format.</label>
      </div>

      <button type="button" id="btnLogin" class="btn btn-primary" v-on:click="processLogin()" disabled>Login</button>
      <button type="button" id="btnSignup" class="btn btn-primary" v-on:click="goSignUpPage()">SignUp</button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Login',
  data() {
    return {
      email: "",
      password: "",
      errorEmailMsg: false,
      errorPwMsg: false
    }
  },

  watch : {
    email: function(newValue) {
      // email 정규식 체크
      var regEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

      // 이메일 형식이라면 로그인 버튼 활성화
      if(regEmail.test(newValue) === true) {
        this.errorEmailMsg = false;
        document.getElementById("btnLogin").disabled = false;
      }
      else if(newValue == "") {
        this.errorEmailMsg = false;
        document.getElementById("btnLogin").disabled = true;
      }
      // 이메일 형식이 아니라면 로그인 버튼 비활성화
      else {
        this.errorEmailMsg = true;
        document.getElementById("btnLogin").disabled = true;
      }
    },
    password: function(newValue) {
      // password 정규식 체크 : 최소 하나의 문자, 숫자, 특수문자 포함 8자리 이상
      var regPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;

      // password 형식이라면 로그인 버튼 활성화
      if(regPw.test(newValue) === true) {
        this.errorPwMsg = false;
        document.getElementById("btnLogin").disabled = false;
      }
      else if(newValue == "") {
        this.errorPwMsg = false;
        document.getElementById("btnLogin").disabled = true;
      }
      // password 형식이 아니라면 로그인 버튼 비활성화
      else {
        this.errorPwMsg = true;
        document.getElementById("btnLogin").disabled = true;
      }
    },
  },

  methods: {
    // 로그인 처리 함수
    processLogin : function() {
      // 서버에 보낼 인증 데이터(이메일, 비밀번호)
      let data = {};
      data.email = this.email;
      data.password = this.password;

      this.$http.post('/login', data)
      .then(res => {
        // 로그인 성공 시
        if(res.data.STATUS == 'SUCCESS') {
          // 로그인 처리 과정이 성공하면 서버로 보내는 모든 요청에 아래 값 저장(Jwt Token)
          this.$http.defaults.headers.common['X-AUTH-TOKEN'] = res.headers['x-auth-token'];

          // 메인 페이지 이동
          this.$router.push({name: 'ChatMain'});
        }
        // 로그인 실패 시
        else if(res.data.STATUS == 'FAIL'){
         
          let loginFailData = {};
          loginFailData.active = true;
          loginFailData.title = 'Warning !!';
          loginFailData.msg = res.data.MESSAGE;

          this.$eventBus.$emit('openAlertDialog', loginFailData);
        }
      })
    },

    goSignUpPage : function() {
      this.$router.push({name: 'Signup'});
    },

  },

};
</script>
