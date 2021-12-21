import axios from 'axios';

const CUSTOMER_API_BASE_URL = "http://localhost:8080/custm/v1/customer";

class CustomerService {

    getCustomer(){
        return axios.get(CUSTOMER_API_BASE_URL);
    }

    createCustomer(customer){
        return axios.post(CUSTOMER_API_BASE_URL, customer);
    }

    getCustomerById(cid){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + cid);
    }

    updateCustomer(customer, cid){
        return axios.put(CUSTOMER_API_BASE_URL + '/' + cid, customer);
    }

    deleteCustomer(cid){
        return axios.delete(CUSTOMER_API_BASE_URL + '/' + cid);
    }
}

export default new CustomerService()