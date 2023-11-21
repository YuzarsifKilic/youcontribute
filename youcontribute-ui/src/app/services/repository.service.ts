import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Repository} from "../_models/repository";

@Injectable({
  providedIn: 'root'
})
export class RepositoryService {

  constructor(private http: HttpClient) { }

  import(organization: String, repository: String): Observable<any> {
    return this.http.post<any>("http://localhost:8080/repositories", {organization: organization, repository: repository})
  }

  list(): Observable<Repository[]> {
    return this.http.get<Repository[]>("http://localhost:8080/repositories");
  }
}
