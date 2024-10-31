export class Game {
    id!: number;
    nome!: string;
    sigla!: string;
    descricao!: string;
    link!: string;
    preco!: number | null;

    constructor(id: number, nome: string, sigla: string, descricao: string, link: string, preco: number | null){
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.descricao = descricao;
        this.link = link;
        this.preco = preco;
    }
}
