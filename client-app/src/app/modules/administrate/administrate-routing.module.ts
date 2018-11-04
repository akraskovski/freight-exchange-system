import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminDashboardComponent} from './admin-dashboard/admin-dashboard.component';
import {AuthGuard} from '../../guards/auth.guard';
import {Authority} from '../../models/authority.enum';
import {AdministrateComponent} from './administrate.component';
import {UserManagementComponent} from './user-management/user-management.component';

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
        component: AdminDashboardComponent,
        canActivate: [AuthGuard],
        data: {
          authorities: [Authority.ROLE_ADMIN]
        }
      },
      {
        path: 'user-management',
        component: UserManagementComponent,
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

