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
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {UserService} from './services/user.service';
import {AlertComponent} from './components/alert/alert.component';
import {AlertService} from './services/alert.service';
import {HttpUnauthorizedInterceptor} from './interceptors/http-unauthorized.interceptor';
import {AdministrateModule} from './modules/administrate/administrate.module';
import {PagerService} from './services/pager.service';

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
    FontAwesomeModule,
    HttpClientModule,
    LoginModule,
    AdministrateModule,
    AppRoutingModule
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    UserService,
    PagerService,
    AlertService,
    {provide: HTTP_INTERCEPTORS, useClass: HttpUnauthorizedInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
