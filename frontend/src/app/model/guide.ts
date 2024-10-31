import { Fighter } from "./fighter";
import { Game } from "./game";
import { User } from "./user";

export class Guide {
    id!: number;
    titulo!: string;
    tipo!: string;
    descricao!: string;
    link!: string;
    data_cr!: string;
    likes!: number;
    dislikes!: number;

    fighter!: Fighter;
    game!: Game;
    user!: User;
}
