import { Game } from "./game";
import { Guide } from "./guide";

export class Fighter {
    id!: number;
    nome!: string;
    type!: string;
    descricao!: string;
    strengths!: string;
    weaknesses!: string;
    
    game!: Game;
    guide!: Guide[];

}
