import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  getAccessToken(code: string): Observable<any> {
    return this.http.post<any>("http://localhost:8080/auth/github/access_token", {code: code})
  }
}
