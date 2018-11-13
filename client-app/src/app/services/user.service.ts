import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SignUpUser} from '../models/dto/sign-up-user';
import {Observable} from 'rxjs';
import {OAUTH_API} from '../variables/oauth';
import {map, switchMap} from 'rxjs/operators';
import {IdDto} from '../models/dto/id-dto';
import {SERVER_API} from '../variables/server-api';
import {TotalCountResponse} from '../models/dto/total-count-response';
import {AuthenticationService} from './authentication.service';
import {SearchResponse} from '../models/dto/search-response';
import {SearchRequest} from '../models/dto/search-request';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) {
  }

  public register(user: SignUpUser): Observable<IdDto> {
    return this.registerInOAuth(user)
      .pipe(map((response: IdDto) => {
        if (!response || !response.id) {
          Observable.throw('Error was occurred during registering oauth account');
        }

        user.authProfileId = response.id;
        return user;
      }))
      .pipe(switchMap((signUpUser: SignUpUser) => this.registerInServer(signUpUser)));
  }

  public getTotalCount(): Observable<TotalCountResponse> {
    const url = SERVER_API.SERVER + SERVER_API.API_URL + SERVER_API.USER_TOTAL_COUNT;
    const token = this.authenticationService.currentUserValue.token;
    const headers = {headers: new HttpHeaders({'Authorization': `Bearer ${token}`})};

    return this.http.get<any>(url, headers);
  }

  public search(page: number, size: number, text: string): Observable<SearchResponse<any>> {
    const url = SERVER_API.SERVER + SERVER_API.API_URL + SERVER_API.USER_SEARCH;
    const token = this.authenticationService.currentUserValue.token;
    const headers = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      })
    };

    const searchRequestBody = new SearchRequest(page, size, text);

    return this.http.post<any>(url, searchRequestBody, headers);
  }

  private registerInOAuth(user: SignUpUser): Observable<IdDto> {
    const url = OAUTH_API.SERVER + OAUTH_API.REGISTER_ENDPOINT;
    const encodedAuth = btoa(`${OAUTH_API.ADMIN_EMAIL}:${OAUTH_API.ADMIN_PASS}`);
    const headers = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${encodedAuth}`,
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<any>(url, user, headers);
  }

  private registerInServer(user: SignUpUser): Observable<IdDto> {
    const url = SERVER_API.SERVER + SERVER_API.API_URL + SERVER_API.REGISTER_ACCOUNT_URL;
    const headers = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

    return this.http.post<any>(url, user, headers);
  }
}
