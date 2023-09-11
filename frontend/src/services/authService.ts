import apiClient from "../utils/apiClient";
import router from "../router"; // apiClient is an axios instance

class authService {
    login = async (user: any): Promise<any> => {
        return await apiClient.post("/auth/login", {
            username: user.username,
            password: user.password,
        });
    };
    logout() {
        window.localStorage.removeItem("jwtToken");
        router.push("/login");
    }
}
export default new authService();