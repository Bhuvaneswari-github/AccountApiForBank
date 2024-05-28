import axios from "axios";

const REST_API_URL = 'http://localhost:8080/v1/customer';
const REST_ACCOUNT_API_URL = 'http://localhost:8080/v1/account';

export const customerDetails = (customerInfo) => axios.get(REST_API_URL + '/' + customerInfo);

export const accountcreation = (accountDetails) => axios.post(REST_ACCOUNT_API_URL, accountDetails)