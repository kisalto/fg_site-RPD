export class User {
    id!: number;
    apelido!: string;
    email!: string;
    dc_id!: string;
    senha!: string;
    data_reg!: string;
    isMod!: boolean;
    isVet!: boolean;

    constructor(
        apelido: string,
        email: string,
        dc_id: string,
        senha: string,
        data_reg: string,
    ){
        this.apelido = apelido;
        this.email = email;
        this.dc_id = dc_id;
        this.senha = senha;
        this.data_reg = data_reg;
        
    }
}
