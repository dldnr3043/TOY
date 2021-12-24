<template>
  <div class="test">
      <h2>Log In</h2>
      <form method="post" action="/web/login">
          <input type="text" placeholder="Enter your ID" v-model="email">
          <input type="password" placeholder="Enter your password" v-model="password">
          <button type="button" v-on:click="processLogin()">Login</button>
          <button type="button" v-on:click="goSignUpPage()">SignUp</button>
      </form>
  </div>
</template>

<script>

export default {
  name: 'Login',

  data() {
    return {
      email: "",
      password: ""
    }
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
        console.log(res)
        
        // 로그인 성공 시
        if(res.data.STATUS == 'SUCCESS') {
          // 로그인 처리 과정이 성공하면 서버로 보내는 모든 요청에 아래 값 저장(Jwt Token)
          this.$http.defaults.headers.common['X-AUTH-TOKEN'] = res.headers['x-auth-token'];

          // 메인 페이지 이동
          this.$router.push({name: 'Main'});
        }
        // 로그인 실패 시
        else {
          alert(res.data.MESSAGE);
        }
      })
    },

    goSignUpPage : function() {
      this.$router.push({name: 'Signup'});
    },

  },

};
</script>
