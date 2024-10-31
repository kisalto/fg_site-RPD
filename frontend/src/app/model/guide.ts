import { Fighter } from "./fighter";
import { Game } from "./game";
import { User } from "./user";

export class Guide {
  id!: number;
  titulo!: string;
  tipo!: string;
  descricao!: string;
  link!: string;
  data_cr!: Date;
  likes: number = 0;
  dislikes: number = 0;
  fighter!: Fighter;
  game!: Game;
  user!: User;

  constructor(
      titulo: string,
      tipo: string,
      descricao: string,
      link: string,
      fighter: Fighter,
      game: Game,
      user: User,
      data_cr: Date
  ) {
      this.titulo = titulo;
      this.tipo = tipo;
      this.descricao = descricao;
      this.link = link;
      this.fighter = fighter;
      this.game = game;
      this.user = user;
  }
}
