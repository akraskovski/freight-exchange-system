import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthenticationService} from '../services/authentication.service';
import {Authority} from "../models/authority.enum";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private authenticationService: AuthenticationService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.authenticationService.isAuthenticated()) {
      this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
      return false;
    }

    const grantedAuthorities = AuthGuard.convertAuthorities(route.data.authorities);
    if (grantedAuthorities.length == 0) {
      return true;
    }

    return AuthGuard.containsAuthority(grantedAuthorities, Authority[this.authenticationService.currentUserValue.authority]);
  }

  private static convertAuthorities(authoritiesNumbers: number[]) {
    return authoritiesNumbers == undefined ? [] : authoritiesNumbers.map(value => Authority[value])
  }

  private static containsAuthority(grantedAuthorities, userAuthority: string): boolean {
    let index = grantedAuthorities.length;
    while (index--) {
      if (grantedAuthorities[index] === userAuthority)
        return true;
    }
    return false;
  }
}
