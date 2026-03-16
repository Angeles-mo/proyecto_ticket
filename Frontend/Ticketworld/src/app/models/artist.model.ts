import { Event } from "./event.model";
import { Account } from "./account.model";

export interface Artist{
    id?: number;
    name: string;
    lastName: string;
    musicGenre?: string;
    biography?: string;
    events?: Event[];
    account?: Account;
}