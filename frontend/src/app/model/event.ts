import { Game } from "./game";
import { User } from "./user";

export class Event {
    id!: number;
    titulo!: string;
    descricao!: string;
    link!: string;
    date!: string;

    game!: Game[];
    user!: User[];
}
