import { Injectable } from '@angular/core';
import {Issue} from "../_models/issue";
import {ToastrService} from "ngx-toastr";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class IssueService {

  constructor(private http: HttpClient) { }

  list(repositoryId: number): Observable<Issue[]> {
    return this.http.get<Issue[]>(`http://localhost:8080/issues?repository_id=${repositoryId}`);
  }
}
