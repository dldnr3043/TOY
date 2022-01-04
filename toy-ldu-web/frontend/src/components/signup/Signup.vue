<template>
  <fieldset>
    <Alert />
    <legend>SignUp</legend>
    <div class="form-group">
      <label for="exampleInputEmail1" class="form-label text-white-50">Email address</label>
      <input type="email" class="form-control" id="email" v-model="email" aria-describedby="emailHelp" placeholder="Enter email">

      <div class="text-danger" v-if="errorEmailMsg">
        <label for="exampleInputPassword1">It's not in email format.</label>
      </div>

      <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>

    <div class="form-group">
      <label for="exampleInputEmail1" class="form-label text-white-50">Name</label>
      <input type="text" class="form-control" id="name" v-model="name" placeholder="Enter your name">
    </div>


    <div class="form-group">
      <label for="exampleInputPassword1" class="form-label text-white-50">Password</label>
      <input type="password" class="form-control" id="password" v-model="password" placeholder="Password">
    </div>

    <div class="text-danger" v-if="errorPwMsg">
      <label for="exampleInputPassword1">It's not in Password format.</label>
    </div>

    <div class="form-group">
      <label for="exampleInputPassword1" class="form-label text-white-50">Confirm PW</label>
      <input type="password" class="form-control" id="passwordConfirm" v-model="passwordConfirm" placeholder="Password">
    </div>

    <div class="text-danger" v-if="errorConfirmPwMsg">
      <label for="exampleInputPassword1">Passwords do not match</label>
    </div>

    <div class="form-group">
        <button type="button" id="btnSignup" class="btn btn-primary" v-on:click="processSignup()" disabled>Submit</button>
        <button type="button" id="btnBack" class="btn btn-primary" v-on:click="goBackUrl()">back</button>
    </div>
  </fieldset>
    
</template>

<script>

export default {
    name: 'SignupForm',
	data() {
		return {
			email: '',
			name: '',
			password: '',
      passwordConfirm: '',
      errorPwMsg: false,
      errorConfirmPwMsg: false,
      errorEmailMsg: false,
		};
    },
    create() {
    },
    
    watch: {
      password: function(newValue) {
      // password 정규식 체크 : 최소 하나의 문자, 숫자, 특수문자 포함 8자리 이상
      var regPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;

      // password 형식이라면 로그인 버튼 활성화
      if(regPw.test(newValue) === true) {
        this.errorPwMsg = false;
        document.getElementById("btnSignup").disabled = false;
      }
      else if(newValue == "") {
        this.errorPwMsg = false;
        document.getElementById("btnSignup").disabled = true;
      }
      // password 형식이 아니라면 로그인 버튼 비활성화
      else {
        this.errorPwMsg = true;
        document.getElementById("btnSignup").disabled = true;
      }
    },
        passwordConfirm: function(newValue) {
            // 비밀번호와 비밀번호 확인이 같은 경우 회원가입 버튼 활성화
            if(newValue == this.password) {
                this.errorConfirmPwMsg = false;
                document.getElementById("btnSignup").disabled = false;
            }
            else if(newValue == "") {
              this.errorConfirmPwMsg = false;
              document.getElementById("btnSignup").disabled = true;
            }
            // 비밀번호와 비밀번호 확인이 같지 않은 경우 회원가입 버튼 비활성화
            else {
                this.errorConfirmPwMsg = true;
                document.getElementById("btnSignup").disabled = true;
            }
        },

        email: function(newValue) {
          // email 정규식 체크
          var regEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

          // 이메일 형식이라면 회원가입 버튼 활성화
          if(regEmail.test(newValue) === true) {
            this.errorEmailMsg = false;
            document.getElementById("btnSignup").disabled = false;
          }
          else if(newValue == "") {
              this.errorEmailMsg = false;
              document.getElementById("btnSignup").disabled = true;
            }
          // 이메일 형식이 아니라면 회원가입 버튼 비활성화
          else {
            this.errorEmailMsg = true;
            document.getElementById("btnSignup").disabled = true;
          }
        }
    },

	methods: {
		processSignup() {
            // validate 처리
            if(this.processSignupValidate()) return;

            // 서버로 보낼 데이터 세팅
            let data = {};

            data.EMAIL = this.email;
            data.NAME = this.name;
            data.PASSWORD = this.password;

            this.$http.post("/signup", data)
            .then(res => {
                if(res.data.STATUS == "SUCCESS") {
                    alert("회원가입이 완료되었습니다 !!");
                    this.$router.push({name: 'Login'});
                }
                else if(res.data.STATUS == "FAIL") {
                  let loginFailData = {};
                  loginFailData.active = true;
                  loginFailData.title = 'Warning !!';
                  loginFailData.msg = res.data.MESSAGE;

                  this.$eventBus.$emit('openAlertDialog', loginFailData);
                }
            })
        },

        processSignupValidate() {
            // 빈 칸이 있는지 확인
            if(document.getElementById("email").value == "" ||
               document.getElementById("name").value == "" ||
               document.getElementById("password").value == "" ||
               document.getElementById("passwordConfirm").value == "") {
                let signupValidateData = {};
                signupValidateData.active = true;
                signupValidateData.title = 'Warning !!';
                signupValidateData.msg = "모든 칸을 입력해주세요";

                this.$eventBus.$emit('openAlertDialog', signupValidateData);

                return true;
               }
            else {
              return false;
            }
        },
        

        goBackUrl() {
            this.$router.push({name: 'Login'});
        }
	},
};
</script>

<style>

</style>