import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminMainComponent} from './admin-main/admin-main.component';
import {AuthGuard} from '../../guards/auth.guard';
import {Authority} from '../../models/authority.enum';
import {AdministrateComponent} from './administrate.component';
import {UsersManagementComponent} from './users-management/users-management.component';

const routes: Routes = [
  {
    path: 'administrate',
    component: AdministrateComponent,
    canActivate: [AuthGuard],
    data: {
      authorities: [Authority.ROLE_ADMIN]
    },
    children: [
      {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
      {
        path: 'dashboard',
        component: AdminMainComponent,
        canActivate: [AuthGuard],
        data: {
          authorities: [Authority.ROLE_ADMIN]
        }
      },
      {
        path: 'users-management',
        component: UsersManagementComponent,
        canActivate: [AuthGuard],
        data: {
          authorities: [Authority.ROLE_ADMIN]
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AdministrateRoutingModule {
}

