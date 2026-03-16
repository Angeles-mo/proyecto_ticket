import { User } from "./user.model";
import { Payment } from "./payment.model";
import { PurchaseState } from "./enums.model";

export interface Order {
  id?: number;
  user: User;
  purchaseDate: string; // ISO date string
  total: number;
  purchasestate: PurchaseState;
  payment?: Payment;
}