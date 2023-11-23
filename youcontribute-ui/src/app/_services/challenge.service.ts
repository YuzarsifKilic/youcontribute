import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Challenge} from "../_models/challenge";

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {

  constructor(private httpClient: HttpClient) { }

  accept(id: number): Observable<any> {
    return this.httpClient.patch(`http://localhost:8080/challenges/${id}`, {status: "ACCEPTED"});
  }

  reject(id: number): Observable<any> {
    return this.httpClient.patch(`http://localhost:8080/challenges/${id}`, {status: "REJECTED"});
  }

  list(): Observable<Challenge[]> {
    return this.httpClient.get<Challenge[]>("http://localhost:8080/challenges");
  }
}
