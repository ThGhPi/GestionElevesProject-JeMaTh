import http from "../api-routing/HttpCommon";

class LoginService {
  
  logAppUser(data) {
    return http.post("auth/login", data);
  }

};

export default new LoginService();