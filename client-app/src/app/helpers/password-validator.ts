import {AbstractControl} from '@angular/forms';

export class PasswordValidation {

  static matchPassword(control: AbstractControl): void {
    const password = control.get('password').value;
    const confirmPassword = control.get('passwordRepeat').value;

    if (password != confirmPassword) {
      control.get('passwordRepeat').setErrors({matchPassword: true});
    }
  }
}
