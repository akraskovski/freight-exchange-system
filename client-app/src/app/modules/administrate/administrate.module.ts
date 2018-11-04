import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminMainComponent} from './admin-main/admin-main.component';
import {AdministrateRoutingModule} from './administrate-routing.module';
import { UsersManagementComponent } from './users-management/users-management.component';
import { AdministrateComponent } from './administrate.component';

@NgModule({
  imports: [
    CommonModule,
    AdministrateRoutingModule
  ],
  declarations: [AdminMainComponent, UsersManagementComponent, AdministrateComponent]
})
export class AdministrateModule {
}
