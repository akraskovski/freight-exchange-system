import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {MainNavigatorComponent} from './components/main-navigator/main-navigator.component';
import {MainPageComponent} from './components/main-page/main-page.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {LoginModule} from './modules/login/login.module';
import {AuthGuard} from './guards/auth.guard';
import {AuthenticationService} from './services/authentication.service';
import {HttpClientModule} from '@angular/common/http';
import {UserService} from './services/user.service';
import {AlertComponent} from './components/alert/alert.component';
import {AlertService} from './services/alert.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MainNavigatorComponent,
    MainPageComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    LoginModule
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    UserService,
    AlertService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
