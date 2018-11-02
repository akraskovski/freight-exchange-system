import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../../../../services/authentication.service';
import {catchError, first} from 'rxjs/operators';
import {AlertService} from '../../../../services/alert.service';
import {Authority} from "../../../../models/authority.enum";
import {Observable, of, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  AUTHORITIES: typeof Authority = Authority;
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService,
              private alertService: AlertService) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.authenticationService.logout();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get f() {
    return this.loginForm.controls;
  }

  signIn() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.f.email.value, this.f.password.value)
      .pipe(first())
      .pipe(catchError(this.handleError()))
      .subscribe(() => this.router.navigate([this.returnUrl]));
  }


  private handleError() {
    return (errorResponse: HttpErrorResponse) => {
      this.loading = false;
      const error = errorResponse.error;
      let message: string;

      if (errorResponse.error instanceof ErrorEvent) {
        message = 'An error occurred:' + errorResponse.error.message;
      } else if (error.error_description) {
        message = error.error_description;
      } else {
        message = `Backend returned code ${errorResponse.status}, ` + `body was: ${errorResponse.error}`;
      }

      this.alertService.error(message);
      return throwError(message);
    };
  }
}
