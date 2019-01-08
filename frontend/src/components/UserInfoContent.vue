<template>
  <div class="content">
    <div id="left-bar"></div>
    <div id="right-bar">
      <div class="sep20"></div>
      <el-card class="box-card" :body-style="{padding: '10px'}">
        <div class="clearfix" id="card-header">
          <img
            :src="$store.state.user.avatar_path"
            id="card-avatar"
          >
          <div id="card-user-name">
            <span>
              <a href="#">{{$store.state.user.nickname}}</a>
            </span>
          </div>
        </div>
        <div id="card-user-info">
          <br>
          <br>Email:
          <span>{{$store.state.user.email}}</span>
          <br>
          <br>
          <br>Moto:
          <span>{{$store.state.user.moto}}</span>
        </div>
      </el-card>
    </div>

    <div id="main-part">
      <div class="sep20"></div>
      <div class="main-box">
        <el-breadcrumb separator-class="el-icon-arrow-right" class="main-header">
          <el-breadcrumb-item :to="{ path: '/' }">Aumo</el-breadcrumb-item>
          <el-breadcrumb-item>个人信息设置</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="main-form">
          <el-form
            :model="userInfoForm"
            status-icon
            ref="userInfoForm"
            label-width="90px"
            label-position="left"
          >
            <el-form-item label="昵称" prop="nickname">
              <el-input type="text" v-model="userInfoForm.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="个性签名" prop="moto">
              <el-input type="textarea" v-model="userInfoForm.moto" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button
                type="primary"
                @click.prevent="submitInfoForm('userInfoForm')"
                :loading="loading1"
              >保存修改</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div class="sep20"></div>
      <div class="main-box">
        <div class="main-header" style="border-bottom: 1px solid #e2e2e2">更改密码</div>
        <div class="main-form">
          <el-form
            :model="userPasswordForm"
            status-icon
            :rules="rules2"
            ref="userPasswordForm"
            label-width="100px"
            label-position="left"
          >
            <el-form-item label="当前密码" prop="currentPassword">
              <el-input
                type="password"
                v-model="userPasswordForm.currentPassword"
                autocomplete="off"
                placeholder="请输入当前密码"
              ></el-input>
            </el-form-item>
            <el-form-item label="新的密码" prop="newPassword">
              <el-input
                type="password"
                v-model="userPasswordForm.newPassword"
                autocomplete="off"
                placeholder="密码长度在6-32位之间"
              ></el-input>
            </el-form-item>
            <el-form-item label="确认新密码" prop="checkPassword">
              <el-input
                type="password"
                v-model="userPasswordForm.checkPassword"
                autocomplete="off"
                placeholder="再次输入密码"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button
                type="primary"
                @click.prevent="submitPasswordForm('userPasswordForm')"
                :loading="loading1"
              >重置密码</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div class="sep20"></div>
      <div class="sep20"></div>
    </div>
  </div>
</template>
<script>
import { requestUpdateUser } from "../api/api";
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        var reg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){5,31}$/;
        if (!reg.exec(value)) {
          callback(new Error("请输入6-32位的密码"));
        } else {
          callback();
        }
      }
    };
    var validatePass1 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        var reg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){5,31}$/;
        if (!reg.exec(value)) {
          callback(new Error("请输入6-32位的密码"));
        } else if (this.userPasswordForm.checkPassword !== "") {
          this.$refs.userPasswordForm.validateField("checkPassword");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else {
        var reg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){5,31}$/;
        if (!reg.exec(value)) {
          callback(new Error("请输入6-32位的密码"));
        } else if (value !== this.userPasswordForm.newPassword) {
          callback(new Error("两次输入的密码不一致!"));
        } else {
          callback();
        }
      }
    };
    return {
      loading1: false,
      loading2: false,
      userInfoForm: {
        nickname: this.$store.state.user.nickname,
        moto: this.$store.state.user.moto
      },
      userPasswordForm: {
        currentPassword: "",
        newPassword: "",
        checkPassword: ""
      },
      //   rules1: {
      //     nickname: [
      //       { required: true, validator: validateNickname, trigger: "blur" }
      //     ],
      //     moto: [{ required: false, validator: validateMoto, trigger: "blur" }]
      //   },
      rules2: {
        currentPassword: [
          { required: true, validator: validatePass, trigger: "blur" }
        ],
        newPassword: [
          { required: true, validator: validatePass1, trigger: "blur" }
        ],
        checkPassword: [
          { required: true, validator: validatePass2, trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submitInfoForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading1 = true;
          var loginParams = {
            email: this.$store.state.user.email,
            nickname: this.userInfoForm.nickname,
            moto: this.userInfoForm.moto
          };
          requestUpdateUser(loginParams).then(data => {
            this.loading1 = false;
            let code = data.returnCode;
            let msg = data.returnMsg;
            let returnData = data.returnData;
            if (code !== "200") {
              this.$message({
                message: msg,
                type: "error"
              });
            }
            else{
              this.$message({
                message: returnData,
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
    submitPasswordForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style scoped lang="scss">
.sep20 {
  height: 20px;
}
.content {
  min-width: 600px;
  max-width: 1100px;
  margin: 0 auto 0 auto;

  #left-bar {
    width: 0;
    float: left;
  }

  #right-bar {
    width: 270px;
    float: right;
    margin-right: 20px;

    .box-card {
      a,
      a:link,
      a:visited {
        color: #778087;
        text-decoration: none;
        word-break: break-all;
      }

      .text {
        font-size: 14px;
      }

      .item {
        margin-bottom: 18px;
      }

      .clearfix:before,
      .clearfix:after {
        display: table;
        content: "";
      }
      .clearfix:after {
        clear: both;
      }

      #card-header {
        padding: 0;
        border-bottom: 0.5px solid rgb(210, 204, 204);
        margin-bottom: 10px;
      }

      #card-avatar {
        border-radius: 4px;
        width: 60px;
        height: 60px;
      }

      div #card-user-name {
        height: 60px;
        width: 160px;
        float: right;
        line-height: 60px;
        padding-left: 20px;
      }

      #card-user-name > span {
        font-size: 20px;
      }
    }
  }

  .main-box {
    min-width: 580px;
    margin: 0 310px 0 20px;
    border-radius: 4px;
    background-color: #fff;

    .main-header {
      padding: 30px 30px 30px 30px;
      height: 20px;
      font-size: 18px;
      border-bottom: 1px solid #e2e2e2;
    }

    .main-form {
      padding: 30px;
      .el-form-item {
        height: 40px;
        width: 400px;
        margin-top: 30px;
      }
    }
  }
}
</style>
