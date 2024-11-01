import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReuDTO } from '../models/reu.dto';

@Injectable({
  providedIn: 'root'
})
export class ReuService {
  private apiUrl = 'http://localhost:8080/api/reus';

  constructor(private http: HttpClient) {}

  salvar(reu: ReuDTO): Observable<ReuDTO> {
    return this.http.post<ReuDTO>(this.apiUrl, reu);
  }

  listar(): Observable<ReuDTO[]> {
    return this.http.get<ReuDTO[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<ReuDTO> {
    return this.http.get<ReuDTO>(`${this.apiUrl}/${id}`);
  }

  atualizar(id: number, reu: ReuDTO): Observable<ReuDTO> {
    return this.http.put<ReuDTO>(`${this.apiUrl}/${id}`, reu);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
