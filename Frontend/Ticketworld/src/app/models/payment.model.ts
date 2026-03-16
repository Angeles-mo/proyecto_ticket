import { Order } from "./order.model";
import { PaymentStatus } from "./enums.model";

export interface Payment {
  id?: number;
  order?: Order;
  paymentMethod: string;
  transactionId: number;
  paymentstatus: PaymentStatus;
  paymentDate: string; // ISO date string
}