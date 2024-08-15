import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';
import { RestService } from '../services/rest.service';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    CommonModule],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.css'
})
export class CadastroComponent implements OnInit {

  public formCliente!: FormGroup;
  public formAgencia!: FormGroup;
  public formConta!: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private restService: RestService) {

  }

  ngOnInit(): void {

    this.formCliente = this.fb.group({
      idCliente: [''],
      clienteNome: ['', Validators.required],
      clienteCPF: ['', Validators.required],
      clienteFone: ['', Validators.required]
    });

    this.formAgencia = this.fb.group({
      idAgencia: [''],
      nomeAgencia: ['', Validators.required],
      endereco: ['', Validators.required],
      telefone: ['', Validators.required],
      idCliente: [''],
    });

    this.formConta = this.fb.group({
      idContaCorrente: [''],
      contaCorrenteNumero: ['', Validators.required],
      contaCorrenteSaldo: ['', Validators.required],
      idAgencia: [''],
      idCliente: [''],
    })

    if (this.getEditarModo == "editar") {
      this.restService.retornarClienteById(this.getUrl).subscribe(cliente => {
        this.formCliente.patchValue(cliente);
      });
    }
  }

  redirecionar() {
    this.router.navigateByUrl("admin");
  }

  inserir() {
    if (this.getUrl === 'cliente') {
      this.restService.inserirCliente(this.formCliente.getRawValue()).subscribe(res => {
        this.redirecionar();
      });

    } else if (this.getUrl === 'agencia') {
      let objEnvio = { ...this.formAgencia.getRawValue() };

      this.restService.retornarClienteById(objEnvio.idCliente).subscribe(cliente => {
        objEnvio.idCliente = cliente;

        this.restService.inserirAgencia(objEnvio).subscribe(res => {
          this.redirecionar();
        });
      });

    } else if (this.getUrl === 'conta') {
      let objEnvio = { ...this.formConta.getRawValue() };
      this.restService.retornarClienteById(objEnvio.idCliente).subscribe(cliente => {
        objEnvio.idCliente = cliente;

        this.restService.retornarAgenciaById(objEnvio.idAgencia).subscribe(agencia => {
          objEnvio.idAgencia = agencia;
          objEnvio.idAgencia.idCliente =  objEnvio.idCliente;
          this.restService.inserirConta(objEnvio).subscribe(res => {
            this.redirecionar();
          });
        });
      });
    } else if(this.getEditarModo == "editar"){
      this.restService.atualizarCliente(this.formCliente.getRawValue(), this.getUrl).subscribe(res => {
        this.restService.retornarContas().subscribe((res: any) => {
          res.forEach((element: any) => {
            if (element.idCliente.idCliente == this.getUrl) {
              let contaAtualizada = {...element, idCliente: this.formCliente.getRawValue()}
              this.restService.atualizarConta(contaAtualizada, element.idContaCorrente);
            };
          });
        });

        this.restService.retornarAgencias().subscribe((res: any) => {
          res.forEach((element: any) => {
            if (element.idCliente.idCliente == this.getUrl) {
              let agenciaAtualizada = {...element, idCliente: this.formCliente.getRawValue()}
              this.restService.atualizarAgencia(agenciaAtualizada, element.idAgencia);
            };
          });
        });

        this.redirecionar();
      });
    }

  }

  get getUrl() {
    return this.router.url.split('/')[2];
  }

  get getEditarModo() {
    return this.router.url.split('/')[1];
  }

}
