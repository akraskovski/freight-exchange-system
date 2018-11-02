import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {OAUTH_API} from '../variables/oauth';
import {map} from 'rxjs/operators';
import {User} from '../models/user';
import {BehaviorSubject, Observable} from 'rxjs';

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
        this.loadDetails(email, password).subscribe((user: User) => {
            user.token = response.access_token;
            user.refreshToken = response.refresh_token;
            localStorage.setItem(this.CURRENT_USER_KEY, JSON.stringify(user));
            this.currentUserSubject.next(user);
          }
        );
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
