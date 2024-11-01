import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProcessoService } from '../../../shared/services/processo.service';
import { ProcessoDTO } from '../../../shared/models/processo.dto';

@Component({
  selector: 'app-processos',
  templateUrl: './processos.component.html',
  styleUrls: ['./processos.component.scss']
})
export class ProcessosComponent implements OnInit {
  processo: ProcessoDTO = {
    numero: '',
    reusIds: []
  };

  processos: ProcessoDTO[] = []; // Array para armazenar os processos
  displayedColumns: string[] = ['numero', 'reusIds']; // Colunas a serem exibidas na tabela

  constructor(private processoService: ProcessoService, private router: Router) {}

  ngOnInit(): void {
    this.carregarProcessos(); // Carrega os processos ao inicializar o componente
  }

  onSubmit(): void {
    this.processoService.salvar(this.processo).subscribe({
      next: () => {
        alert('Processo salvo com sucesso!');
        this.processo.numero = ''; // Limpa o campo número
        this.processo.reusIds = []; // Limpa o campo de réus
        this.carregarProcessos(); // Atualiza a lista de processos
      },
      error: (err) => {
        console.error(err);
        alert('Erro ao salvar o processo.');
      }
    });
  }

  carregarProcessos(): void {
    this.processoService.listar().subscribe({
      next: (processos: ProcessoDTO[]) => {
        this.processos = processos; // Atualiza a lista de processos
      },
      error: (err) => {
        console.error(err);
        alert('Erro ao carregar processos.');
      }
    });
  }
}
