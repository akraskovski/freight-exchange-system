import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminDashboardComponent} from './admin-dashboard/admin-dashboard.component';
import {AdministrateRoutingModule} from './administrate-routing.module';
import { UserManagementComponent } from './user-management/user-management.component';
import { AdministrateComponent } from './administrate.component';

@NgModule({
  imports: [
    CommonModule,
    AdministrateRoutingModule
  ],
  declarations: [AdminDashboardComponent, UserManagementComponent, AdministrateComponent]
})
export class AdministrateModule {
}
