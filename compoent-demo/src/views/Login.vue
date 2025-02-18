<template>
<body id="poster">
    <el-form class="login-container" label-position="left"  label-width="0px">
        <h3 class="login_title">
            系统登陆
            <el-button @click="toRegister()">点我注册</el-button>
        </h3>
  <el-form-item label="">
    <el-input type="text" v-model="loginForm.username" placeholder="账号"></el-input>
  </el-form-item>
  <el-form-item label="">
    <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
  </el-form-item>
  <el-form-item style="width: 100%;">
    <el-button type="primary" style="width: 100%;background: #505458;border: none;" v-on:click="login()">登录</el-button>
   
  </el-form-item>
</el-form>

</body>

</template>

<script>
 export default{
      name:'Login',

    data() {
      return {
        loginForm: {
          username: '',
          password: '',
        }
      }
    },
    methods: {
      login() {
        this.$http.post("/login", this.loginForm).then((resp) => {
          let data = resp.data;
          if (data.success) {
            localStorage.setItem("isAuthenticated", "true"); // 设置登录状态
            this.loginForm = {};
            this.$message.success(data.message);
            this.$router.push({ path: "/Home" });
          } else {
            this.$message.error(data.message);
          }
        }).catch((error) => {
          console.error(error);
          this.$message.error("登录请求失败，请检查网络");
        });
      },
    logout() {
      localStorage.removeItem("isAuthenticated"); // 清除登录状态
      this.$router.push({ path: "/" });
    },
      toRegister() {
        this.$router.push({path:'/Register'})
      }
    }
}


</script>

<style>
  #poster{
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
    padding: 0px;
  }
  .login-container{
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
 .login_title{
  margin: 0px autp 40px auto;
  text-align: center;
  color: #505458;
  
 }
</style>