<template>
  <div id="login-container-div">
    <el-form
      :model="ruleForm"
      :rules="rules1"
      ref="ruleForm"
      label-position="left"
      label-width="0px"
      class="demo-ruleForm login-container"
      position="relative"
    >
      <h3 class="title">Aumo 登录</h3>
      <el-form-item prop="email">
        <el-input type="text" v-model="ruleForm.email" auto-complete="off" placeholder="邮箱账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="ruleForm.password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox v-model="checked" checked class="remember" style="float:right">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleLogin"
          :loading="logining"
          round
        >登录</el-button>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button
          type="success"
          style="width:100%;"
          @click.native.prevent="handleSwitchToRegister"
          round
        >注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { requestLogin } from "../api/api";
export default {
  data() {
    return {
      logining: false,
      ruleForm: {
        email: "",
        password: ""
      },
      rules1: {
        email: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      checked: true
    };
  },
  methods: {
    handleLogin(ev) {
      var _this = this;
      this.$refs.ruleForm.validate(valid => {
        if (valid) {
          this.logining = true;
          var loginParams = {
            email: this.ruleForm.email,
            password: this.ruleForm.password
          };
          requestLogin(loginParams).then(data => {
            this.logining = false;
            let code = data.returnCode;
            let msg = data.returnMsg;
            let returnData = data.returnData; // 用户信息
            if (code !== "200") {
              this.$message({
                message: msg,
                type: "error"
              });
            } else {
              // sessionStorage.setItem("user", JSON.stringify(user));
              // 保存用户信息到全局
              this.$store.state.user.email = returnData.email;
              this.$store.state.user.nickname = returnData.nickname;
              if(returnData.moto != ''){
                this.$store.state.user.moto = returnData.moto;
              }
              else{
                this.$store.state.user.moto = '好好学习，天天向上';
              }
              if(returnData.avatar_path != ''){
                this.$store.state.user.avatar_path = returnData.avatar_path;
              }
              else{
                this.$store.state.user.avatar_path = 'https://bucket-1255905387.cos.ap-shanghai.myqcloud.com/2018-12-23-09-42-03_r2.png';
              }
              console.log(this.$store.state.user);
              this.$router.push({ path: "/userinfo" });
              this.$message({
                message: msg,
                type: "success"
              });
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    handleSwitchToRegister() {
      this.$router.push({ path: "/register" });
      this.$message({
        message: "已切换至注册页面",
        type: "success"
      });
    }
  }
};
</script>

<style lang="scss" scoped>
#login-container-div {
  position: absolute;
  width: 100%;
  height: 100%;
  background-image: url("../assets/Reef.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
}

.login-container {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  margin: auto;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  width: 400px;
  height: 400px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 2px 2px 5px #525050;
  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .remember {
    margin: 0px 0px 35px 0px;
  }
}
</style>
