import { Event } from "./event";
import { Guide } from "./guide";

export class User {
    id!: number;
    apelido!: string;
    email!: string;
    dc_id!: string;
    senha!: string;
    data_reg!: string;
    isMod!: boolean;
    isVet!: boolean;

    eventos!: Event[];
    guide!: Guide[];
}
