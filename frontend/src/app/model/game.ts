import { Event } from "./event";
import { Fighter } from "./fighter";
import { Guide } from "./guide";

export class Game {
    id!: number;
    nome!: string;
    descricao!: string;
    link!: string;
    preco!: number;

    fighter!: Fighter[];
    evento!: Event[];
    guide!: Guide[];
}
