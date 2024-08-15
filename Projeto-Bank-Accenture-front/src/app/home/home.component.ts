import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Router } from '@angular/router';
import { CabecalhoComponent } from '../cabecalho/cabecalho.component';
import { CPFPipe } from '../pipes/cpf.pipe';
import { RestService } from '../services/rest.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    MatInputModule,
    MatFormFieldModule,
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    MatDividerModule,
    MatButtonModule,
    CPFPipe,
    MatSidenavModule,
    CabecalhoComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})

export class HomeComponent implements OnInit {

  displayedColumns: string[] = ['id_cliente', 'nome', 'cpf', 'telefone', 'numeroConta', 'numeroAgencia', 'acao'];
  dataSource = new MatTableDataSource<any>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private router: Router, 
    private restService: RestService
  ) { }

  ngOnInit(): void {
    this.restService.retornarContas().subscribe((res: any) => {
      this.dataSource = new MatTableDataSource<any>(res.reverse());
      this.dataSource.paginator = this.paginator;
    })
  }

  redirecionar(parametro: string) {
    this.router.navigateByUrl(parametro);
  }

  removerConta(id: string) {
    if (window.confirm('Você tem certeza que deseja remover o cliente de id ' + id + '?')) {
      this.restService.deletarConta(id).subscribe((res: any) => {
        window.alert("Cliente removido com sucesso!");
        location.reload();
      });
    }
    else {
      console.log('Não quero remover!');
    }
  }

  edit(id: string){
    this.redirecionar("/editar/" + id)
  }
}
