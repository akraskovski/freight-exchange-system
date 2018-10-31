import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './components/login/login.component';
import {LoginRoutingModule} from './login-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import { UserRegisterComponent } from './components/user-register/user-register.component';

@NgModule({
  imports: [
    CommonModule,
    LoginRoutingModule,
    ReactiveFormsModule,
  ],
  declarations: [LoginComponent, UserRegisterComponent],
})
export class LoginModule {
}
