import axios from 'axios';

const myAxios = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 5000,
    withCredentials: true,
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });


export const requestRegister = params => {
    return myAxios.post('/register', params).then(data => data.data);
};

export const requestLogin = params => { 
  return myAxios.post('/login', params).then(data => data.data); 
};

export const requestLogout = () => {
  return myAxios.post('/logout').then(data => data.data);
};

export const requestUpdateUser = params => {
  return myAxios.post('/userupdate', params).then(data => data.data); 
};


export const getUserInfo = params => {
  return myAxios.get('/userinfo').then(data => data.data)

};

export const uploadAvatarURL = "http://localhost:8080/static/avatar"
