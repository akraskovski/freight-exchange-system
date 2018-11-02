import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminMainComponent} from './admin-main/admin-main.component';
import {AuthGuard} from '../../guards/auth.guard';

const routes: Routes = [
  {path: 'administrate', component: AdminMainComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AdministrateRoutingModule {
}

