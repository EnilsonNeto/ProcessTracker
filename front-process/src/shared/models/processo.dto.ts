export class ProcessoDTO {
    id?: number;
    numero: string;
    reusIds?: number[];

    constructor(id: number, numero: string, reusIds?: number[]) {
        this.id = id;
        this.numero = numero;
        this.reusIds = reusIds;
    }
}
