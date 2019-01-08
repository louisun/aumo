<template>
  <el-row class="main-footer">
    <el-col :span="24" style="text-align:center; font-weight:bold">
            Copyright © 2018 Louis
    </el-col>

  </el-row>
</template>

<style lang="scss" scoped>
$footer-height: 60px;
$img-height: 50px;
$imgMargin: (($footer-height - $img-height)/2);


.main-footer {
  height: $footer-height;
  padding-top: 20px;
  bottom: 0px;
  background-image: url("../assets/Almost.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  box-shadow: 0 3px 3px #aaa9a9;
  p {
      margin-bottom: 0px;
  }
}
</style>

