import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SignUpUser} from "../models/dto/sign-up-user";
import {Observable} from "rxjs";
import {OAUTH_API} from "../variables/oauth";
import {map} from "rxjs/operators";
import {IdDto} from "../models/dto/id-dto";
import {Authority} from "../models/authority.enum";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  public register(user: SignUpUser): Observable<any> {
    //TODO: test purposes
    if (!user.authority) {
      user.authority = Authority.ROLE_CLIENT;
    }

    this.registerInOAuth(user).subscribe(idDto => user.authProfileId = idDto.id);

    return Observable.create();
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

    return this.http.post<any>(url, user, headers)
      .pipe(map((response: IdDto) => {
        if (!response || !response.id) {
          Observable.throw('Error was occurred during registering oauth account');
        }
        return response;
      }));
  }
}
