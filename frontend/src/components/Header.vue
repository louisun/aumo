<template>
  <el-row class="main-header">
    <el-col :span="6">
      <span id="logo">
        <img src="@/assets/logo.jpg">
      </span>
    </el-col>
    <el-dropdown @command="handleCommand">
      <span class="el-dropdown-link">
        <img
          src="https://bucket-1255905387.cos.ap-shanghai.myqcloud.com/2018-12-23-09-42-03_r2.png "
        >
      </span>

      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="userinfo">个人信息</el-dropdown-item>
        <el-dropdown-item command="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </el-row>
</template>


<<script>
export default {
    methods: {
        handleCommand(command){
            this.$message('click on item ' + command);
        }
        
    }
}
</script>

<style lang="scss" scoped>
$header-height: 60px;
$img-height: 50px;
$imgMargin: (($header-height - $img-height)/2);

.main-header {
  height: $header-height;
  background-image: url("../assets/Almost.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  box-shadow: 0 3px 10px #aaa9a9;

  #logo {
    img {
      display: inline-block;
      height: $header-height;
    }
  }

  .el-dropdown-link {
    img {
      display: inline-block;
      width: 50px;
      height: 50px;
      border-radius: 25px;
      background-color: #fff;
      margin-top: $imgMargin;
      margin-right: 30px;
    }
  }

  .el-dropdown {
    cursor: pointer;
    float: right;
  }
}
</style>