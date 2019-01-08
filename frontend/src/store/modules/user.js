const state={
    email: '',
    nickname: '',
    moto: '',
    avatar_path: '',
};
// const getters={
//   getEmail(state){
//     return state.email;
//   },

//   getNickName(state){
//       return state.nickname;
//   },
// };

// const mutations={
//      pushCollects(state,items){ //如何变化collects,插入items
//         state.collects.push(items)
//      }
//  };
export default {
     namespaced:true, //用于在全局引用此文件里的方法时标识这一个的文件名
     state,
}