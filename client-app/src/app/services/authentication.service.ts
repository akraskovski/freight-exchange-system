import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {OAUTH_API} from '../variables/oauth';
import {map} from 'rxjs/operators';
import {User} from '../models/user';
import {BehaviorSubject, Observable} from 'rxjs';
import {SERVER_API} from "../variables/server-api";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public readonly CURRENT_USER_KEY: string = 'currentUser';
  public currentUser: Observable<User>;
  private currentUserSubject: BehaviorSubject<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem(this.CURRENT_USER_KEY)));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(email: string, password: string): Observable<void> {
    const url = OAUTH_API.SERVER + OAUTH_API.TOKEN_ENDPOINT;
    const clientCredentialsAuth = btoa(`${OAUTH_API.CLIENT_ID}:${OAUTH_API.CLIENT_SECRET}`);
    const headers = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${clientCredentialsAuth}`,
        'Content-Type': 'application/x-www-form-urlencoded'
      })
    };
    const body = new URLSearchParams();
    body.set('username', email);
    body.set('password', password);
    body.set('grant_type', 'password');

    return this.http.post<any>(url, body.toString(), headers)
      .pipe(map(response => {
        const token = response.access_token;
        const refreshToken = response.refresh_token;

        this.loadDetails(token).subscribe((user: User) => {
          user.token = token;
          user.refreshToken = refreshToken;
          localStorage.setItem(this.CURRENT_USER_KEY, JSON.stringify(user));
          this.currentUserSubject.next(user);
        });
      }));
  }

  logout(): void {
    localStorage.removeItem(this.CURRENT_USER_KEY);
    this.currentUserSubject.next(null);
  }

  isAuthenticated(): boolean {
    const currentUser = this.currentUserValue;
    return !!currentUser && !!currentUser.token;
  }

  private loadDetails(token: string): Observable<User> {
    const url = SERVER_API.SERVER + SERVER_API.API_URL + SERVER_API.ME_URL;
    const headers = {headers: new HttpHeaders({'Authorization': `Bearer ${token}`})};

    return this.http.get<User>(url, headers);
  }
}
