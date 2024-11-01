export class ReuDTO {
    id?: number; 
    nome: string;
    cpf: string;
    telefone: string;
  
    constructor(id: number, nome: string, cpf: string, telefone: string) {
      this.id = id;
      this.nome = nome;
      this.cpf = cpf;
      this.telefone = telefone;
    }
  }
  