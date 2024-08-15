import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ClienteComponent } from './cliente/cliente.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { OperacoesComponent } from './operacoes/operacoes.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo:'admin',
        pathMatch: 'full'
    },
    {
        path: 'admin',
        component: HomeComponent,
        title: 'Banco Accenture'
    },
    {
        path: 'cliente/:id',
        component: ClienteComponent,
        title: 'Banco Accenture'
    },
    {
        path: 'operacao/:categoria/:idCliente',
        component: ClienteComponent,
        title: 'Banco Accenture'
    },
    {
        path: 'cadastro/:categoria',
        component: CadastroComponent,
        title: 'Banco Accenture'
    },
    {
        path: 'editar/:id',
        component: CadastroComponent,
        title: 'Banco Accenture'
    },
    {
        path: 'cliente/:id/operacao/:categoria',
        component: OperacoesComponent,
        title: 'Banco Accenture'
    },
];
