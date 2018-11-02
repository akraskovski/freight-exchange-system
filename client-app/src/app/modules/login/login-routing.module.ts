import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {UserRegisterComponent} from './components/user-register/user-register.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'user-register/:role', component: UserRegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule {
}

