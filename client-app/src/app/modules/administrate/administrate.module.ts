import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminMainComponent} from './admin-main/admin-main.component';
import {AdministrateRoutingModule} from './administrate-routing.module';

@NgModule({
  imports: [
    CommonModule,
    AdministrateRoutingModule
  ],
  declarations: [AdminMainComponent]
})
export class AdministrateModule {
}
