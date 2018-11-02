import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainPageComponent} from './components/main-page/main-page.component';
import {LoginRoutingModule} from './modules/login/login-routing.module';
import {AdministrateRoutingModule} from './modules/administrate/administrate-routing.module';

const routes: Routes = [
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: MainPageComponent},
  {path: '**', redirectTo: 'dashboard'}
];

@NgModule({
  imports: [
    LoginRoutingModule,
    AdministrateRoutingModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
