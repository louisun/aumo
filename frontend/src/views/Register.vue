<template>
  <div id="login-container-div">
    <el-form
      :model="registerForm"
      :rules="rules"
      ref="registerForm"
      label-position="left"
      class="demo-ruleForm login-container"
      position="relative"
    >
      <h3 class="title">Aumo 注册新用户</h3>
      <el-form-item prop="nickname" label="昵称" label-width="80px">
        <el-input type="text" auto-complete="off" placeholder="昵称" v-model="registerForm.nickname"></el-input>
      </el-form-item>

      <el-form-item prop="email" label="邮箱" label-width="80px">
        <el-input type="text" v-model="registerForm.email" auto-complete="off" placeholder="Email"></el-input>
      </el-form-item>
      <el-form-item prop="password" label="密码" label-width="80px">
        <el-input
          type="password"
          v-model="registerForm.password"
          auto-complete="off"
          placeholder="密码长度在6-32位之间"
        ></el-input>
      </el-form-item>
      <el-form-item prop="checkPass" label="确认密码" label-width="80px">
        <el-input
          type="password"
          v-model="registerForm.checkPass"
          auto-complete="off"
          placeholder="再次输入密码"
        ></el-input>
      </el-form-item>

      <el-form-item style="width:100%;">
        <el-button type="success" style="width:100%;" @click.native.prevent="handleRegister" round>注册</el-button>
      </el-form-item>

      <el-form-item style="width:100%;">
        <el-button
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleSwitchToLogin"
          round
        >已有账号？</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { requestRegister } from '../api/api';
export default {
  data() {
    var validateEmail = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入邮箱"));
      } else {
        var reg = new RegExp(
          "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"
        );
        if (!reg.exec(value)) {
          callback(new Error("请输入正确的邮箱"));
        }
        else{
            callback();
        }
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        var reg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){5,31}$/;
        if (!reg.exec(value)) {
          callback(new Error("请输入6-32位的密码"));
        } else if (this.registerForm.checkPass !== "") {
          this.$refs.registerForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.registerForm.password) {
        callback(new Error("两次输入的密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      logining: false,
      registerForm: {
        nickname: "",
        email: "",
        password: "",
        checkPass: ""
      },
      rules: {
        nickname: [{ required: true, message:"请输入昵称", trigger: "blur" }],
        email: [{ required: true, validator: validateEmail, trigger: "blur" }],
        password: [
          { required: true, validator: validatePass, trigger: "blur" }
        ],
        checkPass: [
          { required: true, validator: validatePass2, trigger: "blur" }
        ]
      },
      checked: true
    };
  },
  methods: {
    handleRegister(ev) {
      var _this = this;
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          var registerParams = {
            email: this.registerForm.email,
            password: this.registerForm.password,
            nickname: this.registerForm.nickname
          };
          requestRegister(registerParams).then(data => {
            this.logining = false;

            let code = data.returnCode
            let msg = data.returnMsg
            let user = data.returnData

            console.log(code);
            console.log(msg);
            console.log(user);

            if (code !== "200") {
              this.$message({
                message: msg,
                type: "error"
              });
            } else {
              sessionStorage.setItem("user", JSON.stringify(user));
              this.$router.push({ path: "/login" });
              this.$message({
                message: "注册成功，请登录",
                type: "success"
              })
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    handleSwitchToLogin() {
      this.$router.push({ path: "/login" });
      this.$message({
        message: "已切换至登录页面",
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
  background-image: url("../assets/Almost.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
}

.login-container {
  /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
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
  height: 450px;
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
