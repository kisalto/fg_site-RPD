export class Guide {
    id!: number;
    titulo!: string;
    tipo!: string;
    descricao!: string;
    link!: string;
    data_cr!: string;
    likes!: number;
    dislikes!: number;

    constructor(
        titulo: string,
        tipo: string,
        descricao: string,
        link: string,
        
      ) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.link = link;
        
      }
}
