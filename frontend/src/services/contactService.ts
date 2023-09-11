
import type {Contact} from "../types/Contact";
import apiClient from "../utils/apiClient";
import type {Search} from "../types/Search";

class ContactService {
    getAllContacts = (): Promise<Contact[]> => {
        return apiClient.get("/contacts").then((response) => response.data);
    };

    search = (search: Search): Promise<Contact[]> => {
        return apiClient.get("/contacts/search", {
            params: {
                keyword: search.keyword,
                phone: search.phone
            }
        }).then((response) => response.data);
    };
    getContactById = (id: any): Promise<Contact> => {
        return apiClient.get(`/contacts/${id}`).then((response) => response.data);
    };

    edit = (id: any, contact: any): Promise<Contact> => {
        return apiClient
            .put(`/contacts/${id}`, contact)
            .then((response) => response.data);
    };
    delete = async (id: any): Promise<Contact> => {
        return await apiClient
            .delete(`/contacts/${id}`)
            .then((response) => response.data);
    };
    save = (emp: any): Promise<Contact> => {
        return apiClient.post("/contacts", emp).then((response) => response.data);
    };
}

export default new ContactService();