import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RepositoryService {

  constructor(private http: HttpClient) { }

  import(organization: String, repository: String): Observable<any> {
    return this.http.post<any>("http://localhost:8080/repositories", {organization: organization, repository: repository})
  }
}
