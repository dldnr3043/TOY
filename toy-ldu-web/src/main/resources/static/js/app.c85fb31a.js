(function(t){function e(e){for(var r,s,i=e[0],l=e[1],u=e[2],c=0,m=[];c<i.length;c++)s=i[c],Object.prototype.hasOwnProperty.call(n,s)&&n[s]&&m.push(n[s][0]),n[s]=0;for(r in l)Object.prototype.hasOwnProperty.call(l,r)&&(t[r]=l[r]);p&&p(e);while(m.length)m.shift()();return o.push.apply(o,u||[]),a()}function a(){for(var t,e=0;e<o.length;e++){for(var a=o[e],r=!0,i=1;i<a.length;i++){var l=a[i];0!==n[l]&&(r=!1)}r&&(o.splice(e--,1),t=s(s.s=a[0]))}return t}var r={},n={app:0},o=[];function s(e){if(r[e])return r[e].exports;var a=r[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,s),a.l=!0,a.exports}s.m=t,s.c=r,s.d=function(t,e,a){s.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},s.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},s.t=function(t,e){if(1&e&&(t=s(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(s.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var r in t)s.d(a,r,function(e){return t[e]}.bind(null,r));return a},s.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return s.d(e,"a",e),e},s.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},s.p="/";var i=window["webpackJsonp"]=window["webpackJsonp"]||[],l=i.push.bind(i);i.push=e,i=i.slice();for(var u=0;u<i.length;u++)e(i[u]);var p=l;o.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("56d7")},"034f":function(t,e,a){"use strict";a("9085")},"16b4":function(t,e,a){},"56d7":function(t,e,a){"use strict";a.r(e);a("e260"),a("e6cf"),a("cca6"),a("a79d");var r=a("2b0e"),n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"app"}},[a("router-view")],1)},o=[],s={name:"App"},i=s,l=(a("034f"),a("2877")),u=Object(l["a"])(i,n,o,!1,null,null,null),p=u.exports,c=a("2f62");r["a"].use(c["a"]);var m=new c["a"].Store({state:{},mutations:{},getters:{},actions:{}}),d=m,v=a("8c4f"),f=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"test"},[a("h2",[t._v("Log In")]),a("div",{staticClass:"form-group"},[a("input",{directives:[{name:"model",rawName:"v-model",value:t.email,expression:"email"}],staticClass:"form-control",attrs:{type:"text",placeholder:"Enter your Email"},domProps:{value:t.email},on:{input:function(e){e.target.composing||(t.email=e.target.value)}}}),a("input",{directives:[{name:"model",rawName:"v-model",value:t.password,expression:"password"}],staticClass:"form-control",attrs:{type:"password",placeholder:"Enter your password"},domProps:{value:t.password},on:{input:function(e){e.target.composing||(t.password=e.target.value)}}}),a("button",{staticClass:"btn btn-primary",attrs:{type:"button"},on:{click:function(e){return t.processLogin()}}},[t._v("Login")]),a("button",{staticClass:"btn btn-primary",attrs:{type:"button"},on:{click:function(e){return t.goSignUpPage()}}},[t._v("SignUp")])])])},h=[],g={name:"Login",data:function(){return{email:"",password:""}},methods:{processLogin:function(){var t=this,e={};e.email=this.email,e.password=this.password,this.$http.post("/login",e).then((function(e){"SUCCESS"==e.data.STATUS?(t.$http.defaults.headers.common["X-AUTH-TOKEN"]=e.headers["x-auth-token"],t.$router.push({name:"ChatMain"})):"FAIL"==e.data.STATUS&&t.$nav.openAlertDialog("Warning !!",e.data.MESSAGE)}))},goSignUpPage:function(){this.$router.push({name:"Signup"})}}},b=g,_=Object(l["a"])(b,f,h,!1,null,null,null),w=_.exports,y=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"hello"},[a("h1",[t._v(t._s(t.msg))]),t._m(0),a("h3",[t._v("Installed CLI Plugins")]),t._m(1),a("h3",[t._v("Essential Links")]),t._m(2),a("h3",[t._v("Ecosystem")]),t._m(3)])},C=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("p",[t._v(" For a guide and recipes on how to configure / customize this project,"),a("br"),t._v(" check out the "),a("a",{attrs:{href:"https://cli.vuejs.org",target:"_blank",rel:"noopener"}},[t._v("vue-cli documentation")]),t._v(". ")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",[a("li",[a("a",{attrs:{href:"https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-babel",target:"_blank",rel:"noopener"}},[t._v("babel")])]),a("li",[a("a",{attrs:{href:"https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-eslint",target:"_blank",rel:"noopener"}},[t._v("eslint")])])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",[a("li",[a("a",{attrs:{href:"https://vuejs.org",target:"_blank",rel:"noopener"}},[t._v("Core Docs")])]),a("li",[a("a",{attrs:{href:"https://forum.vuejs.org",target:"_blank",rel:"noopener"}},[t._v("Forum")])]),a("li",[a("a",{attrs:{href:"https://chat.vuejs.org",target:"_blank",rel:"noopener"}},[t._v("Community Chat")])]),a("li",[a("a",{attrs:{href:"https://twitter.com/vuejs",target:"_blank",rel:"noopener"}},[t._v("Twitter")])]),a("li",[a("a",{attrs:{href:"https://news.vuejs.org",target:"_blank",rel:"noopener"}},[t._v("News")])])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",[a("li",[a("a",{attrs:{href:"https://router.vuejs.org",target:"_blank",rel:"noopener"}},[t._v("vue-router")])]),a("li",[a("a",{attrs:{href:"https://vuex.vuejs.org",target:"_blank",rel:"noopener"}},[t._v("vuex")])]),a("li",[a("a",{attrs:{href:"https://github.com/vuejs/vue-devtools#vue-devtools",target:"_blank",rel:"noopener"}},[t._v("vue-devtools")])]),a("li",[a("a",{attrs:{href:"https://vue-loader.vuejs.org",target:"_blank",rel:"noopener"}},[t._v("vue-loader")])]),a("li",[a("a",{attrs:{href:"https://github.com/vuejs/awesome-vue",target:"_blank",rel:"noopener"}},[t._v("awesome-vue")])])])}],S={name:"ChatMain",props:{msg:String},methods:{processLogin:function(){var t={};t.email=this.email,t.password=this.password,this.$http.post("/api/test",t).then((function(t){console.log(t)}))},goSignUpPage:function(){this.$router.push({name:"Signup"})}}},x=S,P=Object(l["a"])(x,y,C,!1,null,null,null),j=P.exports,k=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("fieldset",[a("legend",[t._v("SignUp")]),a("div",{staticClass:"form-group"},[a("label",{staticClass:"form-label text-white-50",attrs:{for:"exampleInputEmail1"}},[t._v("Email address")]),a("input",{directives:[{name:"model",rawName:"v-model",value:t.email,expression:"email"}],staticClass:"form-control",attrs:{type:"email",id:"email","aria-describedby":"emailHelp",placeholder:"Enter email"},domProps:{value:t.email},on:{input:function(e){e.target.composing||(t.email=e.target.value)}}}),a("small",{staticClass:"form-text text-muted",attrs:{id:"emailHelp"}},[t._v("We'll never share your email with anyone else.")])]),a("div",{staticClass:"form-group"},[a("label",{staticClass:"form-label text-white-50",attrs:{for:"exampleInputEmail1"}},[t._v("Name")]),a("input",{directives:[{name:"model",rawName:"v-model",value:t.name,expression:"name"}],staticClass:"form-control",attrs:{type:"text",id:"name",placeholder:"Enter your name"},domProps:{value:t.name},on:{input:function(e){e.target.composing||(t.name=e.target.value)}}})]),a("div",{staticClass:"form-group"},[a("label",{staticClass:"form-label text-white-50",attrs:{for:"exampleInputPassword1"}},[t._v("Password")]),a("input",{directives:[{name:"model",rawName:"v-model",value:t.password,expression:"password"}],staticClass:"form-control",attrs:{type:"password",id:"password",placeholder:"Password"},domProps:{value:t.password},on:{input:function(e){e.target.composing||(t.password=e.target.value)}}})]),a("div",{staticClass:"form-group"},[a("label",{staticClass:"form-label text-white-50",attrs:{for:"exampleInputPassword1"}},[t._v("Confirm PW")]),a("input",{directives:[{name:"model",rawName:"v-model",value:t.passwordConfirm,expression:"passwordConfirm"}],staticClass:"form-control",attrs:{type:"password",id:"passwordConfirm",placeholder:"Password"},domProps:{value:t.passwordConfirm},on:{input:function(e){e.target.composing||(t.passwordConfirm=e.target.value)}}})]),t.errorPwMsg?a("div",{staticClass:"text-danger"},[a("label",{attrs:{for:"exampleInputPassword1"}},[t._v("Passwords do not match")])]):t._e(),a("div",{staticClass:"form-group"},[a("button",{staticClass:"btn btn-primary",attrs:{type:"button",id:"btnSignup"},on:{click:function(e){return t.processSignup()}}},[t._v("Submit")]),a("button",{staticClass:"btn btn-primary",attrs:{type:"button",id:"btnBack"},on:{click:function(e){return t.goBackUrl()}}},[t._v("back")])])])},E=[],$=(a("b0c0"),{name:"SignupForm",data:function(){return{email:"",name:"",password:"",passwordConfirm:"",errorPwMsg:!1}},create:function(){},watch:{passwordConfirm:function(t){t==this.password?(this.errorPwMsg=!1,document.getElementById("btnSignup").disabled=!1):(this.errorPwMsg=!0,document.getElementById("btnSignup").disabled=!0)}},methods:{processSignup:function(){var t=this;this.processSignupValidate();var e={};e.EMAIL=this.email,e.NAME=this.name,e.PASSWORD=this.password,this.$http.post("/signup",e).then((function(e){"SUCCESS"==e.data.STATUS?(alert("회원가입이 완료되었습니다 !!"),t.$router.push({name:"Login"})):alert("이미 가입된 이메일 입니다.")}))},processSignupValidate:function(){},goBackUrl:function(){this.$router.push({name:"Login"})}}}),O=$,A=Object(l["a"])(O,k,E,!1,null,null,null),M=A.exports;r["a"].use(v["a"]);var L=new v["a"]({mode:"history",routes:[{path:"/",name:"Root",component:w},{path:"/login",name:"Login",component:w},{path:"/signup",name:"Signup",component:M},{path:"/web/chat/main",name:"ChatMain",component:j}]}),T=a("bc3a"),U=a.n(T),I=function(){var t=this,e=t.$createElement,a=t._self._c||e;return t.alertDialog?a("Alert"):t._e()},N=[],D=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"alert alert-dismissible alert-secondary"},[a("button",{staticClass:"btn-close",attrs:{type:"button","data-bs-dismiss":"alert"}}),a("strong",[t._v(t._s(t.title))]),a("p",[t._v(t._s(t.msg))])])},B=[],F={name:"Alert",props:{},data:function(){return{title:"",msg:""}},methods:{AlertMsg:function(t,e){this.title=t,this.msg=e}}},W=F,H=Object(l["a"])(W,D,B,!1,null,null,null),J=H.exports,R={name:"Nav",props:{},components:{Alert:J},data:function(){return{alertDialog:!1}},methods:{openAlertDialog:function(t,e){this.alertDialog=!0,J.AlertMsg(t,e)}}},V=R,z=Object(l["a"])(V,I,N,!1,null,null,null),G=z.exports;a("16b4"),r["a"].prototype.$store=d,U.a.defaults.headers.post["Content-Type"]="application.json",U.a.defaults.headers.get["Cache-Control"]="no-cache",U.a.defaults.headers.get["Pragma"]="no-cache",r["a"].prototype.$http=U.a,r["a"].prototype.$nav=G,r["a"].config.productionTip=!1,new r["a"]({axios:U.a,store:d,router:L,nav:G,render:function(t){return t(p)}}).$mount("#app")},9085:function(t,e,a){}});
//# sourceMappingURL=app.c85fb31a.js.map