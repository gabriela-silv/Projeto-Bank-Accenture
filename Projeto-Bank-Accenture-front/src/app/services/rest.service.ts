import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private http: HttpClient) { }

  /**
   * 
   * Rest Gets
   */

  retornarClientes() {
    return this.http.get(`http://localhost:8080/clientes`);
  }

  retornarContas() {
    return this.http.get(`http://localhost:8080/conta-corrente`);
  }

  retornarAgencias() {
    return this.http.get(`http://localhost:8080/agencias`);
  }

  retornarExtratoByConta(id: string) {
    return this.http.get(`http://localhost:8080/conta/${id}`);
  }

  retornarClienteById(id: any) {
    return this.http.get(`http://localhost:8080/cliente/${id}`);
  }

  retornarAgenciaById(id: any) {
    return this.http.get(`http://localhost:8080/agencia/${id}`);
  }

  retornarContaById(id: any) {
    return this.http.get(`http://localhost:8080/conta-corrente/${id}`);
  }

  retornarContaByIdCliente(id: any) {
    return this.http.get(`http://localhost:8080/conta/cliente/${id}`);
  }

  /**
   * 
   * Rest Posts
   */

  inserirCliente(cliente: any) {
    return this.http.post("http://localhost:8080/cliente-inserir", cliente);
  }

  inserirAgencia(agencia: any) {
    return this.http.post("http://localhost:8080/agencia-inserir", agencia);
  }

  inserirConta(conta: any) {
    return this.http.post("http://localhost:8080/inserir-conta-corrente", conta);
  }

  inserirExtrato(extrato: any) {
    return this.http.post("http://localhost:8080/extrato-inserir", extrato);
  }

  /**
   * 
   * Rest Puts
   */

  atualizarCliente(cliente: any, id: any) {
    return this.http.put(`http://localhost:8080/cliente-atualizar/${id}`, cliente);
  }

  atualizarAgencia(agencia: any, id: any) {
    return this.http.put(`http://localhost:8080/agencia-atualizar/${id}`, agencia);
  }

  atualizarConta(conta: any, id: any) {
    return this.http.put(`http://localhost:8080/conta-corrente-atualizar/${id}`, conta);
  }

  /**
   * 
   * Rest Deletes
   */

  deletarCliente(id: any) {
    return this.http.delete(`http://localhost:8080/cliente-deletar/${id}`);
  }

  deletarConta(id: any) {
    return this.http.delete(`http://localhost:8080/conta-corrente-deletar/${id}`);
  }

  /**
   * 
   * Operações da conta
   */
  
  sacar(id: any, valor: any) {
    return this.http.post(`http://localhost:8080/sacar/${id}`, valor);
  }
  
  depositar(id: any, valor: any) {
    return this.http.post(`http://localhost:8080/depositar/${id}`, valor);
  }

  transferir(idContaOrigem: any, idContaDestino: any, valor: any) {
    return this.http.post(`http://localhost:8080/transferir/${idContaOrigem}/${idContaDestino}`, valor);
  }


}
