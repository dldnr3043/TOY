<template>
	<div>
        <div>
            <label for="email">Email</label>
            <input type="text" id="email" v-model="email" />
        </div>
        <div>
            <label for="name">Name</label>
            <input type="text" id="name" v-model="name" />
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" id="password" v-model="password" />
        </div>
        <div>
            <label for="passwordConfirm">Confirm Pw</label>
            <input type="password" id="passwordConfirm" v-model="passwordConfirm" />
        </div>
        <div v-if="errorPwMsg">
            <label>Passwords do not match </label>
        </div>
        <button type="button" id="btnSignup" v-on:click="processSignup()">회원가입</button>
        <button type="button" id="btnBack" v-on:click="goBackUrl()">뒤로가기</button>
	</div>
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
                else {
                    alert("이미 가입된 이메일 입니다.");
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

<style scoped></style>