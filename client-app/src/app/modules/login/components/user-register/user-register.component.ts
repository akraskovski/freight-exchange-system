import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../../../services/authentication.service';
import {UserService} from '../../../../services/user.service';
import {AlertService} from '../../../../services/alert.service';
import {Gender} from '../../../../models/gender.enum';
import {PasswordValidation} from '../../../../helpers/password-validator';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading: boolean = false;
  submitted: boolean = false;
  genders: string[] = Object.keys(Gender).filter(k => typeof Gender[k as any] === 'number');

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService,
              private userService: UserService,
              private alertService: AlertService) {
    if (this.authenticationService.getCurrentUser()) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      passwordRepeat: ['', [Validators.required, Validators.minLength(8)]],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      gender: [Gender.UNKNOWN, Validators.required],
      phone: ['', Validators.pattern('^([()\\- x+]*\\d[()\\- x+]*){4,16}$')]
    }, {
      validator: PasswordValidation.matchPassword
    });
  }

  get form() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    console.log(this.registerForm.value);
    this.loading = false;
// this.userService.register(this.registerForm.value)
//   .pipe(first())
//   .subscribe(
//     data => {
//       this.alertService.success('Registration successful', true);
//       this.router.navigate(['/login']);
//     },
//     error => {
//       this.alertService.error(error);
//       this.loading = false;
//     });

  }
}