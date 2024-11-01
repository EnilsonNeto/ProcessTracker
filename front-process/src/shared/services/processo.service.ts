import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProcessoDTO } from '../models/processo.dto';

@Injectable({
  providedIn: 'root'
})
export class ProcessoService {
  private apiUrl = 'http://localhost:8080/api/processos';

  constructor(private http: HttpClient) {}

  salvar(processo: ProcessoDTO): Observable<ProcessoDTO> {
    return this.http.post<ProcessoDTO>(this.apiUrl, processo);
  }

  listar(): Observable<ProcessoDTO[]> {
    return this.http.get<ProcessoDTO[]>(this.apiUrl);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  adicionarReu(processoId: number, reuId: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${processoId}/reus/${reuId}`, {});
  }
}
