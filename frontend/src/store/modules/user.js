import {myAxios} from "@/api/api";



export default {
     namespaced:true, //用于在全局引用此文件里的方法时标识这一个的文件名
     state: {
        email: '',
        nickname: '',
        moto: '',
        avatar_path: '',
     },
    //  mutations: {
    //     getUserInfo(state){
    //         myAxios.get('/userinfo').then(data => data.data).then(data => {
    //             let code = data.returnCode;
    //             let msg = data.returnMsg;
    //             let returnData = data.returnData;
    //             if (code !== "200") {
    //               params.$message({
    //                 message: msg,
    //                 type: "error"
    //               });
    //             }
    //             else{
    //               state.email = returnData.email;
    //               state.nickname = returnData.nickname;
    //                   if(returnData.moto != ''){
    //                     state.moto = returnData.moto;
    //                   }
    //                   else{
    //                     state.moto = '好好学习，天天向上';
    //                   }
    //                   if(returnData.avatar_path != ''){
    //                     state.avatar_path = returnData.avatar_path;
    //                   }
    //                   else{
    //                     state.avatar_path = 'https://bucket-1255905387.cos.ap-shanghai.myqcloud.com/2018-12-23-09-42-03_r2.png';
    //                   }
    //                 //   params.userInfoForm.moto = this.$store.state.user.moto;
    //                 //   params.userInfoForm.nickname = this.$store.state.user.nickname;
    //             }
    //           })
    //     }
    //  },
    //  actions: {
    //     getUserinfo(context){
    //         context.commit('user/getUserInfo');
    //     }
    //  }
}