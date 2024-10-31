import { User } from './user';
import { Game } from './game';
export class Evento {
    id!: number;
    titulo!: string;
    descricao!: string;
    link!: string;
    date!: string;

    user!: User[];
    game!: Game[];
}
