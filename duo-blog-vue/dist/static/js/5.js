webpackJsonp([5],{P7ry:function(e,r,t){"use strict";Object.defineProperty(r,"__esModule",{value:!0});var s=t("mvHQ"),n=t.n(s),a=t("vMJZ"),i=t("TIfe"),o={name:"Login",data:function(){return{username:"",email:"",password:"",nusername:"",nemail:"",npassword:"",npassword2:"",login:0,loginErr:!1,loginTitle:"用户名或密码错误",nusernameErr:!1,nemailErr:!1,npasswordErr:!1,npassword2Err:!1,registerErr:!1,registerTitle:"该邮箱已注册",step:1,fullscreenLoading:!1,urlstate:0}},methods:{routeChange:function(){this.login=void 0==this.$route.query.login?1:parseInt(this.$route.query.login),this.urlstate=void 0==this.$route.query.urlstate?0:this.$route.query.urlstate},loginEnterFun:function(e){13==(window.event?e.keyCode:e.which)&&this.gotoHome()},gotoHome:function(){var e=this;Object(a.d)(this.username,this.password).then(function(r){Object(i.c)(r.token),localStorage.setItem("userInfo",n()(r.userInfo)),localStorage.getItem("logUrl")?e.$router.push({path:localStorage.getItem("logUrl")}):e.$router.push({path:"/"})})},registerEnterFun:function(e){13==(window.event?e.keyCode:e.which)&&this.newRegister()},newRegister:function(){var e=this;e.nusername?e.nusernameErr=!1:e.nusernameErr=!0,/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(e.nemail)?e.nemailErr=!1:e.nemailErr=!0,e.npassword&&/^(\w){6,12}$/.test(e.npassword)?(e.npasswordErr=!1,e.npassword==e.npassword2?e.npassword2Err=!1:e.npassword2Err=!0):e.npasswordErr=!0,e.nusernameErr||e.nemailErr||e.npasswordErr||(e.fullscreenLoading=!0,Object(a.e)(e.nusername,e.nnickName,e.nemail,e.npassword).then(function(r){e.goLogin()}).catch(function(r){e.fullscreenLoading=!1}))},goLogin:function(){this.$router.push({path:"/Login?login=1"})},goRegister:function(){this.$router.push({path:"/Login?login=0"})}},components:{},watch:{$route:"routeChange"},created:function(){this.routeChange()}},l={render:function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",[t("div",{staticClass:"container"},[t("h1",{staticClass:"loginTitle"}),e._v(" "),t("div",{directives:[{name:"show",rawName:"v-show",value:!e.err2005,expression:"!err2005"}]},[1==e.login?t("div",{staticClass:"loginBox"},[e._m(0),e._v(" "),t("el-alert",{directives:[{name:"show",rawName:"v-show",value:e.loginErr,expression:"loginErr"}],attrs:{title:e.loginTitle,type:"error","show-icon":"",closable:!1}}),e._v(" "),t("el-input",{attrs:{type:"text",placeholder:"用户名"},model:{value:e.username,callback:function(r){e.username=r},expression:"username"}}),e._v(" "),t("el-input",{attrs:{type:"password",placeholder:"密码"},nativeOn:{keyup:function(r){return"button"in r||!e._k(r.keyCode,"enter",13,r.key,"Enter")?e.loginEnterFun(r):null}},model:{value:e.password,callback:function(r){e.password=r},expression:"password"}}),e._v(" "),e._m(1),e._v(" "),t("div",{staticClass:"lr-btn tcolors-bg",on:{click:e.gotoHome}},[e._v("登录")]),e._v(" "),e._m(2)],1):t("div",{staticClass:"registerBox"},[e._m(3),e._v(" "),t("el-alert",{directives:[{name:"show",rawName:"v-show",value:e.registerErr,expression:"registerErr"}],attrs:{title:e.registerTitle,type:"error","show-icon":"",closable:!1}}),e._v(" "),t("el-input",{attrs:{type:"text",placeholder:"用户名"},model:{value:e.nusername,callback:function(r){e.nusername=r},expression:"nusername"}}),e._v(" "),t("el-alert",{directives:[{name:"show",rawName:"v-show",value:e.nusernameErr,expression:"nusernameErr"}],attrs:{title:"用户名错误",type:"error","show-icon":"",closable:!1}}),e._v(" "),t("el-input",{attrs:{type:"text",placeholder:"昵称"},model:{value:e.nnickName,callback:function(r){e.nnickName=r},expression:"nnickName"}}),e._v(" "),t("el-input",{attrs:{type:"email",placeholder:"邮箱"},model:{value:e.nemail,callback:function(r){e.nemail=r},expression:"nemail"}}),e._v(" "),t("el-alert",{directives:[{name:"show",rawName:"v-show",value:e.nemailErr,expression:"nemailErr"}],attrs:{title:"邮箱错误",type:"error","show-icon":"",closable:!1}}),e._v(" "),t("el-input",{attrs:{type:"password",placeholder:"密码:6-12位英文、数字、下划线"},model:{value:e.npassword,callback:function(r){e.npassword=r},expression:"npassword"}}),e._v(" "),t("el-alert",{directives:[{name:"show",rawName:"v-show",value:e.npasswordErr,expression:"npasswordErr"}],attrs:{title:"密码错误",type:"error","show-icon":"",closable:!1}}),e._v(" "),t("el-input",{attrs:{type:"password",placeholder:"确认密码"},nativeOn:{keyup:function(r){return"button"in r||!e._k(r.keyCode,"enter",13,r.key,"Enter")?e.registerEnterFun(r):null}},model:{value:e.npassword2,callback:function(r){e.npassword2=r},expression:"npassword2"}}),e._v(" "),t("el-alert",{directives:[{name:"show",rawName:"v-show",value:e.npassword2Err,expression:"npassword2Err"}],attrs:{title:"重复密码有误",type:"error","show-icon":"",closable:!1}}),e._v(" "),t("div",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.fullscreenLoading,expression:"fullscreenLoading",modifiers:{fullscreen:!0,lock:!0}}],staticClass:"lr-btn tcolors-bg",attrs:{"element-loading-text":"提交中"},on:{click:e.newRegister}},[e._v("注册")])],1)])])])},staticRenderFns:[function(){var e=this.$createElement,r=this._self._c||e;return r("div",{staticClass:"lr-title"},[r("h1",[this._v("登录")]),this._v(" "),r("p",[this._v("\n                        新用户"),r("a",{staticClass:"tcolors",attrs:{href:"#/Login?login=0"}},[this._v("注册")])])])},function(){var e=this.$createElement,r=this._self._c||e;return r("h3",[r("a",{attrs:{href:""}},[this._v("忘记密码？")])])},function(){var e=this.$createElement,r=this._self._c||e;return r("div",{staticClass:"otherLogin"},[r("a",{attrs:{href:"javascript:void(0)"}},[r("i",{staticClass:"fa fa-fw fa-wechat"})]),this._v(" "),r("a",{attrs:{href:"javascript:void(0)"}},[r("i",{staticClass:"fa fa-fw fa-qq"})]),this._v(" "),r("a",{attrs:{href:"javascript:void(0)"}},[r("i",{staticClass:"fa fa-fw fa-weibo"})])])},function(){var e=this.$createElement,r=this._self._c||e;return r("div",{staticClass:"lr-title"},[r("h1",[this._v("注册")]),this._v(" "),r("p",[this._v("\n                        已有账号"),r("a",{staticClass:"tcolors",attrs:{href:"#/Login?login=1"}},[this._v("登录")])])])}]};var c=t("VU/8")(o,l,!1,function(e){t("g01P")},null,null);r.default=c.exports},g01P:function(e,r){},mvHQ:function(e,r,t){e.exports={default:t("qkKv"),__esModule:!0}},qkKv:function(e,r,t){var s=t("FeBl"),n=s.JSON||(s.JSON={stringify:JSON.stringify});e.exports=function(e){return n.stringify.apply(n,arguments)}}});