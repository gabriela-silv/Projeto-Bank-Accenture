import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { RestService } from '../services/rest.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-operacoes',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    CommonModule,
  ],
  templateUrl: './operacoes.component.html',
  styleUrl: './operacoes.component.css',
})
export class OperacoesComponent implements OnInit {
  operacoes = ["sacar", "depositar", "transferir"]

  public form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private restService: RestService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      valor: ['', Validators.required],
      idContaCorrenteDestino: [''],
      idContaCorrente: [''],
      operacao: [''],
    });
  }

  inserir() {
    let tipoOperacao = 0;
    this.operacoes.forEach((element, id) => {
      if (element == this.router.url.split('/')[4] ) {
        tipoOperacao = id;
      }
    });
    let objEnvio = {...this.form.getRawValue(), operacao: tipoOperacao};
    this.restService.retornarContaByIdCliente(this.getIdUser).subscribe((res: any) => {
        this.restService.retornarExtratoByConta(res[0].idContaCorrente).subscribe((res: any) => {
            objEnvio.idContaCorrente = res[0].idContaCorrente;
            if (tipoOperacao == 0) {
              this.restService.sacar(objEnvio.idContaCorrente.idContaCorrente, parseInt(objEnvio.valor)).subscribe((res:any) => {
                  this.redirecionar();
              });
            } else if(tipoOperacao == 1) {
              this.restService.depositar(objEnvio.idContaCorrente.idContaCorrente, parseInt(objEnvio.valor)).subscribe((res:any) => {
                  this.redirecionar();
              });
            } else if(tipoOperacao == 2) {
              this.restService.transferir(objEnvio.idContaCorrente.idContaCorrente, objEnvio.idContaCorrenteDestino, parseInt(objEnvio.valor)).subscribe((res:any) => {
                  this.redirecionar();
              });
            }
    })
    
      
    })
  }

  get getIdUser() {
    return this.router.url.split('/')[2];
  }

  get getTipoOperacao() {
    return this.router.url.split('/')[4];
  }

  redirecionar() {
    this.router.navigateByUrl(
      this.router.url.split('/')[1] + '/' + this.router.url.split('/')[2]
    );
  }
}
