import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {OAUTH_API} from '../variables/oauth';
import {map} from 'rxjs/operators';
import {User} from '../models/user';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly CURRENT_USER_KEY: string = 'currentUser';

  constructor(private http: HttpClient) {
  }

  login(email: string, password: string): Observable<void> {
    const url = OAUTH_API.SERVER + OAUTH_API.TOKEN_ENDPOINT;
    const encodedAuth = btoa(`${OAUTH_API.CLIENT_ID}:${OAUTH_API.CLIENT_SECRET}`);
    const headers = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${encodedAuth}`,
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
            user.token = response.access_token;
            user.refreshToken = response.refresh_token;
            localStorage.setItem(this.CURRENT_USER_KEY, JSON.stringify(user));
          }
        );
      }));
  }

  logout(): void {
    localStorage.removeItem(this.CURRENT_USER_KEY);
  }

  isAuthenticated(): boolean {
    const currentUser = this.getCurrentUser();
    return !!currentUser && !!currentUser.token;
  }

  getCurrentUser(): User {
    return JSON.parse(localStorage.getItem(this.CURRENT_USER_KEY));
  }

  private loadDetails(email: string, password: string): Observable<User> {
    const url = OAUTH_API.SERVER + OAUTH_API.ME_ENDPOINT;
    const encodedAuth = btoa(`${email}:${password}`);
    const headers = {headers: new HttpHeaders({'Authorization': `Basic ${encodedAuth}`})};

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
