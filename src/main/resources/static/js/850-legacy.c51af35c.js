"use strict";(self["webpackChunkbasd060"]=self["webpackChunkbasd060"]||[]).push([[850],{850:function(t,o,A){A.r(o),A.d(o,{default:function(){return Z}});var s=function(){var t=this,o=t.$createElement,s=t._self._c||o;return s("v-row",{staticClass:"ma-0"},[s("v-img",{staticClass:"LoginBg",attrs:{src:A(8054)}}),s("v-card",{staticClass:"ma-auto elevation-8 rounded-lg",attrs:{width:"350px"}},[s("v-card-title",{staticStyle:{color:"white","background-color":"#226498","background-image":"linear-gradient(to bottom, #297ab9, #1b4e77)"}},[s("v-img",{attrs:{src:A(6572),"max-width":"128px","max-height":"27px",contain:""}}),s("v-spacer"),t._v(" 崇友系統登入 "),s("v-spacer")],1),s("v-card-text",[s("v-text-field",{attrs:{"prepend-icon":"mdi-account",label:"工號",autofocus:!0},model:{value:t.loginForm.empNo,callback:function(o){t.$set(t.loginForm,"empNo",o)},expression:"loginForm.empNo"}}),s("v-text-field",{attrs:{counter:"6",maxlength:"6","prepend-icon":"mdi-lock",label:"密碼","append-icon":t.showPassWord?"mdi-eye":"mdi-eye-off",type:t.showPassWord?"text":"password"},on:{"click:append":function(o){t.showPassWord=!t.showPassWord}},model:{value:t.loginForm.usrPaswd,callback:function(o){t.$set(t.loginForm,"usrPaswd",o)},expression:"loginForm.usrPaswd"}})],1),s("v-sheet",{staticClass:"mt-n3",staticStyle:{"text-align":"center",color:"red"}},[t._v(t._s(t.msg))]),s("v-card-text",[s("v-btn",{attrs:{disabled:0==t.loginForm.empNo.length||0==t.loginForm.usrPaswd.length,color:"primary",loading:t.loading,block:""},on:{click:function(o){return t.logIn()}}},[t._v("登入"),s("v-icon",{attrs:{right:""}},[t._v("mdi-login")])],1)],1)],1)],1)},i=[],e=(A(1539),A(8862),A(1955)),n=A(9669),a=A.n(n),l=A(739),r={name:"Login",data:function(){return{loginForm:{empNo:"",usrPaswd:"",empName:"",usrGroup:"",token:""},msg:"",showPassWord:!1,loading:!1}},methods:{logIn:function(){var t=this;this.msg="",this.loading=!0,a().post("http://localhost:5000/login",this.loginForm).then((function(o){if("ok"==o.data.status){var A="rhs256";t.loginForm.token=A,t.loginForm.empName=o.data.empName,t.loginForm.usrGroup=o.data.usrGroup,e.Z.set("loginForm",JSON.stringify(t.loginForm),{expires:1/4,sameSite:"lax"}),t.$router.push({path:"menu"})}"invaildUserId"==o.data.status&&(t.msg="無效的工號",t.loginForm.empNo=""),"invaildPassWord"==o.data.status&&(t.msg="密碼錯誤",t.loginForm.usrPaswd="")})).catch((function(o){t.msg=l.b.errMsg(o)})).finally((function(){t.loading=!1}))}}},g=r,c=A(1001),d=A(3453),m=A.n(d),u=A(3150),b=A(3237),I=A(7118),w=A(6428),v=A(7047),h=A(2877),G=A(3385),p=A(9762),C=A(4063),N=(0,c.Z)(g,s,i,!1,null,"26874f86",null),Z=N.exports;m()(N,{VBtn:u.Z,VCard:b.Z,VCardText:I.ZB,VCardTitle:I.EB,VIcon:w.Z,VImg:v.Z,VRow:h.Z,VSheet:G.Z,VSpacer:p.Z,VTextField:C.Z})},6572:function(t){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAdwAAABkCAYAAAAsTYFZAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBXaW5kb3dzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkQwNDU1NzRGRTk5QTExRTNBODQwODhGQzdDNDI1N0VGIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkQwNDU1NzUwRTk5QTExRTNBODQwODhGQzdDNDI1N0VGIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RDA0NTU3NERFOTlBMTFFM0E4NDA4OEZDN0M0MjU3RUYiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RDA0NTU3NEVFOTlBMTFFM0E4NDA4OEZDN0M0MjU3RUYiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz6UwbYqAAAKg0lEQVR42uyd7VFiSxCG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"},8054:function(t,o,A){t.exports=A.p+"img/signin-bg.232c9d61.jpg"}}]);
//# sourceMappingURL=850-legacy.c51af35c.js.map