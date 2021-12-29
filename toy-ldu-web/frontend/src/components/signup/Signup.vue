<template>
  <fieldset>
    <Nav />
    <legend>SignUp</legend>
    <div class="form-group">
      <label for="exampleInputEmail1" class="form-label text-white-50">Email address</label>
      <input type="email" class="form-control" id="email" v-model="email" aria-describedby="emailHelp" placeholder="Enter email">
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

    <div class="form-group">
      <label for="exampleInputPassword1" class="form-label text-white-50">Confirm PW</label>
      <input type="password" class="form-control" id="passwordConfirm" v-model="passwordConfirm" placeholder="Password">
    </div>

    <div class="text-danger" v-if="errorPwMsg">
      <label for="exampleInputPassword1">Passwords do not match</label>
    </div>

    <div class="form-group">
        <button type="button" id="btnSignup" class="btn btn-primary" v-on:click="processSignup()">Submit</button>
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
		};
    },
    create() {

    },
    
    watch: {
        passwordConfirm: function(newValue) {
            // 비밀번호와 비밀번호 확인이 같은 경우 회원가입 버튼 활성화
            if(newValue == this.password) {
                this.errorPwMsg = false;
                document.getElementById("btnSignup").disabled = false;
            }
            // 비밀번호와 비밀번호 확인이 같지 않은 경우 회원가입 버튼 비활성화
            else {
                this.errorPwMsg = true;
                document.getElementById("btnSignup").disabled = true;
            }
        }
    },

	methods: {
		processSignup() {
            // validate 처리
            this.processSignupValidate();

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
            // TODO 비밀번호, 이메일 정규식 포함 필요
        },
        

        goBackUrl() {
            this.$router.push({name: 'Login'});
        }
	},
};
</script>

<style>

</style>