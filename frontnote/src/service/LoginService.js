import http from "../api-routing/HttpCommon";

class LoginService {
  
  logAppUser(data) {
    return http.post("login", data);
  }

};

export default new LoginService();