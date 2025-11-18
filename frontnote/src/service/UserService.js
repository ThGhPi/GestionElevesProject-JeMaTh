import http from "../api-routing/HttpCommon";

class UserService {
  
  getAll() {
    return http.get("users");
  }

};

export default new UserService();