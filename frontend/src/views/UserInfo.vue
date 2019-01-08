<template>
  <div id="main-div">
    <Header></Header>
    <div id="content-wrapper-div">
      <UserInfoContent></UserInfoContent>
    </div>
    <!-- <div id="middle">hello</div> -->
    <Footer></Footer>

  </div>
 
</template>

<style scoped lang="scss">
#content-wrapper-div{
  background-color: #e2e2e2;
  min-height: calc(100vh - 120px);
}

</style>

<script>
import Header from "@/components/Header.vue";
import UserInfoContent from '@/components/UserInfoContent.vue';
import Footer from '@/components/Footer.vue';
export default {
  components: {
    Header,
    UserInfoContent,
    Footer
  }
};
</script>