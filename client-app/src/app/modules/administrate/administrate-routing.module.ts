import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminMainComponent} from './admin-main/admin-main.component';
import {AuthGuard} from '../../guards/auth.guard';
import {Authority} from "../../models/authority.enum";

const routes: Routes = [
  {
    path: 'administrate',
    component: AdminMainComponent,
    canActivate: [AuthGuard],
    data: {
      authorities: [Authority.ROLE_ADMIN]
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AdministrateRoutingModule {
}

