import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { RestService } from '../services/rest.service';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cliente',
  standalone: true,
  imports: [
    MatSidenavModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    CommonModule,
  ],
  templateUrl: './cliente.component.html',
  styleUrl: './cliente.component.css',
})
export class ClienteComponent implements OnInit, AfterViewInit {
  cliente = { clienteNome: '', saldo: 0, conta: 0 };
  verSaldo = true;
  displayedColumns: string[] = [
    'idExtrato',
    'operacao',
    'valor',
    'dataHoraMovimento',
  ];
  dataSource = new MatTableDataSource<any>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private router: Router, private restService: RestService) {}

  ngOnInit(): void {
    this.restService
      .retornarClienteById(this.getUrl)
      .subscribe((cliente: any) => {
        this.cliente = { ...cliente };
      });
    this.restService
      .retornarContaByIdCliente(this.getUrl)
      .subscribe((res: any) => {
        console.log(res);
        
        this.restService
          .retornarExtratoByConta(res[0].idContaCorrente)
          .subscribe((res: any) => {
            this.cliente = {
              ...this.cliente,
              saldo: res[0].idContaCorrente.contaCorrenteSaldo,
              conta: res[0].idContaCorrente.contaCorrenteNumero,
            };
            this.dataSource = new MatTableDataSource<any>(res.reverse());
            this.dataSource.paginator = this.paginator;
          });
      });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  redirecionar(parametro: string) {
    this.router.navigateByUrl(this.router.url + '/operacao/' + parametro);
  }

  trocarVisualizacao() {
    this.verSaldo = !this.verSaldo;
  }

  get getUrl() {
    return this.router.url.split('/')[2];
  }

  redirecionarAdmin() {
    this.router.navigateByUrl('admin');
  }
}
