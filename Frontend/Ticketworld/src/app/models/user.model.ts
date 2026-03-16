import { Order } from "./order.model";
import { Account } from "./account.model";

export interface User{
    id?: number;
    name: string;
    lastName: string;
    phoneNumber: string;
    orders?: Order[];
    account?: Account;
}