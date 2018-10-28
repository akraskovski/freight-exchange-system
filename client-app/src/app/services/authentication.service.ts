import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {OAUTH_API} from '../variables/oauth';
import {map} from 'rxjs/operators';
import {User} from '../models/user';
import {UserService} from './user.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient, private userService: UserService) {
  }

  login(email: string, password: string) {
    const url = OAUTH_API.SERVER + OAUTH_API.TOKEN_ENDPOINT;
    const encodedAuth = btoa(OAUTH_API.CLIENT_ID + ':' + OAUTH_API.CLIENT_SECRET);
    const headers = {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + encodedAuth,
        'Content-Type': 'application/x-www-form-urlencoded'
      })
    };
    const body = new URLSearchParams();
    body.set('username', email);
    body.set('password', password);
    body.set('grant_type', 'password');

    return this.http.post<any>(url, body.toString(), headers)
      .pipe(map(response => {
        if (!response || !response.access_token) {
          Observable.throw('Invalid username or password');
        }

        this.loadDetails(email, password).subscribe(
          (user: User) => {
            user.token = JSON.stringify(response.access_token);
            user.refreshToken = JSON.stringify(response.refresh_token);
            localStorage.setItem('currentUser', JSON.stringify(user));
          }
        );
      }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }

  private loadDetails(email: string, password: string): Observable<User> {
    const url = OAUTH_API.SERVER + OAUTH_API.ME_ENDPOINT;
    const headers = {headers: new HttpHeaders({'Authorization': 'Basic ' + btoa(email + ':' + password)})};

    return this.http.get<any>(url, headers)
      .pipe(map(response => {
        const user = new User();
        user.id = response.id;
        user.email = response.email;
        user.isActive = response.active;
        user.authority = response.authority;
        return user;
      }));
  }
}
